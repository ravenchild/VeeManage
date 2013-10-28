package org.uigl.veemanage.httpd;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;

import org.uigl.veemanage.VeeManage;

public class Session {
	
	private String mID;
	private HashMap<String, Object> mVariables;
	
	private boolean mReturnToTop = false;
	
	/**
	 * Creates a new session with sessionID.
	 * Use <b>null</b> if temporary storage.
	 * 
	 * @param sessionID
	 */
	public Session(String sessionID) {
		this.mID = sessionID;
		this.mVariables = new HashMap<String, Object>();
		if (sessionID != null)
			VeeManage.LOGGER.logp(Level.INFO, Session.class.getName(), "Session(UUID id)", "New Session (" + (sessionID == null ? "null" : sessionID) + ")");
	}
	
	/**
	 * Gets the sessionID, can be null.
	 * 
	 * @return The sessionID
	 */
	public String getSessionID() {
		return mID;
	}
	
	/**
	 * Set mReturnToTop. Used in the purging process.
	 */
	public void setReturnToTop(boolean returnToTop) {
		this.mReturnToTop = returnToTop;
	}
	
	/**
	 * Get mReturnToTop. Used in the purging process.
	 */
	public boolean getReturnToTop() {
		return this.mReturnToTop;
	}
	
	/**
	 * Removes all variables from the Session.
	 */
	public void clear() {
		mVariables.clear();
	}
	
	/**
	 * Nulls the session vars.
	 */
	public void close() {
		mVariables.clear();
		mVariables = null;
		mID = null;
	}
	
	/**
	 * Removes the variable associated with the given key.
	 * 
	 * @param key a String, or null
	 */
	public void remove(String key) {
		mVariables.remove(key);
	}
	
	/**
	 * Inserts an Object, replacing any existing value for the given key.
	 *
	 * @param key a String, or null
	 * @param value an Object, or null
	 */
	public void put(String key, Object value) {
		mVariables.put(key, value);
	}

	/**
	 * Inserts an Object[], replacing any existing value for the given key.
	 *
	 * @param key a String, or null
	 * @param value an Object[], or null
	 */
	public void putArray(String key, Object value[]) {
		mVariables.put(key, value);
	}

	/**
	 * Inserts a boolean, replacing any existing value for the given key.
	 *
	 * @param key a String, or null
	 * @param value a boolean
	 */
	public void putBoolean(String key, boolean value) {
		mVariables.put(key, value);
	}

	/**
	 * Inserts a boolean[], replacing any existing value for the given key.
	 *
	 * @param key a String, or null
	 * @param value a boolean[], or null
	 */
	public void putBooleanArray(String key, boolean value[]) {
		mVariables.put(key, value);
	}

	/**
	 * Inserts a byte, replacing any existing value for the given key.
	 *
	 * @param key a String, or null
	 * @param value a byte
	 */
	public void putByte(String key, byte value) {
		mVariables.put(key, value);
	}

	/**
	 * Inserts a byte[], replacing any existing value for the given key.
	 *
	 * @param key a String, or null
	 * @param value a byte[], or null
	 */
	public void putByteArray(String key, byte value[]) {
		mVariables.put(key, value);
	}

	/**
	 * Inserts a char, replacing any existing value for the given key.
	 *
	 * @param key a String, or null
	 * @param value a char
	 */
	public void putChar(String key, char value) {
		mVariables.put(key, value);
	}

	/**
	 * Inserts a char[], replacing any existing value for the given key.
	 *
	 * @param key a String, or null
	 * @param value a char[], or null
	 */
	public void putCharArray(String key, char value[]) {
		mVariables.put(key, value);
	}

	/**
	 * Inserts a CharSequence, replacing any existing value for the given key.
	 *
	 * @param key a String, or null
	 * @param value a CharSequence, or null
	 */
	public void putCharSequence(String key, CharSequence value) {
		mVariables.put(key, value);
	}

