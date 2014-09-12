package server.connect;

import java.io.IOException;
import java.net.Socket;
import java.sql.SQLException;
import java.util.Objects;

public abstract class ClientHandler {

	protected final Socket socket;
	
	protected ClientHandler (Socket socket){
		Objects.requireNonNull (socket);
		this.socket = socket;
		
	}
	
	public abstract void handle () throws IOException, SQLException;
	
	
}