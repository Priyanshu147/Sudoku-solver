import java.util.Scanner;

public class Sudoku {
    static boolean find = false;
    static String ANSI_RESET = "\u001B[0m";
    static String ANSI_YELLOW = "\u001B[33m";
    static String ANSI_RED = "\u001B[31m";
    static String ANSI_CYAN = "\u001B[36m";

    static void display(int[][] board) {

        find = true;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                String color;
                if ((i / 3 + j / 3) % 2 == 0) {
                    color = ANSI_CYAN;
                } else {
                    color = ANSI_YELLOW;
                }

                System.out.print(color + board[i][j] + ANSI_RESET + ANSI_RED + " | " + ANSI_RESET);
            }
            System.out.println();
            System.out.println(ANSI_RED + "--|---|---|---|---|---|---|---|---| " + ANSI_RESET);
        }
    }

    public static void solve(int[][] board, int i, int j) {
        if (i == board.length) {
            System.out.println();
            System.out.println("Solved Sudoku:-");
            System.out.println();
            display(board);
            return;
        } else {
            int next_i = 0;
            int next_j = 0;
            if (j == (board.length - 1)) {
                next_i = i + 1;
                next_j = 0;
            } else {
                next_i = i;
                next_j = j + 1;
            }
            if (board[i][j] != 0) {
                solve(board, next_i, next_j);
            } else {
                for (int k = 1; k <= 9; k++) {
                    if (isValid(board, i, j, k) == true) {
                        board[i][j] = k;
                        solve(board, next_i, next_j);
                        board[i][j] = 0;
                    }
                }
            }
        }
    }

    public static boolean isValid(int[][] board, int x, int y, int val) {
        for (int j = 0; j < board.length; j++) {
            if (board[x][j] == val) {
                return false;
            }
        }
        for (int i = 0; i < board.length; i++) {
            if (board[i][y] == val) {
                return false;
            }
        }
        int sub_Matrix_i = (x / 3) * 3;
        int sub_Matrix_j = (y / 3) * 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[sub_Matrix_i + i][sub_Matrix_j + j] == val) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String args[]) throws Exception {
        Scanner sc = new Scanner(System.in);

        int[][] board = {
                { 5, 3, 0, 0, 7, 0, 0, 0, 0 },
                { 6, 0, 0, 1, 9, 5, 0, 0, 0 },
                { 0, 9, 8, 0, 0, 0, 0, 6, 0 },
                { 8, 0, 0, 0, 6, 0, 0, 0, 3 },
                { 4, 0, 0, 8, 0, 3, 0, 0, 1 },
                { 7, 0, 0, 0, 2, 0, 0, 0, 6 },
                { 0, 6, 0, 0, 0, 0, 2, 8, 0 },
                { 0, 0, 0, 4, 1, 9, 0, 0, 5 },
                { 0, 0, 0, 0, 8, 0, 0, 7, 9 }
        };

        System.out.println("Entered sudoku:-");
        System.out.println();

        /* for scanner input
          int[][] board = new int[9][9];
          for (int i = 0; i < 9; i++) {
          for (int j = 0; j < 9; j++) {
          board[i][j] = sc.nextInt();
          
          }
          }
         */
        display(board);

        solve(board, 0, 0);
        if (!find) {
            System.out.println("No solution possible");
        }

        sc.close();
    }
}