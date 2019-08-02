package com.ni.recommend.exception;

import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandling extends ResponseEntityExceptionHandler {

	@ExceptionHandler({ NoSuchElementException.class, RecordNotFoundException.class })
	public ResponseEntity<ErrorDetails> coutryNotFound(Exception ex, WebRequest request) {

		ErrorDetails errorDetails = new ErrorDetails(ex.getMessage(), HttpStatus.NOT_FOUND, ex.getClass().getName(),
				((ServletWebRequest) request).getRequest().getRequestURL().toString());
		log.error("{}", errorDetails);
		return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(Throwable.class)
	public ResponseEntity<ErrorDetails> others(Exception ex, WebRequest request) {

		ErrorDetails errorDetails = new ErrorDetails(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR,
				ex.getClass().getName(), ((ServletWebRequest) request).getRequest().getRequestURL().toString());
		log.error("{}", errorDetails);
		return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}