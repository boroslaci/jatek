package game;

import java.io.*;
import java.util.Arrays;

public class Board {
    private final int N = 6;
    private final int M = 7;
    private String[][] board;

    public Board() {
        board = new String[N][M];
        for (String[] row : board) {
            Arrays.fill(row, " ");
        }
    }

    public void printBoard() {
        System.out.println("  A B C D E F G");
        for (int i = 0; i < N; i++) {
            System.out.print((i + 1) + " ");
            for (int j = 0; j < M; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void saveToFile(String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    writer.write(board[i][j]);
                    if (j < M - 1) writer.write(" ");
                }
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Hiba a fájl mentés közben.");
        }
    }

    public void loadFromFile(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            for (int i = 0; i < N; i++) {
                String line = reader.readLine();
                if (line != null) {
                    String[] cells = line.split(" ");
                    for (int j = 0; j < M; j++) {
                        board[i][j] = cells[j];
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Hiba a fájl beolvasása közben.");
        }
    }

    public boolean makeMove(int column, String color) {
        if (column < 0 || column >= M) {
            System.out.println("Érvénytelen oszlop!");
            return false;
        }
        for (int i = N - 1; i >= 0; i--) {
            if (board[i][column].equals(" ")) {
                board[i][column] = color;
                return true;
            }
        }
        System.out.println("Az oszlop már tele van.");
        return false;
    }

    public boolean checkWin(String color) {
        return checkVertical(color) || checkHorizontal(color) || checkDiagonal(color);
    }

    private boolean checkVertical(String color) {
        for (int col = 0; col < M; col++) {
            for (int row = 0; row < N - 3; row++) {
                if (board[row][col].equals(color) &&
                        board[row + 1][col].equals(color) &&
                        board[row + 2][col].equals(color) &&
                        board[row + 3][col].equals(color)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean checkHorizontal(String color) {
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < M - 3; col++) {
                if (board[row][col].equals(color) &&
                        board[row][col + 1].equals(color) &&
                        board[row][col + 2].equals(color) &&
                        board[row][col + 3].equals(color)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean checkDiagonal(String color) {
        for (int row = 0; row < N - 3; row++) {
            for (int col = 0; col < M - 3; col++) {
                if (board[row][col].equals(color) &&
                        board[row + 1][col + 1].equals(color) &&
                        board[row + 2][col + 2].equals(color) &&
                        board[row + 3][col + 3].equals(color)) {
                    return true;
                }
            }
        }

        for (int row = 3; row < N; row++) {
            for (int col = 0; col < M - 3; col++) {
                if (board[row][col].equals(color) &&
                        board[row - 1][col + 1].equals(color) &&
                        board[row - 2][col + 2].equals(color) &&
                        board[row - 3][col + 3].equals(color)) {
                    return true;
                }
            }
        }

        return false;
    }
}
