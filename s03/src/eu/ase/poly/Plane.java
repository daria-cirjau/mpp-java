package eu.ase.poly;

public class Plane extends Vehicle {
    private float capacity;
    private int enginesNo;

    public Plane() {
    }

    public Plane(int weight, float capacity, int engineN) {
        super(weight);
        this.capacity = capacity;
        this.enginesNo = engineN;
    }

    @Override
    public String display() {
        return "Plane - w = " + this.getWeight() +
                ", capacity = " + this.capacity +
                ", engines no = " + this.enginesNo;
    }

}