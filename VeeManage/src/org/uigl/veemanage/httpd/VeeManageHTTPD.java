package org.uigl.veemanage.httpd;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import fi.iki.elonen.NanoHTTPD.NanoHTTPD;

public class VeeManageHTTPD extends NanoHTTPD {
	
	public interface VeeManageHTTPPage {
		public boolean hasMatch(String uri, String method);
		public void init(String uri, String method, Properties oldHeaders, Properties params, Properties files, Session userSession, Properties newHeaders);
		public String getStatus();
		public String getMimeType();
		public Properties getHeaders();
		public InputStream getData();
	}
	
	private File mRoot;
	private SessionManager mSessions;
	
	public VeeManageHTTPD(int port, File wwwroot) throws IOException {
		super(port, wwwroot);
		this.mRoot = wwwroot;
		this.mSessions = new SessionManager();
	}
	
	@Override
	public Response serve(String uri, String method, Properties header, Properties params, Properties files) {
		
		if (uri.equals("/")) {
			cookiesToParams(header, params);
			Response index = new Response();
			Session userSession = startSession(index, params);
			
			if ( params.getProperty("clear", "").equals("true") ) {
				userSession = removeSession(userSession);
				redirectHeader(index, "/");
			} else if (userSession.getBoolean("newSession", true)) {
				index.status = HTTP_OK;
				index.mimeType = MIME_HTML;
				index.data = new ByteArrayInputStream("<html><body>New Session</body></html>".getBytes());
				userSession.putBoolean("newSession", false);
			} else {
				index.status = HTTP_OK;
				index.mimeType = MIME_HTML;
				index.data = new ByteArrayInputStream("<html><body>Old Stinky Session <br /> <form method=\"POST\"><input type=\"hidden\" name=\"clear\" value=\"true\" /><input type=\"submit\" name=\"clearSubmit\" value=\"Clear Session\" /></form></body></html>".getBytes());
			}
			return index;
		}
			
		return super.serveFile(uri, header, this.mRoot, false);
	}
	
	private static void cookiesToParams(Properties header, Properties params) {
		if (header.containsKey("cookie")) {
			String cookieString = (String) header.get("cookie");
			String[] cookieGroups = cookieString.split("(=|; )");
			for (int i=0; i<cookieGroups.length; i+=2)
				params.put(cookieGroups[i].trim(), cookieGroups[i+1].trim());
		}
	}
	
	private Session startSession(Response r, Properties params) {
		Session ret = null;
		if (params.containsKey("SessionID"))
			ret = mSessions.findSession((String) params.get("SessionID"));
		if (ret == null)
			ret = mSessions.getNewSession();
		r.addHeader("Set-Cookie", "SessionID=" + ret.getSessionID());
		return ret;
	}
	
	public static void redirectHeader(Response res, String location) {
		res.status = HTTP_REDIRECT;
		res.mimeType = MIME_HTML;
		res.addHeader("Location", "/");
		res.data = new ByteArrayInputStream("<html><body>Redirecting.<br />Click <a href=\"location\">Here</a> if you are not automatically redirected.</body></html>".getBytes());
	}
	
	private Session removeSession(Session ses) {
		mSessions.removeSession(ses.getSessionID());
		return null;
	}
}
