package com.plasko.verifyRealestate;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.dropbox.core.DbxDownloader;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.DownloadBuilder;
import com.dropbox.core.v2.files.FileMetadata;


public class TypeRules {
	public HashMap<String, List<RulesCheck>> getProcessRules() {
		return processRules;
	}

	public void setProcessRules(HashMap<String, List<RulesCheck>> processRules) {
		this.processRules = processRules;
	}

	private HashMap<String, List<RulesCheck>> processRules;

	public void setContext(DbxClientV2 client) throws Exception {
		HashMap<String, List<RulesCheck>> masterRules = new HashMap<String, List<RulesCheck>>();
		File tempINI = File.createTempFile("TemporaryVR", ".ini");
		FileOutputStream outputStream = new FileOutputStream(tempINI);
		List<RulesCheck> typeBuyer_Under_Contract = new ArrayList<RulesCheck>();
		List<RulesCheck> typeBuyer_Under_Contract_Vacant_Land = new ArrayList<RulesCheck>();
		List<RulesCheck> typeListing = new ArrayList<RulesCheck>();
		List<RulesCheck> typeListing_Vacant_Land = new ArrayList<RulesCheck>();
		List<RulesCheck> typeListing_Under_Contract = new ArrayList<RulesCheck>();
		List<RulesCheck> typeListing_Vacant_Land_Under_Contract = new ArrayList<RulesCheck>();
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
			if (!sCurrentLine.isEmpty()
					&& Constants.testTypeName(outrecords[0])) {
				//System.out.println(sCurrentLine);

				RulesCheck newRecord = new RulesCheck();
				newRecord.setName(outrecords[1]);
				newRecord.setType(outrecords[0]);
				newRecord.setReq(outrecords[2]);
				if (outrecords.length == 4) {
					newRecord.setOpt(outrecords[3]);
				} else {
					newRecord.setOpt("");
				}
				switch (outrecords[0]) {
				case Constants.typeBuyer_Under_Contract:
					typeBuyer_Under_Contract.add(newRecord);
					break;
				case Constants.typeBuyer_Under_Contract_Vacant_Land:
					typeBuyer_Under_Contract_Vacant_Land.add(newRecord);
					break;
				case Constants.typeListing:
					typeListing.add(newRecord);
					break;
				case Constants.typeListing_Under_Contract:
					typeListing_Under_Contract.add(newRecord);
					break;
				case Constants.typeListing_Vacant_Land:
					typeListing_Vacant_Land.add(newRecord);
					break;
				case Constants.typeListing_Vacant_Land_Under_Contract:
					typeListing_Vacant_Land_Under_Contract.add(newRecord);
					break;
				default:
					break;
				}

			} else {
				if (!sCurrentLine.isEmpty()) {
					System.out.println("Invalid rule: " + sCurrentLine);
				}
			}
		}
		masterRules.put(Constants.typeListing_Vacant_Land_Under_Contract,
				typeListing_Vacant_Land_Under_Contract);
		masterRules.put(Constants.typeBuyer_Under_Contract,
				typeBuyer_Under_Contract);
		masterRules.put(Constants.typeBuyer_Under_Contract_Vacant_Land,
				typeBuyer_Under_Contract_Vacant_Land);
		masterRules.put(Constants.typeListing, typeListing);
		masterRules.put(Constants.typeListing_Under_Contract,
				typeListing_Under_Contract);
		masterRules.put(Constants.typeListing_Vacant_Land,
				typeListing_Vacant_Land);
		tempINI.delete();
		br.close();
		processRules = masterRules;
	}

}
