package eu.ase.streams_lambda;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Random;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Java8ProcessingStreams {
    public static void main(String[] args) {
        List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd", "");
        long tstart = 0L, tstop = 0L;
        int co1 = 0;

        tstart = System.nanoTime();
        for(int i = 0; i < strings.size(); i++) {
            if(strings.get(i) != null && strings.get(i).length() == 0) {
                co1++;
            }
        }
        tstop = System.nanoTime();
        System.out.println("co1 " + co1 + " nb if seconds = " + (tstop - tstart));

        tstart = System.nanoTime();
        int co2 = 0;
        for(String s: strings) {
            if(s.isEmpty()) {
                co2++;
            }
        }
        tstop = System.nanoTime();
        System.out.println("co2 " + co2 + " nb if seconds = " + (tstop - tstart));

        Predicate<String> predicateEmptyString = (String s) -> {
            boolean result = s.isEmpty();
            return result;
        };

        tstart = System.nanoTime();
        long countEmptyStrings = strings.stream().filter(predicateEmptyString).count();
        tstop = System.nanoTime();
        System.out.println("count " + countEmptyStrings + " nb if seconds = " + (tstop - tstart));

        tstart = System.nanoTime();
        long count = strings.stream().filter(s -> s.isEmpty()).count();
        tstop = System.nanoTime();
        System.out.println("count " + count + " nb if seconds = " + (tstop - tstart));

        tstart = System.nanoTime();
        count = strings.parallelStream().filter(s -> s.isEmpty()).count();
        tstop = System.nanoTime();
        System.out.println("count " + count + " nb if seconds = " + (tstop - tstart));

        count = strings.stream().filter(s -> s.length() == 3).count();
        System.out.println("strings of length 3: " + count);

        List<String> filtered = strings.stream().filter(s -> !s.isEmpty()).collect(Collectors.toList());
        System.out.println("Filtered list: " + filtered);

        String mergedString = strings.stream().filter(s -> !s.isEmpty()).collect(Collectors.joining("; "));
        System.out.println("Merged string: " + mergedString);

        List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);
        List<Integer> squares = numbers.stream().map(i -> i * i).distinct().collect(Collectors.toList());
        System.out.println("Squares list: " + squares);

        List<Integer> integers = Arrays.asList(1, 2, 13, 4, 15, 6, 17, 8, 19);
        IntSummaryStatistics stats = integers.stream().mapToInt(x -> x).summaryStatistics();

        System.out.println("max: " + stats.getMax());
        System.out.println("min: " + stats.getMin());
        System.out.println("avg: " + stats.getAverage());
        System.out.println("sum: " + stats.getSum());

        Random random = new Random();
//        for(int i = 0; i < 10; i ++) {
//            System.out.println(random.nextInt());
//        }
        random.ints().limit(10).sorted().forEach(System.out::println);

    }
}
