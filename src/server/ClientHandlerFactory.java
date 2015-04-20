package server;
import java.io.IOException;
import java.net.Socket;

public interface ClientHandlerFactory {

	public abstract ClientHandler createHandler (Socket socket) throws IOException;
	
}