package client;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;
import common.Product;

public class Product_Table_Model extends AbstractTableModel {

	private ArrayList<String[]> prodList = new ArrayList<String[]>();
	private String[] columnNames = { "Product Name", "Description", "Price" };

	Product_Table_Model(ArrayList<Product> products) {

		getTableData(products);

	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	public String getColumnName(int column) {
		return columnNames[column];
	}

	@Override
	public int getRowCount() {
		return prodList.size();
	}

	@Override
	public Object getValueAt(int row, int column) {
		return (prodList.get(row))[column];
	}

	private void getTableData(ArrayList<Product> products) {
				
		for (Product p : products) {

			String[] objects = new String[columnNames.length];

			objects[0] = p.getProductName();
			objects[1] = p.getDescription();
			objects[2] = String.valueOf(p.getPrice());

			prodList.add(objects);

		}

	}
}
