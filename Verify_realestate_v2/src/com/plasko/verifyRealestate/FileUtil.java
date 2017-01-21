package com.plasko.verifyRealestate;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.dropbox.core.DbxException;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.FileMetadata;
import com.dropbox.core.v2.files.UploadErrorException;



public class FileUtil {
	public boolean upload(String destination, File inputFile, DbxClientV2 client) {
		boolean testUpload = false;

		FileInputStream inputStream = null;
		try {
			inputStream = new FileInputStream(inputFile);
		} catch (FileNotFoundException e) {
			System.out.println(destination+"   "+e.getMessage());
		}
		try {
			FileMetadata metadata = client.files().uploadBuilder(destination)
			        .uploadAndFinish(inputStream);
			
		
			System.out.println("Uploaded: " + metadata.getPathLower().toString());
		}catch (IOException e) {
			System.out.println(destination+"   "+e.getMessage());
		} catch (UploadErrorException e) {
			// TODO Auto-generated catch block
			System.out.println(destination+"   "+e.getMessage());
		} catch (DbxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				inputStream.close();
			} catch (IOException e) {
			
				System.out.println(destination+"   "+e.getMessage());
			}
		}

		return testUpload;
	}

}
