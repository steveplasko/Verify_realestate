/*
 * (c)Copyright 2004 Gervase Gallant gervasegallant@yahoo.com
 * see license DataGrid.java
 */
package com.plasko.verifyRealestate.window;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import com.plasko.verifyRealestate.DetailItems;
import com.plasko.verifyRealestate.FolderItems;
import com.plasko.verifyRealestate.RulesCheck;

/**
 * @author Gervase Gallant gervasegallant@yahoo.com
 * 
 *         data about Phones
 */

public class RulesData {
	private static RulesData thisData = null;
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
	private HashMap<String, List<RulesCheck>> rulesCheck;
	private String[] columns = { "Name", "File Name Contains", "Req", "Optional" };
	private int[] columnWidths = { 30, 30, 10, 20 };

	public void setData(HashMap<String, List<RulesCheck>> data) {
		this.rulesCheck = data;
	}

	public HashMap<String, List<RulesCheck>> processData() {
		return this.rulesCheck;
	}

	/**
	 * 
	 */
	public RulesData(HashMap<String, List<RulesCheck>> rulesCheck) {
		super();
		this.rulesCheck = rulesCheck;
	}

	public static void getInstance(HashMap<Integer, List<DetailItems>> detailAddress) {

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
		List<String> keySet = new ArrayList<>(rulesCheck.keySet());
		int size =4;
		int index = 0;
		for (String keyValue : keySet) {
			for (RulesCheck temp : rulesCheck.get(keyValue)) {
				++index;
			}
		}
		
		data = new String[index][size];
		int countersets = 0;
		for (String keyValue : keySet) {
			if ((rulesCheck.get(keyValue) == null) || (keyValue == null)) {
			} else {/*
					 * String folderName; String fileExisting; String type;
					 * String dateLastModified; boolean testedValid; {"Name",
					 * "File", "Type", "Date","Valid"};
					 */
				for (RulesCheck temp : rulesCheck.get(keyValue)) {
					data[countersets][0] = keyValue;
					data[countersets][1] = "" + temp.getName();
					data[countersets][2] = "" + temp.getReq();
					data[countersets][3] = "" + temp.getOpt();
			
					countersets++;

				}
			}
		}
		return data;
	}

	 public String[][] find(String key) {

		
		int size =4;
		int index = 0;	
		
			for (RulesCheck temp : rulesCheck.get(key)) {
				++index;
			
		}
		
		data = new String[index][size];
		int countersets = 0;
		
			if ((rulesCheck.get(key) == null) || (key == null)) {
			} else {/*
					 * String folderName; String fileExisting; String type;
					 * String dateLastModified; boolean testedValid; {"Name",
					 * "File", "Type", "Date","Valid"};
					 */
				for (RulesCheck temp : rulesCheck.get(key)) {
					data[countersets][0] = key;
					data[countersets][1] = "" + temp.getName();
					data[countersets][2] = "" + temp.getReq();
					data[countersets][3] = "" + temp.getOpt();
			
					countersets++;

				}
			}
	
		return data;
	}

}
