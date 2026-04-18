package eu.ase.threads;

public class ThreadSync extends Thread {
    private static int a = 0;
    private static int b = 0;
    private static final Object myLock = new Object();

    public ThreadSync(String name) {
        super(name);
    }

    public void myMethod() {
        synchronized (myLock) {
            System.out.println("Thread = " + this.getName() + "; a = " + a + ";b = " + b);
            a++;
            try {
                sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            b++;
        }
    }

    @Override
    public void run() {
        for(int i = 0; i < 3; i ++) {
            this.myMethod();;
        }
    }

}
