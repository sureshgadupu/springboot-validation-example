package dev.fullstackcode.eis.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND,reason = "Department not found" )
public class DeptNotFoundException extends RuntimeException {
    public DeptNotFoundException() {
    }

    public DeptNotFoundException(String message) {
        super(message);
    }

    public DeptNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