	/**
	 * Inserts a CharSequence[], replacing any existing value for the given key.
	 *
	 * @param key a String, or null
	 * @param value a CharSequence[], or null
	 */
	public void putCharSequenceArray(String key, CharSequence value[]) {
		mVariables.put(key, value);
	}

	/**
	 * Inserts an ArrayList<CharSequence>, replacing any existing value for the given key.
	 *
	 * @param key a String, or null
	 * @param value a ArrayList<CharSequence>, or null
	 */
	public void putCharSequenceArrayList(String key, ArrayList<CharSequence> value) {
		mVariables.put(key, value);
	}

	/**
	 * Inserts a double, replacing any existing value for the given key.
	 *
	 * @param key a String, or null
	 * @param value a double
	 */
	public void putDouble(String key, double value) {
		mVariables.put(key, value);
	}

	/**
	 * Inserts a double[], replacing any existing value for the given key.
	 *
	 * @param key a String, or null
	 * @param value a double[], or null
	 */
	public void putDoubleArray(String key, double value[]) {
		mVariables.put(key, value);
	}

	/**
	 * Inserts a float, replacing any existing value for the given key.
	 *
	 * @param key a String, or null
	 * @param value a float
	 */
	public void putFloat(String key, float value) {
		mVariables.put(key, value);
	}

	/**
	 * Inserts a float[], replacing any existing value for the given key.
	 *
	 * @param key a String, or null
	 * @param value a float[], or null
	 */
	public void putFloatArray(String key, float value[]) {
		mVariables.put(key, value);
	}

	/**
	 * Inserts an int, replacing any existing value for the given key.
	 *
	 * @param key a String, or null
	 * @param value an int
	 */
	public void putInteger(String key, int value) {
		mVariables.put(key, value);
	}

	/**
	 * Inserts an int[], replacing any existing value for the given key.
	 *
	 * @param key a String, or null
	 * @param value an int[], or null
	 */
	public void putIntegerArray(String key, int value[]) {
		mVariables.put(key, value);
	}

	/**
	 * Inserts an ArrayList<Integer>, replacing any existing value for the given key.
	 *
	 * @param key a String, or null
	 * @param value an ArrayList<Integer>, or null
	 */
	public void putIntegerArrayList(String key, ArrayList<Integer> value) {
		mVariables.put(key, value);
	}

	/**
	 * Inserts a long, replacing any existing value for the given key.
	 *
	 * @param key a String, or null
	 * @param value a long
	 */
	public void putLong(String key, long value) {
		mVariables.put(key, value);
	}

	/**
	 * Inserts a long[], replacing any existing value for the given key.
	 *
	 * @param key a String, or null
	 * @param value a long[], or null
	 */
	public void putLongArray(String key, long value[]) {
		mVariables.put(key, value);
	}

	/**
	 * Inserts a short, replacing any existing value for the given key.
	 *
	 * @param key a String, or null
	 * @param value a short
	 */
	public void putShort(String key, short value) {
		mVariables.put(key, value);
	}

	/**
	 * Inserts a short[], replacing any existing value for the given key.
	 *
	 * @param key a String, or null
	 * @param value a short[], or null
	 */
	public void putShortArray(String key, short value[]) {
		mVariables.put(key, value);
	}

	/**
	 * Inserts a String, replacing any existing value for the given key.
	 *
	 * @param key a String, or null
	 * @param value a String, or null
	 */
	public void putString(String key, String value) {
		mVariables.put(key, value);
	}

	/**
	 * Inserts a String[], replacing any existing value for the given key.
	 *
	 * @param key a String, or null
	 * @param value a String[], or null
	 */
	public void putStringArray(String key, String value[]) {
		mVariables.put(key, value);
	}

	/**
	 * Inserts a ArrayList<String>, replacing any existing value for the given key.
	 *
	 * @param key a String, or null
	 * @param value a ArrayList<String>, or null
	 */
	public void putStringArrayList(String key, ArrayList<String> value) {
		mVariables.put(key, value);
	}

