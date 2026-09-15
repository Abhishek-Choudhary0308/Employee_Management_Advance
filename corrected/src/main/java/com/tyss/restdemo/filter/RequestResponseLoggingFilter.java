package com.tyss.restdemo.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Component
@Slf4j
public class RequestResponseLoggingFilter extends OncePerRequestFilter {

    private static final int MAX_LOG_LENGTH = 4000;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain)
            throws ServletException, IOException {

        boolean multipart =
                request.getContentType() != null
                        && request.getContentType()
                        .toLowerCase()
                        .startsWith("multipart/");

        ContentCachingRequestWrapper requestWrapper = null;

        if (!multipart) {
            requestWrapper =
                    new ContentCachingRequestWrapper(
                            request,
                            MAX_LOG_LENGTH
                    );
        }

        ContentCachingResponseWrapper responseWrapper =
                new ContentCachingResponseWrapper(response);

        long startTime = System.currentTimeMillis();

        try {

            if (requestWrapper != null) {
                filterChain.doFilter(
                        requestWrapper,
                        responseWrapper
                );
            } else {
                filterChain.doFilter(
                        request,
                        responseWrapper
                );
            }

        } finally {

            long timeTaken =
                    System.currentTimeMillis() - startTime;

            String requestBody;

            if (requestWrapper != null) {
                requestBody =
                        getBody(
                                requestWrapper.getContentAsByteArray()
                        );
            } else {
                requestBody =
                        "[multipart body omitted]";
            }

            String responseBody =
                    getBody(
                            responseWrapper.getContentAsByteArray()
                    );

            log.info(
                    "REQUEST method={} uri={} query={} body={}",
                    request.getMethod(),
                    request.getRequestURI(),
                    request.getQueryString(),
                    requestBody
            );

            log.info(
                    "RESPONSE status={} uri={} timeMs={} body={}",
                    response.getStatus(),
                    request.getRequestURI(),
                    timeTaken,
                    responseBody
            );

            /*
             * Very important:
             * ContentCachingResponseWrapper stores the response
             * until we copy it back to the real response.
             */
            responseWrapper.copyBodyToResponse();
        }
    }

    private String getBody(byte[] content) {

        if (content == null || content.length == 0) {
            return "[empty]";
        }

        String body =
                new String(
                        content,
                        StandardCharsets.UTF_8
                );

        // Mask password
        body = body.replaceAll(
                "(?i)(\"password\"\\s*:\\s*\")[^\"]*(\")",
                "$1***$2"
        );

        // Mask token
        body = body.replaceAll(
                "(?i)(\"token\"\\s*:\\s*\")[^\"]*(\")",
                "$1***$2"
        );

        if (body.length() > MAX_LOG_LENGTH) {
            body =
                    body.substring(0, MAX_LOG_LENGTH)
                            + "...[truncated]";
        }

        return body;
    }
}