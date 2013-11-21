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

	public abstract void setConnection(Object ... connectionParams) throws DatabaseException;
	
	public abstract long open(boolean keepAlive) throws DatabaseException;
	public abstract long close(boolean saveConnection);
	public abstract long dispose();
	
	public abstract boolean isOpen();
	public abstract boolean isDisposed();

	public abstract Cursor rawQuery(String sql) throws DatabaseException;
	public abstract Cursor rawQuery(String sql, BindParam ... params) throws DatabaseException;
	
	protected String convertParam(BindParam param) throws DatabaseException {
		String retVal = null;
		switch (param.getType()) {
		case NULL:
			retVal = "NULL";
			break;
		case BLOB:
			retVal = "BLOB"; //TODO:Get Blob
			break;
		case INTEGER:
			retVal = param.getInteger().toString();
			break;
		case REAL:
			retVal = param.getReal().toString();
			break;
		case TEXT:
			retVal = "\"" + param.getText() + "\"";
			break;
		default:
			break;
		}
		return retVal;
	}
	
	protected String bindParams(String sql, BindParam ... params) throws DatabaseException {
		if (params != null && params.length > 0) {
			StringBuilder query = new StringBuilder();
			int paramsUsed = 0;
			
			if (sql.startsWith("??"))
				query.append('?');
			
			String [] doubleQuestionSplit = sql.split("\\?\\?");
			for(int i = 0; i < doubleQuestionSplit.length; i++) {
				String doubleQuestionPart = doubleQuestionSplit[i];
				
				while(doubleQuestionPart.contains("?")) {
					if (params.length <= paramsUsed)
						throw new DatabaseException("Too many params.");
					doubleQuestionPart = doubleQuestionPart.replaceFirst("\\?", convertParam(params[paramsUsed]));
					paramsUsed++;
				}
				
				query.append(doubleQuestionSplit);
				if (i < doubleQuestionSplit.length - 1)
					query.append('?');
			}
			
			if (sql.endsWith("??"))
				query.append("?");
			
			sql = query.toString();
		}
		
		return sql;
	}
	
	public abstract Cursor query(Statement statement) throws DatabaseException;
	public abstract long insert(Statement statement) throws DatabaseException;
	public abstract long update(Statement statement) throws DatabaseException;
	public abstract long delete(Statement statement) throws DatabaseException;
}
