package org.uigl.veemanage.settings;

import java.io.File;

import com.almworks.sqlite4java.SQLiteConnection;
import com.almworks.sqlite4java.SQLiteException;

public class SettingsManager {

	/**
	 * Default settings database name.
	 */
	public static final String SETTINGS_DATABASE_NAME = "Settings";

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
	public static final String DEFAULT_WWW_APP_NAME = "VeeManage";

	public static final int DEFAULT_WWW_MAX_SESSIONS = 10000;
	
	public static SettingsManager getSettingsFromDB() throws SQLiteException {
		
		SettingsManager ret = new SettingsManager();
		
		//new SettingsDatabase();
		
		SQLiteConnection db = new SQLiteConnection(new File(SETTINGS_DATABASE_NAME));
		db.open(true);

		//int user_version = db.prepare("PRAGMA user_version;").columnInt(0);
		
		// Cleanup
		db.dispose();
		
		return ret;
	}
	
}
