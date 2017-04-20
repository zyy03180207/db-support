package com.db.support;
/**
 * @author yangyang.zhang      
 * @version 2.0     
 * @created Oct 29, 2012 10:01:57 AM    
 */
public class DbException extends Exception {

	private static final long serialVersionUID = 1L;

	public DbException() {
		super();
	}

	public DbException(String message, Throwable cause) {
		super(message, cause);
	}

	public DbException(String message) {
		super(message);
	}

	public DbException(Throwable cause) {
		super(cause);
	}

}
