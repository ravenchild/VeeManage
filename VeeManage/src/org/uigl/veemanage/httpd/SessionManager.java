package org.uigl.veemanage.httpd;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class SessionManager {
	
	public class Session {
		private UUID mID;
		private HashMap<String, SessionVar> mVariables;
		
		public Session(UUID id) {
			this.mID = id;
			this.mVariables = new HashMap<String, SessionManager.SessionVar>();
		}
		
		public UUID getID() {
			return mID;
		}
		
		/**
		 * Inserts anything, replacing any existing value for the given key.
		 *
		 * @param key a String, or null
		 * @param type an int
		 * @param value a Object, or null
		 */
		private void putVar(String key, int type, Object value) {
			mVariables.put(key, new SessionVar(key, type, value));
		}

		/**
		 * Inserts a Object, replacing any existing value for the given key.
		 *
		 * @param key a String, or null
		 * @param value a Object, or null
		 */
		public void putObject(String key, Object value) {
			putVar(key, SessionVar.TYPE_OBJECT, value);
		}

		/**
		 * Inserts a ObjectArray, replacing any existing value for the given key.
		 *
		 * @param key a String, or null
		 * @param value a ObjectArray, or null
		 */
		public void putObjectArray(String key, Object value[]) {
			putVar(key, SessionVar.TYPE_OBJECT_ARRAY, value);
		}

		/**
		 * Inserts a Boolean, replacing any existing value for the given key.
		 *
		 * @param key a String, or null
		 * @param value a Boolean, or null
		 */
		public void putBoolean(String key, boolean value) {
			putVar(key, SessionVar.TYPE_BOOLEAN, value);
		}

		/**
		 * Inserts a BooleanArray, replacing any existing value for the given key.
		 *
		 * @param key a String, or null
		 * @param value a BooleanArray, or null
		 */
		public void putBooleanArray(String key, boolean value[]) {
			putVar(key, SessionVar.TYPE_BOOLEAN_ARRAY, value);
		}

		/**
		 * Inserts a Byte, replacing any existing value for the given key.
		 *
		 * @param key a String, or null
		 * @param value a Byte, or null
		 */
		public void putByte(String key, byte value) {
			putVar(key, SessionVar.TYPE_BYTE, value);
		}

		/**
		 * Inserts a ByteArray, replacing any existing value for the given key.
		 *
		 * @param key a String, or null
		 * @param value a ByteArray, or null
		 */
		public void putByteArray(String key, byte value[]) {
			putVar(key, SessionVar.TYPE_BYTE_ARRAY, value);
		}

		/**
		 * Inserts a Char, replacing any existing value for the given key.
		 *
		 * @param key a String, or null
		 * @param value a Char, or null
		 */
		public void putChar(String key, char value) {
			putVar(key, SessionVar.TYPE_CHAR, value);
		}

		/**
		 * Inserts a CharArray, replacing any existing value for the given key.
		 *
		 * @param key a String, or null
		 * @param value a CharArray, or null
		 */
		public void putCharArray(String key, char value[]) {
			putVar(key, SessionVar.TYPE_CHAR_ARRAY, value);
		}

		/**
		 * Inserts a CharSequence, replacing any existing value for the given key.
		 *
		 * @param key a String, or null
		 * @param value a CharSequence, or null
		 */
		public void putCharSequence(String key, CharSequence value) {
			putVar(key, SessionVar.TYPE_CHAR_SEQUENCE, value);
		}

		/**
		 * Inserts a CharSequenceArray, replacing any existing value for the given key.
		 *
		 * @param key a String, or null
		 * @param value a CharSequenceArray, or null
		 */
		public void putCharSequenceArray(String key, CharSequence value[]) {
			putVar(key, SessionVar.TYPE_CHAR_SEQUENCE_ARRAY, value);
		}

		/**
		 * Inserts a CharSequenceArrayList, replacing any existing value for the given key.
		 *
		 * @param key a String, or null
		 * @param value a CharSequenceArrayList, or null
		 */
		public void putCharSequenceArrayList(String key, ArrayList<CharSequence> value) {
			putVar(key, SessionVar.TYPE_CHAR_SEQUENCE_ARRAYLIST, value);
		}

		/**
		 * Inserts a Double, replacing any existing value for the given key.
		 *
		 * @param key a String, or null
		 * @param value a Double, or null
		 */
		public void putDouble(String key, double value) {
			putVar(key, SessionVar.TYPE_DOUBLE, value);
		}

		/**
		 * Inserts a DoubleArray, replacing any existing value for the given key.
		 *
		 * @param key a String, or null
		 * @param value a DoubleArray, or null
		 */
		public void putDoubleArray(String key, double value[]) {
			putVar(key, SessionVar.TYPE_DOUBLE_ARRAY, value);
		}

		/**
		 * Inserts a Float, replacing any existing value for the given key.
		 *
		 * @param key a String, or null
		 * @param value a Float, or null
		 */
		public void putFloat(String key, float value) {
			putVar(key, SessionVar.TYPE_FLOAT, value);
		}

		/**
		 * Inserts a FloatArray, replacing any existing value for the given key.
		 *
		 * @param key a String, or null
		 * @param value a FloatArray, or null
		 */
		public void putFloatArray(String key, float value[]) {
			putVar(key, SessionVar.TYPE_FLOAT_ARRAY, value);
		}

		/**
		 * Inserts a Integer, replacing any existing value for the given key.
		 *
		 * @param key a String, or null
		 * @param value a Integer, or null
		 */
		public void putInteger(String key, int value) {
			putVar(key, SessionVar.TYPE_INTEGER, value);
		}

		/**
		 * Inserts a IntegerArray, replacing any existing value for the given key.
		 *
		 * @param key a String, or null
		 * @param value a IntegerArray, or null
		 */
		public void putIntegerArray(String key, int value[]) {
			putVar(key, SessionVar.TYPE_INTEGER_ARRAY, value);
		}

		/**
		 * Inserts a IntegerArrayList, replacing any existing value for the given key.
		 *
		 * @param key a String, or null
		 * @param value a IntegerArrayList, or null
		 */
		public void putIntegerArrayList(String key, ArrayList<Integer> value) {
			putVar(key, SessionVar.TYPE_INTEGER_ARRAYLIST, value);
		}

		/**
		 * Inserts a Long, replacing any existing value for the given key.
		 *
		 * @param key a String, or null
		 * @param value a Long, or null
		 */
		public void putLong(String key, long value) {
			putVar(key, SessionVar.TYPE_LONG, value);
		}

		/**
		 * Inserts a LongArray, replacing any existing value for the given key.
		 *
		 * @param key a String, or null
		 * @param value a LongArray, or null
		 */
		public void putLongArray(String key, long value[]) {
			putVar(key, SessionVar.TYPE_LONG_ARRAY, value);
		}

		/**
		 * Inserts a Short, replacing any existing value for the given key.
		 *
		 * @param key a String, or null
		 * @param value a Short, or null
		 */
		public void putShort(String key, short value) {
			putVar(key, SessionVar.TYPE_SHORT, value);
		}

		/**
		 * Inserts a ShortArray, replacing any existing value for the given key.
		 *
		 * @param key a String, or null
		 * @param value a ShortArray, or null
		 */
		public void putShortArray(String key, short value[]) {
			putVar(key, SessionVar.TYPE_SHORT_ARRAY, value);
		}

		/**
		 * Inserts a String, replacing any existing value for the given key.
		 *
		 * @param key a String, or null
		 * @param value a String, or null
		 */
		public void putString(String key, String value) {
			putVar(key, SessionVar.TYPE_STRING, value);
		}

		/**
		 * Inserts a StringArray, replacing any existing value for the given key.
		 *
		 * @param key a String, or null
		 * @param value a StringArray, or null
		 */
		public void putStringArray(String key, String value[]) {
			putVar(key, SessionVar.TYPE_STRING_ARRAY, value);
		}

		/**
		 * Inserts a StringArrayList, replacing any existing value for the given key.
		 *
		 * @param key a String, or null
		 * @param value a StringArrayList, or null
		 */
		public void putStringArrayList(String key, ArrayList<String> value) {
			putVar(key, SessionVar.TYPE_STRING_ARRAYLIST, value);
		}




	}
	
	private class SessionVar {
		
		public class SessionVariableTypeException extends Exception {
			private static final long serialVersionUID = 2167069913015231891L;
		}
		
		public class SessionVariableNotFoundException extends Exception {
			private static final long serialVersionUID = -3488764519778509702L;
		}

		public static final int TYPE_OBJECT = 0;					// Object		// Using this can be dangerous.  Check your types.
		public static final int TYPE_OBJECT_ARRAY = 1;				// ObjectArray	// Using this can be dangerous.  Check your types.
		public static final int TYPE_BOOLEAN = 2;					// Boolean
		public static final int TYPE_BOOLEAN_ARRAY = 3;				// BooleanArray
		public static final int TYPE_BYTE = 4;						// Byte
		public static final int TYPE_BYTE_ARRAY = 5;				// ByteArray
		public static final int TYPE_CHAR = 6;						// Char
		public static final int TYPE_CHAR_ARRAY = 7;				// CharArray
		public static final int TYPE_CHAR_SEQUENCE = 8;				// CharSequence
		public static final int TYPE_CHAR_SEQUENCE_ARRAY = 9;		// CharSequenceArray
		public static final int TYPE_CHAR_SEQUENCE_ARRAYLIST = 10;	// CharSequenceArrayList
		public static final int TYPE_DOUBLE = 11;					// Double
		public static final int TYPE_DOUBLE_ARRAY = 12;				// DoubleArray
		public static final int TYPE_FLOAT = 13;					// Float
		public static final int TYPE_FLOAT_ARRAY = 14;				// FloatArray
		public static final int TYPE_INTEGER = 15;					// Int
		public static final int TYPE_INTEGER_ARRAY = 16;			// IntArray
		public static final int TYPE_INTEGER_ARRAYLIST = 17;		// IntArrayList
		public static final int TYPE_LONG = 18;						// Long
		public static final int TYPE_LONG_ARRAY = 19;				// LongArray
		public static final int TYPE_SHORT = 20;					// Short
		public static final int TYPE_SHORT_ARRAY = 21;				// ShortArray
		public static final int TYPE_STRING = 22;					// String
		public static final int TYPE_STRING_ARRAY = 23;				// StringArray
		public static final int TYPE_STRING_ARRAYLIST = 24;			// StringArrayList
		
		public String Key;
		public int Type;
		public Object Value;
		
		public SessionVar() {
		}

		public SessionVar(String key, int type, Object value) {
			this.Key = key;
			this.Type = type;
			this.Value = value;
		}
	}

	private HashMap<String, Session> mSessions;
	
	public SessionManager() {
		mSessions = new HashMap<String, SessionManager.Session>();
	}
	
	public Session getNewSession() {
		java.util.UUID.randomUUID().toString();
		return null;
	}
	
}
