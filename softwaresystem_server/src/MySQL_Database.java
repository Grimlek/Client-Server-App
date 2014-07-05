import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import common.Customer;
import common.Product;

public class MySQL_Database {

	private final Connection connect;
	private PreparedStatement preparedStatement;
	
	MySQL_Database (String path, String user, String password) throws SQLException, ClassNotFoundException {
		
		Class.forName ("com.mysql.jdbc.Driver");
		this.connect = DriverManager.getConnection (path + user + password);
		
	}

	public ResultSet executeQuery (String query) {
		
		ResultSet resultSet;
		
		try {
			resultSet = connect.createStatement().executeQuery(query);
			
			return resultSet;
			
		} catch (SQLException ex) {
			System.out.println("Error creating query!");
		}
		
		return null;	
		
	}
	
	public void removeCustomer (int id) throws SQLException {
		
		String statement = "DELETE FROM customers WHERE CustomerID=?";

		preparedStatement = connect.prepareStatement (statement);
		preparedStatement.setInt (1, id);	
		preparedStatement.executeUpdate ();
		
	}
	
	public void removeProduct (int id) throws SQLException{
		
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
	
	public Customer getCustomers () throws SQLException {
		
		String statement = "SELECT * FROM customers";
		
		preparedStatement = connect.prepareStatement (statement);
		ResultSet resultSet = preparedStatement.executeQuery ();
		
		if (resultSet != null) {
			
			while (resultSet.next ()) {
				
				
			}
			
		}
		
		return null;
	}
	
	public Product getProducts () throws SQLException {
		
		String statement = "SELECT * FROM products";
		
		preparedStatement = connect.prepareStatement (statement);
		ResultSet resultSet = preparedStatement.executeQuery ();
		
		if (resultSet != null) {
			
			while (resultSet.next ()) {
				
				
			}
			
		}
		
		return null;
	}
	
	public void searchCustomers (){
		
		
	}
	
	public void searchProducts (){
		
		
	}
	
	public void close () throws SQLException {
		
		preparedStatement.close ();
		connect.close ();
		
	}	
	
}
