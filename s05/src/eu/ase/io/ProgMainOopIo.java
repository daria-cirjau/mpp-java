package eu.ase.io;

import java.util.ArrayList;
import java.util.List;

public class ProgMainOopIo {

	public static void main(String[] args) {
		double[] prices = new double[] {10, 11, 9};
		int[] units = new int[] {12, 8, 9};
		String[] descs = new String[] {"T-Shirt", "Mug", "Pen"};
		
		Invoice invoice = new Invoice(units, prices, descs);
		invoice.saveInvoice2File("test2.txt");
		invoice.readInvoiceFromFileAndCalcTotal("test2.txt");

		// varianta 2 (OOP)
		List<InvoiceItem> items = new ArrayList<>();

		items.add(new InvoiceItem("T-Shirt", 12, 19.99));
		items.add(new InvoiceItem("Mug", 8, 8.76));
		items.add(new InvoiceItem("Pen", 9, 15.89));

		InvoiceWithItems invoice2 = new InvoiceWithItems(items);

		invoice2.saveToFile("test_items.txt");
		List<InvoiceItem> returnedItems = invoice2.readFromFile("test_items.txt");

		for(InvoiceItem it: returnedItems) {
			System.out.println(it);
		}
	}

}
