/*
 * SqlData.java
 *
 * Copyright (C) 2004 Gervase Gallant gervasegallant@yahoo.com
 *
 * This program is free software; you can use it, redistribute it
 * and / or modify it under the terms of the GNU General Public License
 * (GPL) as published by the Free Software Foundation; either version 2
 * of the License or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * http://www.gnu.org/licenses/gpl.txt OR
 * Write to the Free Software Foundation Inc.,
 * 59 Temple Place - Suite 330, Boston, MA  02111-1307 USA
 * Created on December 15, 2001, 9:53 PM
 */
package com.plasko.verifyRealestate.window;

import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;


import com.plasko.verifyRealestate.FolderItems;
import com.plasko.verifyRealestate.RulesCheck;
import com.plasko.verifyRealestate.TypeRules;
import com.dropbox.core.v2.DbxClientV2;
import com.plasko.verifyRealestate.DetailItems;

public class VerifyRealestateGrid {
	protected static final int HashMap = 0;
	private Shell shell;
	private Button button = null;
	private Button buttonRules = null;
	private List<FolderItems> folderAddress;
	private HashMap<Integer, List<DetailItems>> detailAddress;
	private Table table = null;
	private DbxClientV2 client;
	private List fontResources = new ArrayList();

	/**
	 * 
	 */
	public VerifyRealestateGrid() {
		super();

	}

	public static void main(String[] args) {
		new VerifyRealestateGrid().initialize();
	}

	public void runGrid(List<FolderItems> folderAddress, HashMap<Integer, List<DetailItems>> detailAddress,
			DbxClientV2 client) {

		this.folderAddress = folderAddress;
		this.detailAddress = detailAddress;
		this.client = client;
		initialize();
	}

	private void initialize() {
		Display display = new Display();

		shell = new Shell(display);
		shell.setSize(800, 350);
		shell.setText("Data Table");

		// RowLayout rl = new RowLayout();
		// rl.type=SWT.VERTICAL;

		// shell.setLayout(rl);

		this.setupWidgets(display, shell);

		shell.pack();
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}

		for (int i = 0; i < fontResources.size(); i++) {

			((Font) fontResources.get(i)).dispose();
		}
		display.dispose();

	}

	private void setupWidgets(Display display, Shell shell) {
		FontData[] fd = shell.getFont().getFontData();

		for (int i = 0; i < fd.length; i++) {
			fd[i].setHeight(10);

		}

		Font font = new Font(display, fd);
		fontResources.add(font);

		// tabs
		FolderData data = new FolderData();
		data.getInstance(folderAddress);
		table = new Table(shell, SWT.MULTI | SWT.FULL_SELECTION);
		table.setBounds(0, 0, 1680, 360);
		table.setLinesVisible(true);
		table.setFont(font);
		table.setHeaderVisible(true);

		int width = 0;

		// headers
		for (int i = 0; i < 4; i++) {
			width = data.getColumnWidth(i);
			TableColumn column = new TableColumn(table, SWT.MULTI);
			column.setWidth((int) (width * 8)); // characters by pixels... rough
												// guess

			column.setText(data.getHeaders()[i]);
		}

		TableItem[] items = new TableItem[data.getRowCount()]; // an item for
																// each field
		// data
		Display displayfine = Display.getCurrent();
		Color red = new Color(displayfine, 255, 0, 0);
		Color blank = new Color(displayfine, 255, 255, 255);
		for (int i = 0; i < data.getRowCount(); i++) {
			items[i] = new TableItem(table, SWT.NONE);
			items[i].setText(data.getRow(i));
			String[] output = data.getRow(i);
			items[i].setBackground(blank);
			if ((output[2] == null || "".equals(output[2])) && (output[3] == null || "".equals(output[3]))) {
				items[i].setBackground(red);
			} else if (output[2].contains("Fail")) {
				items[i].setBackground(red);
			} else if (output[3].contains("Fail")) {
				items[i].setBackground(red);
			}
		}
//
		// button
		button = new Button(shell, SWT.CENTER | SWT.PUSH);
		button.setBounds(350, 360, 50, 30);

		button.setText("Details...");
		button.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				if (e.getSource() == VerifyRealestateGrid.this.button) {

					TableItem[] items = VerifyRealestateGrid.this.table.getSelection();
					if (items == null || items.length < 1) {
						Toolkit.getDefaultToolkit().beep();
					} else {
						System.out.println("Selected key..." + items[0]);
						EditDialog form = new EditDialog(VerifyRealestateGrid.this.shell, detailAddress, client);
						form.setKey(items[0].getText());
						form.show();
						VerifyRealestateGrid.this.refreshData();
						
					}

				}
			}
		});
		// button
		buttonRules = new Button(shell, SWT.CENTER | SWT.PUSH);
		buttonRules.setBounds(480, 360, 50, 30);

		buttonRules.setText("Rules...");
		buttonRules.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				if (e.getSource() == VerifyRealestateGrid.this.buttonRules) {

					
					
						TypeRules process= new TypeRules();
						 try {
							process.setContext(client);
						} catch (Exception e1) {
						
							System.out.println(e1);
						}
						java.util.HashMap<String, List<RulesCheck>> verifyValidation=process.getProcessRules();
						RulesDialog form = new RulesDialog(VerifyRealestateGrid.this.shell, verifyValidation, client,null);
						
						form.show();
						VerifyRealestateGrid.this.refreshData();
					

				}
			}
		});
	}

	private void refreshData() {
		FolderData data = new FolderData();
		data.getInstance(folderAddress);
		for (int i = 0; i < data.getRowCount(); i++) {

			table.getItem(i).setText(data.getRow(i));
		}

	}

}
