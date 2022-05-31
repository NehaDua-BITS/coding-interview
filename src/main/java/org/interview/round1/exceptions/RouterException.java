package org.interview.round1.exceptions;

import lombok.Getter;

@Getter
public abstract class RouterException extends RuntimeException {

    protected String errorMsg;

    protected String errorCode;

    public RouterException(String message) {
        this(message, null);
    }

    public RouterException(String message, String errorCode) {
        super(message);
        this.errorMsg = message;
        this.errorCode = errorCode;
    }
}
