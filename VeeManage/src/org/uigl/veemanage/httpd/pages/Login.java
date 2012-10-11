package org.uigl.veemanage.httpd.pages;

import java.io.InputStream;
import java.util.Properties;

import org.uigl.veemanage.httpd.Session;
import org.uigl.veemanage.httpd.VeeManageHTTPD.VeeManageHTTPPage;

public class Login implements VeeManageHTTPPage {

	private Properties mNewHeaders;
	private Session mUserSession;
	
	@Override
	public boolean hasMatch(String uri, String method) {
		return uri.trim().equals('/');
	}

	@Override
	public void init(String uri, String method, Properties oldHeaders, Properties params, Properties files, Session userSession, Properties newHeaders) {
		this.mNewHeaders = newHeaders;
		this.mUserSession = userSession;
	}

	@Override
	public String getStatus() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getMimeType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Properties getHeaders() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InputStream getData() {
		// TODO Auto-generated method stub
		return null;
	}

}
