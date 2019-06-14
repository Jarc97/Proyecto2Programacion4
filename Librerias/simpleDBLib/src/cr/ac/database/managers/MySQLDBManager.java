package cr.ac.database.managers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLDBManager extends DBManager {

    // <editor-fold defaultstate="collapsed" desc="constructors">
    MySQLDBManager(String serverURL)
            throws ClassNotFoundException,
            IllegalAccessException,
            InstantiationException {
        super("MySQL database manager", serverURL);
        Class.forName(DATABASE_DRIVER).newInstance();
    }

    MySQLDBManager()
            throws ClassNotFoundException,
            IllegalAccessException,
            InstantiationException {
        this(SERVER_DEFAULT);
    }

    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="methods">
    @Override
    public Connection getConnection(String database,
            String user, String password)
            throws SQLException {
        closeConnection();
        String URL_conexion
                = String.format("%s//%s/%s", PROTOCOL, serverURL, database);

        cnx = DriverManager.getConnection(URL_conexion, user, password);
        return cnx;
    }

    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="attributes">
    // Parámetros para la conexión a un servidor de base de datos MySQL.
    private static final String DATABASE_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String PROTOCOL = "jdbc:mysql:";
    // </editor-fold>
}
