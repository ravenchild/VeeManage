package org.uigl.veemanage.db;

public interface Cursor {
	
	public Cursor requery();
	public Statement getStatement();
	public void next();
	public long count();
	public Long getInt(int column);
	public Double getReal(int column);
	public String getText(int column);
	public Byte[] getBlob(int column);
	
}
