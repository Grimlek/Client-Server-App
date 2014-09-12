package client.connect;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientController {

	private String hostName = "localhost";
	private int port = 20999;

	private ObjectOutputStream outObj;
	private ObjectInputStream inObj;
	private Socket socket;

	public ClientController () {
		this.connect (hostName, port);
		this.createOutputStream ();
		this.createInputStream ();
	}

	private void connect (String hostName, int port) {
		try {
			socket = new Socket (hostName, port);
		} catch (IOException ex) {
			System.err.println ("Host was not found: " + hostName);
		} 
	}

	private void createOutputStream () {
		try {
			outObj = new ObjectOutputStream (socket.getOutputStream ());
		} catch (IOException ex) {
			System.err.println ("Object output stream failure");
		}
	}

	private void createInputStream () {
		try {
			inObj = new ObjectInputStream (socket.getInputStream ());
		} catch (IOException ex) {
			System.err.println ("Object input stream failure");
		}
	}

	public void closeConnection () throws IOException {
		try {
			this.sendMessage ("Disconnect");
		}
		finally {
			try {
				outObj.close ();
			}
			finally {
				try {
					inObj.close ();
				}
				finally {
					socket.close ();
				}
			}
		}
	}

	public void sendMessage (String message) throws IOException {
			outObj.writeUTF (message);
			outObj.flush ();
	}
	
	public void sendObject (Object object) throws IOException {
			outObj.writeObject (object);
			outObj.flush ();
	}
	
	public Object readObject () throws ClassNotFoundException, IOException {
		return inObj.readObject ();
	}
}