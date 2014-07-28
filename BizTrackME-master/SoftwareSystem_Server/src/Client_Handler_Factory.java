import java.io.IOException;
import java.net.Socket;

public interface Client_Handler_Factory {

	public abstract Client_Handler createHandler (Socket socket) throws IOException;
	
}
