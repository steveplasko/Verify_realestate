/*
 * (c)Copyright 2004 Gervase Gallant gervasegallant@yahoo.com
 * see license DataGrid.java
 */
package com.plasko.verifyRealestate.window;

/**
 * @author Gervase Gallant	gervasegallant@yahoo.com
 *
 * methods for all data provider sub classes.
 */
public abstract class AbstractData {
	

	
	public AbstractData() {
		super();
	}
	
	public int getColumnWidth(int i){
		
		return getColumnWidths()[i];
	}
	
	
	public String[] getRow(String key){
	
		for (int i = 0 ; i < getData().length; i ++){
			if (getData()[i][0].equals(key)){
				return getData()[i];	
			}		
		
		}				
		return null;
	}

	public int getRowCount(){
		return getData().length;
		
	}
	public int getColumnCount(){
		return getData()[0].length;	
	}

	public String getColumn(int i){
		return getColumns()[i];
	
	}

	public String getData(int row, int column){
		return getData()[column][row];
	
	}
	public String[] getRow(int row){
		return getData()[row];
	}

	public String[] getHeaders(){
		return getColumns();	
	}
	

		
	
	protected abstract String[] getColumns();
	protected abstract String[][] getData();
	protected abstract int[] getColumnWidths();
	
	
}