	/**
	 * Returns the object associated with the key.
	 * If no key exists, null is returned.
	 *
	 * @param key a String, or null
	 * @return an Object value, or null
	 */
	public Object get(String key) {
		Object ret = mVariables.get(key);
		if (ret == null)
			return null;
		return ret;
	}

	/**
	 * Returns the Object associated with the key.
	 * If no key exists, defaultValue is returned.
	 *
	 * @param key a String, or null
	 * @param defaultValue Value to return if key does not exist
	 * @return an Object value, or null
	 */
	public Object get(String key, Object defaultValue) {
		Object ret = mVariables.get(key);
		if (ret == null)
			return defaultValue;
		try {
			return ret;
		} catch (ClassCastException e) {
			// Hopefully this never happens, otherwise java is broken :(
			VeeManage.LOGGER.logp(Level.WARNING, Session.class.getName(), "get", "Invalid cast to 'Object'.", e);
			return defaultValue;
		}
	}

	/**
	 * Returns the Object[] associated with the key.
	 * If no key exists, null is returned.
	 *
	 * @param key a String, or null
	 * @return an Object[] value, or null
	 */
	public Object[] getArray(String key) {
		Object ret = mVariables.get(key);
		if (ret == null)
			return null;
		return (Object[]) ret;
	}

	/**
	 * Returns the Object associated with the key.
	 * If no key exists, defaultValue is returned.
	 *
	 * @param key a String, or null
	 * @param defaultValue Value to return if key does not exist
	 * @return an Object[] value, or null
	 */
	public Object[] getArray(String key, Object[] defaultValue) {
		Object ret = mVariables.get(key);
		if (ret == null)
			return defaultValue;
		try {
			return (Object[]) ret;
		} catch (ClassCastException e) {
			VeeManage.LOGGER.logp(Level.WARNING, Session.class.getName(), "getObjectArray", "Invalid cast to 'Object[]'.", e);
			return defaultValue;
		}
	}

	/**
	 * Returns the boolean associated with the key.
	 * If no key exists, defaultValue is returned.
	 *
	 * @param key a String, or null
	 * @param defaultValue Value to return if key does not exist
	 * @return a boolean value
	 */
	public boolean getBoolean(String key, boolean defaultValue) {
		Object ret = mVariables.get(key);
		if (ret == null)
			return defaultValue;
		try {
			return (Boolean) ret;
		} catch (ClassCastException e) {
			VeeManage.LOGGER.logp(Level.WARNING, Session.class.getName(), "getBoolean", "Invalid cast to 'boolean'.", e);
			return defaultValue;
		}
	}

	/**
	 * Returns the boolean[] associated with the key.
	 * If no key exists, null is returned.
	 *
	 * @param key a String, or null
	 * @return a boolean[] value, or null
	 */
	public boolean[] getBooleanArray(String key) {
		Object ret = mVariables.get(key);
		if (ret == null)
			return null;
		return (boolean[]) ret;
	}

	/**
	 * Returns the boolean[] associated with the key.
	 * If no key exists, defaultValue is returned.
	 *
	 * @param key a String, or null
	 * @param defaultValue Value to return if key does not exist
	 * @return a boolean[] value, or null
	 */
	public boolean[] getBooleanArray(String key, boolean[] defaultValue) {
		Object ret = mVariables.get(key);
		if (ret == null)
			return defaultValue;
		try {
			return (boolean[]) ret;
		} catch (ClassCastException e) {
			VeeManage.LOGGER.logp(Level.WARNING, Session.class.getName(), "getBooleanArray", "Invalid cast to 'boolean[]'.", e);
			return defaultValue;
		}
	}

	/**
	 * Returns the byte associated with the key.
	 * If no key exists, (byte) 0 is returned.
	 *
	 * @param key a String, or null
	 * @return a byte value
	 */
	public Byte getByte(String key) {
		return getByte(key, (byte) 0);
	}

