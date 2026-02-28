package eu.ase.arrays;

public class ProgMainBidimensionalArrays {
    static void main(String[] args) {
        int studentNo = 2; // liniile
        int lectNo = 3; // coloane

        short[][] studMarks = new short[][] { {5, 5, 9}, {9, 10, 9} };
        // Linia 0 -> stud 0 -> {5, 5, 9}
        // Linia 1 -> stud 1 -> {9, 10, 9}

        float[] avgMarks = new float[studentNo];

        for(int i = 0; i < studentNo; i ++) { // i < studMarks.length
            avgMarks[i] = 0;
            // parcurgem disciplinele
            for(int j = 0; j < lectNo; j ++) { // j < studMarks[i].length
                avgMarks[i] = avgMarks[i] + studMarks[i][j];
                // avgMarks[i] += studMarks[i][j]
            }
            avgMarks[i] = avgMarks[i] / lectNo;
            // avgMarks[i] /= lectNo;
        }

        for(int i = 0; i < studentNo; i ++) {
            System.out.println("Student " + i + " avg " + avgMarks[i]);
        }
    }
}
