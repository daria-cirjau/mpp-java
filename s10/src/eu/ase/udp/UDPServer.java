package eu.ase.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Date;

public class UDPServer {

	public static void main(String[] args) {
		byte[] bRecv = null;
		byte[] bResp = null;

		try (DatagramSocket socket = new DatagramSocket(7778)) {
			System.out.println("My UDP DEIC/DICE Server is binding on port 7778");

			while (true) {
				bRecv = new byte[256];
				DatagramPacket packet = new DatagramPacket(bRecv, bRecv.length);
				socket.receive(packet);

				String request = new String(packet.getData(), 0, packet.getLength());
				System.out.println("UDP Client " + packet.getAddress() + ":" + packet.getPort()
						+ " sent to Server = " + request);

				String respS;
				if ("What date & time is it?".equals(request.trim())) {
					respS = new Date().toString();
				} else {
					respS = "I don't understand!";
				}

				bResp = respS.getBytes();

				InetAddress addrSender = packet.getAddress();
				int portSender = packet.getPort();

				DatagramPacket respPacket =
						new DatagramPacket(bResp, bResp.length, addrSender, portSender);
				socket.send(respPacket);
			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
}