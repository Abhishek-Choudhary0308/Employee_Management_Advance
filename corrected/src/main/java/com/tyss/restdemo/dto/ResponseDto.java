package com.tyss.restdemo.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ResponseDto {

    private boolean error;

    private String message;

    private Object data;
}
