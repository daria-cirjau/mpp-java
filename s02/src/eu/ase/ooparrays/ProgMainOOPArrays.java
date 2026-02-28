package eu.ase.ooparrays;

public class ProgMainOOPArrays {
    public static void main(String[] args) {
        Student[] students = new Student[2];

        students[0] = new Student("S0", new short[] {5, 5, 9});
        students[1] = new Student("S1", new short[] {9, 10, 9});

        for(int i = 0; i < students.length; i ++) {
            System.out.println("Avg mark for student " + i + " is " + students[i].getAvgMark());
        }

        System.out.println(students[0].toString());
        System.out.println(students[0]);
        //System.out.println(students);

        Student newStudent = new Student("S0", new short[] {5, 5, 9});
        System.out.println("== " + (newStudent == students[0]));
        System.out.println("equals " + newStudent.equals(students[0]));
        System.out.println("hashCode s[0] " + students[0].hashCode());
        System.out.println("hashCode newStudent " + newStudent.hashCode());
    }
}
