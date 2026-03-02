package eu.ase.ooparrays;

public class ProgMainOOPArrays {

    public static void main(String[] args) {
        Student[] students = new Student[2];

        students[0] = new Student("S0", new short[]{5, 5, 9});
        students[1] = new Student("S1", new short[]{9, 10, 9});

        for (int i = 0; i < 2; i++) {
            System.out.println("The average mark for the student " + i + " is = " + students[i].getAverageMark());
        }

        // toString
        System.out.println(students[0]);

        // equals + hashCode
        Student copy = new Student("S0", new short[]{5, 5, 9});
        System.out.println("students[0] == copy ? " + (students[0] == copy));
        System.out.println("students[0].equals(copy) ? " + students[0].equals(copy));
        System.out.println("hash students[0] = " + students[0].hashCode());
        System.out.println("hash copy = " + copy.hashCode());

        // clone
        try {
            Student cloned = (Student) students[0].clone();
            System.out.println("cloned = " + cloned);
            System.out.println("students[0] == cloned ? " + (students[0] == cloned));
            System.out.println("students[0].equals(cloned) ? " + students[0].equals(cloned));
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }
}