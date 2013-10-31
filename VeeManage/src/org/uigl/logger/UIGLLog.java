package org.uigl.logger;

import java.util.logging.Level;
import java.util.logging.Logger;

public final class UIGLLog {
	private final static String className = UIGLLog.class.getName();
	private final static Logger LOGGER = Logger.getLogger(className);

	public static Logger getLogger() {
		return LOGGER;
	}
	
	public static void s(String msg) {
		LOGGER.severe(msg);
	}
	
	public static void s(String sourceClass, String sourceMethod, String msg) {
		LOGGER.logp(Level.SEVERE, sourceClass, sourceMethod, msg);
	}
	
	public static void s(String sourceClass, String sourceMethod, String msg, Throwable thrown) {
		LOGGER.logp(Level.SEVERE, sourceClass, sourceMethod, msg, thrown);
	}
	
	public static void w(String msg) {
		LOGGER.warning(msg);
	}
	
	public static void w(String sourceClass, String sourceMethod, String msg) {
		LOGGER.logp(Level.WARNING, sourceClass, sourceMethod, msg);
	}
	
	public static void w(String sourceClass, String sourceMethod, String msg, Throwable thrown) {
		LOGGER.logp(Level.WARNING, sourceClass, sourceMethod, msg, thrown);
	}
	
	public static void i(String msg) {
		LOGGER.info(msg);
	}
	
	public static void i(String sourceClass, String sourceMethod, String msg) {
		LOGGER.logp(Level.INFO, sourceClass, sourceMethod, msg);
	}
	
	public static void i(String sourceClass, String sourceMethod, String msg, Throwable thrown) {
		LOGGER.logp(Level.INFO, sourceClass, sourceMethod, msg, thrown);
	}
	
	public static void c(String msg) {
		LOGGER.config(msg);
	}
	
	public static void c(String sourceClass, String sourceMethod, String msg) {
		LOGGER.logp(Level.CONFIG, sourceClass, sourceMethod, msg);
	}
	
	public static void c(String sourceClass, String sourceMethod, String msg, Throwable thrown) {
		LOGGER.logp(Level.CONFIG, sourceClass, sourceMethod, msg, thrown);
	}
	
	public static void logp(Level level, String sourceClass, String sourceMethod, String msg) {
		LOGGER.logp(level, sourceClass, sourceMethod, msg);
	}
	
	public static void logp(Level level, String sourceClass, String sourceMethod, String msg, Throwable thrown) {
		LOGGER.logp(level, sourceClass, sourceMethod, msg, thrown);
	}
}
