import java.io.IOException;
import java.net.Socket;
import java.util.Objects;

public abstract class Client_Handler {

	protected final Socket socket;
	
	protected Client_Handler (Socket socket){
		Objects.requireNonNull(socket);
		this.socket = socket;
		
	}
	
	public abstract void handle() throws IOException;
	
	
}
