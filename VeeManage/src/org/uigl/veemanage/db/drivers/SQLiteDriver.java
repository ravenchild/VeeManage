package org.uigl.veemanage.db.drivers;

import org.uigl.veemanage.db.BindParam;
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
	public Cursor query(Statement statement) {
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
		
		//TODO: return something else.
		return null;
	}

	@Override
	public long dispose() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Cursor rawQuery(String sql) {
		return rawQuery(sql, (BindParam[]) null);
	}
	
	@Override
	public Cursor rawQuery(String sql, BindParam ... params) {
		return null;
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

	
}
