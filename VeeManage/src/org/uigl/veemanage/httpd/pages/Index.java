package org.uigl.veemanage.httpd.pages;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Properties;

import org.uigl.veemanage.httpd.Session;
import org.uigl.veemanage.httpd.VeeManageHTTPD;
import org.uigl.veemanage.httpd.VeeManageHTTPD.VeeManageHTTPPage;

public class Index implements VeeManageHTTPPage {

	private String pRedirect = "/login/";
	private String pStatus = VeeManageHTTPD.HTTP_OK;
	private int pFlags = VeeManageHTTPD.FLAG_NONE;
	
	private Session mUserSession = null;
	
	@Override
	public boolean hasMatch(String uri, String method) {
		return uri.equals("/");
	}

	@Override
	public void init(String uri, String method, Properties headers, Properties params, Properties files, Session userSession) {
		// Reset all page vars.
		pRedirect = "/login/";
		pStatus = VeeManageHTTPD.HTTP_OK;
		pFlags = VeeManageHTTPD.FLAG_NONE;
		
		mUserSession = userSession;
		
		if (!mUserSession.getBoolean("UserAuthenticated", false)) {
			pRedirect = "/login/";
			pStatus = VeeManageHTTPD.HTTP_OK;
			pFlags |= VeeManageHTTPD.FLAG_REDIRECT;
			return;
		}
			
	}

	@Override
	public String getStatus() {
		return pStatus;
	}

	@Override
	public String getMimeType() {
		return VeeManageHTTPD.MIME_HTML;
	}

	@Override
	public Properties getHeaders() {
		return null;
	}

	@Override
	public InputStream getData() {
		StringBuilder data = new StringBuilder();
		data.append("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\"><html><head><title>VeeManage Index</title></head><body><h1>Index</h1></body></html>");
		return new ByteArrayInputStream(data.toString().getBytes());
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
