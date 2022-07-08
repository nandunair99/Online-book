package com.narola.bookmyseat.exception;

public class ValidationException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	private String field;

	public ValidationException(String field, String message) {
		super(message);
		this.field = field;
	}

	public ValidationException(String message, Throwable cause) {
		super(message, cause);
	}

	public ValidationException(Throwable cause) {
		super(cause);
	}

	public String getField() {
		return field;
	}
}
