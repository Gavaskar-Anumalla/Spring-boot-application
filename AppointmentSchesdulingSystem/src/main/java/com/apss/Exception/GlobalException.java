package com.apss.Exception;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.apss.Common.ApiResponse;

@RestControllerAdvice
public class GlobalException extends ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String message = error.getDefaultMessage();
			errors.put(fieldName, message);
		});
		return new ResponseEntity<Object>(errors, HttpStatus.BAD_REQUEST);

	}

	@ExceptionHandler(RecordNotFoundException.class)
	public ResponseEntity<ErrorDetails> handleResourceNotFound(RecordNotFoundException exception, WebRequest request) {
		ErrorDetails errorDetails = new ErrorDetails(new Date(), exception.getMessage(), request.getDescription(false));

		return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.NOT_FOUND);

	}

	@ExceptionHandler(DataNotFoundException.class)
	public ResponseEntity<ErrorDetails> handleDataNotFound(DataNotFoundException exception, WebRequest request) {
		System.out.println("am from global");
		ErrorDetails errorDetails = new ErrorDetails(new Date(), exception.getMessage(), request.getDescription(false));
		return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.NOT_FOUND);

	}

	@ExceptionHandler(AccessDeniedException.class)
	public ResponseEntity<ApiResponse> handleTokenAuthentication(AccessDeniedException exception, WebRequest request) {
		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setStatus(HttpStatus.BAD_REQUEST.value());
		apiResponse.setError(exception.getMessage());

		return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.NOT_FOUND);

	}

}
