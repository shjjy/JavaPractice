package ds.hw1;

public class MagicSquare {
    public static void main(String[] args) {
        int[] request = new int[]{1, 3, 5, 7, 9};
        for(int n : request) {
            System.out.println("Magic Number: " + n);
            printMagicSquare(makeMagicSquare(n));
            System.out.println();
        }
    }

    private static int[][] makeMagicSquare(int n) {
        int[][] magicSquare = new int[n][n];
        if(n == 1){
            magicSquare[0][0] = 1;
        }else{
            int row = 0, col = n/2;
            for (int i = 1; i <= n*n; i++) {
                magicSquare[row][col] = i;
                int nextR = (row -1 +n) %n;
                int nextC = (col -1 +n) %n;
                if(magicSquare[nextR][nextC] != 0){
                    row = (row+1)%n;
                }else{
                    row = nextR;
                    col = nextC;
                }
            }
        }
        return magicSquare;
    }

    private static void printMagicSquare(int[][] magicSquare) {
        for(int[] row : magicSquare){
            for(int value : row){
                System.out.printf("%3d ", value);
            }
            System.out.println();
        }
    }
}
