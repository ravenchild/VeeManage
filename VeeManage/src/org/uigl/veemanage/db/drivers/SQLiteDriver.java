package org.uigl.veemanage.db.drivers;

import org.uigl.veemanage.db.Cursor;
import org.uigl.veemanage.db.DatabaseDriver;
import org.uigl.veemanage.db.Statement;

public class SQLiteDriver extends DatabaseDriver {

	@Override
	public void setConnection(Object... connectionParams) {
		// TODO Auto-generated method stub
	}
	
	@Override
	public long open(boolean keepAlive) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long close(boolean saveConnection) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isOpen() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isDisposed() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Cursor query(Statement statment) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long dispose() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Cursor rawQuery(String sql) {
		// TODO Auto-generated method stub
		return null;
	}

}