	/**
	 * Returns the byte associated with the key.
	 * If no key exists, defaultValue is returned.
	 *
	 * @param key a String, or null
	 * @param defaultValue Value to return if key does not exist
	 * @return a byte value
	 */
	public byte getByte(String key, byte defaultValue) {
		Object ret = mVariables.get(key);
		if (ret == null)
			return defaultValue;
		try {
			return (Byte) ret;
		} catch (ClassCastException e) {
			VeeManage.LOGGER.logp(Level.WARNING, Session.class.getName(), "getByte", "Invalid cast to 'byte'.", e);
			return defaultValue;
		}
	}

	/**
	 * Returns the byte[] associated with the key.
	 * If no key exists, null is returned.
	 *
	 * @param key a String, or null
	 * @return a byte[] value, or null
	 */
	public byte[] getByteArray(String key) {
		Object ret = mVariables.get(key);
		if (ret == null)
			return null;
		return (byte[]) ret;
	}

	/**
	 * Returns the byte[] associated with the key.
	 * If no key exists, defaultValue is returned.
	 *
	 * @param key a String, or null
	 * @param defaultValue Value to return if key does not exist
	 * @return a byte[] value, or null
	 */
	public byte[] getByteArray(String key, byte[] defaultValue) {
		Object ret = mVariables.get(key);
		if (ret == null)
			return defaultValue;
		try {
			return (byte[]) ret;
		} catch (ClassCastException e) {
			VeeManage.LOGGER.logp(Level.WARNING, Session.class.getName(), "getByteArray", "Invalid cast to 'byte[]'.", e);
			return defaultValue;
		}
	}

	/**
	 * Returns the char associated with the key.
	 * If no key exists, (char) 0 is returned.
	 *
	 * @param key a String, or null
	 * @return a char value
	 */
	public char getChar(String key) {
		return getChar(key, (char) 0);
	}

	/**
	 * Returns the char associated with the key.
	 * If no key exists, defaultValue is returned.
	 *
	 * @param key a String, or null
	 * @param defaultValue Value to return if key does not exist
	 * @return a char value
	 */
	public char getChar(String key, char defaultValue) {
		Object ret = mVariables.get(key);
		if (ret == null)
			return defaultValue;
		try {
			return (Character) ret;
		} catch (ClassCastException e) {
			VeeManage.LOGGER.logp(Level.WARNING, Session.class.getName(), "getChar", "Invalid cast to 'char'.", e);
			return defaultValue;
		}
	}

	/**
	 * Returns the char[] associated with the key.
	 * If no key exists, null is returned.
	 *
	 * @param key a String, or null
	 * @return a char[] value, or null
	 */
	public char[] getCharArray(String key) {
		Object ret = mVariables.get(key);
		if (ret == null)
			return null;
		return (char[]) ret;
	}

	/**
	 * Returns the char[] associated with the key.
	 * If no key exists, defaultValue is returned.
	 *
	 * @param key a String, or null
	 * @param defaultValue Value to return if key does not exist
	 * @return a char[] value, or null
	 */
	public char[] getCharArray(String key, char[] defaultValue) {
		Object ret = mVariables.get(key);
		if (ret == null)
			return defaultValue;
		try {
			return (char[]) ret;
		} catch (ClassCastException e) {
			VeeManage.LOGGER.logp(Level.WARNING, Session.class.getName(), "getCharArray", "Invalid cast to 'char[]'.", e);
			return defaultValue;
		}
	}

	/**
	 * Returns the CharSequence associated with the key.
	 * If no key exists, null is returned.
	 *
	 * @param key a String, or null
	 * @return a CharSequence value, or null
	 */
	public CharSequence getCharSequence(String key) {
		Object ret = mVariables.get(key);
		if (ret == null)
			return null;
		return (CharSequence) ret;
	}

