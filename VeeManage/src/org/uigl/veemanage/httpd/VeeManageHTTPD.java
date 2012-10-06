package org.uigl.veemanage.httpd;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import fi.iki.elonen.NanoHTTPD.NanoHTTPD;

public class VeeManageHTTPD extends NanoHTTPD {

	public static final String WWW_ROOT = "wwwroot";
	public static final int WWW_PORT = 8080;
	
	private File mRoot;
	
	public VeeManageHTTPD(int port, File wwwroot) throws IOException {
		super(WWW_PORT, wwwroot);
		this.mRoot = wwwroot;
	}
	
	@Override
	public Response serve(String uri, String method, Properties header, Properties parms, Properties files) {
		
		if (uri.equals("/")) {
			return new Response(HTTP_OK, MIME_HTML, "<html><body>Index</body></html>");
		}
			
		return super.serveFile(uri, header, this.mRoot, false);
	}

}
