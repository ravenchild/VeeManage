package org.uigl.veemanage.webgui.pages;

import java.io.InputStream;
import java.util.Properties;

import org.uigl.veemanage.settings.SettingsManager;
import org.uigl.webgui.Session;
import org.uigl.webgui.WebGuiHTTPD;
import org.uigl.webgui.WebGuiHTTPPage;
import org.uigl.webgui.templates.Template;

public class Index implements WebGuiHTTPPage {

	private static final String TEMPLATE_NAME = "/org/uigl/veemanage/webgui/templates/Index.html";
	
	@Override
	public String getPageClassName() {
		return "Index";
	}
	
	private String pRedirect = "/login/";
	private String pStatus = WebGuiHTTPD.HTTP_OK;
	private int pFlags = WebGuiHTTPD.FLAG_NONE;
	
	private Session mUserSession = null;
	private String mErrorText = null;
	
	@Override
	public boolean hasMatch(String uri, String method) {
		return uri.equals("/");
	}

	@Override
	public void init(String uri, String method, Properties headers, Properties params, Properties files, Session userSession) {
		// Reset all page vars.
		pRedirect = "/login/";
		pStatus = WebGuiHTTPD.HTTP_OK;
		pFlags = WebGuiHTTPD.FLAG_NONE;
		
		mUserSession = userSession;
		
		if (!mUserSession.getBoolean("UserAuthenticated", false)) {
			pRedirect = "/login/";
			pStatus = WebGuiHTTPD.HTTP_OK;
			pFlags |= WebGuiHTTPD.FLAG_REDIRECT;
			return;
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
		pageVars.put("title", "Index");
		
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
