package com.uab.project.config.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.uab.project.config.exception.ResourceException.ResponseCode;
import com.uab.project.model.responseDefault.ResponseDefault;

@ControllerAdvice
public class ExceptionHandlingController extends ResponseEntityExceptionHandler {
	
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		ResourceException resourceException = new ResourceException(0, 0, null, "", true);
		
		if(status.equals(HttpStatus.BAD_REQUEST)) {
			resourceException.setMessage("(" + ResponseCode.BAD_REQUEST.getHttpStatus() + "." + ResponseCode.BAD_REQUEST.getInternalCode() + ") cuerpo de la requisición está incompleta! Verifique el cuerpo del método post");
			resourceException.setCode(ResponseCode.BAD_REQUEST.getInternalCode());
		}
		
		if(status.equals(HttpStatus.NOT_FOUND)) {
			resourceException.setMessage("(" + ResponseCode.BAD_REQUEST.getHttpStatus() + "." + ResponseCode.BAD_REQUEST.getInternalCode() + ") Url incorrecta! No existe ese tipo de servicio!");
			resourceException.setCode(ResponseCode.NOT_FOUND.getInternalCode());
		}
		
		return handleExceptionInternal(ex, resourceException, headers, status, request);
	}
	
	@ExceptionHandler(ResourceException.class)
	public ResponseEntity<ResponseDefault> resourceException(ResourceException ex) {
		String msg = "(" + ex.getHttpStatusValue() + "." + ex.getCode() + ") " + ex.getMessage();
		ResponseDefault responseDefault = new ResponseDefault(null, ex.getCode(), msg, true);
		return new ResponseEntity<ResponseDefault>(responseDefault, HttpStatus.valueOf(ex.getHttpStatusValue()));
	}
	
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<ResourceException> exceptionIllegalArgumen(Exception ex) {
		ResourceException resourceException = new ResourceException(ResponseCode.BAD_REQUEST.getHttpStatus(), ResponseCode.BAD_REQUEST.getInternalCode(), null, ex.getMessage(), true);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resourceException);
	}
	
}
