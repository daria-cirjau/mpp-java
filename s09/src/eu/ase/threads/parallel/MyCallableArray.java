package eu.ase.threads.parallel;

import java.util.concurrent.Callable;

public class MyCallableArray implements Callable<Long> {
    private int[] vi = null;
    private int startC;
    private int stopC;
    private Long sum;

    public MyCallableArray(int[] v, int start, int stop) {
        this.vi = v;
        this.startC = start;
        this.stopC = stop;
    }

    @Override
    public Long call() {
        long s = 0;
        for (int i = startC; i <= stopC; i++) {
            s += this.vi[i];
        }
        this.sum = Long.valueOf(s);
        return this.sum;
    }
}