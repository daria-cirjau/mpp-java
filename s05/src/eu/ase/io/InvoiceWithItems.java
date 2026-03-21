package eu.ase.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class InvoiceWithItems {

    private List<InvoiceItem> items;

    public InvoiceWithItems(List<InvoiceItem> items) {
        this.items = items;
    }

    public void saveToFile(String fileName) {
        DataOutputStream out = null;

        try {
            out = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(fileName)));

            for (InvoiceItem item : items) {
                out.writeDouble(item.price);
                out.writeInt(item.unit);
                out.writeUTF(item.desc);
            }

            out.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<InvoiceItem> readFromFile(String fileName) {
        DataInputStream in = null;
        List<InvoiceItem> items = new ArrayList<>();

        try {
            in = new DataInputStream(new BufferedInputStream(new FileInputStream(fileName)));

            while (true) {
                try {
                    double price = in.readDouble();
                    int unit = in.readInt();
                    String desc = in.readUTF();

                    InvoiceItem item = new InvoiceItem(desc, unit, price);
                    items.add(item);

                    System.out.printf("\n Read item: %s, unit = %d, price = %f", item.desc, item.unit, item.price);

                } catch (EOFException e) {
                    break;
                }
            }

            in.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return items;
    }
}