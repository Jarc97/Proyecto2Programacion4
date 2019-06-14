/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import cr.ac.database.managers.DBManager;
import java.io.File;
import java.io.StringReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import modelo.Usuario;
import org.xml.sax.InputSource;

/**
 *
 * @author julio
 */
public class GestorCargaUsuarios {    
    
    private GestorCargaUsuarios() throws InstantiationException, ClassNotFoundException, IllegalAccessException {
        bd = DBManager.getDBManager(DBManager.DB_MGR.MYSQL_SERVER);
    }
    
    public static GestorCargaUsuarios obtenerInstancia()
            throws InstantiationException, ClassNotFoundException, IllegalAccessException {
        if (instancia == null) {
            instancia = new GestorCargaUsuarios();
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
        private DBManager bd = null;
    private static GestorCargaUsuarios instancia = null;
    private static final String BASE_DATOS = "bd_votaciones";
    private static final String USUARIO_BD = "root";
    private static final String CLAVE_BD = "root";
    
    private static final String CMD_AGREGAR_USUARIO = "INSERT INTO usuario (cedula, apellido1, apellido2, nombre, clave, activo) VALUES (?,?,?,?,?,?);";
}
