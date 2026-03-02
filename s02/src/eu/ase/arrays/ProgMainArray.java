package eu.ase.arrays;

import java.util.Arrays;

public class ProgMainArray {

    public static void main(String[] args) {
        // Declarare array
        int[] v;

        // Initializare
        v = new int[5];
        // Toate valorile sunt 0 implicit

        System.out.println("Dupa initializare: " + Arrays.toString(v));

        // Setare valori manual
        v[0] = 10;
        v[1] = 20;
        v[2] = 30;

        System.out.println("Dupa setare valori: " + Arrays.toString(v));

        // Initializare rapida (shorthand)
        int[] v1 = {5, 6, 7};
        System.out.println("v1: " + Arrays.toString(v1));

        // Acces prin index
        System.out.println("Primul element v1[0] = " + v1[0]);
        System.out.println("Ultimul element v1[v1.length - 1] = "
                + v1[v1.length - 1]);

        // Parcurgere cu for clasic
        System.out.println("Parcurgere cu for clasic:");
        for (int i = 0; i < v1.length; i++) {
            System.out.println("v1[" + i + "] = " + v1[i]);
        }

        // Parcurgere cu enhanced for
        System.out.println("Parcurgere cu enhanced for:");
        for (int x : v1) {
            System.out.println("Element = " + x);
        }

        // Shallow copy
        int[] v2 = v1;
        v2[0] = 100;

        System.out.println("v1 dupa modificare prin v2: "
                + Arrays.toString(v1));

        // Deep copy - varianta 1
        int[] v3 = Arrays.copyOf(v1, v1.length);
        v3[1] = 200;

        System.out.println("v1 dupa deep copy: "
                + Arrays.toString(v1));
        System.out.println("v3: "
                + Arrays.toString(v3));

        // Deep copy - varianta 2
        int[] v4 = new int[v1.length];
        System.arraycopy(v1, 0, v4, 0, v1.length);

        System.out.println("v4 (copiat cu System.arraycopy): "
                + Arrays.toString(v4));

        // Pass by value (referinta)
        modifyArray(v1);
        System.out.println("v1 dupa modifyArray: "
                + Arrays.toString(v1));
    }

    public static void modifyArray(int[] arr) {
        arr[0] = 999; // modifica array-ul din heap

        // daca am scrie:
        // arr = new int[10];
        // am modifica doar referinta locala
    }
}