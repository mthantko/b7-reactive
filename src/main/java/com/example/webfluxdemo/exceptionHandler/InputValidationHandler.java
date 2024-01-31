package com.example.webfluxdemo.exceptionHandler;

import com.example.webfluxdemo.dto.InputFailedValidationError;
import com.example.webfluxdemo.dto.InputFailedValidationResponse;
import org.springframework.http.ResponseEntity;

public class InputValidationHandler {

    public ResponseEntity<InputFailedValidationResponse>
    handleResponse(InputFailedValidationError ex) {
        InputFailedValidationResponse response =
                new InputFailedValidationResponse();
        response.setInput(ex.getInput());
        response.setErrorCode(ex.getErrorCode());
        response.setMessage(ex.getMessage());
        return ResponseEntity.badRequest().body(response);
    }
}
