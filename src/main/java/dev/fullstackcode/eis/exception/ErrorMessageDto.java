package dev.fullstackcode.eis.exception;


public class ErrorMessageDto {
    private String object;
    private String field;
    private String message;
    private Object rejectedValue;

    public ErrorMessageDto() {
    }

    public ErrorMessageDto(String object, String field, String message, Object rejectedValue) {
        this.object = object;
        this.field = field;
        this.message = message;
        this.rejectedValue = rejectedValue;
    }

    public ErrorMessageDto(String object, String field, String message) {
        this.object = object;
        this.field = field;
        this.message = message;
    }

    public String getObject() {
        return object;
    }

    public void setObject(String code) {
        this.object = code;
    }

    public String getFields() {
        return field;
    }

    public void setFields(String field) {
        this.field = field;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getRejectedValue() {
        return rejectedValue;
    }

    public void setRejectedValue(Object rejectedValue) {
        this.rejectedValue = rejectedValue;
    }
}
