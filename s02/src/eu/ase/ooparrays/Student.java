package eu.ase.ooparrays;

import java.util.Arrays;

public class Student {

    private static int noStud;
    private String sName;
    private short[] marks;
    private float averageMark;

    public Student() {
    }

    public Student(String studentName, short[] studentMarks) {
        this.sName = studentName;
        this.marks = studentMarks;
        Student.noStud++;
        this.averageMark = this.calcAvgMark();   // calculez avgMark
    }

    public void setMarks(short[] marks) {
        this.marks = marks;
        this.averageMark = this.calcAvgMark();   // recalculez avgMark cand schimb marks
    }

    public float getAverageMark() {
        return averageMark;
    }

    private float calcAvgMark() {
        if (marks == null || marks.length == 0) {
            return 0.0f;
            // daca nu avem note, media este 0
            // evitam erorile si impartire la 0
        }

        float result = 0.0f;
        // variabila locala pentru medie

        for (int j = 0; j < marks.length; j++) {
            result = result + this.marks[j];
        }
        result = result / marks.length;

        return result;
    }

    @Override
    public int hashCode() {
        int result = 0;

        if (sName != null) {
            result = result + sName.hashCode();
            // daca numele nu este null, folosim hashCode din clasa String.
        }

        result = result + Float.floatToIntBits(averageMark);
        // Float.floatToIntBits transforma valoarea float intr-un int, astfel incat sa fie consistenta cu equals().

        if (marks != null) {
            result = result + Arrays.hashCode(marks);
            // Arrays.hashCode calculeaza hash pe baza elementelor din array.
        }

        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
            // daca este acelasi obiect (aceeasi referinta in memorie), atunci sunt egale
        }

        if (obj == null) {
            return false;
        }

        if (getClass() != obj.getClass()) {
            return false;
            // verificam sa fie aceeasi clasa
        }

        Student other = (Student) obj;
        // facem cast dupa ce stim sigur ca este Student

        if (Float.floatToIntBits(averageMark) !=
                Float.floatToIntBits(other.averageMark)) {
            return false;
        }

        if (!Arrays.equals(marks, other.marks)) {
            return false;
        }

        if (sName == null) {
            if (other.sName != null) {
                return false;
            }
        } else if (!sName.equals(other.sName)) {
            return false;
        }

        return true;
        // daca am trecut de toate verificarile, obiectele sunt egale
    }

    @Override
    public String toString() {
        return "Student [sName=" + sName + ", marks=" + Arrays.toString(marks) + ", averageMark=" + averageMark + "]";
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        Student cloneObj = new Student();
        // cream un obiect nou de tip Student. nu folosim referinta existenta, vrem un obiect diferit in heap

        cloneObj.sName = new String(this.sName);

        cloneObj.marks = this.marks.clone();
        // marks este array, deci trebuie copiat
        // daca am face doar: cloneObj.marks = this.marks;
        // am copia doar referinta (shallow copy)
        // clone() creeaza un array nou in heap cu aceleasi valori

        this.averageMark = this.calcAvgMark();
        cloneObj.averageMark = this.averageMark;

        Student.noStud++;
        // incrementam contorul pentru ca am creat un nou obiect Student

        return cloneObj;
        // returnam noul obiect
    }
}