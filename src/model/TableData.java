package model;

import java.io.File;
import java.util.*;
import javax.swing.table.AbstractTableModel;

import controller.DBConnection;

public class TableData extends AbstractTableModel {
	private List<Transaction> list;
	private String userId;
	private DBConnection conn;
	private String[] headers = { "Name", "Type", "Amount", "Memo", "Date" };

	public TableData(String userId) {
		// TODO Auto-generated constructor stub
		this.userId = userId;
		conn = new DBConnection();
		updateList();
	}

	public TableData() {
		// TODO Auto-generated constructor stub
		this.userId = null;
		conn = new DBConnection();
		updateList();
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getColumnName(int col) {
		return headers[col];
	}

	public void updateList() {

		if (userId != null) {
			try {

				list = conn.getTranTable(userId);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			for (Transaction t : list) {
				System.out.println(t);

			}
			System.out.println("success");
		} else
			list = null;

	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 5;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		if(list == null) {
			return -1;
		}
		return list.size();
	}

	@Override
	public Object getValueAt(int row, int cell) {
		switch (cell) {
		case 0:
			return list.get(row).getName();
		case 1:
			return list.get(row).getType();
		case 2:
			return list.get(row).getAmount();
		case 3:
			return list.get(row).getNote();
		case 4:
			return list.get(row).getDate();
		}
		return null;
	}

	public void refresh() {
		updateList();
		super.fireTableDataChanged();
	}

}
