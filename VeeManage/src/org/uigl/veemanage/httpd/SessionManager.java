package org.uigl.veemanage.httpd;

import java.util.HashMap;
import java.util.Stack;

public class SessionManager {

	private HashMap<String, Session> mSessions;
	private Stack<Session> mSessionPurge;
	private int mMaxSessions = 10000;
	private boolean mCanPurge = true;

	public SessionManager() {
		mSessions = new HashMap<String, Session>();
		mSessionPurge = new Stack<Session>();
	}
	
	/**
	 * Sets the maximum number of sessions before purging old sessions.
	 * Sessions are generally small so this number can be large.
	 * 
	 * @param maxSessions
	 */
	public void setMaxSessions(int maxSessions) {
		this.mMaxSessions = maxSessions;
		mSessionPurge.ensureCapacity(maxSessions + 1);
	}
	
	/**
	 * Creates a new Session object and adds it to the HashMap.
	 * The new Session object is returned.
	 * 
	 * @return a Session object
	 */
	public synchronized Session getNewSession() {
		String newSessionID = java.util.UUID.randomUUID().toString();
		Session newSession = new Session(newSessionID);
		newSession.setReturnToTop(true);
		mSessions.put(newSessionID, newSession);
		mSessionPurge.push(newSession);
		
		if (mSessionPurge.size() > mMaxSessions && mCanPurge) {
			mCanPurge = false;
			new Thread(new purgeSessionsThread()).run();
		}
		
		return newSession;
	}
	
	/**
	 * Session purging occurs when the stack/hashmap are full.
	 * Any non-recently used sessions are discarded.
	 * 
	 * @author Eric Roth
	 *
	 */
	private class purgeSessionsThread implements Runnable {

		@Override
		public void run() {
			synchronized (mSessionPurge) {
				Session baseSession = mSessionPurge.peek();
				while (baseSession.getReturnToTop() == false) {
					baseSession.close();
					mSessions.remove(baseSession.getSessionID());
					baseSession = mSessionPurge.pop();
				}
				do {
					Session currentSession = mSessionPurge.pop();
					if (currentSession.getReturnToTop()) {
						mSessionPurge.push(currentSession);
						currentSession.setReturnToTop(false);
					} else {
						currentSession.close();
						mSessions.remove(currentSession.getSessionID());
					}
				} while (baseSession != mSessionPurge.peek());
			}

			mCanPurge = true;
		}
	}

	
	/**
	 * Finds an existing Session object in the HashMap.
	 * If it exists, the Session object is returned.
	 * Else, it returns null.
	 * 
	 * @return a Session object, null
	 */
	public Session findSession(String sessionID) {
		Session retSession = mSessions.get(sessionID);
		if (retSession != null)
			retSession.setReturnToTop(true);
		return retSession;
	}
	
}
