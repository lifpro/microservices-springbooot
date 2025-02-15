package com.technolab.auth.dto;

import lombok.Data;
import org.springframework.http.HttpStatus;
@Data
public class ErrorResDto {
    HttpStatus httpStatus;
    String message;

    public ErrorResDto(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }

}