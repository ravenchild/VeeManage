package org.uigl.liballdata;

import java.io.Closeable;

import org.uigl.liballdata.DataTypes;

public interface Cursor extends Closeable {
	
	public Cursor requery();
	public void close();
	
	public boolean canUpdate();
	public boolean isClosed();

	public Database getDatabase();
	public Statement getStatement();
	public long getCount();
	public String[] getColumnNames() throws DatabaseException;
	public int getColumnCount() throws DatabaseException;
	public long getPosition();
	
	public boolean moveToNext() throws DatabaseException;
	public boolean moveToPrevious() throws DatabaseException;
	public boolean move(long offset) throws DatabaseException;
	public boolean moveToPosition(long position) throws DatabaseException;
	public boolean moveToFirst() throws DatabaseException;
	public boolean moveToLast() throws DatabaseException;
	
	public boolean isFirst();
	public boolean isLast();

	public int getColumnIndex(String name) throws DatabaseException;
	public String getColumnName(int column) throws DatabaseException;
	public DataTypes getType(int column) throws DatabaseException;
	
	public Long getInt(int column) throws DatabaseException;
	public Double getReal(int column) throws DatabaseException;
	public String getText(int column) throws DatabaseException;
	public byte[] getBlob(int column) throws DatabaseException;
	public boolean isNull(int column) throws DatabaseException;

	public void setInt(int column, Long value);
	public void setReal(int column, Double value);
	public void setText(int column, String value);
	public void setBlob(int column, Byte[] value);
	public void setNull(int column);
	
}
