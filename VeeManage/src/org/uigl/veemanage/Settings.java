package org.uigl.veemanage;

import java.io.File;

import com.almworks.sqlite4java.SQLiteConnection;
import com.almworks.sqlite4java.SQLiteException;

public class Settings {

	/**
	 * Standard settings database.
	 */
	public static final String SETTINGS_DATABASE_NAME = "settings.db";

	/**
	 * Logging default settings
	 */
	//TODO: Add Logging to file.
	//private static final String DEFAULT_LOG_FILE = "veemanage.log";
	
	/**
	 * HTTPD specific defaults.
	 */
	public static final String DEFAULT_WWW_ROOT = "wwwroot";
	public static final int DEFAULT_WWW_PORT = 39183;
	public static final boolean DEFAULT_WWW_SSL = true;
	
	public static Settings getSettingsFromDB() throws SQLiteException {
		
		Settings ret = new Settings();
		
		
		SQLiteConnection db = new SQLiteConnection(new File(SETTINGS_DATABASE_NAME));
		db.open(true);
		
		// Cleanup
		db.dispose();
		
		return ret;
	}
	
}
