package com.example.demo.exception;

import lombok.Getter;

@Getter
public class NotFoundException extends RuntimeException {

    private final String code;

    /**
     * Not Found Exception
     *
     * @param message Error Message
     * @param code    Error Code
     */
    public NotFoundException(String message, String code) {
        super(message);
        this.code = code;
    }

}
