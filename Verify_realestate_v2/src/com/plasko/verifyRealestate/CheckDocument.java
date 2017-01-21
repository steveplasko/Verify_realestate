package com.plasko.verifyRealestate;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import java.util.Set;

public class CheckDocument {
	private HashMap<String, List<RulesCheck>> verifyRules;
	private HashMap<Integer, List<DetailItems>> mapDetailAddress;

	public CheckDocument(HashMap<String, List<RulesCheck>> verifyRules,
			HashMap<Integer, List<DetailItems>> mapDetailAddress) {
		this.verifyRules = verifyRules;
		this.mapDetailAddress = mapDetailAddress;
	}

	public CheckDocument(HashMap<String, List<RulesCheck>> processRules) {
		this.verifyRules = processRules;
	}

	public HashMap<Integer, List<DetailItems>> verify() {
		HashMap<Integer, List<DetailItems>> newDocuments = new HashMap<Integer, List<DetailItems>>();
		HashMap<String, List<RulesCheck>> testedRules = new HashMap<String, List<RulesCheck>>();
		for (int i = 0; i < mapDetailAddress.size(); i++) {

			List<DetailItems> setDocuments = mapDetailAddress.get(i);
			List<DetailItems> holdDocuments = new ArrayList<DetailItems>();
			List<String> typeofDocuments = new ArrayList<String>();
			List<RulesCheck> holdRules = new ArrayList<RulesCheck>();
			if (!(setDocuments == null)) {
				for (int z = 0; z < setDocuments.size(); z++) {
					boolean testDoc = false;
					boolean testReq = false;
					DetailItems tempDetail = setDocuments.get(z);
					List<RulesCheck> testRules = verifyRules.get(tempDetail.type);
					typeofDocuments.add(tempDetail.type);
					for (int k = 0; k < testRules.size(); k++) {
						RulesCheck tempRule = testRules.get(k);

						if (tempDetail.getFileExisting().contains(tempRule.getName())) {
							testDoc = true;
							if ("X".equalsIgnoreCase(tempRule.getReq())) {
								testReq = true;
								tempRule.setPass(true);
								holdRules.add(tempRule);
							}
						}

					}
					tempDetail.setTested(testDoc);
					tempDetail.setRequired(testReq);
					holdDocuments.add(tempDetail);
				}

			}
			Set mySet2 = new HashSet(typeofDocuments);

			Iterator iterator = mySet2.iterator();

			while (iterator.hasNext()) {

				String ruleKey = (String) iterator.next();
				List<RulesCheck> testRules = verifyRules.get(ruleKey);
				
				for (int k = 0; k < testRules.size(); k++) {
					boolean valid = false;
					boolean req=false;
					RulesCheck tempRule = testRules.get(k);
					String checkRecMaster = tempRule.getName();
					String checkType = tempRule.getType();
					if ("X".equalsIgnoreCase(tempRule.getReq())) {
						 req=true;
						
						for (RulesCheck rulePassed : holdRules) {
							if (checkRecMaster.equals(rulePassed.getName()) && checkType.equals(rulePassed.getType())) {
								valid = true;
								break;
							}
						}

					}
					if (req&&!valid)
					{
						DetailItems tempDetail = new DetailItems();
						tempDetail.setRequired(true);
						tempDetail.setTested(false);
						tempDetail.setType(checkType);
						tempDetail.setFileExisting(checkRecMaster);
						tempDetail.setFolderName("");
						tempDetail.setDateLastModified("");
						holdDocuments.add(tempDetail);	
						
					}
				}

			}

			newDocuments.put(i, holdDocuments);
		}

		return newDocuments;
	}

	public FolderItems verifyFolder(List<DetailItems> newDocuments, FolderItems input, String typeConstant) {
		HashMap<String, String> createverifaction = new HashMap<String, String>();

		List<RulesCheck> testRules = verifyRules.get(typeConstant);
		if (!(testRules == null) && !testRules.isEmpty()) {
			for (int k = 0; k < testRules.size(); k++) {
				RulesCheck tempRule = testRules.get(k);
				if ("X".equalsIgnoreCase(tempRule.getReq())) {
					createverifaction.put(tempRule.getName(), "Fail");
				} else {
					createverifaction.put(tempRule.getName(), "Optional");
				}
				if (!(newDocuments == null)) {
					for (int z = 0; z < newDocuments.size(); z++) {

						DetailItems tempDetail = newDocuments.get(z);

						boolean reqdoc = false;
						if (!tempRule.getReq().isEmpty()) {
							if (tempDetail.getFileExisting().contains(tempRule.getName())) {
								createverifaction.put(tempRule.getName(), "Pass");
							}

						}
					}

				}

			}
		}
		if (typeConstant.contains("Contract")) {
			input.setValidOfferReq(createverifaction);
		} else
			input.setValidPaperReq(createverifaction);
		return input;
	}
}
