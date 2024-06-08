package com.rakbank.fee.collection.exceptions;

import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.ArrayList;

@Slf4j
@ControllerAdvice
public class CustomExceptionHandler {

	@ExceptionHandler({ Exception.class })
	public final ResponseEntity<ErrorResponse> handleException(Exception ex, WebRequest request) {
		if (ex instanceof CustomException) {
			return handleLevelupException((CustomException) ex, request);
		} else if (ex instanceof MethodArgumentNotValidException) {
			return handleMethodArgumentNotValidException((MethodArgumentNotValidException) ex, request);
		}

		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		String errorMessage = ex.getMessage();
		String errorType = ErrorType.TECHNICAL.name();
		return prepareErrorResponse(errorMessage, errorType, status, ex, request);
	}

	private ResponseEntity<ErrorResponse> handleLevelupException(CustomException ex, WebRequest request) {
		HttpStatus status = determineHttpStatus(ex);
		String errorMessage = ex.getErrorMessage();
		String errorType = ex.getErrorType().name();

		return prepareErrorResponse(errorMessage, errorType, status, ex, request);
	}

	private HttpStatus determineHttpStatus(CustomException ex) {
		return (ex.getHttpStatus() != null) ? ex.getHttpStatus() : getDefaultHttpStatus(ex.getErrorType());
	}

	private HttpStatus getDefaultHttpStatus(ErrorType errorType) {
		return (errorType == ErrorType.VALIDATION || errorType == ErrorType.FUNCTIONAL) ? HttpStatus.BAD_REQUEST
				: HttpStatus.INTERNAL_SERVER_ERROR;
	}

	private ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex,
			WebRequest request) {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		String errorMessage = ex.getAllErrors().get(0).getDefaultMessage();
		String errorType = ErrorType.VALIDATION.name();
		return prepareErrorResponse(errorMessage, errorType, status, ex, request);
	}

	@SuppressWarnings("unused")
	private ResponseEntity<ErrorResponse> handleConstraintViolationException(ConstraintViolationException ex,
			WebRequest request) {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		String errorMessage = new ArrayList<>(ex.getConstraintViolations()).get(0).getMessage();
		String errorType = ErrorType.VALIDATION.name();
		return prepareErrorResponse(errorMessage, errorType, status, ex, request);
	}

	private ResponseEntity<ErrorResponse> prepareErrorResponse(String errorMessage, String errorType, HttpStatus status,
			Exception ex, WebRequest request) {
		String errorRequestId = String.valueOf(System.currentTimeMillis());
		ErrorResponse errorResponse = ErrorResponse.builder().errorMessage(errorMessage).errorType(errorType)
				.errorRequestId(errorRequestId).build();

		log.error("prepareErrorResponse > \n{}, \n{}", errorResponse, request.getDescription(false), ex);
		return new ResponseEntity<>(errorResponse, status);
	}

}
