package org.homeless.homeless.controllers;

import org.homeless.homeless.exceptions.MonException;
import org.homeless.homeless.exceptions.ResourceNotFoundException;
import org.homeless.homeless.models.ApiError;
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
     * Build my own custom message
     *
     * @param ex
     * @param request
     * @return
     * @throws IOException
     */
    @ExceptionHandler(value = {ResourceNotFoundException.class})
    protected ResponseEntity<Object> handleConflict(ResourceNotFoundException ex, WebRequest request) throws IOException {
        ApiError error = new ApiError();
        error.setMessage("This should be application specific");
        return handleExceptionInternal(ex, error, new HttpHeaders(), HttpStatus.CONFLICT, request);
//        return new ResponseEntity<>(, HttpStatus.NOT_FOUND);
//        response.sendError(404, " tt");
//        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    /**
     * Reuse the existing Error message from HttpServletResponse
     * <p>
     * https://stackoverflow.com/questions/26236811/spring-boot-customize-http-error-response
     * <p>
     * same behavior if you simply do not handle the exception and put this annotation in the Exception definition @ResponseStatus(HttpStatus.NOT_FOUND)
     *
     * @param ex
     * @param request
     * @return
     * @throws IOException
     */
    @ExceptionHandler({MonException.class})
    void handleBadRequests(Exception ex, HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.BAD_REQUEST.value(), "mon putain de message");
    }
}
