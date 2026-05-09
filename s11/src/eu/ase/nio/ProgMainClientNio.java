package eu.ase.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.List;

public class ProgMainClientNio {

	public static void main(String[] args) throws IOException, InterruptedException {

		InetSocketAddress myAddr = new InetSocketAddress("127.0.0.1", 8989);
		SocketChannel myClient = SocketChannel.open(myAddr);

		System.out.println("Connecting to Server on port 8989 ...");

		List<String> companyDetails = new ArrayList<>();

		companyDetails.add("Facebook");
		companyDetails.add("Twitter");
		companyDetails.add("IBM");
		companyDetails.add("Google");

		for (String companyName : companyDetails) {

			byte[] message = new String(companyName).getBytes();
			ByteBuffer buffer = ByteBuffer.wrap(message);

			myClient.write(buffer);

			System.out.println("sending: " + companyName);
			buffer.clear();

			Thread.sleep(2000);
		}

		myClient.close();
	}
}