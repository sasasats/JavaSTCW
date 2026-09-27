package algo;

public class Fibonacci {
    public static void main(String[] args) {
        printAnswer(2);
        printAnswer(3);
        printAnswer(4);
        printAnswer(5);
        printAnswer(6);
    }

    private static void printAnswer(int n) {
        System.out.println(fib(n));
    }

    public static int fib(int n) {
        if (n <= 1) {
            return n;
        }

        return fib(n - 1) + fib(n - 2);
    }
}
