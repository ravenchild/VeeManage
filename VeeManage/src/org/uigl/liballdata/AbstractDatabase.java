package org.uigl.liballdata;

import java.util.ArrayList;

public abstract class AbstractDatabase<DriverClass extends DatabaseDriver> implements Database {

	private DriverClass mConnection;
	private ArrayList<Table> mTables;

	public void addTables(Table... tables) {
		if (tables == null)
			return;
		for (Table table : tables)
			if (getTable(table.getTableName()) == null)
				this.mTables.add(table);
	}

	public Table getTable(String tableName) {
		for (Table table : mTables)
			if (table.getTableName().equals(tableName))
				return table;
		return null;
	}

	public abstract String getDatabaseName();

	public abstract Object[] getDatabaseParams();

	public abstract int getDatabaseVersion();

	public abstract void onUpgrade(int previousVersion, int newVersion);

	public DatabaseDriver getConnection() {
		return mConnection;
	}

	@SuppressWarnings("unchecked")
	public void open() throws DatabaseException {
		if (mConnection == null || mConnection.isDisposed())
			mConnection = (DriverClass) DatabaseDriver.getInstance(
					mConnection.getClass(), getDatabaseParams());

		try {
			if (!mConnection.isOpen())
				mConnection.open(true);
		} catch (Exception ex) {
			close();
			throw new DatabaseException(ex);
		}
	}

	public void close() {
		mConnection.close(false);
	}

	public void close(boolean saveConnection) {
		if (mConnection == null)
			return;

		mConnection.dispose();

		if (!saveConnection)
			mConnection = null;
	}

	public Cursor rawQuery(String sql) throws DatabaseException {
		return mConnection.rawQuery(sql);
	}

	public Cursor rawQuery(String sql, BindParam... params)
			throws DatabaseException {
		return mConnection.rawQuery(sql, params);
	}

	public Cursor query(Statement query) {
		return mConnection.query(query);
	}

	public long insert(Statement insert) {
		return mConnection.insert(insert);
	}

	/*
	 * private Cursor bindParams(Cursor statement, List<BindParam> params)
	 * throws DatabaseException {
	 * 
	 * int paramCount = statement.getBindParameterCount();
	 * 
	 * if (paramCount != params.size()) throw new
	 * DatabaseException("Available params not equal to params supplied.");
	 * 
	 * for (int i=1; i <= paramCount; i++) { BindParam param = params.get(i -
	 * 1); if (param != null) param.bindTo(i, statement); }
	 * 
	 * return statement; }
	 */
}
