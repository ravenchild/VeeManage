package org.uigl.veemanage.webgui;

import java.io.File;
import java.io.IOException;

import org.uigl.webgui.WebGuiHTTPD;
import org.uigl.webgui.WebGuiHTTPPage;

public class VeeManageHTTPD extends WebGuiHTTPD {

	private static final WebGuiHTTPPage[] mPages = new WebGuiHTTPPage[] {
		new org.uigl.veemanage.webgui.pages.Index(),
		new org.uigl.veemanage.webgui.pages.Logout(),
		new org.uigl.veemanage.webgui.pages.Login(),
		new org.uigl.veemanage.webgui.pages.SettingsPage()
	};
	
	@Override
	protected WebGuiHTTPPage[] getPages() {
		return mPages;
	}
	
	public VeeManageHTTPD(int port, File wwwroot) throws IOException {
		super(port, wwwroot);
	}

}