	/**
	 * Returns the CharSequence associated with the key.
	 * If no key exists, defaultValue is returned.
	 *
	 * @param key a String, or null
	 * @param defaultValue Value to return if key does not exist
	 * @return a CharSequence value, or null
	 */
	public CharSequence getCharSequence(String key, CharSequence defaultValue) {
		Object ret = mVariables.get(key);
		if (ret == null)
			return defaultValue;
		try {
			return (CharSequence) ret;
		} catch (ClassCastException e) {
			VeeManage.LOGGER.logp(Level.WARNING, Session.class.getName(), "getCharSequence", "Invalid cast to 'CharSequence'.", e);
			return defaultValue;
		}
	}

	/**
	 * Returns the CharSequence[] associated with the key.
	 * If no key exists, null is returned.
	 *
	 * @param key a String, or null
	 * @return a CharSequence[] value, or null
	 */
	public CharSequence[] getCharSequenceArray(String key) {
		Object ret = mVariables.get(key);
		if (ret == null)
			return null;
		return (CharSequence[]) ret;
	}

	/**
	 * Returns the CharSequence[] associated with the key.
	 * If no key exists, defaultValue is returned.
	 *
	 * @param key a String, or null
	 * @param defaultValue Value to return if key does not exist
	 * @return a CharSequence[] value, or null
	 */
	public CharSequence[] getCharSequenceArray(String key, CharSequence[] defaultValue) {
		Object ret = mVariables.get(key);
		if (ret == null)
			return defaultValue;
		try {
			return (CharSequence[]) ret;
		} catch (ClassCastException e) {
			VeeManage.LOGGER.logp(Level.WARNING, Session.class.getName(), "getCharSequenceArray", "Invalid cast to 'CharSequence[]'.", e);
			return defaultValue;
		}
	}

	/**
	 * Returns the ArrayList<CharSequence> associated with the key.
	 * If no key exists, null is returned.
	 *
	 * @param key a String, or null
	 * @return an ArrayList<CharSequence> value, or null
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<CharSequence> getCharSequenceArrayList(String key) {
		Object ret = mVariables.get(key);
		if (ret == null)
			return null;
		return (ArrayList<CharSequence>) ret;
	}

	/**
	 * Returns the ArrayList<CharSequence> associated with the key.
	 * If no key exists, defaultValue is returned.
	 *
	 * @param key a String, or null
	 * @param defaultValue Value to return if key does not exist
	 * @return an ArrayList<CharSequence> value, or null
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<CharSequence> getCharSequenceArrayList(String key, ArrayList<CharSequence> defaultValue) {
		Object ret = mVariables.get(key);
		if (ret == null)
			return defaultValue;
		try {
			return (ArrayList<CharSequence>) ret;
		} catch (ClassCastException e) {
			VeeManage.LOGGER.logp(Level.WARNING, Session.class.getName(), "getCharSequenceArrayList", "Invalid cast to 'ArrayList<CharSequence>'.", e);
			return defaultValue;
		}
	}

	/**
	 * Returns the double associated with the key.
	 * If no key exists, (double) 0.0 is returned.
	 *
	 * @param key a String, or null
	 * @return a double value
	 */
	public double getDouble(String key) {
		return getDouble(key, 0.0d);
	}

	/**
	 * Returns the double associated with the key.
	 * If no key exists, defaultValue is returned.
	 *
	 * @param key a String, or null
	 * @param defaultValue Value to return if key does not exist
	 * @return a double value
	 */
	public double getDouble(String key, Double defaultValue) {
		Object ret = mVariables.get(key);
		if (ret == null)
			return defaultValue;
		try {
			return (Double) ret;
		} catch (ClassCastException e) {
			VeeManage.LOGGER.logp(Level.WARNING, Session.class.getName(), "getDouble", "Invalid cast to 'double'.", e);
			return defaultValue;
		}
	}

	/**
	 * Returns the double[] associated with the key.
	 * If no key exists, null is returned.
	 *
	 * @param key a String, or null
	 * @return a double[] value, or null
	 */
	public double[] getDoubleArray(String key) {
		Object ret = mVariables.get(key);
		if (ret == null)
			return null;
		return (double[]) ret;
	}

