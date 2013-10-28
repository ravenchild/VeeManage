package org.uigl.veemanage.db;

public class DatabaseException extends Exception {
	
	private static final long serialVersionUID = -129096787784621709L;

	public DatabaseException(String message) {
		super(message);
	}
	
	public DatabaseException(Throwable cause) {
		super(cause);
	}
	
	public DatabaseException(String message, Throwable cause) {
		super(message, cause);
	}
	
}