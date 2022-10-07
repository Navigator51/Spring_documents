package su.goodcat.spring_documents.controllers;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.ValidationException;

import static su.goodcat.spring_documents.constants.ErrorMessagesConstants.BAD_VALUES_FORMAT_ERROR;

@ControllerAdvice
public class ControllerExceptionHandler {


    @ExceptionHandler(ValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public HttpEntity<String> badFormat(ValidationException a) {
        return new HttpEntity<>(BAD_VALUES_FORMAT_ERROR + a.getMessage());
    }


}
