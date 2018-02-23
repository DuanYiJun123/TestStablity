package com.cloudwalk.test.client;

public class ClientSystemException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -297960220471212161L;

	public ClientSystemException(String msg) {
		super(msg);
	}

	public ClientSystemException(String msg, Throwable throwable) {
		super(msg, throwable);
	}
}
