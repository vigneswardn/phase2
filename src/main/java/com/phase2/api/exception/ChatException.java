package com.phase2.api.exception;

public class ChatException extends RuntimeException {

	public ChatException() {
		super();
	}

	public ChatException(String message) {
		super(message);
	}

	public ChatException(Throwable cause) {
		super(cause);
	}

	public ChatException(String message, Throwable cause) {
		super(message, cause);
	}

	public ChatException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
