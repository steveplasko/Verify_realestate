package com.plasko.verifyRealestate.window;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.plasko.verifyRealestate.FolderItems;


public class FolderData {
	public static String newline = System.getProperty("line.separator");
	// private String[][] data = {
	// {"1298374","Gervase", "A", "Gallant", "1222 Westtown Rd", "Des Moines",
	// "IA","50311", "US" },
	// {"1298373","Garry", "Q", "Purcell", "123 Hull Blvd", "Aylmer",
	// "QC","Q7COH9","CA"},
	// {"1298372","Harvey","","Hines", "12 Hospital Rd", "Stephenville Xing",
	// "NL","AON2C0", "CA"} ,
	// {"1298375","Smith","B","Robert", "1233 64th St West", "Sacracmento",
	// "CA","95211", "US"},
	// {"1298376","Wagon","B","Poincare", "1305 W 64th", "Des Moines",
	// "IA","50322", "US"},
	// {"1298378","Henry","","Greene", "12 Butterpot Rd", "Holyrood",
	// "NL","AON2C0", "CA"} ,
	// {"1298377","Thomas","","Greene", "34 O'Neil Ave", "St John's",
	// "NL","A1C5G4", "CA"}
	// };
	private List<FolderItems> folderAddress;
	private int[] columnWidths = { 5, 75, 75, 75 };

	private String[] columns = { "Id", "FolderName", "Paperwork Test",
			"Offer Test" };

	boolean paperWorkTest;

	boolean offerTest;

	/**
	 * Constructor
	 */
	public FolderData() {

	}

	public void getInstance(List<FolderItems> folderAddress) {
		this.folderAddress = folderAddress;

	}

	public int getColumnWidth(int i) {

		return getColumnWidths()[i];
	}

	public String[] getRow(String key) {

		for (int i = 0; i < getData().length; i++) {
			if (getData()[i][0].equals(key)) {
				return getData()[i];
			}

		}
		return null;
	}

	public int getRowCount() {
		return getData().length;

	}

	public int getColumnCount() {
		return getData()[0].length;
	}

	public String getColumn(int i) {
		return getColumns()[i];

	}

	public String getData(int row, int column) {
		return getData()[column][row];

	}

	public String[] getRow(int row) {
		return getData()[row];
	}

	public String[] getHeaders() {
		return getColumns();
	}

	/**
	 * @return
	 */
	public String[] getColumns() {
		return columns;
	}

	/**
	 * @return
	 */
	public int[] getColumnWidths() {
		return columnWidths;
	}

	/**
	 * @return
	 */
	public String[][] getData() {
		int size = folderAddress.size();
		int index = folderAddress.size();
		String[][] returnData = new String[index][size];

		int countersets = 0;

		for (FolderItems temp : folderAddress) {
			returnData[countersets][0] = "" + temp.getIdNumber();
			returnData[countersets][1] = temp.getFolderName();
			if (temp.isPaperwork_folder()) {
				
				returnData[countersets][2] = findPass(temp.getValidPaperReq());
			} else {
				returnData[countersets][2] = "";
			}

			if (temp.isOffer_folder()) {
				
				returnData[countersets][3] = findPass(temp.getValidOfferReq());
			} else {
				returnData[countersets][3] = "";
			}
			
			countersets++;
		}
		return returnData;
	}

	public String[] find(String key) {

		return null;

	}

	private String findPass(HashMap<String, String> temp) {
		String returndata = "Fail";

		if ((!(temp == null) && !(temp.isEmpty())) && !(temp.containsValue("Fail"))) {
			returndata = "Pass";
		} else {
			if (!(temp==null)) {
			Set<String> keysHash = temp.keySet();
			String results = "Fail  "+newline;
			
				for (String keySet : keysHash) {
					
					if ("Fail".equalsIgnoreCase(temp.get(keySet))) {
						results=results+keySet+   "  " + newline;
					}
				}
				return results;
			}
		}
		return returndata;
	}
}
