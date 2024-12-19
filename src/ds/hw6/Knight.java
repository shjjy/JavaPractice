package ds.hw6;

import java.util.stream.IntStream;

public class Knight {

    static int n = 5;
    static int[][] board = new int[n][n];
    static boolean hasSolution = true;
    static int[][] eightDirection = new int[][]{
            {-2, 1}, {-1, 2}, {1, 2}, {2, 1}, {2, -1}, {1, -2}, {-1, -2}, {-2, -1}
    };
    @SuppressWarnings("rawtypes")
    private static final MyStack stack = new MyStack<>(n * n);


    public static void main(String[] args) {
        initBoard();
        move();
        printBoard();
    }

    private static void initBoard() {
        for (int[] rows : board) {
            IntStream.range(0, board.length).forEach(j -> rows[j] = 0);
        }
    }

    private static void printBoard() {
        if (hasSolution) {
            for (int[] rows : board) {
                IntStream.range(0, board.length).forEach(j -> System.out.printf("%3d", rows[j]));
                System.out.println();
            }
        } else {
            System.out.println("No Solution");
        }
    }

    @SuppressWarnings("unchecked")
    private static void move() {
        int totalStep = n * n;
        int row = 0, col = 0, dirIndex = 0;
        board[row][col] = 1;
        boolean isBack = false;
        out:
        while (stack.size() < totalStep -1) {
            for (int i = dirIndex; i < (isBack?9:8); i++) {
                if(i == 8){
                    board[row][col] = 0;
                    int [] topStack = (int[]) stack.top();
                    row = topStack[0] - 1;
                    col = topStack[1] - 1;
                    dirIndex = topStack[2];
                    stack.pop();
                    isBack = true;
                    break;
                }
                int newRow = row + eightDirection[i][0];
                int newCol = col + eightDirection[i][1];
                boolean isCanMove = newRow >= 0 && newRow < n && newCol >= 0 && newCol < n && board[newRow][newCol] == 0;
                if (isCanMove) {
                    stack.push(new int[]{row + 1, col + 1, i + 1});
                    board[newRow][newCol] = stack.size() + 1;
                    row = newRow;
                    col = newCol;
                    dirIndex = 0;
                    isBack = false;
                    break;
                } else if (i == 7) {
                    if (stack.isEmpty()) {
                        hasSolution = false;
                        break out;
                    }
                    board[row][col] = 0;
                    int [] topStack = (int[]) stack.top();
                    row = topStack[0] - 1;
                    col = topStack[1] - 1;
                    dirIndex = topStack[2];
                    stack.pop();
                    isBack = true;
                    break;
                }
            }
        }
    }
}
