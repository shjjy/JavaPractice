package ds.hw7;

import java.util.stream.IntStream;

public class KnightR {
    static int n = 5;
    static int[][] board = new int[n][n];
    static boolean hasSolution = false;
    static int[][] eightDirection = new int[][]{
            {-2, 1}, {-1, 2}, {1, 2}, {2, 1}, {2, -1}, {1, -2}, {-1, -2}, {-2, -1}
    };

    public static void main(String[] args) {
        initBoard();
        int row = 0, col = 0, solIndex = 2;
        board[row][col] = 1;
        if (move(row, col, solIndex)) {
            printBoard();
        } else {
            System.out.println("No Solution");
        }

    }

    private static void initBoard() {
        for (int[] rows : board) {
            IntStream.range(0, board.length).forEach(j -> rows[j] = 0);
        }
    }

    private static void printBoard() {
        for (int[] rows : board) {
            IntStream.range(0, board.length).forEach(j -> System.out.printf("%3d", rows[j]));
            System.out.println();
        }
    }

    private static boolean move(int row, int col, int solIndex) {
        if (solIndex == n * n + 1) {
            return true;
        }
        for (int[] direction : eightDirection) {
            int newRow = row + direction[0];
            int newCol = col + direction[1];
            if (newRow >= 0 && newRow < n && newCol >= 0 && newCol < n && board[newRow][newCol] == 0) {
                board[newRow][newCol] = solIndex;
                if (move(newRow, newCol, solIndex + 1)) {
                    return true;
                } else {
                    board[newRow][newCol] = 0;
                }
            }
        }
        return false;
    }
}
