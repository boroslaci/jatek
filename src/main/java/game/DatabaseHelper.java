package game;

import java.sql.*;

public class DatabaseHelper {
    private static final String URL = "jdbc:sqlite:connect4.db";

    public static void createTable() {
        String sql = "CREATE TABLE IF NOT EXISTS high_scores (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name TEXT NOT NULL," +
                "wins INTEGER NOT NULL DEFAULT 0);";

        try (Connection conn = DriverManager.getConnection(URL);
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println("Hiba a táblázat létrehozásakor: " + e.getMessage());
        }
    }

    public static void updatePlayerScore(String name, int wins) {
        String sql = "INSERT OR REPLACE INTO high_scores (name, wins) VALUES (?, ?)";

        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setInt(2, wins);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Hiba a játékos adatainak frissítésekor: " + e.getMessage());
        }
    }

    public static void displayHighScores() {
        String sql = "SELECT name, wins FROM high_scores ORDER BY wins DESC LIMIT 10";

        try (Connection conn = DriverManager.getConnection(URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            System.out.println("High Score:");
            while (rs.next()) {
                System.out.println(rs.getString("name") + ": " + rs.getInt("wins"));
            }
        } catch (SQLException e) {
            System.out.println("Hiba a high score táblázat megjelenítésekor: " + e.getMessage());
        }
    }
}
