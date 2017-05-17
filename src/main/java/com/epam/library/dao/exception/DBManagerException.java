package com.epam.library.dao.exception;

public class DBManagerException extends Exception{
	   /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public DBManagerException() {
	        super();
	    }
	 
	    public DBManagerException(String message) {
	        super(message);
	        
	    }
	 
	    public DBManagerException(Throwable cause) {
	        super(cause);
	    }
	    public DBManagerException(String message,Throwable cause) {
	        super(message,cause);
	    }
}
