package com.image.resizer.exception.handler;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@RestControllerAdvice
public class ValidImageConstraintHandler {

    @ExceptionHandler({ConstraintViolationException.class})
    public ResponseEntity<Map<String, String>> handleException(ConstraintViolationException e) {
        Map<String, String> errors = new HashMap<>();
        Set<ConstraintViolation<?>> violations = e.getConstraintViolations();

        for (ConstraintViolation<?> violation : violations) {
            errors.put(violation.getPropertyPath()
                .toString(), violation.getMessage());
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(errors);
    }
}
