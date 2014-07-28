import java.io.DataInputStream;

import common.Customer;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;


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
			System.out.println("View Customer Has Been Initialized");
			ArrayList<Customer> customers = new ArrayList<>();
			customers.add(new Customer(35, "adam", "john", "aaaa", "bbbbb"));
			outObj.writeObject(customers);
			break;

		}
		out.writeUTF("Yes I am connected!!!!");
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

		



	

