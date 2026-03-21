package eu.ase.io;

import java.io.*;

public class ProgMainIo {
	public static void main(String[] args) {
		double[] prices = new double[] {19.99, 8.76, 15.89};
		int[] units = new int[] {12, 8, 9};
		String[] descs = new String[] {"T-Shirt", "Mug", "Pen"};
		
		DataOutputStream out = null;
		DataInputStream in = null;
		
		try {
			FileOutputStream fos = new FileOutputStream("test.txt");
			BufferedOutputStream bos = new BufferedOutputStream(fos);
			out = new DataOutputStream(bos);
			
			for (int i = 0; i < prices.length; i++) {
				out.writeDouble(prices[i]);
				out.writeInt(units[i]);
				out.writeUTF(descs[i]);
			}
			
			out.close();
			
			
			in = new DataInputStream(new BufferedInputStream(new FileInputStream("test.txt")));
			double price; int unit; String desc;
			double total = 0.0;
			
			try {
				while (true) {
					price = in.readDouble();
					unit = in.readInt();
					desc = in.readUTF();
					total += (unit * price);
					System.out.printf("\n Read record: %s, unit = %d, price = %f", desc, unit, price);
				}
			} catch(EOFException eofe) {
				System.out.println("\n Total = " + total);
			}


			BufferedWriter bw = new BufferedWriter(new FileWriter("invoice_text.txt")); // FileWriter → deschide fisierul, BufferedWriter → scrie eficient (buffer)
			for (int i = 0; i < prices.length; i++) {
				bw.write(descs[i] + "," + units[i] + "," + prices[i]); // scrie text
				bw.newLine(); // adauga o linie noua
			}
			bw.close();

			BufferedReader br = new BufferedReader(new FileReader("invoice_text.txt"));
			String line;
			total = 0.0;
			while ((line = br.readLine()) != null) {

				String[] parts = line.split(",");
				desc = parts[0];
				unit = Integer.parseInt(parts[1]);
				price = Double.parseDouble(parts[2]);

				total += unit * price;

				System.out.printf("Read record: %s, unit = %d, price = %f%n", desc, unit, price);
			}
			System.out.println("Total = " + total);
			br.close();

		} catch(IOException ioe) {
			ioe.printStackTrace();
		}
	}
}






