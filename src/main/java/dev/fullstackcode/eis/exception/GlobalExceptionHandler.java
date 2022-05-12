package dev.fullstackcode.eis.exception;

import org.hibernate.validator.internal.engine.ConstraintViolationImpl;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {


//    @ExceptionHandler(ConstraintViolationException.class)
//    @ResponseBody
//    public ErrorDetails handleExceptions(Exception ex, WebRequest request) {
//        ErrorDetails errorDetails = new ErrorDetails(LocalDate.now(),ex.getMessage(), "Exception");
//        return errorDetails;
//    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        List<ErrorMessageDto> validationErrorDetails = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> new ErrorMessageDto(error.getObjectName(),error.getField(),error.getDefaultMessage(),error.getRejectedValue()))
                .collect(Collectors.toList());

        ErrorResponse response = new ErrorResponse(status.name(), status.value(),validationErrorDetails);
        return new ResponseEntity<>(response,status );

    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseBody
    public ResponseEntity<Object> handleConstraintViolationException(Exception ex,  WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(LocalDate.now(),"ConstraintViolationException", ex.getMessage());
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }
}