	/**
	 * Returns the double[] associated with the key.
	 * If no key exists, defaultValue is returned.
	 *
	 * @param key a String, or null
	 * @param defaultValue Value to return if key does not exist
	 * @return a double[] value, or null
	 */
	public double[] getDoubleArray(String key, double[] defaultValue) {
		Object ret = mVariables.get(key);
		if (ret == null)
			return defaultValue;
		try {
			return (double[]) ret;
		} catch (ClassCastException e) {
			VeeManage.LOGGER.logp(Level.WARNING, Session.class.getName(), "getDoubleArray", "Invalid cast to 'double[]'.", e);
			return defaultValue;
		}
	}

	/**
	 * Returns the float associated with the key.
	 * If no key exists, (float) 0.0 is returned.
	 *
	 * @param key a String, or null
	 * @return a float value
	 */
	public Float getFloat(String key) {
		return getFloat(key, 0.0f);
	}

	/**
	 * Returns the float associated with the key.
	 * If no key exists, defaultValue is returned.
	 *
	 * @param key a String, or null
	 * @param defaultValue Value to return if key does not exist
	 * @return a float value
	 */
	public float getFloat(String key, float defaultValue) {
		Object ret = mVariables.get(key);
		if (ret == null)
			return defaultValue;
		try {
			return (Float) ret;
		} catch (ClassCastException e) {
			VeeManage.LOGGER.logp(Level.WARNING, Session.class.getName(), "getFloat", "Invalid cast to 'float'.", e);
			return defaultValue;
		}
	}

	/**
	 * Returns the float[] associated with the key.
	 * If no key exists, null is returned.
	 *
	 * @param key a String, or null
	 * @return a float[] value, or null
	 */
	public float[] getFloatArray(String key) {
		Object ret = mVariables.get(key);
		if (ret == null)
			return null;
		return (float[]) ret;
	}

	/**
	 * Returns the float[] associated with the key.
	 * If no key exists, defaultValue is returned.
	 *
	 * @param key a String, or null
	 * @param defaultValue Value to return if key does not exist
	 * @return a float[] value, or null
	 */
	public float[] getFloatArray(String key, float[] defaultValue) {
		Object ret = mVariables.get(key);
		if (ret == null)
			return defaultValue;
		try {
			return (float[]) ret;
		} catch (ClassCastException e) {
			VeeManage.LOGGER.logp(Level.WARNING, Session.class.getName(), "getFloatArray", "Invalid cast to 'float[]'.", e);
			return defaultValue;
		}
	}

	/**
	 * Returns the int associated with the key.
	 * If no key exists, (int) 0 is returned.
	 *
	 * @param key a String, or null
	 * @return an int value
	 */
	public int getInteger(String key) {
		return getInt(key, 0);
	}

	/**
	 * Returns the int associated with the key.
	 * If no key exists, defaultValue is returned.
	 *
	 * @param key a String, or null
	 * @param defaultValue Value to return if key does not exist
	 * @return an int value
	 */
	public int getInt(String key, int defaultValue) {
		Object ret = mVariables.get(key);
		if (ret == null)
			return defaultValue;
		try {
			return (Integer) ret;
		} catch (ClassCastException e) {
			VeeManage.LOGGER.logp(Level.WARNING, Session.class.getName(), "getInteger", "Invalid cast to 'int'.", e);
			return defaultValue;
		}
	}

	/**
	 * Returns the int[] associated with the key.
	 * If no key exists, null is returned.
	 *
	 * @param key a String, or null
	 * @return an int[] value, or null
	 */
	public int[] getIntArray(String key) {
		Object ret = mVariables.get(key);
		if (ret == null)
			return null;
		return (int[]) ret;
	}

	/**
	 * Returns the int[] associated with the key.
	 * If no key exists, defaultValue is returned.
	 *
	 * @param key a String, or null
	 * @param defaultValue Value to return if key does not exist
	 * @return an int[] value, or null
	 */
	public int[] getIntArray(String key, int[] defaultValue) {
		Object ret = mVariables.get(key);
		if (ret == null)
			return defaultValue;
		try {
			return (int[]) ret;
		} catch (ClassCastException e) {
			VeeManage.LOGGER.logp(Level.WARNING, Session.class.getName(), "getIntegerArray", "Invalid cast to 'int[]'.", e);
			return defaultValue;
		}
	}

