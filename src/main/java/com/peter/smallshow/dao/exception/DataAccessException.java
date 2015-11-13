package com.peter.smallshow.dao.exception;

@SuppressWarnings("serial")
public class DataAccessException extends Exception {
	public DataAccessException(Throwable cause) {
		super(cause);
	}

	public DataAccessException(String message) {
		super(message);
	}
}
