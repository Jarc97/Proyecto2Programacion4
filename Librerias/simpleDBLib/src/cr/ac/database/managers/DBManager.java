package cr.ac.database.managers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class DBManager {

    // <editor-fold defaultstate="collapsed" desc="constructors">
    protected DBManager(String description, String serverURL) {
        this.description = description;
        this.serverURL = (serverURL != null) ? serverURL : SERVER_DEFAULT;
    }

    protected DBManager(String description) {
        this(description, null);
    }

    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="methods">
    public abstract Connection getConnection(String database, String user, String password)
            throws SQLException;

    public Connection getConnection(String connectionString)
            throws SQLException {
        cnx = DriverManager.getConnection(connectionString);
        return cnx;
    }

    public void closeConnection() {
        if (cnx != null) {
            try {
                cnx.close();
            } catch (SQLException ex) {
                System.err.printf("Exception: '%s'%n", ex.getMessage());
            }
        }
    }

    public static DBManager getDBManager(DB_MGR serverType, String serverURL)
            throws InstantiationException, ClassNotFoundException, IllegalAccessException {
        if (instance == null) {
            switch (serverType) {
                case MYSQL_SERVER:
                    instance = new MySQLDBManager(serverURL);
                    break;
                default:
                    throw new InstantiationException("Server type is not implemented.");
            }
        }
        return instance;
    }

    public static DBManager getDBManager(DB_MGR serverType)
            throws InstantiationException, ClassNotFoundException, IllegalAccessException {
        return DBManager.getDBManager(serverType, SERVER_DEFAULT);
    }

    public static DBManager getDBManager()
            throws InstantiationException {
        if (instance == null) {
            throw new InstantiationException("Invalid instance.");
        }
        return instance;
    }

    public String getServerURL() {
        return serverURL;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        StringBuilder r = new StringBuilder();
        r.append(String.format("%s%nDatabase server in: %s",
                description, serverURL));
        return r.toString();
    }

    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="attributes">
    public static final String SERVER_DEFAULT = "localhost";

    public enum DB_MGR {
        ODBC_SERVER,
        MYSQL_SERVER,
        POSTGRESQL_SERVER,
        MSSQL_SERVER,
        ORACLE_SERVER
    };

    protected static DBManager instance = null;

    protected Connection cnx = null;
    protected String serverURL = null;
    private String description = null;
    //</editor-fold>
}
