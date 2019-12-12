package org.danslarue.homeless.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No such resource")  // 404
public class MonException extends RuntimeException {

    public MonException(String message) {
        super(message);
    }
}
