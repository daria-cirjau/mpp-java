package eu.ase.io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class Invoice implements Cloneable {
	
	private double[] prices;
	private int[] units;
	private String[] descs;
	
	public Invoice(int[] units, double[] prices, String[] productsDesc) {
		this.units = units;
		this.prices = prices;
		this.descs = productsDesc;
	}

	public void saveInvoice2File(String invoiceFileName) {
		DataOutputStream out = null;
		
		try {
			FileOutputStream fos = new FileOutputStream(invoiceFileName);
			BufferedOutputStream bos = new BufferedOutputStream(fos);
			out = new DataOutputStream(bos);
			
			for (int i = 0; i < prices.length; i++) {
				out.writeDouble(prices[i]);
				out.writeInt(units[i]);
				out.writeUTF(descs[i]);
			}
			
			out.close(); 
		} catch(IOException ioe) {
			ioe.printStackTrace();
		}
	}
	
	public double readInvoiceFromFileAndCalcTotal(String invoiceFileName) {
		double total = 0.0;
		DataInputStream in = null;
		
		try {
			in = new DataInputStream(new BufferedInputStream(
					new FileInputStream(invoiceFileName)));
			double price; int unit; String desc;
			
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
				in.close();
			}
			
		} catch(IOException ioe) {
			ioe.printStackTrace();
		}
		return total;
	}
	
	public double readInvoiceFromFileAndCalcTotalWithEx(String invoiceFileName)
			throws IOException {
		double total = 0.0;
		DataInputStream in = null;
		
			in = new DataInputStream(new BufferedInputStream(
					new FileInputStream(invoiceFileName)));
			double price; int unit; String desc;
			
			try {
				while (true) {
					price = in.readDouble();
					unit = in.readInt();
					desc = in.readUTF();
					total += (unit * price);
				}
			} catch(EOFException eofe) {
				System.out.println("\n Total = " + total);
				in.close();
			}

		return total;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		Invoice r = (Invoice) super.clone();

		r.prices = this.prices.clone();
		r.units = this.units.clone();
		r.descs = this.descs.clone();

		return r;
	}
}
