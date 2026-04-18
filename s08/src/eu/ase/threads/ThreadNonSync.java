package eu.ase.threads;

public class ThreadNonSync extends Thread {
    private static int a = 0;
    private static int b = 0;

    public ThreadNonSync(String name) {
        super(name);
    }

    public void myMethod() {
        System.out.println("Thread = " + this.getName() + "; a = " + a + ";b = " + b);
        a++;
        try {
            sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        b++;
    }

    @Override
    public void run() {
        for(int i = 0; i < 3; i ++) {
            this.myMethod();
        }
    }
}
