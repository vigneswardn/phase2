package com.phase2.api.exception;

public class InvalidUserNameException extends LoginException {

	public InvalidUserNameException() {
		super();
	}

	public InvalidUserNameException(String message) {
		super(message);
	}

	public InvalidUserNameException(Throwable cause) {
		super(cause);
	}

	public InvalidUserNameException(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidUserNameException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
