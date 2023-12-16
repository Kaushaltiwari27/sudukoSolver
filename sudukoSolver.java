import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class sudukoSolver {
    public static void main(String[] args) {
        int[][] board = {
                {0, 0, 8, 0, 0, 0, 0, 0, 0},
                {4, 9, 0, 1, 5, 7, 0, 0, 2},
                {0, 0, 3, 0, 0, 4, 1, 9, 0},
                {1, 8, 5, 0, 6, 0, 0, 2, 0},
                {0, 0, 0, 0, 2, 0, 0, 6, 0},
                {9, 6, 0, 4, 0, 5, 3, 0, 0},
                {0, 3, 0, 0, 7, 2, 0, 0, 4},
                {0, 4, 9, 0, 3, 0, 0, 5, 7},
                {8, 2, 7, 0, 0, 9, 0, 1, 3}
        };

        sudukoSolver solver = new sudukoSolver();
        boolean solved = solver.solve(board);

        if (solved) {
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    System.out.print(board[i][j] + " ");
                }
                System.out.println();
            }
        } else {
            System.out.println("No solution found!");
        }
    }

    private boolean solve(int[][] board) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == 0) {
                    List<Integer> possibleNumbers = getPossibleNumbers(board, i, j);
                    if (possibleNumbers.isEmpty()) {
                        return false;
                    }
                    for (int number : possibleNumbers) {
                        board[i][j] = number;
                        if (solve(board)) {
                            return true;
                        }
                    }
                    board[i][j] = 0;
                    return false;
                }
            }
        }
        return true;
    }

    private List<Integer> getPossibleNumbers(int[][] board, int row, int col) {
        Set<Integer> existingNumbers = new HashSet<>();
        for (int i = 0; i < 9; i++) {
            existingNumbers.add(board[row][i]);
            existingNumbers.add(board[i][col]);
        }
        int startRow = row - row % 3;
        int startCol = col - col % 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                existingNumbers.add(board[i + startRow][j + startCol]);
            }
        }
        List<Integer> possibleNumbers = new ArrayList<>();
        for (int i = 1; i <= 9; i++) {
            if (!existingNumbers.contains(i)) {
                possibleNumbers.add(i);
            }
        }
        return possibleNumbers;
    }
}