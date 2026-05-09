package eu.ase.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPClient {
	// byte[] buf = new byte[256];
	// buf[0] = 'H'; buf[1] = 'e'; buf[2] = 'l'; buf[3] = 'l'; buf[4] = 'o';
	// buf = (new String("Hello")).getBytes();
	public static void udpClient(String[] args) throws IOException {
		DatagramSocket clientSock = new DatagramSocket();

		// byte[] buf = "Something else".getBytes();
		byte[] buf = "What date & time is it?".getBytes();

		// javac eu/ase/udp/UDPClient.java
		// java -cp . eu.ase.udp.UDPClient 127.0.0.1 7778
		InetAddress dstAddr = InetAddress.getByName(args[0]);
		int dstPort = Integer.parseInt(args[1]);

		DatagramPacket packet = new DatagramPacket(buf, buf.length, dstAddr, dstPort);
		clientSock.send(packet);

		byte[] bufResp = new byte[256];
		DatagramPacket packetRecvFromServer = new DatagramPacket(bufResp, bufResp.length);
		clientSock.receive(packetRecvFromServer);

		String response = new String(packetRecvFromServer.getData(), 0, packetRecvFromServer.getLength());
		System.out.println("Client received from Server = " + response);

		clientSock.close();
	}

	public static void main(String[] args) {
		try {
			udpClient(args);
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
}