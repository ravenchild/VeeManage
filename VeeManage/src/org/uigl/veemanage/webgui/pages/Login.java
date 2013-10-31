package org.uigl.veemanage.webgui.pages;

import java.io.InputStream;
import java.util.Properties;

import org.uigl.veemanage.settings.SettingsManager;
import org.uigl.webgui.Session;
import org.uigl.webgui.WebGuiHTTPD;
import org.uigl.webgui.WebGuiHTTPPage;
import org.uigl.webgui.templates.Template;

public class Login implements WebGuiHTTPPage {
	
	private static final String TEMPLATE_NAME = "Login.html";
	
	@Override
	public String getPageClassName() {
		return "Login";
	}

	private String pRedirect = "/";
	private String pStatus = WebGuiHTTPD.HTTP_OK;
	private int pFlags = WebGuiHTTPD.FLAG_NONE;
	
	private Session mUserSession = null;
	
	private String mErrorText = null;
	
	@Override
	public boolean hasMatch(String uri, String method) {
		return uri.equals("/login/");
	}

	@Override
	public void init(String uri, String method, Properties headers, Properties params, Properties files, Session userSession) {
		// Reset all page vars.
		pRedirect = "/";
		pStatus = WebGuiHTTPD.HTTP_OK;
		pFlags = WebGuiHTTPD.FLAG_NONE;
		mErrorText = null;
		
		mUserSession = userSession;
		
		if (mUserSession.getBoolean("UserAuthenticated", false)) {
			pRedirect = "/";
			pStatus = WebGuiHTTPD.HTTP_REDIRECT;
			pFlags |= WebGuiHTTPD.FLAG_REDIRECT;
			return;
		}
		
		if (params.getProperty("username") != null
				&& params.getProperty("password") != null) {
			
			
			//TODO: Replace this with DB lookup.
			if (params.getProperty("username").equals("user")
					&& params.getProperty("password").equals("123456")) {
				
				mUserSession.putBoolean("UserAuthenticated", true);
				pRedirect = "/";
				pStatus = WebGuiHTTPD.HTTP_REDIRECT;
				pFlags |= WebGuiHTTPD.FLAG_REDIRECT;
				return;
				
			} else {
				mErrorText = "Invalid Username/Password.";
			}
		}
			
	}

	@Override
	public String getStatus() {
		return pStatus;
	}

	@Override
	public String getMimeType() {
		return WebGuiHTTPD.MIME_HTML;
	}

	@Override
	public Properties getHeaders() {
		return null;
	}

	@Override
	public InputStream getData() {
		Session pageVars = new Session(null);
		pageVars.put("app_name", SettingsManager.DEFAULT_WWW_APP_NAME);
		pageVars.put("title", "Login");
		
		//Handle errors
		pageVars.put("error_text", mErrorText == null ? "" : mErrorText);

		return Template.applyTemplate(TEMPLATE_NAME, pageVars);
	}

	@Override
	public int getFlags() {
		return pFlags;
	}

	@Override
	public String getRedirectLocation() {
		return pRedirect;
	}

}
