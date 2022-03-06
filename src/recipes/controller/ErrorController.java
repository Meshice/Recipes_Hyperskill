package recipes.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import recipes.model.NotValidException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;

@ControllerAdvice
public class ErrorController {

    @ExceptionHandler({ConstraintViolationException.class})
    public ResponseEntity<NotValidException> notValidExceptionHandler(ConstraintViolationException ex, HttpServletRequest request) {
        return new ResponseEntity<>(new NotValidException(HttpStatus.BAD_REQUEST, ex.getMessage(), request), HttpStatus.BAD_REQUEST);
    }
}
