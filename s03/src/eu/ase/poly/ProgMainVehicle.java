package eu.ase.poly;

public class ProgMainVehicle {

    public static void main(String[] args) {
        Vehicle v = null;
        Auto a = null;
        try {
            a = new Auto(1200, 5);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        Plane p = new Plane(15000, 12, 2);

        v = a;
        System.out.println(v.display());

        v = p;
        System.out.println(v.display());

        Vehicle v0 = null;
        // v0 = a; // Auto -> Vehicle - upcast (implicit)
        // try-catch mechanism

        /* incercam sa facem downcast la Plane
         eroarea apare deoarece obiectul real este Auto, nu Plane.
         JVM verifica tipul obiectului la runtime și arunca ClassCastException
         daca incercam sa convertim catre un tip incompatibil.
         comentam linia v0 = a sau scriem v0 = null => OK */
        try {
            p = (Plane) v0; // runtime error: Auto cannot be cast to Plane
        } catch (ClassCastException cce) {
            cce.printStackTrace();
        }

        Movement m = null;
        try {
            m = new Auto(2900, 4);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        // m.display(); // eroare la compilare
        m.startEngine();
    }

}