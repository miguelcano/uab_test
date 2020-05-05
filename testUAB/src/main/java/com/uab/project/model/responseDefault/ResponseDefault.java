package com.uab.project.model.responseDefault;

public class ResponseDefault {
	private Object content;
	private Integer code;
	private String message;
	private Boolean error;
	
	public ResponseDefault(Object content, Integer code, String message, Boolean error) {
		this.content = content;
		this.code = code;
		this.message = message;
		this.error = error;
	}
	
	public ResponseDefault(Object content, Integer code, String message) {
		this.content = content;
		this.code = code;
		this.message = message;
		this.error = false;
	}
	
	public Object getContent() {
		return content;
	}
	public void setContent(Object content) {
		this.content = content;
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
	public boolean isError() {
		return error;
	}
	public void setError(boolean error) {
		this.error = error;
	}
	
}
