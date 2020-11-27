package com.hexad.library.exception;

public class LibraryException extends RuntimeException {

    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    private String errorCode;

    public LibraryException(String id, String message, String errorCode) {
        super(message);
        this.id = id;
        this.errorCode = errorCode;
    }
}
