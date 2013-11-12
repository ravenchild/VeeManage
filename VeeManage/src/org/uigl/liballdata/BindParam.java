package org.uigl.liballdata;

public class BindParam {

	private Long mInteger;
	private Double mReal;
	private String mText;
	private byte[] mBlob;
	private boolean mNull;
	
	public BindParam() {
		recycle();
	}

	public BindParam(Integer intVal) {
		recycle();
		if (intVal == null)
			mNull = true;
		else
			mInteger = (long) intVal;
	}
	public BindParam(Long longVal) {
		recycle();
		if (longVal == null)
			mNull = true;
		else
			mInteger = longVal;
	}
	
	public BindParam(Double real) {
		recycle();
		if (real == null)
			mNull = true;
		else
			mReal = real;
	}
	
	public BindParam(String text) {
		recycle();
		if (text == null)
			mNull = true;
		else
			mText = text;
	}
	
	public BindParam(byte[] blob) {
		recycle();
		if (blob == null)
			mNull = true;
		else
			mBlob = blob;
	}
	
	public void recycle() {
		mInteger = null;
		mReal = null;
		mText = null;
		mBlob = null;
		mNull = false;
	}
	
	public String getText() {
		return mText;
	}
	
	public byte[] getBlob() {
		return mBlob;
	}
	
	public Double getReal() {
		return mReal;
	}
	
	public Long getInteger() {
		return mInteger;
	}
	
	public DataTypes getType() throws DatabaseException {
		if (mInteger != null)
			return DataTypes.INTEGER;
		else if (mNull)
			return DataTypes.NULL;
		else if (mText != null)
			return DataTypes.TEXT;
		else if (mReal != null)
			return DataTypes.REAL;
		else if (mBlob != null)
			return DataTypes.BLOB;
		else
			throw new DatabaseException("Invalid Type");
	}
	
}
