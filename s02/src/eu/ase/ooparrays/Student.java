package eu.ase.ooparrays;

import java.util.Arrays;

public class Student extends Object {
    public String name;
    public short[] marks;
    public float avgMark;
    public static int noStud;

    public Student() {

    }

    public Student(String name, short[] marks) {
        this.name = name;
        this.marks = marks;
        Student.noStud++;
        avgMark = calcAvgMarks();
    }

    public void setMarks(short[] marks) {
        this.marks = marks;
        this.avgMark = calcAvgMarks();
    }

    public float getAvgMark() {
        return this.avgMark;
    }

    public float calcAvgMarks() {
        float result = 0.0f;
        if(marks == null || marks.length == 0) {
            return 0.0f;
        }

        for(int i = 0; i < marks.length; i ++) {
            result += this.marks[i];
        }

        result = result / marks.length;

        return result;
    }

    @Override
    public String toString() {
        return "Student: name=" + name + ", marks=" + Arrays.toString(marks) + ", avgMark=" + avgMark;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) {
            return true;
        }
        if(obj == null) {
            return false;
        }
        if(getClass() != obj.getClass()) {
            return false;
        }
        Student other = (Student) obj;
        if(!Arrays.equals(marks, other.marks)) {
            return false;
        }
        if(Float.floatToIntBits(avgMark) != Float.floatToIntBits(other.avgMark)) {
            return false;
        }
        if(name == null) {
            if(other.name != null) {
                return false;
            }
        } else {
            if(!name.equals(other.name)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        int result = 0;

        if(name != null) {
            result += name.hashCode();
        }
        if(marks != null) {
            result += Arrays.hashCode(marks);
        }
        result += Float.floatToIntBits(avgMark);

        return result;
    }

}
