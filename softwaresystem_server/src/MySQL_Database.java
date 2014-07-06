import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

import common.Customer;
import common.Product;

public class MySQL_Database {

	private final Connection connect;
	private PreparedStatement preparedStatement;
	
	MySQL_Database (String path, String user, String password) throws SQLException, ClassNotFoundException {
		
		Class.forName ("com.mysql.jdbc.Driver");
		this.connect = DriverManager.getConnection (path + "user=" + user + "&password=" + password);
	}
	
	public void removeCustomer (int id) throws SQLException {
		
		String statement = "DELETE FROM customers WHERE CustomerID=?";

		preparedStatement = connect.prepareStatement (statement);
		preparedStatement.setInt (1, id);	
		preparedStatement.executeUpdate ();
		
	}
	
	public void removeProduct (int id) throws SQLException {
		
		String statement = "DELETE FROM customers WHERE customerID=?";

		preparedStatement = connect.prepareStatement (statement);
		preparedStatement.setInt (1, id);	
		preparedStatement.executeUpdate ();
		
	}
	
	public void addCustomer (Customer [] data) throws SQLException {
		
        String statement = "INSERT INTO customers (customerID, firstName, lastName, address, telephoneNumber)" +
        		" VALUES (?,?,?,?,?)";

        preparedStatement = connect.prepareStatement (statement);

        preparedStatement.setObject (1, data [0]);
        preparedStatement.setObject (2, data [1]);
        preparedStatement.setObject (3, data [2]);
        preparedStatement.setObject (4, data [3]);
        preparedStatement.setObject (5, data [4]);
        preparedStatement.executeUpdate ();
		
		
	}
	
	public void addProduct (Product [] data) throws SQLException {
		
        String statement = "INSERT INTO products (productID, productName, description, price)" +
        		" VALUES (?,?,?,?)";

        preparedStatement = connect.prepareStatement (statement);

        preparedStatement.setObject (1, data [0]);
        preparedStatement.setObject (2, data [1]);
        preparedStatement.setObject (3, data [2]);
        preparedStatement.setObject (4, data [3]);
        preparedStatement.executeUpdate ();
		
	}
	
	public void updateCustomer () {
		
		
	}
	
	public void updateProduct () {
		
		
	}
	
	public ArrayList <Customer> getCustomers () throws SQLException {
		
		ArrayList <Customer> customerData = new ArrayList <Customer> ();
		
		String statement = "SELECT * FROM customers";
		
		preparedStatement = connect.prepareStatement (statement);
		ResultSet resultSet = preparedStatement.executeQuery ();
		
		if (resultSet != null) {
			
			while (resultSet.next ()) {
				
				customerData.add (new Customer (resultSet.getInt ("customerID"), resultSet.getString ("firstName"),
						resultSet.getString ("lastName"), resultSet.getString ("address"), 
						resultSet.getString ("phoneNum")));
				
			}
			
		}
		
		return customerData;
	}
	
	public ArrayList <Product> getProducts () throws SQLException {
		
		ArrayList <Product> productData = new ArrayList <Product> ();
		
		String statement = "SELECT * FROM products";
		
		preparedStatement = connect.prepareStatement (statement);
		ResultSet resultSet = preparedStatement.executeQuery ();
		
		if (resultSet != null) {
			
			while (resultSet.next ()) {
				
				productData.add (new Product (resultSet.getInt ("productID"), resultSet.getString ("productName"),
						resultSet.getString ("description"), resultSet.getDouble ("price")));
				
			}
			
		}
		
		return productData;
	}
	
	public void searchCustomers () {
		
		
	}
	
	public void searchProducts () {
		
		
	}
	
	public void close () throws SQLException {
		
		preparedStatement.close ();
		connect.close ();
		
	}	
	
}
