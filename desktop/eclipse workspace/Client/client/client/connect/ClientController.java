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

	public ClientController() {

		this.connect(hostName, port);
		this.outputStream();
		this.inputStream();

	}

	private void connect(String hostName, int port) {

		try {
			socket = new Socket(hostName, port);
		} catch (IOException ex) {
			System.out.println("Host was not found: " + hostName);
		} 
	}

	private void outputStream() {

		try {
			outObj = new ObjectOutputStream(socket.getOutputStream());
		} catch (IOException ex) {
			System.out.println("Object output stream failure");

		}
	}

	private void inputStream() {

		try {
			inObj = new ObjectInputStream(socket.getInputStream());
		} catch (IOException ex) {
			System.out.println("Object input stream failure");
		}

	}

	public void closeConnection() throws IOException {

		try {
			this.sendMessage("Client_Close");
		}

		finally {

			try {
				outObj.close();
			}

			finally {
				try {
					inObj.close();
				}

				finally {
					socket.close();
				}
			}
		}
	}

	public void sendMessage(String message) throws IOException {
			outObj.writeUTF(message);
			outObj.flush();

	}
	
	public void sendObject(Object object) throws IOException {
		outObj.writeObject(object);
		outObj.flush();
	}
	
	public Object receiveObject(String message) {

		Object input = null;
		
		try {
			sendMessage(message);
		} catch (IOException ex) {
			System.out.println("Error sending the message to the server while trying to receive the object.");
			ex.printStackTrace();
		}
		
		try {
			input = inObj.readObject();
		} catch (IOException | ClassNotFoundException ex) {
			System.out.println("Error receving the object from the server ");
		}

		return input;

	}
}
