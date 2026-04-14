package org.example.ss07.Bai05;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalStateException.class)
    public String handleFileSizeException(IllegalStateException ex) {
        if (ex.getMessage() != null && ex.getMessage().contains("exceeds maximum size")) {
            System.out.println("File qua lon");
            return "file-error";
        }
        return "error";
    }
}
