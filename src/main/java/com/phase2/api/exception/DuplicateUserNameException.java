package com.phase2.api.exception;

public class DuplicateUserNameException extends RegisterException {

	public DuplicateUserNameException() {
		super();
	}

	public DuplicateUserNameException(String message) {
		super(message);
	}

	public DuplicateUserNameException(Throwable cause) {
		super(cause);
	}

	public DuplicateUserNameException(String message, Throwable cause) {
		super(message, cause);
	}

	public DuplicateUserNameException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
