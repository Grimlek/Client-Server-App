import java.io.DataInputStream;

import common.Customer;
import common.Product;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class ClientName_Handler extends Client_Handler {

	private final DataOutputStream out;
	private final DataInputStream in;
	private final ObjectInputStream inObj;
	private final ObjectOutputStream outObj;

	public ClientName_Handler(Socket socket) throws IOException {
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
		
		out.writeUTF("Sup nigga");
		out.flush();
		/**do {

			switch (input) {

			case ("Get_Customer_Data"):
				ArrayList<Customer> customers = new ArrayList<>();
				customers.add(new Customer(35, "adam", "john", "aaaa", "pppp"));
				customers.add(new Customer(35, "dan", "jo", "ccc", "yyyy"));
				customers.add(new Customer(35, "mark", "joh", "hhh", "zzz"));
				customers.add(new Customer(35, "rocky", "jon", "llll", "tttt"));
				outObj.writeObject(customers);
				outObj.flush();
				break;

			case ("Get_Product_Data"):
				ArrayList<Product> products = new ArrayList<>();
				products.add(new Product("john", "aaaa", 90.00));
				products.add(new Product("jo", "ccc", 100.00));
				products.add(new Product("joh", "hhh", 200.00));
				products.add(new Product("jon", "llll", 400.00));
				outObj.writeObject(products);
				outObj.flush();
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

		} while (!(input == "Shutdown"));*/

		socket.shutdownInput();
		socket.shutdownOutput();

	}

	public static final class Factory implements Client_Handler_Factory {

		@Override
		public Client_Handler createHandler(Socket socket) throws IOException {
			return new ClientName_Handler(socket);
		}

	}

	  public static void main(String... args) throws Exception {
		    ExecutorService executor = Executors.newCachedThreadPool();
		    System.out.println("Server has started");
		    try (Server server = new Server(20999, new ClientName_Handler.Factory(), executor))
		    {
		    	
		      server.start();
		      
		    }
		    
		    finally {
		      executor.shutdown();
		    }
		  }
		}



		



	

