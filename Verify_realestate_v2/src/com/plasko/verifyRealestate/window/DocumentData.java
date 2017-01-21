/*
 * (c)Copyright 2004 Gervase Gallant gervasegallant@yahoo.com
 * see license DataGrid.java
 */
package com.plasko.verifyRealestate.window;

import java.util.HashMap;
import java.util.List;

import com.plasko.verifyRealestate.DetailItems;
import com.plasko.verifyRealestate.FolderItems;

/**
 * @author Gervase Gallant gervasegallant@yahoo.com
 * 
 *         data about Phones
 */

public class DocumentData {
	private static DocumentData thisData = null;
	// private HashMap<Integer, List<DetailItems>> data;
	private String[][] data;
	// = {
	//
	// {"1298374","515","5551212","Home"},
	// {"1298374","515","5551213","Business"},
	// {"1298374","515","5551214","Fax"},
	// {"1298374","515","5551215","Children"},
	// {"1298373","915","3331212","Home"},
	// {"1298373","915","3331213","Business"},
	// {"1298373","915","3331214","Fax"},
	// {"1298372","709","4441212","Home"},
	// {"1298372","709","4441213","Business"},
	// {"1298372","709","4441214","Fax"},
	// {"1298377","515","5552222","Home"},
	// {"1298378","515","5551111","Home"},
	// {"1298375","915","5551212","Home"},
	// {"1298376","515","5551212","Home"}
	// };
	private HashMap<Integer, List<DetailItems>> detailAddress;
	private String[] columns = { "Name", "File", "Type", "Date", "Valid" };
	private int[] columnWidths = { 30, 30, 10, 20, 10 };

	public void setData(HashMap<Integer, List<DetailItems>> data) {
		this.detailAddress = data;
	}

	public HashMap<Integer, List<DetailItems>> processData() {
		return this.detailAddress;
	}

	/**
	 * 
	 */
	public DocumentData(HashMap<Integer, List<DetailItems>> detailAddress) {
		super();
		this.detailAddress = detailAddress;
	}

	public static void getInstance(
			HashMap<Integer, List<DetailItems>> detailAddress) {

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

	public String[] find(String key, String type) {
		return (String[]) processData().get(key).toArray();

	}

	protected String[][] getData() {
		// TODO Auto-generated method stub
		return data;
	}

	public String[][] find(String key) {
		int keyNumber = Integer.parseInt(key);

		List<DetailItems> getvalues = detailAddress.get(keyNumber);
		int size = 5;
		int index = getvalues.size();
		data = new String[index][size];

		int countersets = 0;
		if ((getvalues == null) || getvalues.isEmpty()) {
		} else {/*
				 * String folderName; String fileExisting; String type; String
				 * dateLastModified; boolean testedValid; {"Name", "File",
				 * "Type", "Date","Valid"};
				 */
			for (DetailItems temp : getvalues) {
				data[countersets][0] = "" + temp.getFolderName();
				data[countersets][1] = "" + temp.getFileExisting();
				data[countersets][2] = "" + temp.getType();
				data[countersets][3] = "" + temp.getDateLastModified();
				if (temp.isTested()) {

					if (temp.isRequired()) {
						data[countersets][4] = "Pass";
					} else {
						data[countersets][4] = "Optional";
					}
				} else {
					data[countersets][4] = "";
				}

				countersets++;

			}
		}
		return data;
	}

}
