package eu.ase.io.serializable;

class MyResource implements AutoCloseable {

    public void doSomething() {
        System.out.println("working...");
    }

    @Override
    public void close() {
        System.out.println("resource closed");
    }
}