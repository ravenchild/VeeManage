package org.uigl.webgui;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Properties;

import org.uigl.logger.UIGLLog;

import fi.iki.elonen.NanoHTTPD.NanoHTTPD;

public class WebGuiHTTPD extends NanoHTTPD {

	public static final int FLAG_NONE = 0;
	public static final int FLAG_REDIRECT = 1;
	public static final int FLAG_REMOVE_SESSION = 2;
	public static final int FLAG_FILE = 4;
	public static final int FLAG_NO_CACHE = 8;
	
	private File mRoot;
	private SessionManager mSessions;
	

	
	/**
	 * List the pages for the HTTPD here.
	 * The pages should be stateless classes.
	 * These classes are reused even for different users.
	 * 
	 */
	protected WebGuiHTTPPage[] getPages() {
		return new WebGuiHTTPPage[] {};
	}
	
	/**
	 * Start a new HTTPD.
	 * 
	 * @param port
	 * @param wwwroot
	 * @throws IOException
	 */
	public WebGuiHTTPD(int port, File wwwroot) throws IOException {
		super(port, wwwroot);
		this.mRoot = wwwroot;
		this.mSessions = new SessionManager();
		this.mSessions.setMaxSessions(1000);
	}
	
	@Override
	public Response serve(String uri, String method, Properties header, Properties params, Properties files) {

		UIGLLog.i( WebGuiHTTPD.class.getName(), "serve(String uri, String method, Properties header, Properties params, Properties files)", "URI:" + uri);

		Response page = new Response();
		page.status = HTTP_OK;
		page.mimeType = MIME_HTML;
		cookiesToParams(header, params);
		
		for (WebGuiHTTPPage pageClass : getPages()) {
			UIGLLog.i( WebGuiHTTPD.class.getName(), "serve(String uri, String method, Properties header, Properties params, Properties files)", "Testing Page: " + pageClass.getPageClassName());

			try {
				if (pageClass.hasMatch(uri, method)) {

					UIGLLog.i( WebGuiHTTPD.class.getName(), "serve(String uri, String method, Properties header, Properties params, Properties files)", "Page:" + pageClass.getPageClassName());

					Session userSession = startSession(page, params);
					
					pageClass.init(uri, method, header, params, files, userSession);
					page.status = pageClass.getStatus();
					
					//FIXME: Change these so they don't run twice.
					String mimeTypeString = pageClass.getMimeType();
					if (mimeTypeString != null)
						page.mimeType = mimeTypeString;
					
					Properties headerProperties = pageClass.getHeaders();
					if (headerProperties != null)
						page.header = headerProperties;
					
					InputStream dataStream = pageClass.getData();
					if (dataStream != null)
						page.data = dataStream;
					else
						page.data = new ByteArrayInputStream("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\"><html><head></head><body></body></html>".getBytes());

					if ((pageClass.getFlags() & FLAG_REDIRECT) == FLAG_REDIRECT)
						redirectHeader(page, pageClass.getRedirectLocation());
					if ((pageClass.getFlags() & FLAG_NO_CACHE) == FLAG_NO_CACHE)
						noCacheHeader(page);
					if ((pageClass.getFlags() & FLAG_REMOVE_SESSION) == FLAG_REMOVE_SESSION)
						userSession = removeSession(userSession);
					if ((pageClass.getFlags() & FLAG_FILE) == FLAG_FILE)
						break;
					
					UIGLLog.i( WebGuiHTTPD.class.getName(), "serve(String uri, String method, Properties header, Properties params, Properties files)", "Returned Status: " + page.status);
					
					return page;
				}
			} catch (Exception e) {
				UIGLLog.s( WebGuiHTTPD.class.getName(), "serve(String uri, String method, Properties header, Properties params, Properties files)", "500 Error", e);
			
				page.status = HTTP_INTERNALERROR;
				page.mimeType = MIME_HTML;
				page.data = new ByteArrayInputStream(("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\"><html><head><title>" + HTTP_INTERNALERROR + "</title></head><body><h1>" + HTTP_INTERNALERROR + "</h1><br><h2>Error contents:</h2><br><pre>" + e.getMessage() + "</pre><br><h2>Stack Trace:</h2><br><pre>" + getStackTrace(e) + "</pre></body></html>").getBytes());
				
				return page;
			}
			
		}
		
		UIGLLog.w( WebGuiHTTPD.class.getName(), "serve(String uri, String method, Properties header, Properties params, Properties files)", "Forwarding Response.");

		return super.serveFile(uri, header, this.mRoot, false);
	}
	
	/**
	 * Converts any client cookies into an accessible Properties class.
	 * 
	 * @param header
	 * @param params
	 */
	private static void cookiesToParams(Properties header, Properties params) {
		if (header.containsKey("cookie")) {
			String cookieString = (String) header.get("cookie");
			String[] cookieGroups = cookieString.split("(=|; )");
			for (int i=0; i<cookieGroups.length; i+=2)
				params.put(cookieGroups[i].trim(), cookieGroups[i+1].trim());
		}
	}
	
	/**
	 * Generates a new session or returns an existing session for a given sessionID.
	 * 
	 * @param r
	 * @param params
	 * @return The Session variable.
	 */
	private Session startSession(Response r, Properties params) {
		Session ret = null;
		if (params.containsKey("SessionID"))
			ret = mSessions.findSession((String) params.get("SessionID"));
		if (ret == null)
			ret = mSessions.getNewSession();
		r.addHeader("Set-Cookie", "SessionID=" + ret.getSessionID() + "; Path=/; HttpOnly" + (isSecure() ? "; Secure" : ""));
		return ret;
	}
	
	/**
	 * Creates a redirect header when a page has the REDIRECT flag set.
	 * 
	 * @param res
	 * @param location
	 */
	public static void redirectHeader(Response res, String location) {
		res.status = HTTP_REDIRECT;
		res.mimeType = MIME_HTML;
		res.addHeader("Location", location);
		res.data = new ByteArrayInputStream(("<html><body>Redirecting.<br />Click <a href=\"" + location + "\">Here</a> if you are not automatically redirected.</body></html>").getBytes());
	}
	
	/**
	 * Prevents the response from being cached.
	 *  
	 * @param res
	 */
	public static void noCacheHeader(Response res) {
		res.addHeader("Cache-Control", "no-cache, must-revalidate");
		res.addHeader("Expires", "Sat, 26 Jul 1997 05:00:00 GMT");
	}
	
	/**
	 * Removes a session.
	 * Let purge handle everything unless the user is logging out.
	 * 
	 * @param ses
	 * @return null
	 */
	private Session removeSession(Session ses) {
		ses.clear();
		return null;
	}
	
	/**
	 * Utility function to divert the printing of a stack trace.
	 * Should be moved at some point.
	 * 
	 * @param aThrowable
	 * @return The throwable's stack trace.
	 */
	public static String getStackTrace(Throwable aThrowable) {
		final Writer result = new StringWriter();
		final PrintWriter printWriter = new PrintWriter(result);
		aThrowable.printStackTrace(printWriter);
		return result.toString();
	}
}
