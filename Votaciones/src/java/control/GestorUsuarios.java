// GestorUsuarios.java
// 
// EIF209 - Programación 4 – Proyecto #2
// Abril 2019 
// 
// Autores: 
//  - 402360123 Luis Felipe Soto Cruz
//  - 116760031 Julio Rodriguez Chavarria 


package control;

import cr.ac.database.managers.DBManager;
import java.io.File;
import java.io.StringReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import modelo.Usuario;
import org.xml.sax.InputSource;

public class GestorUsuarios {    
    
    private GestorUsuarios() throws InstantiationException, ClassNotFoundException, IllegalAccessException {
        bd = DBManager.getDBManager(DBManager.DB_MGR.MYSQL_SERVER);
    }
    
    public static GestorUsuarios obtenerInstancia()
            throws InstantiationException, ClassNotFoundException, IllegalAccessException {
        if (instancia == null) {
            instancia = new GestorUsuarios();
        }
        return instancia;
    }
    
    // leer el contenido del xml y crear lista de Usuarios
    public void interpretar(String content) throws Exception {
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        SAXParser saxParser = saxParserFactory.newSAXParser();
        UsuariosHandler handler = new UsuariosHandler();
        
        StringReader sr = new StringReader(content);
        InputSource is = new InputSource(sr);
        saxParser.parse(is, handler);
        this.guardar(handler.getUsuarios());
    }
    
    // guardar los usuarios en la BD
    private void guardar(ArrayList <Usuario> usuarios) {
        try (
            Connection cnx = bd.getConnection(BASE_DATOS, USUARIO_BD, CLAVE_BD);
            PreparedStatement stm = cnx.prepareStatement(CMD_AGREGAR_USUARIO)) {
            
            for (Usuario u : usuarios) {
                stm.clearParameters();
                stm.setString(1, u.getCedula());
                stm.setString(2, u.getApellido1());
                stm.setString(3, u.getApellido2());
                stm.setString(4, u.getNombre());
                stm.setString(5, u.getClave());
                stm.setInt(6, u.getActivo());
                stm.execute();
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public boolean verificarUsuario(String id, String clave) {
        boolean encontrado = false;
        try {
            Connection cnx = bd.getConnection(BASE_DATOS, USUARIO_BD, CLAVE_BD);
            try (PreparedStatement stm = cnx.prepareStatement(CMD_VERIFICAR)) {
                stm.clearParameters();
                stm.setString(1, id);
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
    
     public String obtenerNombre(HttpSession session) {
        StringBuilder r = new StringBuilder();
        try {
            String registros = listarNombre(session);
            StringBuilder append = r.append(registros);
        } catch (SQLException ex) {
            r.append(String.format(
                    "<tr><td>(Excepción: '%s')</td></tr>", ex.getMessage()));
        }
        return r.toString();
    }

    public String listarNombre(HttpSession session) throws SQLException {
        String e;
        try (Connection cnx = bd.getConnection(BASE_DATOS, USUARIO_BD, CLAVE_BD);
                PreparedStatement stm = cnx.prepareStatement(CMD_RECUPERAR_NOMBRE)) {
            Object objeto = session.getAttribute("usuario");
            String idEst = objeto.toString();

            stm.clearParameters();

            stm.setString(1, idEst);

            try (ResultSet rs = stm.executeQuery()) {
                while (rs.next()) {
                    return e = rs.getString("nombre") + " " + rs.getString("apellido1")+ " " + rs.getString("apellido2");
                }
            }
            return null;
        }
    }
    
        public void cambiarClave(String id, String claveNueva) throws SQLException {
        try {

            try (Connection cnx = bd.getConnection(BASE_DATOS, USUARIO_BD, CLAVE_BD);
                    PreparedStatement stm = cnx.prepareStatement(CMD_CAMBIAR_CLAVE)) {
                stm.clearParameters();
                stm.setString(1, claveNueva);
                stm.setString(2, id);
                stm.executeUpdate();
            } catch (Exception ex) {
                System.out.println(ex);
            }

        } catch (Exception e) {
            System.out.println("Error");
        }
    }
    
    private DBManager bd = null;
    private static GestorUsuarios instancia = null;
    private static final String BASE_DATOS = "bd_votaciones";
    private static final String USUARIO_BD = "root";
    private static final String CLAVE_BD = "root";
    
    private static final String CMD_AGREGAR_USUARIO = "INSERT INTO usuario (cedula, apellido1, apellido2, nombre, clave, activo) VALUES (?,?,?,?,?,?);";
    private static final String CMD_VERIFICAR = "SELECT cedula FROM usuario WHERE cedula = ? AND clave = ?;";
    private static final String CMD_RECUPERAR_NOMBRE = "SELECT nombre, apellido1, apellido2 FROM usuario WHERE cedula=?; ";
    private static final String CMD_CAMBIAR_CLAVE = "UPDATE usuario SET clave = ? WHERE cedula=?; ";
    
}
