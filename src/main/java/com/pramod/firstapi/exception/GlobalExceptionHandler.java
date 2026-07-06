package com.pramod.firstapi.exception;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.pramod.firstapi.dto.ErrorResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(
            EmployeeNotFoundException.class)
    public ResponseEntity<ErrorResponse>
    handleEmployeeNotFound(
            EmployeeNotFoundException ex) {

        ErrorResponse error =
                new ErrorResponse(
                        ex.getMessage());

        return new ResponseEntity<>(
                error,
                HttpStatus.NOT_FOUND);
    }
    
    @ExceptionHandler(
            StudentNotFoundException.class)
    public ResponseEntity<ErrorResponse>
    handleStudentNotFound(
            StudentNotFoundException ex) {

        ErrorResponse error =
                new ErrorResponse(
                        ex.getMessage());

        return new ResponseEntity<>(
                error,
                HttpStatus.NOT_FOUND);
    }
//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<ErrorResponse>
//    handleException(Exception ex) {
//
//        ErrorResponse error =
//                new ErrorResponse(
//                        "Something went wrong");
//
//        return new ResponseEntity<>(
//                error,
//                HttpStatus.INTERNAL_SERVER_ERROR);
//    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleException(
            Exception ex) {

        ex.printStackTrace();

        return ResponseEntity
                .status(500)
                .body(Map.of(
                        "message",
                        ex.getMessage()));
    }
}