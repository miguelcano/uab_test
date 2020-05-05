package com.uab.project.config.exception;

public class ResourceException extends RuntimeException {

	private static final long serialVersionUID = 128260440403302281L;

	public enum ResponseCode {
		/*200*/
		OK(200, 0), 
		CREATED(201, 0), 
		/*300*/
		
		/*400*/
		BAD_REQUEST(400, 0), 
		
		/*401 Attendant*/
		UNAUTHORIZED(401,0),
		UNAUTHORIZED_ATTENDANT(401, 1),
		
		FORBIDDEN(403,0),
		TOKEN_FORBIDDEN(403,101),
		
		NOT_FOUND(404, 0), 

		/*406 token*/
		NOT_ACCEPTABLE(406, 0),
		BAD_TOKEN(406, 100),
		
		/*406 person*/
		BAD_PERSON(406, 200),
		BAD_PERSON_NAME(406, 201),
		BAD_PERSON_BIRTHDATE(406, 202),
		BAD_PERSON_PHONE(406, 203),
		
		CAN_NOT_CREATE_PERSON(406, 341),
		CAN_NOT_UPDATE_PERSON(406, 344),
		
		/*500*/
		INTERNAL_SERVER_ERROR(500, 0), 
		NOT_IMPLEMENTED(501, 0),  
		
		;
		
		private Integer httpStatus;
		private Integer internalCode;
		
		private ResponseCode(Integer httpStatus, Integer internalCode) {
			this.httpStatus = httpStatus;
			this.internalCode = internalCode;
		}
		
		public Integer getHttpStatus() {
			return this.httpStatus;
		}
		
		public Integer getInternalCode() {
			return this.internalCode;
		}
	}
	
	public Integer httpStatusValue;
	public Integer code;
	private Object content;
	private String message;
	private Boolean error;
	
	public ResourceException(Integer httpStatus, Integer code, Object content, String message, boolean error) {
		super(message);
		this.httpStatusValue = httpStatus;
		this.code = code;
		this.content = content;
		this.message = message;
		this.error = error;
	}

	public ResourceException(ResponseCode responseCode, String message) {
		super(message);
		this.httpStatusValue = responseCode.getHttpStatus();
		this.code = responseCode.getInternalCode();
		this.content = null;
		this.message = message;
		this.error = true;
	}

	public ResourceException(ResponseCode responseCode, Object content, String message) {
		super(message);
		this.httpStatusValue = responseCode.getHttpStatus();
		this.code = responseCode.getInternalCode();
		this.content = content;
		this.message = message;
		this.error = true;
	}

	public Object getContent() {
		return content;
	}

	public void setContent(Object object) {
		this.content = object;
	}

	public Integer getHttpStatusValue() {
		return httpStatusValue;
	}

	public void setHttpStatusValue(Integer httpStatusValue) {
		this.httpStatusValue = httpStatusValue;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Boolean getError() {
		return error;
	}

	public void setError(Boolean error) {
		this.error = error;
	}

}
