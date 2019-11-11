package org.danslarue.homeless.controllers;

import org.danslarue.homeless.exceptions.MonException;
import org.danslarue.homeless.exceptions.ResourceNotFoundException;
import org.danslarue.homeless.models.ApiError;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * Build my own custom message using the spring feature
     *
     * @param ex the exception that has been intercepted
     * @param request the web request 
     * @return an error response entity that will be sent s
     */
    @ExceptionHandler(value = {ResourceNotFoundException.class})
    protected ResponseEntity<Object> handleConflict(ResourceNotFoundException ex, WebRequest request) {
        ApiError error = new ApiError();
        error.setMessage(ex.getMessage());
        return handleExceptionInternal(ex, error, new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    /**
     * Reuse the existing Error message from HttpServletResponse
     * <p>
     * https://stackoverflow.com/questions/26236811/spring-boot-customize-http-error-response
     * <p>
     * same behavior if you simply do not handle the exception and put this annotation in the Exception definition @ResponseStatus(HttpStatus.NOT_FOUND)
     *
     * @param ex the exception that has been intercepted
     * @param response the web response 
     * @return a basic servlet exception
     * @throws IOException
     */
    @ExceptionHandler({MonException.class})
    void handleBadRequests(Exception ex, HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.BAD_REQUEST.value(), "mon putain de message");
    }
}
