package com.library.libraryservice.exception;

import com.library.libraryservice.client.ExceptionMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GeneralExceptionHandler {

    @ExceptionHandler(LibraryNotFoundException.class)
    public ResponseEntity<?> handle(LibraryNotFoundException libraryNotFoundException) {
        return new ResponseEntity<>(libraryNotFoundException.getMessage(),HttpStatus.NOT_FOUND);
    }

    //@ExceptionHandler(BookNotFoundException.class)
    //public ResponseEntity<String> handle(BookNotFoundException bookNotFoundException) {
    //    return new ResponseEntity<>(bookNotFoundException.getMessage(), HttpStatus.NOT_FOUND);
    //}

    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<ExceptionMessage> handle(BookNotFoundException bookNotFoundException) {
        return new ResponseEntity<>(bookNotFoundException.getExceptionMessage(),HttpStatus.NOT_FOUND);
    }

}
