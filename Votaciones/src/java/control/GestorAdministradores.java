// GestorAdministradores.java
// 
// EIF209 - Programación 4 – Proyecto #2
// Abril 2019 
// 
// Autores: 
//  - 402360123 Luis Felipe Soto Cruz
//  - 116760031 Julio Rodriguez Chavarria

package control;

import cr.ac.database.managers.DBManager;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class GestorAdministradores {
    
    private GestorAdministradores()
         throws InstantiationException, ClassNotFoundException, IllegalAccessException {
        bd = DBManager.getDBManager(DBManager.DB_MGR.MYSQL_SERVER);
    }

    public static GestorAdministradores obtenerInstancia()
            throws InstantiationException, ClassNotFoundException, IllegalAccessException {
        if (instancia == null) {
            instancia = new GestorAdministradores();
        }
        return instancia;
    }

    public void agregarAdministrador() throws SQLException {
       
        try (
                Connection cnx = bd.getConnection(BASE_DATOS, USUARIO_BD, CLAVE_BD);
                PreparedStatement stm = cnx.prepareStatement(CMD_AGREGAR_ADMIN)) {
                
            stm.clearParameters();
            stm.setString(1, cedula);
            stm.setString(2, apellido1);
            stm.setString(3, apellido2);
            stm.setString(4, nombre);
            stm.setString(5, usuario);
            stm.setString(6, clave);
            stm.execute();

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }  
        public boolean verificarUsuario(String usuario, String clave) {
        boolean encontrado = false;
        try {
            Connection cnx = bd.getConnection(BASE_DATOS, USUARIO_BD, CLAVE_BD);
            try (PreparedStatement stm = cnx.prepareStatement(CMD_VERIFICAR)) {
                stm.clearParameters();
                stm.setString(1, usuario);
                stm.setString(2, clave);
                ResultSet rs = stm.executeQuery();
                encontrado = rs.next();
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.err);
        } finally {
            if (bd != null) {
                bd.closeConnection();
            }
        }
        return encontrado;
    }

    private DBManager bd = null;
    private static GestorAdministradores instancia = null;
    private static final String BASE_DATOS = "bd_votaciones";
    private static final String USUARIO_BD = "root";
    private static final String CLAVE_BD = "root";
    public static String cedula;
    public static String apellido1;
    public static String apellido2;
    public static String nombre;
    public static String usuario;
    public static String clave;
    
    private static final String CMD_AGREGAR_ADMIN = "INSERT INTO bd_votaciones.administrador(cedula, apellido1, apellido2, nombre, usuario, clave) VALUES (?,?,?,?,?,?);";
    private static final String CMD_VERIFICAR = "SELECT usuario FROM bd_votaciones.administrador WHERE usuario = ? AND clave = ?;";
    
}
