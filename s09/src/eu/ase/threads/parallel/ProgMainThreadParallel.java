package eu.ase.threads.parallel;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class ProgMainThreadParallel {
    private static final int NTHREADS = 4; // 8 or 4 or 2

    public static void main(String[] args) {
        int dimVect = 40_000_000;

        int[] v = new int[dimVect];
        Long sum = Long.valueOf(0);

        for (int i = 0; i < dimVect; i++) {
            v[i] = 1 + i;
        }

        int startIdx;
        int stopIdx;
        long startTime;
        long stopTime;

        // 1. Sequential
        sum = 0L;
        startTime = System.currentTimeMillis();

        for (int i = 0; i < dimVect; i++) {
            sum += v[i];
        }

        stopTime = System.currentTimeMillis();
        System.out.println("1. Seq time = " + (stopTime - startTime)
                + " , sum = " + sum);

        // 2. Multi-threading standard
        sum = Long.valueOf(0);
        startTime = System.currentTimeMillis();

        Thread[] vectThreads = new Thread[NTHREADS];
        MyMultiThreadArray[] vectTasks = new MyMultiThreadArray[NTHREADS];

        for (int it = 0; it < NTHREADS; it++) {
            startIdx = it * (dimVect / NTHREADS);
            stopIdx = (it + 1) * (dimVect / NTHREADS) - 1;

            vectTasks[it] = new MyMultiThreadArray(v, startIdx, stopIdx);
            vectThreads[it] = new Thread(vectTasks[it]);
        }

        for (int it = 0; it < NTHREADS; it++) {
            vectThreads[it].start();
        }

        for (int it = 0; it < NTHREADS; it++) {
            try {
                vectThreads[it].join();
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }
        }

        for (int it = 0; it < NTHREADS; it++) {
            sum += vectTasks[it].getSum();
        }

        stopTime = System.currentTimeMillis();
        System.out.println("2. MultiThread Standard Time = " + (stopTime - startTime)
                + " , sum = " + sum);

        // 3. Multi-threading executor-service
        sum = Long.valueOf(0);
        startTime = System.currentTimeMillis();

        ExecutorService execThreadPool = Executors.newFixedThreadPool(NTHREADS);
        MyMultiThreadArray[] workerTask = new MyMultiThreadArray[NTHREADS];

        for (int it = 0; it < NTHREADS; it++) {
            startIdx = it * (dimVect / NTHREADS);
            stopIdx = (it + 1) * (dimVect / NTHREADS) - 1;

            workerTask[it] = new MyMultiThreadArray(v, startIdx, stopIdx);
            execThreadPool.execute(workerTask[it]);
        }

        execThreadPool.shutdown();
        try {
            execThreadPool.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }

        for (int it = 0; it < NTHREADS; it++) {
            sum += workerTask[it].getSum();
        }

        stopTime = System.currentTimeMillis();
        System.out.println("3. MultiThread Executor-Service time = " + (stopTime - startTime)
                + " , sum = " + sum);

        // 4. Future - Callable mechanism
        ExecutorService executor = Executors.newFixedThreadPool(NTHREADS);
        List<Future<Long>> list = new ArrayList<Future<Long>>();

        sum = Long.valueOf(0);
        startTime = System.currentTimeMillis();

        for (int it = 0; it < NTHREADS; it++) {
            startIdx = it * (dimVect / NTHREADS);
            stopIdx = (it + 1) * (dimVect / NTHREADS) - 1;

            Callable<Long> worker = new MyCallableArray(v, startIdx, stopIdx);
            Future<Long> submit = executor.submit(worker);
            list.add(submit);
        }

        for (Future<Long> future : list) {
            try {
                sum += future.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException ee) {
                ee.printStackTrace();
            }
        }

        executor.shutdown();
        try {
            executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        stopTime = System.currentTimeMillis();
        System.out.println("4. Array Multithreading - thread pool (ExecutorService) + Callable & Future - stopTime - startTime = "
                + (stopTime - startTime) + " , sum = " + sum);

        // 5. Fork-Join
        startTime = System.currentTimeMillis();
        long sumForkJoin = SumForkJoin.sumArrays(v);
        stopTime = System.currentTimeMillis();

        System.out.println("5. Fork-Join Parallel Array time = "
                + (stopTime - startTime) + " , sum = " + sumForkJoin);

        // 6. Virtual Threads
        sum = Long.valueOf(0);
        startTime = System.currentTimeMillis();

        MyMultiThreadArray[] vectVirtRThreads = new MyMultiThreadArray[NTHREADS];

        try (ExecutorService executorServ =
                     Executors.newVirtualThreadPerTaskExecutor()) {

            for (int it = 0; it < NTHREADS; it++) {
                startIdx = it * (dimVect / NTHREADS);
                stopIdx = (it + 1) * (dimVect / NTHREADS) - 1;

                vectVirtRThreads[it] = new MyMultiThreadArray(v, startIdx, stopIdx);
                executorServ.execute(vectVirtRThreads[it]);
            }
        }

        for (int it = 0; it < NTHREADS; it++) {
            sum += vectVirtRThreads[it].getSum();
        }

        stopTime = System.currentTimeMillis();
        System.out.println("6. Virtual Threads = "
                + (stopTime - startTime) + " , sum = " + sum);
    }
}