package eu.ase.arrays;

public class ProgMainBidimensionalArrays {

    public static void main(String[] args) {
        // Vrem sa calculam media fiecarui student dintr-o clasa
        // Avem 2 studenti si 3 discipline

        int studentsNo = 2;
        // numarul de studenti (linii)

        int lectNo = 3;
        // numarul de discipline (coloane)

        short[][] studentsMarksAtDisciplines = new short[][]{{5, 5, 9}, {9, 10, 9}};
        /*
         * Linia 0 -> studentul 0 -> {5, 5, 9}
         * Linia 1 -> studentul 1 -> {9, 10, 9}
         */

        float[] avgStudMark = new float[studentsNo];
        // Array unidimensional care tine media fiecarui student

        for (int i = 0; i < studentsNo; i++) {

            avgStudMark[i] = 0;
            for (int j = 0; j < lectNo; j++) {

                avgStudMark[i] += studentsMarksAtDisciplines[i][j];
            }

            avgStudMark[i] /= lectNo;
        }

        // Afisam mediile
        for (int i = 0; i < studentsNo; i++) {
            System.out.println("The average mark for the student " + i + " is = " + avgStudMark[i]);
        }
    }
}