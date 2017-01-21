package com.plasko.verifyRealestate;

public class Checklist {
	String  folderName;
	boolean valid_Folder_Check;
	boolean offer_folder;
	boolean paperwork_folder;
	boolean valid_test;
	Integer idNumber;
	
	public String getFolderName() {
		return folderName;
	}
	public void setFolderName(String folderName) {
		this.folderName = folderName;
	}
	public boolean getValid_Check() {
		return valid_Folder_Check;
	}
	public void setValid_Check(boolean valid_Check) {
		this.valid_Folder_Check = valid_Check;
	}
	public boolean getValid_test() {
		return valid_test;
	}
	public void setValid_test(boolean valid_test) {
		this.valid_test = valid_test;
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
	public Checklist(){}

}
