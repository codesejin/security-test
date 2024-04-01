package com.spring.security.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public abstract class CustomException extends RuntimeException {
    protected CustomException(String message) {
        super(message);
    }
}
