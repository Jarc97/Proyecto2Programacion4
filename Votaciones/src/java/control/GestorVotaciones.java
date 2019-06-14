/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import cr.ac.database.managers.DBManager;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Feli
 */
public class GestorVotaciones {

    private GestorVotaciones()
            throws InstantiationException, ClassNotFoundException, IllegalAccessException {
        bd = DBManager.getDBManager(DBManager.DB_MGR.MYSQL_SERVER);
    }

    public static GestorVotaciones obtenerInstancia()
            throws InstantiationException, ClassNotFoundException, IllegalAccessException {
        if (instancia == null) {
            instancia = new GestorVotaciones();
        }
        return instancia;
    }

    public void crearVotacion() throws SQLException {

        try (
                Connection cnx = bd.getConnection(BASE_DATOS, USUARIO_BD, CLAVE_BD);
                PreparedStatement stm = cnx.prepareStatement(CMD_AGREGAR_VOTACION)) {

            stm.clearParameters();
            stm.setString(1, finicio);
            stm.setString(2, fapertura);
            stm.setString(3, fcierre);
            stm.setString(4, ffinal);
            stm.execute();
            insertarVotacionUsuario();

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void insertarVotacionUsuario() throws SQLException {

        try (
                Connection cnx = bd.getConnection(BASE_DATOS, USUARIO_BD, CLAVE_BD);
                PreparedStatement stm = cnx.prepareStatement(CMD_INSERTAR_VOT_USU)) {
                
            Statement stm1 = cnx.createStatement();
            ResultSet rs1 = stm1.executeQuery(CMD_SELECT_ID_VOT);
            Statement stm2 = cnx.createStatement();
            ResultSet rs2 = stm2.executeQuery(CMD_SELECT_CED_USUARIOS);
            
            while (rs2.next()) {                
                stm.clearParameters();
                stm.setInt(1, rs1.getInt("id"));
                stm.setString(2, rs2.getString("cedula"));
                stm.execute();
            }      
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }

    private DBManager bd = null;
    private static GestorVotaciones instancia = null;
    private static final String BASE_DATOS = "bd_votaciones";
    private static final String USUARIO_BD = "root";
    private static final String CLAVE_BD = "root";
    public static String finicio;
    public static String fapertura;
    public static String fcierre;
    public static String ffinal;

    private static final String CMD_AGREGAR_VOTACION = "INSERT INTO bd_votaciones.votacion(fecha_inicio, fecha_apertura, fecha_cierre, fecha_final, estado) VALUES (?,?,?,?,1);";
    private static final String CMD_SELECT_ID_VOT = "SELECT id FROM bd_votaciones.votacion;";
    private static final String CMD_SELECT_CED_USUARIOS = "SELECT cedula FROM bd_votaciones.usuario;";
    private static final String CMD_INSERTAR_VOT_USU = "INSERT INTO bd_votaciones.votacion_usuario(votacion_id, usuario_cedula, voto_completado) VALUES(?,?,0);";
}
