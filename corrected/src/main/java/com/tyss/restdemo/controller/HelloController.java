package com.tyss.restdemo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

/**
 * Controller exposing simple greeting endpoint.
 */
@RestController
@RequestMapping("/api")
public class HelloController {

    @GetMapping("/hello")
    public ResponseEntity<Map<String, String>> sayHello() {
        return ResponseEntity.ok(Collections.singletonMap("message", "Hello World REST API"));
    }
}
