package org.uigl.veemanage.db;

import java.sql.SQLDataException;

import com.almworks.sqlite4java.SQLiteException;
import com.almworks.sqlite4java.SQLiteStatement;

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
	
	public void bindTo(int index, SQLiteStatement statement) throws DatabaseException, SQLiteException, SQLDataException {
		if (mInteger != null)
			statement.bind(index, mInteger);
		else if (mNull)
			statement.bindNull(index);
		else if (mText != null)
			statement.bind(index, mText);
		else if (mReal != null)
			statement.bind(index, mReal);
		else if (mBlob != null)
			statement.bind(index, mBlob);
		else
			throw new SQLDataException("No data to bind.");
	}
	
	
}
