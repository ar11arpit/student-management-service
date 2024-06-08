package com.rakbank.fee.collection.exceptions;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class CustomException extends Exception {

	private static final long serialVersionUID = 1L;

	private String errorMessage;
	private ErrorType errorType;
	private HttpStatus httpStatus;

	public CustomException(String errorMessage, ErrorType errorType) {
		super(errorMessage);
		this.errorMessage = errorMessage;
		this.errorType = errorType;
	}

	public CustomException(String errorMessage, ErrorType errorType, HttpStatus httpStatus) {
		super(errorMessage);
		this.errorMessage = errorMessage;
		this.errorType = errorType;
		this.httpStatus = httpStatus;
	}

}