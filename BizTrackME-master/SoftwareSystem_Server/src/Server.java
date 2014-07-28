import java.io.Closeable;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Objects;

public final class Server implements Closeable {

	private final ServerSocket serverSocket;
	private final Client_Handler_Factory handlerFactory;

	private volatile boolean listening, closed;

	public Server(int port, Client_Handler_Factory handlerFactory)
			throws IOException {
		Objects.requireNonNull(port);

		this.serverSocket = new ServerSocket(port);
		this.handlerFactory = handlerFactory;

	}

	public void start() throws IOException {

		/**
		 * Add run method here for multi-threaded app and include the listen()
		 * method within
		 * 
		 * @future implementation
		 */
		listen();

	}

	public void listen() throws IOException {

		synchronized (this) {

			if (listening) {

				throw new IllegalStateException(
						"The server has already started listening");
			}

			listening = true;
		}

		try {
			while (!closed) {
				Socket socket = serverSocket.accept();
				delegateToHandler(socket);
			}
		}

		catch (SocketException ex) {
			if (closed)
				System.out.println("Socket is closed!");
			else
				throw ex;
		}

	}

	private void delegateToHandler(Socket socket) {

		try {
			handlerFactory.createHandler(socket).handle();
		} catch (IOException ex) {
			System.out
					.println("Exception occured while handling the connection to "
							+ socket.getInetAddress());
		}

		finally {
			try {

				socket.close();

			} catch (IOException ex) {

				System.out
						.println("Exception occured while trying to close the connection to "
								+ socket.getInetAddress());

			}
		}
	}

	@Override
	public void close() throws IOException {

		if (!closed) {
			closed = true;
			serverSocket.close();

		}
	}
}
