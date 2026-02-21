package eu.ase.oop;

public class Certificate extends Object {
    private int id;
    private String name;

    public Certificate(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Certificate myClone() {
        Certificate newCert = new Certificate(this.id, this.name);
        return newCert;
    }

//    public boolean myCustomEquals(Certificate cert) {
//        if(this.id == cert.getId() && this.name.equals(cert.getName())) {
//            return true;
//        }
//        return false;
//    }

    @Override
    public boolean equals(Object obj) {
        if(this.id == ((Certificate) obj).getId() && this.name.equals(((Certificate) obj).getName())) {
            return true;
        }
        return false;
    }
}
