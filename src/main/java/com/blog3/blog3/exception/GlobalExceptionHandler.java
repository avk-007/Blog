package com.blog3.blog3.exception;

import com.blog3.blog3.payload.ErrorDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.*;
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    // firstly handle specific exceptions
    @ExceptionHandler(ResourceNotFoundException.class)
    //error details has dto class
    public ResponseEntity<ErrorDetails> handleResourceNotFoundException(ResourceNotFoundException exception,
                                                                        WebRequest webRequest) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(), exception.getMessage(),
                webRequest.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(BLOGAPIEXCEPTION.class)
    //handle BLogAPIexception
    public ResponseEntity<ErrorDetails>handleBLOGAPIEXCEPTION(BLOGAPIEXCEPTION exception,WebRequest webRequest){
        ErrorDetails errorDetails = new ErrorDetails(new Date(), exception.getMessage(),
                webRequest.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }
    //handle global exception
    @ExceptionHandler({Exception.class})
    public ResponseEntity<ErrorDetails>handleGlobalException(Exception exception
                                                                       ,WebRequest webRequest){
        ErrorDetails errorDetails = new ErrorDetails(new Date(), exception.getMessage(),
                webRequest.getDescription(false));
        return new ResponseEntity<>(errorDetails,HttpStatus.NOT_FOUND);
    }

}
