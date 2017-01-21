/*
 * (c)Copyright 2004 Gervase Gallant gervasegallant@yahoo.com
 * see license DataGrid.java
 */
package com.plasko.verifyRealestate.window;


import java.util.HashMap;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;

import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.Rectangle;

import org.eclipse.swt.widgets.Button;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;

import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;

import com.dropbox.core.v2.DbxClientV2;
import com.plasko.verifyRealestate.DetailItems;
import com.plasko.verifyRealestate.FileUtil;

import java.util.Date;


/**
 * @author Gervase Gallant gervasegallant@yahoo.com
 *
 * 
 * 
 */
public class EditDialog extends Dialog {
	private String key = null;
	private String[][] row = null;
	private Table table = null;
	

	private Shell shell = null;
	
	private Button saveButton = null; 

	private HashMap<Integer, List<DetailItems>>  detailAddress=null;
	private DbxClientV2 client;
	private TableItem[] items;
	/**
	 * @param arg0
	 * @param detailAddress 
	 * @param client 
	 */
	public EditDialog(Shell arg0, HashMap<Integer, List<DetailItems>> detailAddress, DbxClientV2 client) {
		this(arg0, SWT.APPLICATION_MODAL);
		this.detailAddress=detailAddress;
		this.client=client;
	}

	/**
	 * @param arg0
	 * @param arg1
	 */
	public EditDialog(Shell arg0, int arg1) {
		super(arg0, arg1);
		
	}

	public static void main(String[] args) {
	}
	
	public void show(){
		shell = new Shell(this.getParent());
		shell.setText("Documents");
		shell.setSize(640, 350);
		
		this.initData();
		//new Button(shell,SWT.PUSH).setText("Go");
		this.initWidgets();
		
		shell.pack();
		shell.open(); 
		Display display = this.getParent().getDisplay(); 
		while (!shell.isDisposed()) { 
			if (!display.readAndDispatch()) 
			display.sleep(); 
		} 	
	}
	
	public void initWidgets(){
		FontData[] fd = shell.getFont().getFontData();
		
		for (int i = 0; i < fd.length; i++) {
			fd[i].setHeight(10);

		}

		

		// tabs
		DocumentData data=new DocumentData(detailAddress);
		row=data.find(this.getKey());
		table = new Table(shell, SWT.SINGLE | SWT.FULL_SELECTION);
		table.setBounds(0, 0, 640, 320);
		table.setLinesVisible(true);
//		table.setFont(font);
		table.setHeaderVisible(true);

		int width = 0;

		// headers
		for (int i = 0; i < 5; i++) {
			width = data.getColumnWidth(i);
			TableColumn column = new TableColumn(table, SWT.NONE);
			column.setWidth((int) (width * 8)); // characters by pixels... rough
												// guess
			column.setText(data.getHeaders()[i]);
		}

		items = new TableItem[data.getRowCount()]; // an item for
																// each field
		// data
		Display displayfine = Display.getCurrent();
		Color red = new Color(displayfine, 255, 0, 0);
		for (int i = 0; i < data.getRowCount(); i++) {
			items[i] = new TableItem(table, SWT.NONE);
			items[i].setText(data.getRow(i));
			String[] output=data.getRow(i);
			if ("Fail".contains(output[2])) {
				items[i].setBackground(red);
			}else
				if ((!(output[3]==null))&&"Fail".contains(output[3])) {
					items[i].setBackground(red);
				}
		}

		// button
		saveButton = new Button(shell, SWT.CENTER | SWT.PUSH);
		saveButton.setText("Print");
		saveButton.setBounds(300, 330, 50, 30);
		saveButton.addSelectionListener( new SelectionAdapter(){
			public void widgetSelected(SelectionEvent e){
					if (e.getSource() == EditDialog.this.saveButton){
						EditDialog.this.saveAll();
						EditDialog.this.askQuit();	
					}
					
			}
			
		}
			
		);
	}
	
	public Rectangle getDialogBounds(int height, int width){
		Rectangle temp = this.getParent().getBounds();
		return new Rectangle(temp.x + 50,temp.y + 50,width, height);
	}
	
	/**
	 * @return
	 */
	public String getKey() {
		return key;
	}

	/**
	 * @param string
	 */
	public void setKey(String string) {
		key = string;
	}
	
	/**
	 * set up one row of data for the form
	 */
	private void initData(){
		
		//phone = this.getPhone("Home"); //default
		
	}
	
	private void saveAll(){
		String filename="/PrintFiles/";
		boolean firstTime= true;
		
		String pattern = "yyyy-MM-dd";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		String dateCreated = simpleDateFormat.format(new Date());
	

	 try {
		 DocumentData data=new DocumentData(detailAddress);
		 
		File temp = File.createTempFile("temp-file-name", ".tmp");
		FileOutputStream fos = new FileOutputStream(temp);
		 
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
		int headercnt=data.getHeaders().length;
		
		for (int i = 0; i < headercnt; i++) {
			bw.write(data.getHeaders()[i].toString()+",");
			
		}
		bw.newLine();
		
		for (int i = 0; i < row.length; i++) {
		
			
			
			for (int k = 0; k < headercnt; k++) {
				if (firstTime){
					firstTime=false;
					int getorigFoldstartIndex=1;
					int startindex=0;
					int getorigFoldlastIndex=row[i][0].lastIndexOf("/");
					while (getorigFoldstartIndex<getorigFoldlastIndex){
						startindex=getorigFoldstartIndex+1;
						getorigFoldstartIndex=row[i][0].indexOf("/",getorigFoldstartIndex+1);}
					filename=filename+row[i][0].substring(startindex, getorigFoldlastIndex).replace("/", "_").replace(" ", "-")+dateCreated+".csv";
				}
			bw.write(row[i][k]+",");
			}
			bw.newLine();
		}

		bw.close();
		System.out.println("temp    "
				+ temp.getPath());
		if (row.length>0){
		FileUtil createxls=new FileUtil();
		createxls.upload(filename, temp, client);
		temp.deleteOnExit();}
	} catch (IOException e) {
		System.out.println("temp-file-name   "+e.getMessage());
	} 	
	}
	
	private void askQuit(){
		MessageBox mb = new MessageBox(shell,SWT.OK|SWT.CANCEL|SWT.ICON_QUESTION);
		mb.setText("Quit Edit?");
		mb.setMessage("Are you finished printing?");
		int reply = mb.open();
		if (reply == SWT.OK){
			shell.dispose();	
		}	
	}							
							
}
