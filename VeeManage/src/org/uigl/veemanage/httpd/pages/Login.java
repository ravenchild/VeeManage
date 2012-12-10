package org.uigl.veemanage.httpd.pages;

import java.io.InputStream;
import java.util.Properties;

import org.uigl.veemanage.httpd.Session;
import org.uigl.veemanage.httpd.Template;
import org.uigl.veemanage.httpd.VeeManageHTTPD;
import org.uigl.veemanage.httpd.VeeManageHTTPD.VeeManageHTTPPage;

public class Login implements VeeManageHTTPPage {

	private String pRedirect = "/";
	private String pStatus = VeeManageHTTPD.HTTP_OK;
	private int pFlags = VeeManageHTTPD.FLAG_NONE;
	
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
		pStatus = VeeManageHTTPD.HTTP_OK;
		pFlags = VeeManageHTTPD.FLAG_NONE;
		
		mUserSession = userSession;
		
		if (mUserSession.getBoolean("UserAuthenticated", false)) {
			pRedirect = "/";
			pStatus = VeeManageHTTPD.HTTP_REDIRECT;
			pFlags |= VeeManageHTTPD.FLAG_REDIRECT;
			return;
		}
		
		if (params.getProperty("username") != null
				&& params.getProperty("password") != null) {
			
			
			//TODO: Replace this with DB lookup.
			if (params.getProperty("username").equals("user")
					&& params.getProperty("password").equals("123456")) {
				
				mUserSession.putBoolean("UserAuthenticated", true);
				pRedirect = "/";
				pStatus = VeeManageHTTPD.HTTP_REDIRECT;
				pFlags |= VeeManageHTTPD.FLAG_REDIRECT;
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
		return VeeManageHTTPD.MIME_HTML;
	}

	@Override
	public Properties getHeaders() {
		return null;
	}

	@Override
	public InputStream getData() {
		Session pageVars = new Session(null);
		pageVars.put("title", "Login");
		
		//Handle errors
		pageVars.put("error_text", mErrorText == null ? "" : mErrorText);
		
		return Template.applyTemplate("/org/uigl/veemanage/httpd/templates/Login.html", pageVars);
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
