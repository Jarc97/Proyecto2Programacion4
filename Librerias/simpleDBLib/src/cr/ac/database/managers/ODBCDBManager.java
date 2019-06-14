package cr.ac.database.managers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ODBCDBManager extends DBManager {

    // <editor-fold defaultstate="collapsed" desc="constructors">
    ODBCDBManager(String serverURL)
            throws ClassNotFoundException,
            IllegalAccessException,
            InstantiationException {
        super("ODBC database manager", serverURL);
        Class.forName(DATABASE_DRIVER).newInstance();
    }

    ODBCDBManager()
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
        throw new UnsupportedOperationException("MySQLDBManager.getConnection(String)");
        // closeConnection();
        // cnx = DriverManager.getConnection(null);
        // return cnx;
    }

    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="attributes">
    // Parámetros para la conexión a un servidor de base de datos ODBC.
    private static final String DATABASE_DRIVER = "sun.jdbc.odbc.JdbcOdbcDriver";
    // </editor-fold>
}
