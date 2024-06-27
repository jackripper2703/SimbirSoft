package utils;

import java.time.LocalDate;

public class Fibonacci {

    public static long fibonacci() {
        LocalDate today = LocalDate.now();
        int dayOfMonth = today.getDayOfMonth();
        int n = dayOfMonth + 1;

        if (n <= 0) {
            throw new IllegalArgumentException("N должно быть больше нуля");
        }
        if (n == 1 || n == 2) {
            return 1;
        }

        long a = 1;
        long b = 1;
        long fib = 0;

        for (int i = 3; i < n; i++) {
            fib = a + b;
            a = b;
            b = fib;
        }

        return fib;
    }
}
