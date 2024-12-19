package ds.hw2;

public class Tower {
    public static void main(String[] args) {
        move(3, "A", "B", "C");
    }

    private static void move(int n, String a, String b, String c) {
        if (n == 1) {
            System.out.println("move 1 from " + a + " to " + c);
            return;
        }
        move(n-1, a, c, b);
        System.out.println("move " + n + " from " + a + " to " + c);
        move(n-1, b, a, c);
    }
}
