package org.uigl.veemanage.settings.db;

import org.uigl.veemanage.db.Database;
import org.uigl.veemanage.db.drivers.SQLiteDriver;
import org.uigl.veemanage.settings.SettingsManager;

public class SettingsDatabase extends Database<SQLiteDriver> {

	public static final String DATABASE_NAME = SettingsManager.SETTINGS_DATABASE_NAME;

	@Override
	public String getDatabaseName() {
		return DATABASE_NAME;
	}

	@Override
	public Object[] getDatabaseParams() {
		return null;
	}

	@Override
	public int getDatabaseVersion() {
		// TODO Auto-generated method stub
		return 0;
	}

}
