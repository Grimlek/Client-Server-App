package client.gui.model;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import common.Product;

public class ProductTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;

	private ArrayList <String []> prodList = new ArrayList <String []> ();
	private String [] columnNames = { "Product ID", "Product Name", "Description", "Price" };

	public ProductTableModel (ArrayList <Product> products) {
		getTableData (products);
	}

	@Override
	public int getColumnCount () {
		return columnNames.length;
	}

	public String getColumnName (int column) {
		return columnNames [column];
	}

	@Override
	public int getRowCount () {
		return prodList.size ();
	}

	@Override
	public Object getValueAt (int row, int column) {
		return (prodList.get (row)) [column];
	}
	
	public String [] getRowValues (int row) {
		String [] rowValues = prodList.get (row);
		return rowValues; 
	}
	
	public void removeRow (int row) {
		prodList.remove (row);
		fireTableRowsDeleted (row, row);
	}
	
	public void updateRow (int row, String [] values) {
		for (int i = 0; i < prodList.size(); i++){
			if (i == row){
				fireTableRowsUpdated (row, row);
				prodList.set (row, values);
			}
		}
	}

	private void getTableData (ArrayList <Product> products) {
		for (Product p : products) {

			String [] objects = new String [columnNames.length];
			
			objects [0] = String.valueOf (p.getId ());
			objects [1] = p.getProductName ();
			objects [2] = p.getDescription ();
			objects [3] = String.valueOf (p.getPrice ());

			prodList.add (objects);

		}
	}
}
