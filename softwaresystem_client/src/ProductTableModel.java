import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;


public class ProductTableModel extends AbstractTableModel{

	private ArrayList<String []> prodList;
	private String [] columnNames = {"ID", "Product Name", "Description", "Price"};
	
	ProductTableModel (){
		
		this.prodList = new ArrayList<String []>();
		
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
