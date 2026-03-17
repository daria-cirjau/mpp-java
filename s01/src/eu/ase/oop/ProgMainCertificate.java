package eu.ase.oop;

public class ProgMainCertificate {
    public static void main(String[] args) {
        Certificate c1 = new Certificate(777, "C1");
        Certificate c2 = new Certificate(999, "C2");

        int x = 10;

//       System.out.println("c1: " + c1.getId() + " " + c1.getName());
//       System.out.println("c2: " + c2.getId() + " " + c2.getName());

        c2 = c1;
        c2.setId(333);

        System.out.println("c1: " + c1.getId() + " " + c1.getName());
        System.out.println("c2: " + c2.getId() + " " + c2.getName());

        Certificate c3 = c1.myClone();
        System.out.println("c3: " + c3.getId() + " " + c3.getName());
        System.out.println(c1 + " " + c2 + " " + c3);
        c3.setId(444);

        System.out.println("c1: " + c1.getId() + " " + c1.getName());
        System.out.println("c3: " + c3.getId() + " " + c3.getName());

        boolean eq = c1 == c2;
        System.out.println("c1 == c2 " + eq);
        eq = c1 == c3;
        System.out.println("c1 == c3 " + eq);

//        boolean equals = c1.myCustomEquals(c3);
//        System.out.println("c1 equals c3 " + equals);
        boolean equals = c1.equals(c3);
        System.out.println("c1 equals c3 " + equals);
    }

}
