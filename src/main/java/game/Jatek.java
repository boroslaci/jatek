package game;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Jatek {
    private Board board;
    private Player player1;
    private Player player2;
    private Player currentPlayer;

    private int player1Wins = 0;
    private int player2Wins = 0;

    public Jatek(String player1Name, String player2Name) {
        this.board = new Board();
        this.player1 = new Player(player1Name, "y");
        this.player2 = new Player(player2Name, "r");
        this.currentPlayer = player1;
    }

    public Board getBoard() {
        return board;
    }

    public int getPlayer1Wins() {
        return player1Wins;
    }

    public int getPlayer2Wins() {
        return player2Wins;
    }

    public void player1Wins() {
        player1Wins++;
    }

    public void player2Wins() {
        player2Wins++;
    }

    public void startGame() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            board.printBoard();
            System.out.println(currentPlayer.getName() + " (" + currentPlayer.getColor() + ") lépése:");

            System.out.print("Válaszd az oszlopot (A-G): ");
            String input = scanner.nextLine();

            int column = getColumnIndex(input);
            if (column != -1 && board.makeMove(column, currentPlayer.getColor())) {
                if (board.checkWin(currentPlayer.getColor())) {
                    board.printBoard();
                    System.out.println(currentPlayer.getName() + " nyert!");

                    if (currentPlayer == player1) {
                        player1Wins++;
                    } else {
                        player2Wins++;
                    }

                    saveHighScores();

                    break;
                }
                currentPlayer = (currentPlayer == player1) ? player2 : player1;
            } else {
                System.out.println("Érvénytelen lépés, próbáld újra.");
            }
        }

        scanner.close();
    }

    private int getColumnIndex(String input) {
        switch (input.toUpperCase()) {
            case "A": return 0;
            case "B": return 1;
            case "C": return 2;
            case "D": return 3;
            case "E": return 4;
            case "F": return 5;
            case "G": return 6;
            default: return -1;
        }
    }

    private void saveHighScores() {
        try (FileWriter writer = new FileWriter("highscores.txt", true)) {
            writer.write("Játékos 1 (" + player1.getName() + "): " + player1Wins + " győzelem\n");
            writer.write("Játékos 2 (" + player2.getName() + "): " + player2Wins + " győzelem\n");
            writer.write("---------------------------\n");
            System.out.println("A nyerési statisztikák mentésre kerültek a highscores.txt fájlba.");
        } catch (IOException e) {

        }
    }
}
