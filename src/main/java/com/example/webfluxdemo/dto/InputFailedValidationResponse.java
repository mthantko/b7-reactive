package com.example.webfluxdemo.dto;


import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class InputFailedValidationResponse {

    private int errorCode;
    public int input;
    public String message;
}
