package ds.hw2;

public class Fibonacci {
    public static void main(String[] args) {
        System.out.println(getFibonacciByI(10));
        System.out.println(getFibonacciByR(10));
    }

    private static int getFibonacciByI(int n) {
        if (n <= 1){
            return n;
        }
        int first = 0;
        int second = 1;
        int temp;
        for(int i = 2; i <= n; i++){
            temp = first;
            first = second;
            second = temp+first;
        }
        return second;
    }

    private static int getFibonacciByR(int n) {
        if (n <= 1){
            return n;
        }
        return getFibonacciByR(n-1) + getFibonacciByR(n - 2);
    }
}
