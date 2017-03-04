package com.phase2.api.exception;

public class BloggerException extends RuntimeException {

	public BloggerException() {
		super();
	}

	public BloggerException(String message) {
		super(message);
	}

	public BloggerException(Throwable cause) {
		super(cause);
	}

	public BloggerException(String message, Throwable cause) {
		super(message, cause);
	}

	public BloggerException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
