package com.pedro.study.exceptions;

import com.pedro.study.exceptions.exceptionsPersonalizadas.NotFoundExceptionStudy;
import com.pedro.study.exceptions.exceptionsPersonalizadas.UniqueExceptionStudy;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ExceptionHandlerStudy extends ResponseEntityExceptionHandler {

    private Problema problema;

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        List<Problema.Campo> list = new ArrayList<>();
        for (ObjectError error : ex.getBindingResult().getAllErrors()) {
            list.add(new Problema.Campo( ((FieldError) error).getField(), error.getDefaultMessage()));

        }
        ;
        Problema problema = new Problema();
        problema.setStatus(status.value());
        problema.setDataHora(LocalDateTime.now());

        problema.setTitulo("Um ou mais campos estão com erros, faça a correçaõ e preencha-os corretamente!");
        problema.setCampos(list);

        return handleExceptionInternal(ex, problema, headers, status, request);
    }


    @ExceptionHandler(UniqueExceptionStudy.class)
    protected ResponseEntity<Object> uniqueExceptionplication(Exception ex, WebRequest request, HttpServletRequest r) {

        Problema problema = new Problema();
        problema.setTitulo(ex.getMessage());
        problema.setDataHora(LocalDateTime.now());
        problema.setStatus(HttpStatus.BAD_REQUEST.value());
        problema.setPath(r.getRequestURI());
        return super.handleExceptionInternal(ex, problema, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(NotFoundExceptionStudy.class)
    protected ResponseEntity<Object> NotFoundExceptionplication(Exception ex, WebRequest request, HttpServletRequest r) {

        Problema problema = new Problema();
        problema.setTitulo(ex.getMessage());
        problema.setDataHora(LocalDateTime.now());
        problema.setStatus(HttpStatus.BAD_REQUEST.value());
        problema.setPath(r.getRequestURI());
        return super.handleExceptionInternal(ex, problema, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    protected ResponseEntity<Object> constraintViolationExceptionplication(Exception ex, WebRequest request, HttpServletRequest r) {

        Problema problema = new Problema();
        problema.setTitulo(ex.getMessage());
        problema.setDataHora(LocalDateTime.now());
        problema.setStatus(HttpStatus.BAD_REQUEST.value());
        problema.setPath(r.getRequestURI());
        return super.handleExceptionInternal(ex, problema, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

}
