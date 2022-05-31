package org.interview.round1.exceptions;

import lombok.Getter;

@Getter
public enum ErrorCodes {

    ROUTE_NOT_FOUND("DR.4001", "API route not found");

    private String code;
    private String msg;

    ErrorCodes(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
