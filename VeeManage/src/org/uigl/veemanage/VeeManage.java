package org.uigl.veemanage;

import java.io.File;

import org.uigl.logger.UIGLLog;
import org.uigl.veemanage.settings.SettingsManager;
import org.uigl.veemanage.webgui.VeeManageHTTPD;

import com.almworks.sqlite4java.SQLiteException;

public class VeeManage {

	private static SettingsManager VeeManageSettings;
	
	public static void main(String[] args) {
		System.out.println("VeeManage 0.0.1");
		try {
			VeeManageSettings = SettingsManager.getSettingsFromDB();
		} catch (SQLiteException e1) {
			e1.printStackTrace();
		}
		
		if (SettingsManager.DEFAULT_WWW_SSL) {
			try {
				if (new File("key.store").exists()) {
					System.setProperty("javax.net.ssl.keyStore", new File("key.store").getAbsolutePath());
					System.setProperty("javax.net.ssl.keyStorePassword", "eLKTPWTlFvKz8Aos1dMvtNvlbxsP70PJeQkVdm4Qzzyj8o9hjetWOHwVbdupTng");
					System.setProperty("javax.net.ssl.sessionCacheSize", Integer.toString(SettingsManager.DEFAULT_WWW_MAX_SESSIONS));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		try {
			VeeManageHTTPD httpd = new VeeManageHTTPD( SettingsManager.DEFAULT_WWW_PORT, new File(SettingsManager.DEFAULT_WWW_ROOT).getAbsoluteFile());
			
			if (httpd.isSecure())
				UIGLLog.c(VeeManage.class.getName(), "main", "Using HTTPS on port " + SettingsManager.DEFAULT_WWW_PORT + ".");
			else
				UIGLLog.c(VeeManage.class.getName(), "main", "Using HTTP on port " + SettingsManager.DEFAULT_WWW_PORT + ".");
			
		} catch(Exception e) {
			System.err.println( "Couldn't start server:\n" + e );
			System.exit( -1 );
		}
		

		try { System.in.read(); } catch( Throwable t ) {}
		
	}
	
	public static SettingsManager getSettings() {
		return VeeManageSettings;
	}
	
}
