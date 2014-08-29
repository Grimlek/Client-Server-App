package server.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.Customer;
import common.Product;

public class MySQLDatabase {

	private final Connection connect;
	private PreparedStatement preparedStatement;
	
	public MySQLDatabase (String path, 
			String user, 
			String password) throws SQLException, ClassNotFoundException {
		
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
        String statement = "INSERT INTO customers (firstName, lastName, address, phoneNum" +
        		" VALUES (?,?,?,?)";

        preparedStatement = connect.prepareStatement (statement);

        preparedStatement.setObject (1, data [1]);
        preparedStatement.setObject (2, data [2]);
        preparedStatement.setObject (3, data [3]);
        preparedStatement.setObject (4, data [4]);
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
	
	public void updateCustomer (String [] value) throws SQLException {
        String statement = "UPDATE customers SET firstName=?, lastName=?, " + 
        		"address=?, phoneNum=? WHERE customerID =?";

        preparedStatement = connect.prepareStatement (statement);

        preparedStatement.setObject (1, value [1]);
        preparedStatement.setObject (2, value [2]);
        preparedStatement.setObject (3, value [3]);
        preparedStatement.setObject (4, value [4]);
        preparedStatement.setObject (5, value [0]);
        preparedStatement.executeUpdate ();
	}
	
	public void updateProduct (String [] value) throws SQLException {
        String statement = "UPDATE products SET productName=?, description=?, " + 
        		"price=? WHERE productID =?";

        preparedStatement = connect.prepareStatement (statement);

        preparedStatement.setObject (1, value [1]);
        preparedStatement.setObject (2, value [2]);
        preparedStatement.setObject (3, value [3]);
        preparedStatement.setObject (4, value [4]);
        preparedStatement.executeUpdate ();
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
	
	public ArrayList <Customer> searchCustomers (String search) throws SQLException {
		ArrayList <Customer> customer = new ArrayList <Customer> ();

			String statement = "SELECT * FROM customers WHERE customerID =?";
			
	        preparedStatement = connect.prepareStatement (statement);
	        preparedStatement.setObject (1, search);
	        
			ResultSet resultSet = preparedStatement.executeQuery ();
				
				while (resultSet.next ()) {
					customer.add(new Customer (
							resultSet.getInt ("customerID"),
							resultSet.getString ("firstName"),
							resultSet.getString ("lastName"),
							resultSet.getString ("address"),
							resultSet.getString ("phoneNum")));
				}
				return customer;
	}
	
	public ArrayList <Product> searchProducts (String search) throws SQLException {
		ArrayList <Product> product = new ArrayList <Product> ();
		String statement = "SELECT * FROM products WHERE productID =?";
		
        preparedStatement = connect.prepareStatement (statement);
        preparedStatement.setObject (1, search);
        
		ResultSet resultSet = preparedStatement.executeQuery ();
			
			while (resultSet.next ()) {
				product.add(new Product (
						resultSet.getInt ("productID"),
						resultSet.getString ("productName"),
						resultSet.getString ("description"),
						resultSet.getDouble ("price")));
			}
			return product;
	}
	
	public void close () throws SQLException {
		preparedStatement.close ();
		connect.close ();
	}	
}