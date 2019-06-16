/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import cr.ac.database.managers.DBManager;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Feli
 */
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
    
    public boolean validarCandidato(String cedula) throws SQLException {
        String formattedQuery = String.format(CMD_BUSCAR_CEDULA, cedula);
        try (
                Connection cnx = bd.getConnection(BASE_DATOS, USUARIO_BD, CLAVE_BD);
                Statement stm = cnx.createStatement();
                ResultSet rs = stm.executeQuery(formattedQuery)) {

            while (rs.next()) {
                return true;
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }
    
    public void insertarVotacionPartido() {
        
        int id = buscarIdVotacionActiva();
        
        
        try (
            Connection cnx = bd.getConnection(BASE_DATOS, USUARIO_BD, CLAVE_BD);
            PreparedStatement stm = cnx.prepareStatement(CMD_AGREGAR_VOTACION_PARTIDO)) {
                
            stm.clearParameters();
            stm.setInt(1, id);
            stm.setString(2, siglas);
            stm.setString(3, cedulaCandidato);
            stm.setBinaryStream(4, fotoCandidato);
            stm.setInt(5, 0);
            stm.execute();

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    private int buscarIdVotacionActiva() {
        String formattedQuery = String.format(CMD_BUSCAR_ID_VOTACION_ACTIVA);
        try (
                Connection cnx = bd.getConnection(BASE_DATOS, USUARIO_BD, CLAVE_BD);
                Statement stm = cnx.createStatement();
                ResultSet rs = stm.executeQuery(formattedQuery)) {

            while (rs.next()) {
                return rs.getInt("id");
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return 0;
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
    
    public static String cedulaCandidato;
    public static InputStream fotoCandidato;
    
    private static final String CMD_AGREGAR_PARTIDO = "INSERT INTO bd_votaciones.partido(siglas, nombre, bandera, observaciones) VALUES (?,?,?,?);";
    private static final String CMD_AGREGAR_VOTACION_PARTIDO = "insert into bd_votaciones.votacion_partido (votacion_id, partido_siglas, cedula_candidato, foto_candidato, votos_obtenidos) values (?,?,?,?,?);";
    private static final String CMD_BUSCAR_ID_VOTACION_ACTIVA = "select id from votacion where estado = 1;";
    private static final String CMD_BUSCAR_CEDULA = "select cedula from usuario where cedula = '%s';";
}
