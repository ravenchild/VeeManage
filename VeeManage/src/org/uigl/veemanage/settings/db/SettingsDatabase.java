package org.uigl.veemanage.settings.db;

import org.uigl.liballdata.AbstractDatabase;
import org.uigl.liballdata.drivers.SQLiteDriver;
import org.uigl.veemanage.settings.SettingsManager;

public class SettingsDatabase extends AbstractDatabase<SQLiteDriver> {

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

	@Override
	public void onUpgrade(int previousVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}

}
