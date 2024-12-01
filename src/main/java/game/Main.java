package game;

import java.io.*;
import java.sql.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Kérem az első játékos nevét: ");
        String player1Name = scanner.nextLine();

        System.out.print("Kérem a második játékos nevét: ");
        String player2Name = scanner.nextLine();

        Jatek jatek = new Jatek(player1Name, player2Name);

        String fileName = "oard.txt";
        jatek.getBoard().loadFromFile(fileName);

        jatek.startGame();

        askToSave(scanner, jatek);

        savePlayerScore(player1Name, jatek.getPlayer1Wins());
        savePlayerScore(player2Name, jatek.getPlayer2Wins());

        displayHighScores();
    }

    private static void askToSave(Scanner scanner, Jatek jatek) {
        System.out.println("Mentsem a játék állását (igen/nem)?");

        while (true) {
            try {
                if (scanner.hasNextLine()) {
                    String response = scanner.nextLine().toLowerCase().trim();

                    if (response.equals("igen")) {

                        System.out.println("A játék állását menteni fogom.");
                        jatek.getBoard().saveToFile("board.txt");
                        break;
                    } else if (response.equals("nem")) {

                        System.out.println("A játék állása nem lett mentve.");
                        break;
                    } else {

                        System.out.println("Hibás válasz. Kérlek, írd be, hogy 'igen' vagy 'nem'.");
                    }
                }
            } catch (Exception e) {
                System.out.println("Hiba történt a bemenet olvasása közben. Kérlek próbáld újra!");
            }
        }
    }

    private static void savePlayerScore(String playerName, int wins) {
        DatabaseHelper.updatePlayerScore(playerName, wins);
    }

    private static void displayHighScores() {
        DatabaseHelper.displayHighScores();
    }
}
