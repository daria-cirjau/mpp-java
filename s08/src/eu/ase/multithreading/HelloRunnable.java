package eu.ase.multithreading;

class HelloRunnable /* extends OtherClass */ implements Runnable {
    @Override
    public void run() {
        String name = Thread.currentThread().getName();
        System.out.println("Hello " + name);
    }
}