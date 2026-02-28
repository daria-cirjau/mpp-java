package eu.ase.arrays;

public class ProgMainArrays {
    public static void main(String[] args) {
        int[] v;
        v = new int[5];
        int[] n = {10, 20, 30};
        n[0] = 10;
        n[1] = 20;
        // 0 -> v.length - 1
        // System.out.println(v[5]);
        for(int i = 0; i < v.length - 1; i++) {
            System.out.println("index: " + i + " val: " + v[i]);
        }
        v[2] = 20;
        v[3] = 40;
        for(int x: v) {
            System.out.println(x);
        }
        int[] v2 = v;
        v2 = java.util.Arrays.copyOf(v, v.length);
        System.arraycopy(v, 0, v2,  0, v.length);
        modifyArray(v);
        System.out.println("v dupa modifyArray: " + v);
        for(int i = 0; i < v.length - 1; i++) {
            System.out.println("index: " + i + " val: " + v[i]);
        }
    }

    public static void modifyArray(int[] arr) {
        arr[0] = 999;
    }
}
