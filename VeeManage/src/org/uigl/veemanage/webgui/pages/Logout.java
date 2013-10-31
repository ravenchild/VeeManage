package org.uigl.veemanage.webgui.pages;

import java.io.InputStream;
import java.util.Properties;

import org.uigl.webgui.Session;
import org.uigl.webgui.WebGuiHTTPD;
import org.uigl.webgui.WebGuiHTTPPage;

public class Logout implements WebGuiHTTPPage {
	
	@Override
	public String getPageClassName() {
		return "Logout";
	}
	
	@Override
	public boolean hasMatch(String uri, String method) {
		return uri.equals("/logout/");
	}

	@Override
	public void init(String uri, String method, Properties headers, Properties params, Properties files, Session userSession) {
		userSession.putBoolean("UserAuthenticated", false);
	}

	@Override
	public String getStatus() {
		return WebGuiHTTPD.HTTP_OK;
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
		return null;
	}

	@Override
	public int getFlags() {
		return WebGuiHTTPD.FLAG_REDIRECT | WebGuiHTTPD.FLAG_NO_CACHE | WebGuiHTTPD.FLAG_REMOVE_SESSION;
	}

	@Override
	public String getRedirectLocation() {
		return "/";
	}

}
