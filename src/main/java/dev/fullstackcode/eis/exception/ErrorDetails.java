package dev.fullstackcode.eis.exception;

import java.time.LocalDate;

public class ErrorDetails {
    private LocalDate date;
    private String message ;
    private String errorDetails;


    public ErrorDetails(LocalDate date, String message, String errorDetails) {
        this.date = date;
        this.message = message;
        this.errorDetails = errorDetails;
    }

    public ErrorDetails() {
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getErrorDetails() {
        return errorDetails;
    }

    public void setErrorDetails(String errorDetails) {
        this.errorDetails = errorDetails;
    }
}