	/**
	 * Returns the ArrayList<Integer> associated with the key.
	 * If no key exists, null is returned.
	 *
	 * @param key a String, or null
	 * @return an ArrayList<Integer> value, or null
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<Integer> getIntArrayList(String key) {
		Object ret = mVariables.get(key);
		if (ret == null)
			return null;
		return (ArrayList<Integer>) ret;
	}

	/**
	 * Returns the ArrayList<Integer> associated with the key.
	 * If no key exists, defaultValue is returned.
	 *
	 * @param key a String, or null
	 * @param defaultValue Value to return if key does not exist
	 * @return an ArrayList<Integer> value, or null
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<Integer> getIntArrayList(String key, ArrayList<Integer> defaultValue) {
		Object ret = mVariables.get(key);
		if (ret == null)
			return defaultValue;
		try {
			return (ArrayList<Integer>) ret;
		} catch (ClassCastException e) {
			VeeManage.LOGGER.logp(Level.WARNING, Session.class.getName(), "getIntegerArrayList", "Invalid cast to 'ArrayList<Integer>'.", e);
			return defaultValue;
		}
	}

	/**
	 * Returns the long associated with the key.
	 * If no key exists, (long) 0 is returned.
	 *
	 * @param key a String, or null
	 * @return a long value
	 */
	public long getLong(String key) {
		return getLong(key, (long) 0);
	}

	/**
	 * Returns the long associated with the key.
	 * If no key exists, defaultValue is returned.
	 *
	 * @param key a String, or null
	 * @param defaultValue Value to return if key does not exist
	 * @return a long value
	 */
	public long getLong(String key, long defaultValue) {
		Object ret = mVariables.get(key);
		if (ret == null)
			return defaultValue;
		try {
			return (Long) ret;
		} catch (ClassCastException e) {
			VeeManage.LOGGER.logp(Level.WARNING, Session.class.getName(), "getLong", "Invalid cast to 'long'.", e);
			return defaultValue;
		}
	}

	/**
	 * Returns the long[] associated with the key.
	 * If no key exists, null is returned.
	 *
	 * @param key a String, or null
	 * @return a long[] value, or null
	 */
	public long[] getLongArray(String key) {
		Object ret = mVariables.get(key);
		if (ret == null)
			return null;
		return (long[]) ret;
	}

	/**
	 * Returns the long[] associated with the key.
	 * If no key exists, defaultValue is returned.
	 *
	 * @param key a String, or null
	 * @param defaultValue Value to return if key does not exist
	 * @return a long[] value, or null
	 */
	public long[] getLongArray(String key, long[] defaultValue) {
		Object ret = mVariables.get(key);
		if (ret == null)
			return defaultValue;
		try {
			return (long[]) ret;
		} catch (ClassCastException e) {
			VeeManage.LOGGER.logp(Level.WARNING, Session.class.getName(), "getLongArray", "Invalid cast to 'long[]'.", e);
			return defaultValue;
		}
	}

	/**
	 * Returns the short associated with the key.
	 * If no key exists, (short) 0 is returned.
	 *
	 * @param key a String, or null
	 * @return a short value
	 */
	public short getShort(String key) {
		return getShort(key, (short) 0);
	}

	/**
	 * Returns the short associated with the key.
	 * If no key exists, defaultValue is returned.
	 *
	 * @param key a String, or null
	 * @param defaultValue Value to return if key does not exist
	 * @return a short value
	 */
	public short getShort(String key, short defaultValue) {
		Object ret = mVariables.get(key);
		if (ret == null)
			return defaultValue;
		try {
			return (Short) ret;
		} catch (ClassCastException e) {
			VeeManage.LOGGER.logp(Level.WARNING, Session.class.getName(), "getShort", "Invalid cast to 'short'.", e);
			return defaultValue;
		}
	}

