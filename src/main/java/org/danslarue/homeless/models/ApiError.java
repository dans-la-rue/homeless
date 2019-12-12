package org.danslarue.homeless.models;

import lombok.Data;

@Data
public class ApiError {

    private long timestamp;
    private String error;
    private int status;
    private String exception;
    private String message;

}
