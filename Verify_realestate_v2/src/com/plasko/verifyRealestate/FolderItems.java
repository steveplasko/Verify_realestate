package com.plasko.verifyRealestate;

import java.util.HashMap;
import java.util.List;

public class FolderItems {
	public HashMap<String, String> getValidPaperReq() {
		return validPaperReq;
	}
	public void setValidPaperReq(HashMap<String, String> validPaperReq) {
		this.validPaperReq = validPaperReq;
	}
	public HashMap<String, String> getValidOfferReq() {
		return validOfferReq;
	}
	public void setValidOfferReq(HashMap<String, String> validOfferReq) {
		this.validOfferReq = validOfferReq;
	}
	String  folderName;
	boolean paperWorkTest;
	boolean offer_folder;
	boolean paperwork_folder;
	boolean offerTest;
	Integer idNumber;
	private HashMap<String, String> validPaperReq;
	private HashMap<String, String> validOfferReq;
	public String getFolderName() {
		return folderName;
	}
	public void setFolderName(String folderName) {
		this.folderName = folderName;
	}
	public boolean getPaperWorkTestValue() {
		return paperWorkTest;
	}
	public void setPaperWorkTestValue(boolean valid_Check) {
		this.paperWorkTest = valid_Check;
	}
	public boolean getOfferTestValue() {
		return offerTest;
	}
	public void setOfferTestValue(boolean valid_test) {
		this.offerTest = valid_test;
	}
	public Integer getIdNumber() {
		return idNumber;
	}
	public void setIdNumber(Integer idNumber) {
		this.idNumber = idNumber;
	}
	public boolean isOffer_folder() {
		return offer_folder;
	}
	public void setOffer_folder(boolean offer_folder) {
		this.offer_folder = offer_folder;
	}
	public boolean isPaperwork_folder() {
		return paperwork_folder;
	}
	public void setPaperwork_folder(boolean paperwork_folder) {
		this.paperwork_folder = paperwork_folder;
	}
	public FolderItems(){}
	public boolean isPaperWorkTest() {
		return paperWorkTest;
	}
	public void setPaperWorkTest(boolean paperWorkTest) {
		this.paperWorkTest = paperWorkTest;
	}
	public boolean isOfferTest() {
		return offerTest;
	}
	public void setOfferTest(boolean offerTest) {
		this.offerTest = offerTest;
	}
	

}
