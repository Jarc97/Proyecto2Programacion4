// GestorPartidos.java
// 
// EIF209 - Programación 4 – Proyecto #2
// Abril 2019 
// 
// Autores: 
//  - 402360123 Luis Felipe Soto Cruz
//  - 116760031 Julio Rodriguez Chavarria

package control;

import cr.ac.database.managers.DBManager;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GestorPartidos {

    private GestorPartidos()
         throws InstantiationException, ClassNotFoundException, IllegalAccessException {
        bd = DBManager.getDBManager(DBManager.DB_MGR.MYSQL_SERVER);
    }

    public static GestorPartidos obtenerInstancia()
            throws InstantiationException, ClassNotFoundException, IllegalAccessException {
        if (instancia == null) {
            instancia = new GestorPartidos();
        }
        return instancia;
    }

    public void crearPartido() throws SQLException {
       
        try (
                Connection cnx = bd.getConnection(BASE_DATOS, USUARIO_BD, CLAVE_BD);
                PreparedStatement stm = cnx.prepareStatement(CMD_AGREGAR_PARTIDO)) {
                
            stm.clearParameters();
            stm.setString(1, siglas);
            stm.setString(2, nombre);
            stm.setBinaryStream(3, bandera);
            stm.setString(4, observaciones);
            stm.execute();

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static boolean validate(final String fileName) {
        Matcher matcher = PATTERN.matcher(fileName);
        return matcher.matches();
    }
    private static final String IMAGE_PATTERN = "([^\\s]+(\\.(?i)(jpg|png|gif|bmp))$)";

    private static final Pattern PATTERN = Pattern.compile(IMAGE_PATTERN);

    private DBManager bd = null;
    private static GestorPartidos instancia = null;
    private static final String BASE_DATOS = "bd_votaciones";
    private static final String USUARIO_BD = "root";
    private static final String CLAVE_BD = "root";
    public static String siglas;
    public static String nombre;
    public static String observaciones;
    public static InputStream bandera;
    
    private static final String CMD_AGREGAR_PARTIDO = "INSERT INTO bd_votaciones.partido(siglas, nombre, bandera, observaciones) VALUES (?,?,?,?);";
}
