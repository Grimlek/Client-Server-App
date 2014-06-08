import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client_Controller {

	private String SERVER_HOSTNAME;
	private int SERVER_PORT = 20999;

	private DataOutputStream out;
	private DataInputStream in;
	private Socket socket;

	Client_Controller() {

		this.connect(SERVER_HOSTNAME, SERVER_PORT);
		this.outputStream();
		this.inputStream();

	}

	private void connect(String hostName, int port) {

		try {
			socket = new Socket(hostName, port);
		} catch (UnknownHostException ex) {
			System.out.println("Host was not found: " + hostName);
		} catch (IOException ex) {
			System.out.println("Error in connecting to server: " + port);
		}

	}

	private void outputStream() {

		try {
			out = new DataOutputStream(socket.getOutputStream());
		} catch (IOException ex) {
			System.out.println("Output stream failure");
		}

	}

	private void inputStream() {

		try {
			in = new DataInputStream(socket.getInputStream());
		} catch (IOException ex) {
			System.out.println("Input stream failure");
		}

	}

	public void closeConnection() throws IOException {

		try {
			this.sendMessage("Client_Close");
		}

		finally {

			try {
				out.close();
			}

			finally {
				try {
					in.close();
				}

				finally {
					socket.close();
				}
			}
		}
	}

	public void sendMessage(String message) throws IOException {

		try {
			out.writeUTF(message);
		}

		finally {
			out.flush();
		}

	}

	public String receiveMessage() {

		String input = null;

		try {
			input = in.readUTF();
		} catch (IOException ex) {
			System.out.println("Error receving the message from the server");
		}

		return input;

	}
}
