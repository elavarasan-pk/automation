package com.hi.techpoints.cucumber.exception;

public class OneviewException extends Exception {

    private static final long serialVersionUID = 1794410629781437391L;
    private static final String DEFAULT_ERROR_CODE = "OC-0000";

    private String code;

    public OneviewException() {
        super();
    }

    public OneviewException(String message) {
        super(message);
        this.code = DEFAULT_ERROR_CODE;
    }

    public OneviewException(String message, Throwable cause) {
        super(message, cause);
        this.code = DEFAULT_ERROR_CODE;
    }

    public OneviewException(String code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public String getCode() {
        return code;
    }

}
