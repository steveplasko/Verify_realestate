package com.plasko.verifyRealestate;

public class Constants {
	public final static String folderPaperwork = "Paperwork";
	public final static String folderOffer = "Offer";
	public final static String documentRPDS = "RPDS";
	public final static String typeBuyer_Under_Contract = "Buyer Under Contract";
	public final static String typeBuyer_Under_Contract_Vacant_Land = "Buyer Under Contract Vacant Land";
	public final static String typeListing = "Listing";
	public final static String typeListing_Vacant_Land = "Listing Vacant Land";
	public final static String typeListing_Under_Contract = "Listing Under Contract";
	public final static String typeListing_Vacant_Land_Under_Contract = "Listing Vacant Land Under Contract";

	public final static boolean testTypeName(String testType) {
		boolean result = false;
		switch (testType) {
		case typeBuyer_Under_Contract:
			result = true;
			break;
		case typeBuyer_Under_Contract_Vacant_Land:
			result = true;
			break;
		case typeListing:
			result = true;
			break;
		case typeListing_Under_Contract:
			result = true;
			break;
		case typeListing_Vacant_Land:
			result = true;
			break;
		case typeListing_Vacant_Land_Under_Contract:
			result = true;
			break;
		default:
			break;
		}
		return result;
	}
}
