import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;


@SuppressWarnings("serial")
public class CustomerTableModel extends AbstractTableModel{

	private ArrayList<String []> custList;
	private String [] columnNames = {"ID", "First Name", "Last Name", "Address", "Phone"};
	
	CustomerTableModel(){
		
		this.custList = new ArrayList<String []>();
		
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Object getValueAt(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return null;
	}
}
