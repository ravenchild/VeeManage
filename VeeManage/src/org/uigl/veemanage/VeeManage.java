package org.uigl.veemanage;

import java.io.File;
import java.util.logging.Logger;

import org.uigl.veemanage.httpd.VeeManageHTTPD;

import com.almworks.sqlite4java.SQLiteException;

public class VeeManage {

	public static final Logger LOGGER = Logger.getLogger(VeeManage.class.getName());
	private static Settings VeeManageSettings;
	
	public static void main(String[] args) {
		System.out.println("VeeManage 0.0.1");
		try {
			VeeManageSettings = Settings.getSettingsFromDB();
		} catch (SQLiteException e1) {
			e1.printStackTrace();
		}
		
		if (Settings.DEFAULT_WWW_SSL) {
			try {
				if (new File("key.store").exists()) {
					System.setProperty("javax.net.ssl.keyStore", new File("key.store").getAbsolutePath());
					System.setProperty("javax.net.ssl.keyStorePassword", "eLKTPWTlFvKz8Aos1dMvtNvlbxsP70PJeQkVdm4Qzzyj8o9hjetWOHwVbdupTng");
					System.setProperty("javax.net.ssl.sessionCacheSize", "1000");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		try {
			new VeeManageHTTPD( Settings.DEFAULT_WWW_PORT, new File(Settings.DEFAULT_WWW_ROOT).getAbsoluteFile());
		} catch(Exception e) {
			System.err.println( "Couldn't start server:\n" + e );
			System.exit( -1 );
		}
		

		try { System.in.read(); } catch( Throwable t ) {}
		
	}
	
	public static Settings getSettings() {
		return VeeManageSettings;
	}
	
}
