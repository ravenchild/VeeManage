package org.uigl.webgui;

import java.util.HashMap;

import org.uigl.logger.UIGLLog;

public class SessionManager {

	private class PurgeList {
		
		private class PurgeItem {
			PurgeItem next;
			Session session;
			PurgeItem prev;
			
			public PurgeItem(Session session, PurgeItem mLast) {
				this.next = null;
				this.session = session;
				this.prev = mLast;
			}
		}
		
		private PurgeItem mFirst = null;
		private PurgeItem mLast = null;
		private int mSize = 0;
		
		private PurgeItem getOrMendNext(PurgeItem item) {
			if (item == null)
				return null;
			if (item.session == null) {
				mSize--;
				PurgeItem replace = getOrMendNext(item.next);
				replace.prev = item.prev;
				if (item.prev != null)
					item.prev.next = replace;

				if (mFirst == item)
					mFirst = replace;
				if (mLast == item)
					mLast = item.prev;
				
				return replace;
			}
			
			return item;
		}
		
		private void removeAndMend(PurgeItem item) {
			PurgeItem nextItem = getOrMendNext(item.next);
			PurgeItem previousItem = item.prev;
			if (nextItem != null)
				nextItem.prev = previousItem;
			if (previousItem != null)
				previousItem.next = nextItem;
			
			if (mFirst == item)
				mFirst = nextItem;
			if (mLast == item)
				mLast = item.prev;
			
			mSize--;
		}
		
		public int doPurge() {
			int purged = 0;
			PurgeItem current = mFirst;
			while (getOrMendNext(current) != null) {
				if (current.session.getReturnToTop()) {
					current.session.setReturnToTop(false);
				}
				else {
					purged++;
					removeAndMend(current);
				}
				current = current.next;
			}
			return purged;
		}
		
		public Session addPurgeSession(Session item) {
			mSize++;
			if (mLast != null)
				return (mLast = mLast.next = new PurgeItem(item, mLast)).session;
			else
				return (mFirst = mLast = new PurgeItem(item, null)).session;
		}
		
		public int size() {
			return mSize;
		}
	}
	
	private HashMap<String, Session> mSessions;
	private PurgeList mSessionPurge;
	private int mMaxSessions = 10000;
	private boolean mCanPurge = true;
	
	public SessionManager() {
		mSessions = new HashMap<String, Session>();
		mSessionPurge = new PurgeList();
	}
	
	/**
	 * Sets the maximum number of sessions before purging old sessions.
	 * Sessions are generally small so this number can be large.
	 * 
	 * @param maxSessions
	 */
	public void setMaxSessions(int maxSessions) {
		this.mMaxSessions = maxSessions;
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
		mSessionPurge.addPurgeSession(newSession);
		
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
			int purged = mSessionPurge.doPurge();
			mCanPurge = true;
			
			UIGLLog.i( purgeSessionsThread.class.getName(), "run()", "Purged " + String.valueOf(purged) + " sessions.");
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
