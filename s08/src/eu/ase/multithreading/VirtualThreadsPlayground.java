package eu.ase.multithreading;

import java.time.Duration;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

class VirtualThreadsPlayground {
    // https://blog.rockthejvm.com/ultimate-guide-to-java-virtual-threads/
    // MacOS - get CPU cores: sysctl hw.physicalcpu hw.logicalcpu
    // Linux - get CPU: lscpu
    // put in Run cofig: --enable-preview and Java 19

    static int numberOfCores() {
        return Runtime.getRuntime().availableProcessors();
    }

    static void concurrentMorningRoutineUsingExecutorsWithName() {
        final ThreadFactory factory = Thread.ofVirtual().name("routine-", 0).factory();
        try (var executor = Executors.newThreadPerTaskExecutor(factory)) {

            var bathTime = executor.submit(() -> {
                // breakpoint here:
                System.out.printf("\n %s - I'm going to take a bath", Thread.currentThread().getName());
                try {
                    Thread.sleep(Duration.ofMillis(500L));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.printf("\n %s - I'm done with the bath", Thread.currentThread().getName());
            });

            var boilingWater = executor.submit(() -> {
                // breakpoint here:
                System.out.printf("\n %s - I'm going to boil some water", Thread.currentThread().getName());
                try {
                    Thread.sleep(Duration.ofSeconds(1L));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.printf("\n %s - I'm done with the water", Thread.currentThread().getName());
            });

            try {
                // breakpoints here:
                bathTime.get();
                boilingWater.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }

        }
    }
}
