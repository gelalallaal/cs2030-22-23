import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.List;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;

class Main {
    private static boolean isPrime(int n) {
        if (n < 2) {
            return false;
        }
        return IntStream.rangeClosed(2, n / 2).noneMatch(i -> n % i == 0);
    }

    static IntStream twinPrimes(int n) {
        return IntStream.rangeClosed(2, n)
                .filter(Main::isPrime)
                .filter(i -> isPrime(i + 2) || isPrime(i - 2));
    }

    static String reverse(String str) {
        return Stream.<String>of(str.split(""))
                .reduce("", (a, b) -> b + a);
    }

    static long countRepeats(List<Integer> list) {
        IntStream repeats = IntStream.range(1, list.size() - 1)
                            .filter(i -> list.get(i).equals(list.get(i - 1))
                            && !list.get(i).equals(list.get(i + 1)));
        return repeats.count();
    }

    static UnaryOperator<List<Integer>> generateRule() {
        return list -> list.stream().map(x -> List.<Integer>of(x, 0))
        .collect(() -> List.<List<Integer>>of(), (acc, x) -> {
            if (acc.isEmpty()) {
                acc.add(x);
                return;
            }

            List<Integer> prevElement = acc.get(acc.size() - 1);
            if (prevElement.get(0) == 1) {
                x.set(1, x.get(1) + 1);
            } else if (x.get(0) == 1) {
                prevElement.set(1, prevElement.get(1) + 1);
            }
            acc.add(x);
        }, (x, y) -> {
            List<Integer> lastElement = x.get(x.size() - 1);
            List<Integer> firstElement = y.get(0);
            if (lastElement.get(0) == 1) {
                firstElement.set(1, firstElement.get(1) + 1);
            } else if (firstElement.get(0) == 1) {
                lastElement.set(1, lastElement.get(1) + 1);
            }
            x.addAll(y);
        }).stream().map(x -> x.get(1) == 1 ? 1 : 0).collect(Collectors.toList());
        // return list -> list.stream()
        // .reduce()
    }


    static Stream<String> gameOfLife(List<Integer> list,
        UnaryOperator<List<Integer>> rule, int n) {
        System.out.println(Stream.iterate(list, rule).getClass());
        return Stream.iterate(list, rule)
                .limit(n)
                .map(l -> l.stream().map(i -> i == 1 ? "x" : ".").collect(Collectors.joining()));
    }
}
