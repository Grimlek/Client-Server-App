import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class Server_Controller {
	
	private ServerSocket SERVER_SOCKET;
	private int SERVER_PORT = 20001;
	private Socket socket;
	private DataOutputStream out;
	private DataInputStream in;
	
	Server_Controller (){
		
		this.connect(SERVER_PORT);
		this.outputStream();
		this.inputStream();
		
	}
	
	private void connect(int port){
		
		try {
			SERVER_SOCKET = new ServerSocket(port);
			socket = SERVER_SOCKET.accept();
		} catch (IOException ex) {
			System.out.println("Error while connecting!");
			ex.printStackTrace();
		}
		
	}
	
	private void outputStream(){
		
		try {
			out = new DataOutputStream(socket.getOutputStream());
		} catch (IOException e) {
			System.out.println("Error while creating output stream!");
		}
		
	}
	
	private void inputStream(){
		
		try {
			in = new DataInputStream(socket.getInputStream());
		} catch (IOException e) {
			System.out.println("Error while creating input stream!");
		}
	}
	
	public void sendMessage(String message){
		
		try {
			out.writeUTF(message);
			out.flush();
		} catch (IOException e) {
			System.out.println("Error sending message to client!");
		}
		
	}
	
	public String receiveMessage(){
		
		String input = null;
		
		try {
			input = in.readUTF();
		} catch (IOException e) {
			System.out.println("Error receiving message from client!");
		}
		
		
		return input;
	}
	
	public void closeConnection() {

		try {
			out.close();
			in.close();
			socket.close();
		} catch (IOException ex) {
			System.out.println("Error closing the socket and streams");
		}

	}  

	

}
