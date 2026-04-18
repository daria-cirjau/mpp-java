package eu.ase.threads;

import java.util.concurrent.*;

public class MainThreads {
    public static void main(String[] args) {
        HelloThread th01 = new HelloThread("Th01 Java 1.1 ...");
        // th01.run();
        th01.start();

//        try {
//            th01.join();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        HelloRunnable r02 = new HelloRunnable();
        Thread th02 = new Thread(r02, "Th02 Java 1.1...");
        th02.start();

        Runnable taskJ7 = new Runnable() {
            @Override
            public void run() {
                String name = Thread.currentThread().getName();
                System.out.println("Hello J7 " + name);
            }
        };
        Thread th03 = new Thread(taskJ7, "Th03 Java 7...");
        th03.start();

        Runnable taskJ8 = () -> {
            String name = Thread.currentThread().getName();
            System.out.println("Hello J8 " + name);
        };
        Thread th04 = new Thread(taskJ8, "Th04 Java8...");

        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.submit(taskJ8);
        executorService.submit(taskJ8);

        try {
            executorService.shutdown();
            executorService.awaitTermination(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            System.out.println("taskinterrupted");
        } finally {
            if (!executorService.isTerminated()) {
                System.out.println("cancel non-finished tasks");
            }
            executorService.shutdownNow();
        }

        ExecutorService executorService4FC = Executors.newFixedThreadPool(1);
        Callable<Integer> taskCallable = () -> {
            try {
                TimeUnit.SECONDS.sleep(2);
                return 105;
            } catch (InterruptedException e) {
                throw new IllegalStateException("task callable interrupted ", e);
            }
        };
        Future<Integer> future = executorService4FC.submit(taskCallable);
        try {
            Integer result = future.get();
            System.out.println("result = " + result);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        try {
            executorService4FC.shutdown();
            executorService4FC.awaitTermination(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            System.out.println("taskinterrupted");
        } finally {
            if (!executorService4FC.isTerminated()) {
                System.out.println("cancel non-finished tasks");
            }
            executorService4FC.shutdownNow();
        }

        System.out.println("Nb of cores: " + VirtualThreadsPlayground.numberOfCores());
        VirtualThreadsPlayground.concurrentMorningRoutine();

        Runnable taskJ19 = () -> {
            String name = Thread.currentThread().getName();
            System.out.println("Hello J19 " + name);
        };
        Thread tj19 = Thread.ofVirtual().name("virtualThread").unstarted(taskJ19);
        tj19.start();
    }
}
