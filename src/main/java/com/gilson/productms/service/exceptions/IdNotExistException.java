package com.gilson.productms.service.exceptions;

public class IdNotExistException extends RuntimeException{

		private static final long serialVersionUID = 1L;

		public IdNotExistException(String msg) {
			super(msg);
		}
		
		public IdNotExistException(String msg, Throwable cause) {
			super(msg, cause);
		}
		
}