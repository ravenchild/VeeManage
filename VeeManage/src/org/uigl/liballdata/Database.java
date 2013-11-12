package org.uigl.liballdata;

public interface Database {

	/**
	 * Used in changing the conflict resolution strategy of the database.
	 * 
	 * @author Eric Roth
	 */
	public enum Conflicts {

		CONFLICT_NONE(""), CONFLICT_ROLLBACK(" OR ROLLBACK "), CONFLICT_ABORT(
				" OR ABORT "), CONFLICT_FAIL(" OR FAIL "), CONFLICT_IGNORE(
				" OR IGNORE "), CONFLICT_REPLACE(" OR REPLACE ");

		public final String ConflictValue;

		private Conflicts(String conflictValue) {
			this.ConflictValue = conflictValue;
		}
	}
	
	public void addTables(Table... tables);
	public Table getTable(String tableName);
	public String getDatabaseName();
	public Object[] getDatabaseParams();
	public int getDatabaseVersion();
	public void onUpgrade(int previousVersion, int newVersion);
	public DatabaseDriver getConnection();
	public void open() throws DatabaseException;
	public void close();
	public void close(boolean saveConnection);
	public Cursor rawQuery(String sql) throws DatabaseException;
	public Cursor rawQuery(String sql, BindParam... params) throws DatabaseException;
	public Cursor query(Statement query) throws DatabaseException;
	public long insert(Statement insert) throws DatabaseException;
	
}
