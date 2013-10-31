package org.uigl.webgui;

import java.io.InputStream;
import java.util.Properties;

/**
 * Interface for all pages available for the HTTPD.
 * Examples are located in <b>org.uigl.veemanage.webgui.pages.
 * 
 * @author Eric Roth
 *
 */
public interface WebGuiHTTPPage  {
	public boolean hasMatch(String uri, String method);
	public void init(String uri, String method, Properties headers, Properties params, Properties files, Session userSession);
	public String getStatus();
	public String getMimeType();
	public Properties getHeaders();
	public InputStream getData();
	public int getFlags();
	public String getRedirectLocation();
	public String getPageClassName();
}