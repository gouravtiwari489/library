package com.hexad.library.model;

public class ErrorResponse {

    private String errorMsg;
    private String id;
    private String errorCode;

    public ErrorResponse(String errorMsg, String id, String errorCode) {
        this.errorMsg = errorMsg;
        this.id = id;
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

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
}
