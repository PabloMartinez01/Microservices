package com.pablodev.shared.infrastructure.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
public class ErrorResponse {

    private HttpStatus status;
    private String message;
}
