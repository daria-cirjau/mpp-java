package eu.ase.jcf.classes;

public class Plane implements Comparable<Plane> {
    private int idPlane;
    private String type;
    private float capacity;

    public Plane(int idPlane, String type, float capacity) {
        this.idPlane = idPlane;
        this.type = type;
        this.capacity = capacity;
    }

    public void print() {
        System.out.println("Plane - id = " + this.idPlane + ", type = " + this.type + ", capacity = " + this.capacity);
    }

    @Override
    public int compareTo(Plane p) {
        if (this.idPlane == p.idPlane) {
            return 0;
        } else if (this.idPlane > p.idPlane) {
            return 1;
        } else {
            return -1;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Plane p)) return false;
        return p.type.equals(this.type) && (p.capacity == this.capacity) && (p.idPlane == this.idPlane);
    }

    @Override
    public int hashCode() {
        return 31 * 31 * idPlane + 31 * type.hashCode() + (int) capacity;
    }
}