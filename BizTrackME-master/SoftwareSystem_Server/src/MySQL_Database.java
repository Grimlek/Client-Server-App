import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MySQL_Database {

	private final Connection connect;
	
	MySQL_Database(String path, String user, String password) throws SQLException, ClassNotFoundException{
		
		Class.forName("com.mysql.jdbc.Driver");
		this.connect = DriverManager.getConnection(path + user + password);
		
	}

	public void close() {
		
		
	}

	public ResultSet executeQuery (String query){
		
		ResultSet resultSet;
		
		try {
			resultSet = connect.createStatement().executeQuery(query);
			
			return resultSet;
		} catch (SQLException ex) {
			System.out.println("Error creating query!");
		}
		
		return null;	
		
	}
	
	public void remove (){
		
		
	}
	
	public void addCustomer(){
		
		
	}
	
	public void addProduct(){
		
		
	}
	
	public void updateCustomer(){
		
		
	}
	
	public void updateProduct(){
		
		
	}
	
	public void getCustomers(){
		
		
	}
	
	public void getProducts(){
		
		
	}
	
	public void searchCustomers(){
		
		
	}
	
	public void searchProducts(){
		
		
	}
	
	
	
	
	
}
