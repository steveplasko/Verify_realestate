package com.plasko.verifyRealestate;

public class DetailItems {
	public boolean isRequired() {
		return required;
	}
	public void setRequired(boolean required) {
		this.required = required;
	}
	String  folderName;
	String fileExisting;
	String type;
	String dateLastModified;
	boolean testedValid;
	boolean required;

	public String getFolderName() {
		return folderName;
	}
	public void setFolderName(String folderName) {
		this.folderName = folderName;
	}
	public String getFileExisting() {
		return fileExisting;
	}
	public void setFileExisting(String fileExisting) {
		this.fileExisting = fileExisting;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public boolean isTested() {
		return testedValid;
	}
	public void setTested(boolean tested) {
		this.testedValid = tested;
	}
	public String getDateLastModified() {
		return dateLastModified;
	}
	public void setDateLastModified(String dateLastModified) {
		this.dateLastModified = dateLastModified;
	}


}
