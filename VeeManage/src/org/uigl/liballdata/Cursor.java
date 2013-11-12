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
	public String[] getColumnNames();
	public int getColumnCount();
	public long getPosition();
	
	public boolean moveToNext();
	public boolean moveToPrevious();
	public boolean move(long offset);
	public boolean moveToPosition(long position);
	public boolean moveToFirst();
	public boolean moveToLast();
	
	public boolean isFirst();
	public boolean isLast();

	public int getColumnIndex(String name);
	public String getColumnName(int column);
	public DataTypes getType(int column);
	
	public Long getInt(int column);
	public Double getReal(int column);
	public String getText(int column);
	public Byte[] getBlob(int column);
	public boolean isNull(int column);

	public void setInt(int column, Long value);
	public void setReal(int column, Double value);
	public void setText(int column, String value);
	public void setBlob(int column, Byte[] value);
	public void setNull(int column);
	
	
	
}
