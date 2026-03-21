package eu.ase.io;

public class InvoiceItem {
    String desc;
    int unit;
    double price;

    public InvoiceItem(String desc, int unit, double price) {
        this.desc = desc;
        this.unit = unit;
        this.price = price;
    }

    @Override
    public String toString() {
        return "InvoiceItem{" +
                "desc='" + desc + '\'' +
                ", unit=" + unit +
                ", price=" + price +
                '}';
    }
}