import java.io.IOException;

public class Server_Handler {

	public static void main (String[] args) throws IOException{
		
			Server_Controller server = new Server_Controller();
			
			String input = server.receiveMessage();
			System.out.println("This message was received: " + input);
			server.sendMessage("The server is connected");
			
	}
	
}
