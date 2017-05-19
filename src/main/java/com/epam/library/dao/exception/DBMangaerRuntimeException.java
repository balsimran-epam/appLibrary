package com.epam.library.dao.exception;

public class DBMangaerRuntimeException extends RuntimeException{
	
		private static final long serialVersionUID = 1L;

		public DBMangaerRuntimeException() {
			super();
		}

		public DBMangaerRuntimeException(String message) {
			super(message);

		}

		public DBMangaerRuntimeException(Throwable cause) {
			super(cause);
		}

		public DBMangaerRuntimeException(String message, Throwable cause) {
			super(message, cause);
		}

}
