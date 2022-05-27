package com.example.demo.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class CommonException extends RuntimeException {
    private final String code;
    private final String type;
    private final HttpStatus status;

    /**
     * Common Exception
     *
     * @param msg    Error message.
     * @param code   Error code.
     * @param type   Error type.
     * @param status Error status.
     */
    public CommonException(String msg, String code, String type, HttpStatus status) {
        super(msg);
        this.code = code;
        this.type = type;
        this.status = status;

    }

    /**
     * Common Exception
     *
     * @param msg    Error message.
     * @param code   Error code.
     * @param type   Error type.
     * @param status Error status.
     * @param t      Throwable exception
     */
    public CommonException(String msg, String code, String type, HttpStatus status, Throwable t) {
        super(msg, t);
        this.code = code;
        this.type = type;
        this.status = status;

    }

}
