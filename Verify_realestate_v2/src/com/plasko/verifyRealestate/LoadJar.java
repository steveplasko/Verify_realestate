package com.plasko.verifyRealestate;



import java.io.File;




import com.dropbox.core.DbxRequestConfig;

import com.dropbox.core.v2.DbxClientV2;

public class LoadJar {

	public static void main(String[] args) {
		
		
	

	

		
	
		@SuppressWarnings("deprecation")
		DbxRequestConfig config = new DbxRequestConfig("dropbox/java-tutorial", "en_US");


		String accessToken = "JmUajWlbEwUAAAAAAAC9LrphgK6SizCnEcL6qFQRm3k8bAr8r2wB8WoLMKd-tHhF";

		DbxClientV2 client = new DbxClientV2(config, accessToken);
		FileUtil place = new FileUtil();
		File inputfile = new File("G:\\VerifyRealestate.zip");
		place.upload("/Real Estate/Steve/2016_May_05.zip", inputfile, client);

	}

}
