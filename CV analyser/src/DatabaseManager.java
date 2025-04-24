import java.sql.*;

public class DatabaseManager {
    private static final String DbURL = "jdbc:sqlite:CvAnalysis.db";

    //creates the table if it doesnt already exist
    public static void InitialiseDatabase() {
        try (Connection conn = DriverManager.getConnection(DbURL)){
            String sql = "CREATE TABLE IF NOT EXISTS CvAnalysis (" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "email TEXT NOT NULL, " +
                        "score REAL NOT NULL" +
                        ");";
            Statement stmnt = conn.createStatement();
            stmnt.execute(sql);
        } catch (SQLException e) {
            System.out.println("Database Initialisation Failed: " + e.getMessage());
        }
    }

    //inserting records
    public static void SaveAnalysisResult(String email, double score) {
        String sql = "INSERT INTO CvAnalysis (email, score) VALUES (?, ?)";

        try (Connection conn = DriverManager.getConnection(DbURL);
            PreparedStatement pstmnt = conn.prepareStatement(sql)) {
            pstmnt.setString(1, email);
            pstmnt.setDouble(2, score);
            pstmnt.executeUpdate();
            System.out.println("Results Saved to Database");
        } catch (SQLException e) {
            System.out.println("Database Save Failed: " + e.getMessage());
        }
    }

    public static void ViewAll() throws SQLException {
        String query = "SELECT * FROM CvAnalysis;";
        try (Connection conn = DriverManager.getConnection(DbURL);
            Statement stmnt = conn.createStatement();
            ResultSet rs = stmnt.executeQuery(query)) {

            System.out.println("\nStored CVs Results: ");
            System.out.println("------------------------------");

            while (rs.next()) {
                int id = rs.getInt("id");
                String email = rs.getString("email");
                double score = rs.getDouble("score");

                System.out.printf("ID: %d, Email: %s, Score: %.2f%%\n", id, email, score);
            }

            System.out.println("------------------------------\n");

        }catch (SQLException e) {
            throw new SQLException("Error while trying to view the database: " + e.getMessage());
        }
        DialogueManager.Welcome();
    }
}
