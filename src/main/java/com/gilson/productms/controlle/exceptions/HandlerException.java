package com.gilson.productms.controlle.exceptions;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.gilson.productms.service.exceptions.ObjectNotFoundException;

@ControllerAdvice
public class HandlerException {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	private ResponseEntity<StandarError> argumentNotValid(MethodArgumentNotValidException e, HttpServletRequest request){
		String messageErro = null;
		
		for(FieldError er : e.getBindingResult().getFieldErrors()) {
			messageErro = messageErro == null ? er.getDefaultMessage() + "." : messageErro + " " + er.getDefaultMessage() + ".";
		}
		
		StandarError err = new StandarError(HttpStatus.BAD_REQUEST.value(), messageErro);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
	}
	
	@ExceptionHandler(ObjectNotFoundException.class)
	private ResponseEntity<StandarError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request){
		StandarError err = new StandarError(HttpStatus.NOT_FOUND.value(), e.getMessage());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
	}
	
}