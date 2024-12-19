package ds.hw1;

import java.util.Scanner;

public class Factorial {

    static int leftNonZeroIndex = 69;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int input = sc.nextInt();
        int[] result = new int[70];
        result[69] = 1;

        for (int i = 1; i <= input; i++) {
            multiply(result, i);
            System.out.print(i+"!=");
            printArray(result);
            System.out.println();
        }
        sc.close();
    }

    private static void multiply(int[] result, int n) {
        int carry = 0;
        for (int i = 69; i >= 0 && i >= leftNonZeroIndex; i--) {
            int nowIndexNum = result[i] * n + carry;//計算的位數
            result[i] = nowIndexNum % 10;//留在這一位
            carry = nowIndexNum / 10;//要進位
            if(i == leftNonZeroIndex && carry > 0) {//如果到邊界值(最左邊)，而且需要近位，才向左擴展
                leftNonZeroIndex --;
            }
        }
    }

    private static void printArray(int[] arr) {
        boolean print = false;
        for(int value : arr){
            if(value != 0){//左邊都是 0 不要印
                print = true;
            }
            if(print){
                System.out.print(value);
            }
        }
    }
}
