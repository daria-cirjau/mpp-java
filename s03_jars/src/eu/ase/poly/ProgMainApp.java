package eu.ase.poly;

import eu.ase.poly.Auto;
import eu.ase.poly.Vehicle;

public class ProgMainApp {
    public static void main(String[] args) throws Exception {
        Vehicle v = new Auto(1200, 5); // din JAR-ul creat anterior
        System.out.println(v.display());
    }
}