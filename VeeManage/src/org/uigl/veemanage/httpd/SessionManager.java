package org.uigl.veemanage.httpd;

import java.util.HashMap;

public class SessionManager {

	private HashMap<String, Session> mSessions;
	
	public SessionManager() {
		mSessions = new HashMap<String, Session>();
	}
	
	/**
	 * Creates a new Session object and adds it to the HashMap.
	 * The new Session object is returned.
	 * 
	 * @return a Session object
	 */
	public Session getNewSession() {
		String newSessionID = java.util.UUID.randomUUID().toString();
		Session newSession = new Session(newSessionID);
		mSessions.put(newSessionID, newSession);
		return newSession;
	}

	
	/**
	 * Finds an existing Session object in the HashMap.
	 * If it exists, the Session object is returned.
	 * Else, it returns null.
	 * 
	 * @return a Session object, null
	 */
	public Session findSession(String sessionID) {
		return mSessions.get(sessionID);
	}
	
	/**
	 * Removes the session with the associated sessionID.
	 * 
	 * @param sessionID
	 */
	public void removeSession(String sessionID) {
		mSessions.remove(sessionID);
	}
	
}
