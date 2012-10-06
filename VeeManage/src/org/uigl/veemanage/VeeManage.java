package org.uigl.veemanage;

import java.io.File;
import org.uigl.veemanage.httpd.VeeManageHTTPD;

public class VeeManage {

	public static void main(String[] args) {
		System.out.println("VeeManage 0.0.1");
		
		try {
			new VeeManageHTTPD( VeeManageHTTPD.WWW_PORT, new File(VeeManageHTTPD.WWW_ROOT).getAbsoluteFile());
		} catch(Exception e) {
			System.err.println( "Couldn't start server:\n" + e );
			System.exit( -1 );
		}
		

		try { System.in.read(); } catch( Throwable t ) {}
		
	}
	
}
