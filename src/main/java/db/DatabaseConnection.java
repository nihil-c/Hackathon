package db; // Ricorda che il package completo è com.hackathon.applicativo.DAO

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Classe utility per gestire la connessione al database PostgreSQL.
 * Fornisce metodi statici per ottenere e chiudere le connessioni.
 */
public class DatabaseConnection {

    // Non servono se vuoi che getConnection crei sempre una nuova connessione
    // private static DatabaseConnectionManager instance;
    // public Connection connection= null;

    private static final String DB_URL = "jdbc:postgresql://localhost:5432/hackathondb";
    private static final String USER = "postgres";
    private static final String PASS = "unravel";

    // Costruttore privato per prevenire l'instanziazione esterna
    private DatabaseConnection() {
        // Questa è una classe utility, non dovrebbe essere istanziata.
    }

    /**
     * Ottiene una nuova connessione al database PostgreSQL.
     * @return Un oggetto Connection.
     * @throws SQLException Se si verifica un errore SQL durante la connessione.
     */
    public static Connection getConnection() throws SQLException {
        System.out.println("Tentativo di ottenere una connessione al database...");
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }

    /**
     * Chiude una connessione al database in modo sicuro.
     * @param conn La connessione da chiudere.
     */
    public static void close(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
                System.out.println("Connessione al database chiusa.");
            } catch (SQLException e) {
                System.err.println("⚠️ Errore durante la chiusura della connessione: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    /**
     * Metodo main per un test rapido della connessione di questa utility.
     * Questo metodo è solo per test e non fa parte della logica dell'applicazione.
     */
    public static void main(String[] args) {
        try (Connection testConn = DatabaseConnection.getConnection()) {
            if (testConn != null) {
                System.out.println("✅ Test di connessione a HackathonDb riuscito!");
                System.out.println("URL di connessione: " + testConn.getMetaData().getURL());
                System.out.println("Versione del driver: " + testConn.getMetaData().getDriverVersion());
            } else {
                System.out.println("❌ Test di connessione a HackathonDb fallito!");
            }
        } catch (SQLException e) {
            System.err.println("🚨 Errore durante il test di connessione: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
