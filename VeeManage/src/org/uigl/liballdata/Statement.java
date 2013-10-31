package org.uigl.liballdata;

public class Statement {
	
	public enum StatementType {
		QUERY,
		INSERT,
		UPDATE,
		DELETE;
	}
	
	private final StatementType mType;
	
	private final String mTable;
	
	private final String[] mColumns;
	private final String mSelection; //Used by Query, Delete and Update
	private final BindParam[] mSelectionArgs; //Used by Query, Delete, Update and Insert
	private final String mGroupBy;
	private final String mHaving;
	private final String mOrderBy;
	private final Boolean mDistinct;
	private final Long mLimit;

	private final BindParam[] mValues; //Used by Update and Insert
	
	private Statement(StatementType type, String table, String[] columns, String selection, BindParam[] selectionArgs, String groupBy, String having, String orderBy, Boolean distinct, Long limit, BindParam[] values) {
		mType = type;
		mTable = table;
		mColumns = columns;
		mSelection = selection;
		mSelectionArgs = selectionArgs;
		mGroupBy = groupBy;
		mHaving = having;
		mOrderBy = orderBy;
		mValues = values;
		mDistinct = distinct;
		mLimit = limit;
	}
	
	public static Statement query(String table, String[] columns, String selection, BindParam[] selectionArgs, String groupBy, String having, String orderBy, Boolean distinct, Long limit) {
		return new Statement(
				StatementType.QUERY,
				table,
				columns,
				selection,
				selectionArgs,
				groupBy,
				having,
				orderBy,
				distinct,
				limit,
				null);
	}
	
	public static Statement delete(String table, String whereClause, BindParam[] whereArgs) {
		return new Statement(
				StatementType.DELETE,
				table,
				null,
				whereClause,
				whereArgs,
				null,
				null,
				null,
				null,
				null,
				null);
	}
	
	public static Statement update(String table, BindParam[] values, String whereClause, BindParam[] whereArgs) {
		return new Statement(
				StatementType.DELETE,
				table,
				null,
				whereClause,
				whereArgs,
				null,
				null,
				null,
				null,
				null,
				values);
	}
	
	public static Statement insert(String table, BindParam[] values) {
		return new Statement(
				StatementType.INSERT,
				table,
				null,
				null,
				null,
				null,
				null,
				null,
				null,
				null,
				values);
	}
	
	public StatementType getType() {
		return mType;
	}
	
	public String getTable() {
		return mTable;
	}
	
	public String[] getColumns() {
		return mColumns;
	}
	
	public String getSelection() {
		return mSelection;
	}

	public BindParam[] getSelectionArgs() {
		return mSelectionArgs;
	}

	public String getGroupBy() {
		return mGroupBy;
	}

	public String getHaving() {
		return mHaving;
	}

	public String getOrderBy() {
		return mOrderBy;
	}

	public Boolean getDistinct() {
		return mDistinct;
	}

	public Long getLimit() {
		return mLimit;
	}

	public String getWhereClause() {
		return mSelection;
	}

	public BindParam[] getWhereArgs() {
		return mSelectionArgs;
	}

	public BindParam[] getValues() {
		return mValues;
	}
	
}
