package com.volapp.charity;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(HttpStatus.NOT_FOUND)
public class FileNotFoundException extends RuntimeException {
	
	@SuppressWarnings("unused")
	private static final long serialVersionnUID = 1L;
	
	public FileNotFoundException(String message) {
		super(message);
	}
	
	public FileNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

}
