package com.hexad.library.exception;

import com.hexad.library.model.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(LibraryException.class)
    public ResponseEntity<ErrorResponse> handleLibraryException(LibraryException libraryException) {

        ResponseEntity<ErrorResponse> responseEntity = null;
        ErrorResponse errorResponse = new ErrorResponse(libraryException.getMessage(),
                libraryException.getId(), libraryException.getErrorCode());

        if ("VALIDATION_ERROR".equalsIgnoreCase(errorResponse.getErrorMsg())) {
             responseEntity = new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return responseEntity;

    }
}