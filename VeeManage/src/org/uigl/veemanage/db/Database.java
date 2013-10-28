package org.uigl.veemanage.db;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.almworks.sqlite4java.SQLParts;

public abstract class Database<DriverClass extends DatabaseDriver> {
	
	/**
	 * Used in changing the conflict resolution strategy of the database.
	 * @author Eric Roth
	 */
	public enum Conflicts {
		
		CONFLICT_NONE(""),
	    CONFLICT_ROLLBACK(" OR ROLLBACK "),
		CONFLICT_ABORT(" OR ABORT "),
		CONFLICT_FAIL(" OR FAIL "),
		CONFLICT_IGNORE(" OR IGNORE "),
		CONFLICT_REPLACE(" OR REPLACE ");

		public final String ConflictValue;
		
	    private Conflicts(String conflictValue) {
	    	this.ConflictValue = conflictValue;
	    }
	}
	
	private DriverClass mConnection;
	private ArrayList<Table> mTables;
	
	protected void addTables(Table ... tables) {
		if (tables == null)
			return;
		for (Table table : tables)
			if (getTable(table.getTableName()) == null)
				this.mTables.add(table);
	}
	
	private Table getTable(String tableName) {
		for (Table table : mTables) 
			if (table.getTableName().equals(tableName))
				return table;
		return null;
	}
	
	public abstract String getDatabaseName();
	public abstract Object[] getDatabaseParams();
	public abstract int getDatabaseVersion();
	
	public DatabaseDriver getConnection() {
		return mConnection;
	}
	
	public void open() throws DatabaseException {
		if (mConnection == null || mConnection.isDisposed())
			mConnection = (DriverClass) DatabaseDriver.getInstance(mConnection.getClass(), getDatabaseParams());
		
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
	
	/*public Cursor rawQuery(String sql, List<BindParam> params) throws DatabaseException {
		return bindParams(mConnection.rawQuery(sql), params);
	}*/
		
	public Cursor query(boolean distinct, String table, String[] columns,
            String selection, List<BindParam> selectionParams, String groupBy,
            String having, String orderBy, String limit) throws DatabaseException {
		
		SQLParts query = new SQLParts();
		
		query.append("SELECT");
		
		if (distinct)
			query.append("DISTINCT");
		
		// Handle column names.
		if (columns == null || columns.length == 0) {
			query.append("*");
		} else {
			int i = 0;
			for(;i < columns.length; i++) {
				query.append(columns[i]).append(",");
			}
			query.append(columns[i]);
		}
		
		query.append("FROM").append(table);
		
		if (selection != null)
			query.append(selection);
		else
			query.append("WHERE 1=1");

		if (groupBy != null)
			query.append(groupBy);
		
		if (having != null)
			query.append(having);
		
		if (orderBy != null)
			query.append(orderBy);
		
		if (limit != null)
			query.append(limit);
		
		return null; //bindParams(mConnection.rawQuery(query), selectionParams);
	}
	
	/*private Cursor bindParams(Cursor statement, List<BindParam> params) throws DatabaseException {
		
		int paramCount = statement.getBindParameterCount();
		
		if (paramCount != params.size())
			throw new DatabaseException("Available params not equal to params supplied.");
		
		for (int i=1; i <= paramCount; i++) {
			BindParam param = params.get(i - 1);
			if (param != null)
				param.bindTo(i, statement);
		}
		
		return statement;
	}*/
}
