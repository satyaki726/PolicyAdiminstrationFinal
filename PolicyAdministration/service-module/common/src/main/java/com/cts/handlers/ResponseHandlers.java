package com.cts.handlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.cts.exception.PolicyException;
import com.cts.model.ServiceResponse;
import com.cts.model.ServiceResponse.Errors;

import lombok.NoArgsConstructor;


@NoArgsConstructor
public class ResponseHandlers<T> {

	public <R> ResponseEntity<R> defaultResponse(T data){
		ServiceResponse<T> responseModel = (ServiceResponse<T>) ServiceResponse.builder()
																	.success(true)
																	.data(data)
																	.build();
		return new ResponseEntity<R>((R) responseModel, HttpStatus.OK);
	}
	
	public <R> ResponseEntity<R> defaultResponse(T data, String message){
		ServiceResponse<T> responseModel = (ServiceResponse<T>) ServiceResponse.builder()
																	.success(true)
																	.additionalMessage(message)
																	.data(data)
																	.build();
		return new ResponseEntity<R>((R) responseModel, HttpStatus.OK);
	}
	
	public <R> ResponseEntity<R> defaultResponse(T data, String message, HttpStatus status){
		ServiceResponse<T> responseModel = (ServiceResponse<T>) ServiceResponse.builder()
																	.success(true)
																	.additionalMessage(message)
																	.data(data)
																	.build();
		return new ResponseEntity<R>((R) responseModel, status);
	}
	
	public <R> ResponseEntity<R> defaultResponse(String message, HttpStatus status){
		ServiceResponse<T> responseModel = (ServiceResponse<T>) ServiceResponse.builder()
																	.success(true)
																	.additionalMessage(message)
																	.build();
		return new ResponseEntity<R>((R) responseModel, status);
	}
	
	@SuppressWarnings("unchecked")
	public <R> ResponseEntity<ServiceResponse<R>> handleExceptions(PolicyException ex) {
		ServiceResponse<T> responseModel = (ServiceResponse<T>) ServiceResponse.builder()
				.success(false)
				.error(
						Errors.builder()
						.errorCode(ex.getErrorCode())
						.errorMessage(ex.getErrorMessage())
						.build()
				)
				.build();
		return new ResponseEntity<ServiceResponse<R>>((ServiceResponse<R>) responseModel, ex.getErrorCode());
	}
}
