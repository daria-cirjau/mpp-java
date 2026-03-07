package eu.ase.poly;

public class Auto extends Vehicle implements Cloneable {
    private int doorsNo;

    public Auto() {

    }

    public Auto(int weight, int doorsNo) throws Exception {
        super(weight);
        if (doorsNo < 0) {
            throw new Exception("The doorsNo must not be less than 0.");
        }
        this.doorsNo = doorsNo;
    }

    public int getDoorsNo() {
        return this.doorsNo;
    }

    public void setDoorsNo(int doorsNo) throws Exception {
        if (doorsNo < 0) {
            throw new Exception("The doorsNo must not be less than 0.");
        }

        this.doorsNo = doorsNo;
    }

    @Override
    public String display() {
        return "Auto - w = " + this.getWeight() + ", doorsNo = " + this.doorsNo; // folosim getWeight() ->
        // nu putem accesa weight direct pentru ca este PRIVATE in superclasa
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return (Auto) super.clone();
    }
}