	/**
	 * Returns the short[] associated with the key.
	 * If no key exists, null is returned.
	 *
	 * @param key a String, or null
	 * @return a short[] value, or null
	 */
	public short[] getShortArray(String key) {
		Object ret = mVariables.get(key);
		if (ret == null)
			return null;
		return (short[]) ret;
	}

	/**
	 * Returns the short[] associated with the key.
	 * If no key exists, defaultValue is returned.
	 *
	 * @param key a String, or null
	 * @param defaultValue Value to return if key does not exist
	 * @return a short[] value, or null
	 */
	public short[] getShortArray(String key, short[] defaultValue) {
		Object ret = mVariables.get(key);
		if (ret == null)
			return defaultValue;
		try {
			return (short[]) ret;
		} catch (ClassCastException e) {
			VeeManage.LOGGER.logp(Level.WARNING, Session.class.getName(), "getShortArray", "Invalid cast to 'short[]'.", e);
			return defaultValue;
		}
	}

	/**
	 * Returns the String associated with the key.
	 * If no key exists, null is returned.
	 *
	 * @param key a String, or null
	 * @return a String value, or null
	 */
	public String getString(String key) {
		Object ret = mVariables.get(key);
		if (ret == null)
			return null;
		return (String) ret;
	}

	/**
	 * Returns the String associated with the key.
	 * If no key exists, defaultValue is returned.
	 *
	 * @param key a String, or null
	 * @param defaultValue Value to return if key does not exist
	 * @return a String value, or null
	 */
	public String getString(String key, String defaultValue) {
		Object ret = mVariables.get(key);
		if (ret == null)
			return defaultValue;
		try {
			return (String) ret;
		} catch (ClassCastException e) {
			VeeManage.LOGGER.logp(Level.WARNING, Session.class.getName(), "getString", "Invalid cast to 'String'.", e);
			return defaultValue;
		}
	}

	/**
	 * Returns the String[] associated with the key.
	 * If no key exists, null is returned.
	 *
	 * @param key a String, or null
	 * @return a String[] value, or null
	 */
	public String[] getStringArray(String key) {
		Object ret = mVariables.get(key);
		if (ret == null)
			return null;
		return (String[]) ret;
	}

	/**
	 * Returns the String[] associated with the key.
	 * If no key exists, defaultValue is returned.
	 *
	 * @param key a String, or null
	 * @param defaultValue Value to return if key does not exist
	 * @return a String[] value, or null
	 */
	public String[] getStringArray(String key, String[] defaultValue) {
		Object ret = mVariables.get(key);
		if (ret == null)
			return defaultValue;
		try {
			return (String[]) ret;
		} catch (ClassCastException e) {
			VeeManage.LOGGER.logp(Level.WARNING, Session.class.getName(), "getStringArray", "Invalid cast to 'String[]'.", e);
			return defaultValue;
		}
	}

	/**
	 * Returns the ArrayList<String> associated with the key.
	 * If no key exists, null is returned.
	 *
	 * @param key a String, or null
	 * @return an ArrayList<String> value, or null
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<String> getStringArrayList(String key) {
		Object ret = mVariables.get(key);
		if (ret == null)
			return null;
		return (ArrayList<String>) ret;
	}

	/**
	 * Returns the ArrayList<String> associated with the key.
	 * If no key exists, defaultValue is returned.
	 *
	 * @param key a String, or null
	 * @param defaultValue Value to return if key does not exist
	 * @return an ArrayList<String> value, or null
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<String> getStringArrayList(String key, ArrayList<String> defaultValue) {
		Object ret = mVariables.get(key);
		if (ret == null)
			return defaultValue;
		try {
			return (ArrayList<String>) ret;
		} catch (ClassCastException e) {
			VeeManage.LOGGER.logp(Level.WARNING, Session.class.getName(), "getStringArrayList", "Invalid cast to 'ArrayList<String>'.", e);
			return defaultValue;
		}
	}

}