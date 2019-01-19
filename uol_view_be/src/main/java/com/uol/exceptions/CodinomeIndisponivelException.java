package com.uol.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class CodinomeIndisponivelException extends RuntimeException{

	public CodinomeIndisponivelException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
}
