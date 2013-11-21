package org.uigl.liballdata.drivers;

import java.io.File;

import org.uigl.liballdata.AbstractCursor;
import org.uigl.liballdata.BindParam;
import org.uigl.liballdata.Cursor;
import org.uigl.liballdata.DataTypes;
import org.uigl.liballdata.DatabaseDriver;
import org.uigl.liballdata.DatabaseException;
import org.uigl.liballdata.Statement;

import com.almworks.sqlite4java.SQLiteConnection;
import com.almworks.sqlite4java.SQLiteConstants;
import com.almworks.sqlite4java.SQLiteException;
import com.almworks.sqlite4java.SQLiteStatement;

public class SQLiteDriver extends DatabaseDriver {
	
	private File mFile;
	
	private class SQLiteCursor extends AbstractCursor {

		SQLiteConnection mConnection;
		SQLiteStatement mStatement;
		
		@Override
		public Cursor requery() {
			//TODO: Need to figure out how to re-query efficiently.
			try {
				mStatement = mStatement.reset(false);
			} catch (SQLiteException e) {
				e.printStackTrace();
			}
			return this;
		}

		@Override
		protected void onClose() {
			mStatement.dispose();
			mConnection.dispose();
		}

		@Override
		public String[] getColumnNames() throws DatabaseException {
			int columns = 0;
			String names[] = null;
			try {
				columns = getColumnCount();
				names = new String[columns];
				for(int i = 0; i < columns; i++)
					names[i] = mStatement.getColumnName(i);

			} catch (SQLiteException e) {
				throw new DatabaseException("Error getting column names.", e);
			}
			return names;
		}

		@Override
		public int getColumnCount() throws DatabaseException {
			try {
				return mStatement.columnCount();
			} catch (SQLiteException e) {
				throw new DatabaseException("Error getting column count.", e);
			}
		}

		@Override
		public String getColumnName(int column) throws DatabaseException {
			try {
				return mStatement.getColumnName(column);
			} catch (SQLiteException e) {
				throw new DatabaseException("Error getting column name.", e);
			}
		}
		
		@Override
		public long getCount() {
			//TODO: Figure out the best way to get row count.
			return 0;
		}

		@Override
		protected boolean onMove(long currentPosition, long newPosition) throws DatabaseException {
			try {
				if (currentPosition == newPosition - 1)
						return mStatement.step();
				if (currentPosition > newPosition) {
					currentPosition = -1;
					mStatement.reset(false);
				}
				while (true) {
					if (mStatement.step())
						currentPosition++;
					else
						throw new DatabaseException("Reached the end of the cursor.");
					
					if (currentPosition == newPosition)
						return true;
				}
			} catch (SQLiteException e) {
				throw new DatabaseException("Unable to move to position " + newPosition + ".", e);
			}
		}

		@Override
		public DataTypes getType(int column) throws DatabaseException {
			try {
				switch (mStatement.columnType(column)) {
				case SQLiteConstants.SQLITE_BLOB:
					return DataTypes.BLOB;
				case SQLiteConstants.SQLITE_FLOAT:
					return DataTypes.REAL;
				case SQLiteConstants.SQLITE_INTEGER:
					return DataTypes.INTEGER;
				case SQLiteConstants.SQLITE_NULL:
					return DataTypes.NULL;
				case SQLiteConstants.SQLITE_TEXT:
					return DataTypes.TEXT;
				default:
					return null;
				}
			} catch (SQLiteException e) {
				throw new DatabaseException("Unable to get Type.", e);
			}
		}

		@Override
		public Long getInt(int column) throws DatabaseException {
			try {
				return mStatement.columnLong(column);
			} catch (SQLiteException e) {
				throw new DatabaseException("Unable to get Integer.", e);
			}
		}

		@Override
		public Double getReal(int column) throws DatabaseException {
			try {
				return mStatement.columnDouble(column);
			} catch (SQLiteException e) {
				throw new DatabaseException("Unable to get Real.", e);
			}
		}

		@Override
		public String getText(int column) throws DatabaseException {
			try {
				return mStatement.columnString(column);
			} catch (SQLiteException e) {
				throw new DatabaseException("Unable to get Text.", e);
			}
		}

		@Override
		public byte[] getBlob(int column) throws DatabaseException {
			try {
				return mStatement.columnBlob(column);
			} catch (SQLiteException e) {
				throw new DatabaseException("Unable to get Blob.", e);
			}
		}

		@Override
		public boolean isNull(int column) throws DatabaseException {
			try {
				return mStatement.columnNull(column);
			} catch (SQLiteException e) {
				throw new DatabaseException("Unable to get Null.", e);
			}
		}
		
	}

	@Override
	public void setConnection(Object... connectionParams) throws DatabaseException {
		// TODO Auto-generated method stub
		if (connectionParams == null 
				|| connectionParams.length != 1
				|| !File.class.equals(connectionParams[0].getClass()))
			throw new DatabaseException("Invalid Connection Params.");
		mFile = (File) connectionParams[0];
	}
	
	@Override
	public long open(boolean keepAlive) {
		//You can't open it so return 0;
		return 0;
	}

	@Override
	public long close(boolean saveConnection) {
		//You can't close it so return 0;
		return 0;
	}

	@Override
	public boolean isOpen() {
		//Technically always open.
		return true;
	}

	@Override
	public boolean isDisposed() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Cursor query(Statement statement) throws DatabaseException {
		StringBuilder query = new StringBuilder();
		
		query.append("SELECT");
		
		if (statement.getDistinct())
			query.append("DISTINCT");
		
		// Handle column names.
		if (statement.getColumns() == null || statement.getColumns().length == 0) {
			query.append("*");
		} else {
			int i = 0;
			for(;i < statement.getColumns().length; i++) {
				query.append(statement.getColumns()[i]).append(",");
			}
			query.append(statement.getColumns()[i]);
		}
		
		query.append("FROM").append(statement.getTable());
		
		if (statement.getSelection() != null)
			query.append(statement.getSelection());
		else
			query.append("WHERE 1=1");

		if (statement.getGroupBy() != null)
			query.append(statement.getGroupBy());
		
		if (statement.getHaving() != null)
			query.append(statement.getHaving());
		
		if (statement.getOrderBy() != null)
			query.append(statement.getOrderBy());
		
		if (statement.getLimit() != null)
			query.append(statement.getLimit());
		
		return rawQuery(query.toString(), statement.getSelectionArgs());
	}

	@Override
	public long dispose() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Cursor rawQuery(String sql) throws DatabaseException {
		return rawQuery(sql, (BindParam[]) null);
	}
	
	@Override
	public Cursor rawQuery(String sql, BindParam ... params) throws DatabaseException {
		SQLiteCursor cursor = null;
		
		try {
			sql = bindParams(sql, params);
			cursor = new SQLiteCursor();
			cursor.mConnection = getConnection();
				cursor.mStatement = cursor.mConnection.prepare(sql);
		} catch (SQLiteException e) {
			throw new DatabaseException("Unable to prepare the query.", e);
		}
		
		return cursor;
	}

	@Override
	public long insert(Statement statement) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long update(Statement statement) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long delete(Statement statement) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	private SQLiteConnection getConnection() {
		return new SQLiteConnection(mFile);
	}

}
