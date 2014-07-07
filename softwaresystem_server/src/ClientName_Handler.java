import java.io.DataInputStream;

import common.Customer;
import common.Product;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ClientName_Handler extends Client_Handler {

	private final DataOutputStream out;
	private final DataInputStream in;
	private final ObjectInputStream inObj;
	private final ObjectOutputStream outObj;
	private MySQL_Database database;

	public ClientName_Handler (Socket socket) throws IOException {
		super (socket);
		
		this.out = new DataOutputStream (socket.getOutputStream ());
		this.in = new DataInputStream (socket.getInputStream ());
		this.outObj = new ObjectOutputStream (socket.getOutputStream ());
		this.inObj = new ObjectInputStream (socket.getInputStream ());

	}

	@Override
	public void handle () throws IOException, SQLException {

		String input;
		
		try {
			database = new MySQL_Database ("jdbc:mysql://localhost/software_system_db?", "root", "root");
		} catch (ClassNotFoundException ex) {
			System.out.println ("Error while connecting to the database!");
			ex.printStackTrace ();
		}
		
		do {
			
			input = in.readUTF ();
			System.out.println (input);
			
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
			break;

		case ("Add_Product"):
			break;

		case ("Remove_Customer"):
			break;

		case ("Remove_Product"):
			break;

		case ("Edit_Customer"):
			break;

		case ("Edit_Produt"):
			break;
		
		} 
		
		} while (! (input.equals ("Disconnect")));
		
		database.close ();
		
	}

	public static final class Factory implements Client_Handler_Factory {

		@Override
		public Client_Handler createHandler (Socket socket) throws IOException {
			return new ClientName_Handler (socket);
		}

	}

	public static void main (String... args) throws Exception {
		ExecutorService executor = Executors.newCachedThreadPool ();
		System.out.println ("Server has started");
		Server server = new Server (20999, new ClientName_Handler.Factory (), executor);

		server.start ();

	}
}
		



	

