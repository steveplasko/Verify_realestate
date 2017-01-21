package com.plasko.verifyRealestate;

import com.dropbox.core.*;

import com.dropbox.core.json.JsonReadException;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.ListFolderResult;
import com.dropbox.core.v2.files.Metadata;
import com.dropbox.core.v2.users.FullAccount;
import com.plasko.verifyRealestate.window.VerifyRealestateGrid;

import java.io.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class ProcessVR {
	
	public void getfiles() throws DbxException, IOException {

		final String APP_KEY = "aok671hej8f73og";
		final String APP_SECRET = "6y79qg6k523jd9j";

	

		DbxAppInfo appInfo = new DbxAppInfo(APP_KEY, APP_SECRET);

		DbxRequestConfig config = new DbxRequestConfig("JavaTutorial/1.0",
				Locale.getDefault().toString());
		DbxWebAuthNoRedirect webAuth = new DbxWebAuthNoRedirect(config, appInfo);

		String accessToken = "JmUajWlbEwUAAAAAAAC9LrphgK6SizCnEcL6qFQRm3k8bAr8r2wB8WoLMKd-tHhF";

		DbxClientV2 client = new DbxClientV2(config, accessToken);

		FullAccount account = client.users().getCurrentAccount();
        System.out.println(account.getName().getDisplayName());

		String buyersFolder = "/Real Estate/Buyers";
		String listingsFolder = "/Real Estate/Listings";
		Integer counterAddress = 0;
		Integer completedAddress = 0;
		Integer notCompletedAddress = 0;
		;
		List<FolderItems> folderAddress = new ArrayList<FolderItems>();
		 TypeRules verifyValidation= new TypeRules();
		HashMap<Integer, List<DetailItems>> mapDetailAddress = new HashMap<Integer, List<DetailItems>>();
		try {
			verifyValidation.setContext(client);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		CheckDocument FolderRuleType= new CheckDocument(verifyValidation.getProcessRules());
		
		ListFolderResult Buyer = client.files().listFolder(buyersFolder);
        
            for (Metadata childB : Buyer.getEntries()) {
            	if( "Closed Buyers".equalsIgnoreCase(childB.getName())||"Miscellaneous".equalsIgnoreCase(childB.getName()))
                System.out.println(childB.getPathLower());
            	else{}
            }

           

           
      
		
		
//		DbxEntry.WithChildren Buyer = client
//				.getMetadataWithChildren(buyersFolder);
		System.out.println("Files in the Buyers:");

//		for (Metadata childB : Buyer.getEntries()) {
//
//			if ((!childB.getPathLower())
//					|| "Closed Buyers".equalsIgnoreCase(childB.name)
//					|| "Miscellaneous".equalsIgnoreCase(childB.name)) {
//			} else {
//				
//				 System.out.println(childB.name + ": " + childB.toString());
//				DbxEntry.WithChildren fileListing = client
//						.getMetadataWithChildren(childB.path);
//				
//				for (DbxEntry document : fileListing.children) {
//					
//					if (document.isFolder()){
//						
//					System.out.println(document.name + ": " + document.toString());
//					List<DetailItems> buyDetailitems = new ArrayList<DetailItems>();
//					
//					System.out.println("	" + document.name + ": "
//							+ document.toString());
//					DbxEntry.WithChildren docList = client
//							.getMetadataWithChildren(document.path);
//					for (DbxEntry docFiles : docList.children) {
//						String Foldercheck = docFiles.name;
//						FolderItems buyerFolder = new FolderItems();
//						buyerFolder.setFolderName(document.path);
//						buyerFolder.setIdNumber(counterAddress);
//						buyerFolder.setPaperWorkTestValue(false);
//						buyerFolder.setPaperwork_folder(false);
//						buyerFolder.setOfferTestValue(false);
//						buyerFolder.setOffer_folder(false);
//					
//						if (docFiles.isFolder()) {
//							if (Foldercheck.endsWith(Constants.folderOffer)) {
//								String typeFolder="";
//								buyerFolder.setOffer_folder(true);
//								buyerFolder.setOfferTestValue(true);
//								DbxEntry.WithChildren childFiles = client
//										.getMetadataWithChildren(docFiles.path,
//												true);
//								typeFolder=Constants.typeBuyer_Under_Contract_Vacant_Land;
//								for (DbxEntry addressFiles : childFiles.children) {
//									if (addressFiles.name
//											.contains(Constants.documentRPDS)) {
//										
//										typeFolder=Constants.typeBuyer_Under_Contract;
//									}
//									
//								}
//
//								for (DbxEntry addressFiles : childFiles.children) {
//									DetailItems buyerFiles = new DetailItems();
//									
//										buyerFiles
//												.setType(typeFolder);
//										buyerFiles
//												.setFileExisting(addressFiles.name);
//										DbxEntry.File metaFile = (File) addressFiles;
//										buyerFiles
//												.setDateLastModified(metaFile.lastModified
//														.toString());
//										buyerFiles
//												.setFolderName(addressFiles.path);
//
//										buyerFiles.setTested(false);
//										buyDetailitems.add(buyerFiles);
//										
//									
//								}
//								FolderRuleType.verifyFolder(buyDetailitems, buyerFolder, typeFolder);
//								
//							}
//						}
//						
//					
//						if (!buyDetailitems.isEmpty() && buyerFolder.getOfferTestValue()) {
//								folderAddress.add(buyerFolder);
//								
//							
//							
//							mapDetailAddress
//							.put(counterAddress, buyDetailitems);
//							counterAddress += 1;
//						} 
//					}
//				}
//				}
//				
//				
//				
//				
//				
//			}
//
//		}
//		DbxEntry.WithChildren listing = client
//				.getMetadataWithChildren(listingsFolder);
//
//		System.out.println("Files in the listing:");
//		if (listing != null){
//		for (DbxEntry child : listing.children) {
//			if ((!child.isFolder()) || "Referrals".equalsIgnoreCase(child.name)
//					|| "Old Listings".equalsIgnoreCase(child.name)
//					|| "Future Listings".equalsIgnoreCase(child.name)
//					|| "In House Brochures".equalsIgnoreCase(child.name)) {
//			} else {
//				//
//				String typeFolder="";
//				DbxEntry.WithChildren fileListing = client
//						.getMetadataWithChildren(child.path);
//
//				for (DbxEntry activelist : fileListing.children) {
//					FolderItems listingFolder = new FolderItems();
//					listingFolder.setFolderName(activelist.path);
//					listingFolder.setPaperWorkTestValue(false);
//					listingFolder.setPaperwork_folder(false);
//					listingFolder.setOffer_folder(false);
//					listingFolder.setOfferTestValue(false);
//					listingFolder.setIdNumber(counterAddress);
//					
//					List<DetailItems> listDetailitems = new ArrayList<DetailItems>();
//					DbxEntry.WithChildren docList = client
//							.getMetadataWithChildren(activelist.path);
//					if (!(docList.children == null)){
//					for (DbxEntry docFiles : docList.children) {
//						String Foldercheck = docFiles.name;
//						if (docFiles.isFolder()) {
//							try{
//							
//							if (Foldercheck.contains(Constants.folderPaperwork)) {
//								listingFolder.setPaperwork_folder(true);
//								boolean property = false;
//						
//								DbxEntry.WithChildren childFiles = client
//										.getMetadataWithChildren(docFiles.path,
//												true);
//								for (DbxEntry addressFiles : childFiles.children) {
//									if (addressFiles.name
//											.contains(Constants.documentRPDS)) {
//										property = true;
//										break;
//									}
//								}
//
//									for (DbxEntry addressFiles : childFiles.children) {
//										DetailItems listingFiles = new DetailItems();
//										if (addressFiles.isFile()) {
//											if (property) {
//												listingFiles
//														.setType(Constants.typeListing);
//												 typeFolder=Constants.typeListing;
//												listingFiles
//														.setFileExisting(addressFiles.name);
//												DbxEntry.File metaFile = (File) addressFiles;
//
//												listingFiles
//														.setDateLastModified(metaFile.lastModified
//																.toString());
//												listingFiles
//														.setFolderName(docFiles.path);
//
//												listingFiles.setTested(false);
//												listDetailitems
//														.add(listingFiles);
//											} else {
//												listingFiles
//														.setType(Constants.typeListing_Vacant_Land);
//												 typeFolder=Constants.typeListing_Vacant_Land;
//												listingFiles
//														.setFileExisting(addressFiles.name);
//												DbxEntry.File metaFile = (File) addressFiles;
//
//												listingFiles
//														.setDateLastModified(metaFile.lastModified
//																.toString());
//												listingFiles
//														.setFolderName(addressFiles.path);
//
//												listingFiles.setTested(false);
//												listDetailitems
//														.add(listingFiles);
//											}
//										}
//									}
//
//							} else if (Foldercheck
//									.contains(Constants.folderOffer)) {
//								listingFolder.setOffer_folder(true);
//								boolean property = false;
//								listingFolder.setOfferTestValue(true);
//								
//								DbxEntry.WithChildren childFiles = client
//										.getMetadataWithChildren(docFiles.path,
//												true);
//								for (DbxEntry addressFiles : childFiles.children) {
//									if (addressFiles.name
//											.contains(Constants.documentRPDS)) {
//										property = true;
//										break;
//									}
//								}
//
//								for (DbxEntry addressFiles : childFiles.children) {
//									DetailItems listingFiles = new DetailItems();
//									if (addressFiles.isFile()) {
//										if (property) {
//											listingFiles
//													.setType(Constants.typeListing_Under_Contract);
//											typeFolder=Constants.typeBuyer_Under_Contract;
//											listingFiles
//													.setFileExisting(addressFiles.name);
//											DbxEntry.File metaFile = (File) addressFiles;
//
//											listingFiles
//													.setDateLastModified(metaFile.lastModified
//															.toString());
//											listingFiles
//													.setFolderName(addressFiles.path);
//
//											listingFiles.setTested(false);
//											listDetailitems.add(listingFiles);
//										} else {
//											listingFiles
//													.setType(Constants.typeListing_Vacant_Land_Under_Contract);
//											typeFolder=Constants.typeListing_Vacant_Land_Under_Contract;
//											listingFiles
//													.setFileExisting(addressFiles.name);
//											DbxEntry.File metaFile = (File) addressFiles;
//
//											listingFiles
//													.setDateLastModified(metaFile.lastModified
//															.toString());
//											listingFiles
//													.setFolderName(addressFiles.path);
//
//											listingFiles.setTested(false);
//											listDetailitems.add(listingFiles);
//										}
//									}
//
//								}
//							
//							}
//							}catch (DbxException e){
//								
//								System.out.println(docFiles.path+"   "+e.getMessage());
//							}
//						}
//						
//					}}
//					mapDetailAddress.put(counterAddress,
//							listDetailitems);
//					folderAddress.add(listingFolder);
//					counterAddress += 1;
//					if ((listingFolder.isOffer_folder() || listingFolder
//							.isPaperwork_folder())) {
//						if (listDetailitems.isEmpty()) {
//							System.out.println("Error folder No items "
//									+ activelist.path +" Offer " +listingFolder.isOffer_folder() + " : Paperwork "+ listingFolder
//									.isPaperwork_folder());
//						}
//					} else {
//						System.out
//								.println("Error folder offer or listing missing"
//										+ activelist.path);
//					}
//					FolderRuleType.verifyFolder(listDetailitems, listingFolder, typeFolder);
//				}
//				
//				
//			}
//		}}
	
		CheckDocument verifiedDocuments= new CheckDocument(verifyValidation.getProcessRules(), mapDetailAddress);
		HashMap<Integer, List<DetailItems>> inputDocuments= verifiedDocuments.verify();
		VerifyRealestateGrid runProcess = new VerifyRealestateGrid();
	//	runProcess.runGrid(folderAddress, inputDocuments,client);
	}
}
