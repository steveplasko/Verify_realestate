package com.plasko.verifyRealestate;

import java.io.IOException;

import com.dropbox.core.DbxException;

public class StartProgram {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		ProcessVR process = new ProcessVR();
		try {
			process.getfiles();
		} catch ( DbxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
