import java.io.DataInputStream;

import common.Customer;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;


public class ClientName_Handler extends Client_Handler{
	
	private final DataOutputStream out;
	private final DataInputStream in;
	private final ObjectInputStream inObj;
	private final ObjectOutputStream outObj;
	
	public ClientName_Handler (Socket socket) throws IOException{
		super(socket);
		
		this.out = new DataOutputStream(socket.getOutputStream());
		this.in = new DataInputStream(socket.getInputStream());
		this.outObj = new ObjectOutputStream(socket.getOutputStream());
		this.inObj = new ObjectInputStream(socket.getInputStream());
		
	}
	
	@Override
	public void handle() throws IOException {
		String input = in.readUTF();
		System.out.println(input);
		socket.shutdownInput();
		switch (input) {

		case ("Get_Customer_Data"):
			ArrayList<Customer> customers = new ArrayList<>();
			customers.add(new Customer(35, "adam", "john", "aaaa", "pppp"));
			customers.add(new Customer(35, "dan", "jo", "ccc", "yyyy"));
			customers.add(new Customer(35, "mark", "joh", "hhh", "zzz"));
			customers.add(new Customer(35, "rocky", "jon", "llll", "tttt"));
			outObj.writeObject(customers);
			break;
			
		case ("Get_Product_Data"):
			
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
		out.flush();
		socket.shutdownOutput();

	}

	public static final class Factory implements Client_Handler_Factory{
		
		@Override
		public Client_Handler createHandler(Socket socket) throws IOException {
			return new ClientName_Handler(socket);
		}
		
	}

	
	public static void main (String[] args) throws IOException{
		
		try (
		Server server = new Server(20999, new ClientName_Handler.Factory());) {
			
			server.start();
			
		} 	
}

	
}

		



	

