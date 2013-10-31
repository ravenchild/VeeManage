package org.uigl.liballdata;

public abstract class DatabaseDriver {
	
	public static DatabaseDriver getInstance(Class<? extends DatabaseDriver> driver, Object ... connectionParams) {
		DatabaseDriver newDriver = null;
		try {
			newDriver = (DatabaseDriver) driver.newInstance();
			newDriver.setConnection(connectionParams);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return newDriver;
	}

	public abstract void setConnection(Object ... connectionParams);
	
	public abstract long open(boolean keepAlive);
	public abstract long close(boolean saveConnection);
	public abstract long dispose();
	
	public abstract boolean isOpen();
	public abstract boolean isDisposed();

	public abstract Cursor rawQuery(String sql);
	public abstract Cursor rawQuery(String sql, BindParam ... params);
	
	public abstract Cursor query(Statement statement);
	public abstract long insert(Statement statement);
	public abstract long update(Statement statement);
	public abstract long delete(Statement statement);
}
