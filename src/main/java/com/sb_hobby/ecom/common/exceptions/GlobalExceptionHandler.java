package com.sb_hobby.ecom.common.exceptions;

import com.sb_hobby.ecom.common.DTOs.APIResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler{

    @ExceptionHandler
    public ResponseEntity<Map<String, String>> methodArgumentNotValidException(MethodArgumentNotValidException e){
        Map<String, String> response = new HashMap<>();
        e.getBindingResult().getFieldErrors().forEach(err -> {
            String fieldName = err.getField();
            String defaultMessage = err.getDefaultMessage();
            response.put(fieldName, defaultMessage);
        });

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<APIResponse> responseNotFoundException(ResourceNotFoundException e){
        String message = e.getMessage();
        APIResponse apiResponse = new APIResponse(message, false);

        return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<APIResponse> apiException(APIException e){
        String message = e.getMessage();
        APIResponse apiResponse = new APIResponse(message, false);

        return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
    }
}
