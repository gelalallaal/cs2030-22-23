/open Log.java

public Log<Integer> sum(int n) {
    Log<Integer> sum = Log.<Integer>of(0, "hit base case!");
    for (int i = 1; i <= n; i++) {
        final int j = i;
        sum = sum.flatMap(x -> Log.<Integer>of(x + j, String.format("adding %d", j)));
    }
    return sum;
}

public Log<Integer> f(int n) {
    Log<Integer> result = Log.<Integer>of(n);
    if (n == 0) {
        return result.flatMap(x -> Log.<Integer>of(x, "1"));
    });

    result = result.flatMap(x -> {
        String s;
        if (x % 2 == 0) {
            s = String.format("%d / 2", x);
            x /= 2;
        } else {
            s = String.format("3(%d) + 1", x);
            x = 3 * x + 1;
        }
        // return Log.<Integer>of(x, s);
        return f(x);
    });
    // while (result.value() > 1) {
    //     result = result.flatMap(x -> {
    //         String s;
    //         if (x % 2 == 0) {
    //             s = String.format("%d / 2", x);
    //             x /= 2;
    //         } else {
    //             s = String.format("3(%d) + 1", x);
    //             x = 3 * x + 1;
    //         }
    //         return Log.<Integer>of(x, s);
    //     });
    // }
    return result.flatMap(x -> Log.<Integer>of(x, "1"));
}


