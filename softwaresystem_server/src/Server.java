import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.sql.SQLException;
import java.util.Objects;
import java.util.concurrent.ExecutorService;

public final class Server {

	private final ServerSocket serverSocket;
	private final Client_Handler_Factory handlerFactory;
	private final ExecutorService executor;
	private Socket socket;

	private volatile boolean listening, closed;

	public Server (int port, Client_Handler_Factory handlerFactory,
			ExecutorService executor) throws IOException {
		Objects.requireNonNull (port);
		Objects.requireNonNull (executor);

		this.serverSocket = new ServerSocket (port);
		this.handlerFactory = handlerFactory;
		this.executor = executor;
	}

	public void start () {

		executor.submit (new Runnable () {
			@Override
			public void run () {
				try {
					listen ();
				}

				catch (IOException ex) {
					System.out.println ("An exception occurred while the server was listening");
					ex.printStackTrace ();
				}
			}
		});

	}

	public void listen () throws IOException {

		synchronized (this) {

			if (listening) {

				throw new IllegalStateException ("The server has already started listening");
			}

			listening = true;

		}

		try {
			while (!closed) {

				socket = serverSocket.accept ();

				executor.submit (new Runnable () {

					public void run () {
						
							delegateToHandler (socket);

					}

				});
			}
		}

		catch (SocketException ex) {
			if (closed)
				System.out.println ("Socket is closed!");
			else
				throw ex;
		}

	}

	private void delegateToHandler (Socket socket) {

		try {
			handlerFactory.createHandler (socket).handle ();
		} catch (IOException | SQLException ex) {
			System.out.println ("Exception occured while handling the connection to "
							+ socket.getInetAddress());
			
			ex.printStackTrace();
		}

		finally {
			try {

				socket.close ();

			} catch (IOException ex) {

				System.out.println ("Exception occured while trying to close the connection to "
								+ socket.getInetAddress());

			}
		}
	}

	public void close () throws IOException {

		if (!closed) {
			closed = true;
			serverSocket.close ();
			executor.shutdown ();

		}
	}
}
