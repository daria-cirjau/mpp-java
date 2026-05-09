package eu.ase.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class ProgMainServerNio {
	public static void main(String[] args) throws IOException {

		// Selector: multiplexor of SelectableChannel objects
		Selector selector = Selector.open();

		// ServerSocketChannel: selectable channel for stream-oriented listening sockets
		ServerSocketChannel serverSocket = ServerSocketChannel.open();
		InetSocketAddress serverAddr = new InetSocketAddress("127.0.0.1", 8989);

		// Binds the channel's socket to a local address and configures the socket to listen for connections
		serverSocket.bind(serverAddr);

		// Adjusts this channel's blocking mode.
		serverSocket.configureBlocking(false);

		int ops = serverSocket.validOps();
		SelectionKey selectKy = serverSocket.register(selector, ops, null);

		// Infinite loop. Keep server running.
		while (true) {

			System.out.println("I'm a server and I'm waiting for new connection and buffer select...");

			// Selects a set of keys whose corresponding channels are ready for I/O operations
			selector.select();

			// Token representing the registration of a SelectableChannel with a Selector
			Set<SelectionKey> cKeys = selector.selectedKeys();
			Iterator<SelectionKey> cIterator = cKeys.iterator();

			while (cIterator.hasNext()) {
				SelectionKey myKey = cIterator.next();

				// Tests whether this key's channel is ready to accept a new socket connection
				if (myKey.isAcceptable()) {
					SocketChannel sClient = serverSocket.accept();

					// Adjusts this channel's blocking mode to false
					sClient.configureBlocking(false);

					// Operation-set bit for read operations
					sClient.register(selector, SelectionKey.OP_READ);
					System.out.println("Connection Accepted: " + sClient.getLocalAddress() + "\n");

				// Tests whether this key's channel is ready for reading
				} else if (myKey.isReadable()) {

					SocketChannel sClient = (SocketChannel) myKey.channel();
					ByteBuffer cBuffer = ByteBuffer.allocate(256);

					sClient.read(cBuffer);
					String result = new String(cBuffer.array()).trim();

					System.out.println("Message received: " + result);

					if (result.equals("Google")) {
						sClient.close();
						System.out.println("\nIt's time to close connection as we got last company name 'Google'");
						System.out.println("\nServer will keep running. Try running client again to establish new connection");
					}
				}

				cIterator.remove();
			}
		}
	}
}