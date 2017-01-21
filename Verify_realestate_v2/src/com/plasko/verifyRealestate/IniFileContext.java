package com.plasko.verifyRealestate;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.DownloadBuilder;

public class IniFileContext {

	public static final List<RulesCheck> setContext(DbxClientV2 client)
			throws Exception {
		File tempINI = File.createTempFile("TemporaryVR", ".ini");
		FileOutputStream outputStream = new FileOutputStream(tempINI);
		List<RulesCheck> ruleCheck = new ArrayList<RulesCheck>();
		try {
			DownloadBuilder downloadedFile = client.files().downloadBuilder("/Real Estate/Steve/vr.ini");
			downloadedFile.download(outputStream);
			System.out.println("Metadata: " + downloadedFile.toString());
		} finally {
			outputStream.close();

		}
		System.out.println("Metadata: " + tempINI.getAbsolutePath());
		BufferedReader br = null;

		String sCurrentLine;

		br = new BufferedReader(new FileReader(tempINI.getAbsolutePath()));

		while ((sCurrentLine = br.readLine()) != null) {
			String[] outrecords = sCurrentLine.split(",");
			if (!sCurrentLine.isEmpty()) {
				System.out.println(sCurrentLine);

				RulesCheck newRecord = new RulesCheck();
				newRecord.setName(outrecords[1]);
				newRecord.setType(outrecords[0]);
				newRecord.setReq(outrecords[2]);
				if (outrecords.length == 4)
				{
				newRecord.setOpt(outrecords[3]);
				}
				else{newRecord.setOpt("");}
					
				ruleCheck.add(newRecord);
			} else {
				System.out.println("Invaled rule: " + sCurrentLine);
			}
		}
		tempINI.delete();
		br.close();
		return ruleCheck;
	}
}
