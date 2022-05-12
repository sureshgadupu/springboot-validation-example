package dev.fullstackcode.eis.exception;

import java.util.List;

public class ErrorResponse {
    private String error;
    private int code;
    private List<ErrorMessageDto> errors;



    public ErrorResponse() {
    }

    public ErrorResponse(String error, int code, List<ErrorMessageDto> errors) {
        this.error = error;
        this.code = code;
        this.errors = errors;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<ErrorMessageDto> getErrors() {
        return errors;
    }

    public void setErrors(List<ErrorMessageDto> errors) {
        this.errors = errors;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
