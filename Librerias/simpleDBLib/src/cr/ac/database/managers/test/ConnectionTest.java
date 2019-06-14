package cr.ac.database.managers.test;

import cr.ac.database.managers.DBManager;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionTest {

    public ConnectionTest() {
        try {
            db = DBManager.getDBManager(DBManager.DB_MGR.MYSQL_SERVER);
        } catch (InstantiationException
                | ClassNotFoundException
                | IllegalAccessException ex) {
            System.err.printf("Exception: '%s'%n", ex.getMessage());
        }
    }

    public void run() {
        try {
            System.out.printf("%s%n", db);
            System.out.printf("Trying to connect to '%s' database..%n", DATABASE);
            try (Connection cnx = db.getConnection(DATABASE, USER, PASSWORD)) {
                System.out.println("Connection successful..");
            }
        } catch (SQLException ex) {
            System.err.printf("Exception: '%s'%n", ex.getMessage());
        }
    }

    public static void main(String[] args) {
        new ConnectionTest().run();
    }

    private DBManager db = null;

    private static final String DATABASE = "test";
    private static final String USER = "root";
    private static final String PASSWORD = "";
}
