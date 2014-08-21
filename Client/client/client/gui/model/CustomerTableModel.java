package client.gui.model;
import java.util.ArrayList;

import common.Customer;

import javax.swing.table.AbstractTableModel;

public class CustomerTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;

	private ArrayList <String []> custList = new ArrayList <String []> ();
	private String [] columnNames = {"ID", "First Name", "Last Name", "Address", "Phone"};
	
	public CustomerTableModel (ArrayList <Customer> customers) {
		getTableData (customers);	
	}

	@Override
	public int getColumnCount () {
		return columnNames.length;
	}
	
	@Override
	public String getColumnName (int column) {	
		return columnNames [column];
	}

	@Override
	public int getRowCount () {
		return custList.size ();
	}

	@Override
	public Object getValueAt (int row, int column) {
		return (custList.get (row)) [column];
	}	
	
	public String [] getRowValues (int row) {
		String [] rowValues = custList.get (row);
		return rowValues; 
	}
	
	public void removeRow (int row) {
		custList.remove (row);
		fireTableRowsDeleted (row, row);
	}
	
	public void updateRow (int row, String [] values) {
		custList.set (row, values);
		fireTableRowsUpdated (row, row);
	}
	
	private void getTableData (ArrayList <Customer> customers) {
		for (Customer c : customers) {
			String [] objects = new String [columnNames.length];
			
			objects [0] = String.valueOf (c.getId ());
			objects [1] = c.getFirstName ();
			objects [2] = c.getLastName ();
			objects [3] = c.getAddress ();
			objects [4] = c.getPhoneNum ();
			
			custList.add (objects);
		}
	}
}