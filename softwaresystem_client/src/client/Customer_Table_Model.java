package client;
import java.util.ArrayList;

import common.Customer;

import javax.swing.table.AbstractTableModel;

@SuppressWarnings("serial")
public class Customer_Table_Model extends AbstractTableModel{

	private ArrayList<String []> custList = new ArrayList<String[]>();
	private String [] columnNames = {"ID", "First Name", "Last Name", "Address", "Phone"};
	
	Customer_Table_Model(ArrayList<Customer> customers){
		
		getTableData(customers);
		
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}
	
	@Override
	public String getColumnName(int column) {	
		return columnNames[column];
	}

	@Override
	public int getRowCount() {
		return custList.size();
	}

	@Override
	public Object getValueAt(int row, int column) {
		return (custList.get(row))[column];
	}	
	
	private void getTableData(ArrayList<Customer> customers){
		
		for (Customer c : customers){
			System.out.println(c);
			String [] objects = new String[columnNames.length];
			
			objects[0] = String.valueOf(c.getId());
			objects[1] = c.getFirstName();
			objects[2] = c.getLastName();
			objects[3] = c.getAddress();
			objects[4] = c.getPhoneNum();
			
			custList.add(objects);
			
		}
	}
	
}
