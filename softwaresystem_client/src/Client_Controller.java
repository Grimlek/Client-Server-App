import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;


public class Client_Controller {

	private String SERVER_HOSTNAME;
	private int SERVER_PORT = 20001;
	
	private DataOutputStream out;
	private DataInputStream in;
	private Socket socket;
	
	Client_Controller(){
		
		this.connect(SERVER_HOSTNAME, SERVER_PORT);
		this.outputStream();
		this.inputStream();
		
	}
	
	private void connect(String hostName, int port){
		
		try {
			socket = new Socket(hostName, port);
		} catch (UnknownHostException ex) {
			System.out.println("Host was not found: " + hostName);
		} catch (IOException ex) {
			System.out.println("Error in connecting to server: " + port);
		}
		
	}
	
	private void outputStream(){
		
		try {
			out = new DataOutputStream(socket.getOutputStream());
		} catch (IOException ex) {
			System.out.println("Output stream failure");
		}
		
		
	}
	
	private void inputStream(){
		
		try {
			in = new DataInputStream(socket.getInputStream());
		} catch (IOException ex) {
			System.out.println("Input stream failure");
		}
		
	}
	
	public void closeConnection() {

		this.sendMessage("Client_Close");
		try {
			out.close();
			in.close();
			socket.close();
		} catch (IOException ex) {
			System.out.println("Error closing the socket and streams");
		}

	}
	
	public void sendMessage(String message){
		
		try {
			out.writeUTF(message);
			out.flush();
		} catch (IOException ex) {
			System.out.println("Error sending the message to the server");
		}
		
		
	}
	
	public String receiveMessage(){
		
		String input = null;
		
		try {
			input = in.readUTF();
		} catch (IOException ex) {
			System.out.println("Error receving the message from the server");
		}
		
		return input;
		
	}
}
