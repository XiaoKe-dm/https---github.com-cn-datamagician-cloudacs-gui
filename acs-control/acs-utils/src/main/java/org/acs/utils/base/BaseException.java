package org.acs.utils.base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BaseException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	private Logger logger = null;
	
	public BaseException(Class<?> clazz, String message) {
		super(message);
		logger = LogManager.getLogger(clazz.getName());
		logger.error(message);
	}
	
	public BaseException(Class<?> clazz, Throwable throwable) {
		super(throwable);
		logger = LogManager.getLogger(clazz.getName());
		logger.error(throwable.getMessage());
	}
}
