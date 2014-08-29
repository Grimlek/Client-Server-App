package server.connect;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.SQLException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import server.database.MySQLDatabase;
import common.Customer;
import common.Product;

public class ClientNameHandler extends ClientHandler {

	private final ObjectInputStream inObj;
	private final ObjectOutputStream outObj;
	private MySQLDatabase database;

	public ClientNameHandler (Socket socket) throws IOException {
		super (socket);

		this.outObj = new ObjectOutputStream (socket.getOutputStream ());
		this.inObj = new ObjectInputStream (socket.getInputStream ());
	}

	@Override
	public void handle () throws IOException, SQLException {

		String input;
		
		try {
			database = new MySQLDatabase ("jdbc:mysql://localhost/software_system_db?", "root", "root");
		} catch (ClassNotFoundException ex) {
			System.out.println ("Error while connecting to the database!");
			ex.printStackTrace ();
		}
		
		do {
			input = inObj.readUTF ();

			switch (input) {

			case ("Get_Customer_Data"):
				outObj.writeObject (database.getCustomers ());
				outObj.flush ();
				break;

			case ("Get_Product_Data"):
				outObj.writeObject (database.getProducts ());
				outObj.flush ();
				break;

			case ("Add_Customer"):
				try {
					database.addCustomer ( (Customer []) inObj.readObject ());
				} catch (ClassNotFoundException ex) {
					ex.printStackTrace ();
				}
				break;

			case ("Add_Product"):
				try {
				   database.addProduct ( (Product []) inObj.readObject ());
				} catch (ClassNotFoundException ex) {
					ex.printStackTrace ();
				}
				break;
				
			case ("Remove_Customer"):
				database.removeCustomer (Integer.parseInt (inObj.readUTF ()));
				break;

			case ("Remove_Product"):
				database.removeProduct (Integer.parseInt (inObj.readUTF ()));
				break;

			case ("Edit_Customer"):
				try {
					database.updateCustomer ( (String []) inObj.readObject ());
				} catch (ClassNotFoundException ex) {
					ex.printStackTrace ();
				}
				break;

			case ("Edit_Product"):
				try {
					database.updateProduct ( (String []) inObj.readObject ());
				} catch (ClassNotFoundException ex) {
					ex.printStackTrace ();
				}
				break;
				
			case ("Search_Customer"):
				outObj.writeObject (database.searchCustomers (inObj.readUTF ()));
				break;
				
			case ("Search_Product"):
				outObj.writeObject (database.searchProducts (inObj.readUTF ()));
				break;
			}

		} while (! (input.equals ("Disconnect")));
		
		database.close ();
	}

	public static final class Factory implements ClientHandlerFactory {

		@Override
		public ClientHandler createHandler (Socket socket) throws IOException {
			return new ClientNameHandler (socket);
		}
	}

	public static void main (String... args) throws Exception {
		ExecutorService executor = Executors.newCachedThreadPool ();
		System.out.println ("Server has started");
		Server server = new Server (20999, new ClientNameHandler.Factory (), executor);

		server.start ();
	}
}