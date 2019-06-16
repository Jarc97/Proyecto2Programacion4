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
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import javax.servlet.http.HttpSession;
import modelo.Votacion;

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
            rs1.next();
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

    public boolean validarClaveCambio(HttpSession session) throws SQLException {
        boolean cambio = false;
       try {
            Connection cnx = bd.getConnection(BASE_DATOS, USUARIO_BD, CLAVE_BD);
            try (PreparedStatement stm = cnx.prepareStatement(CMD_VALIDAR_CLAVE_CAMB)) {
                Object objetoU = session.getAttribute("usuario");
                String ced = objetoU.toString();
                
                stm.clearParameters();
                stm.setString(1, ced);
                ResultSet rs = stm.executeQuery();
                cambio = rs.next();
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.err);
        } finally {
            if (bd != null) {
                bd.closeConnection();
            }
        }
        return cambio;
    }
    
    public List<Votacion> listarVotacionesDisponibles(HttpSession sesion)throws SQLException{
        List<Votacion> v = new ArrayList<>();
        try (Connection cnx = bd.getConnection(BASE_DATOS, USUARIO_BD, CLAVE_BD);
                PreparedStatement stm = cnx.prepareStatement(CMD_LISTAR_VOT_DISP)){
                Object objetoU = sesion.getAttribute("usuario");
                String cedula = objetoU.toString();
                
                stm.clearParameters();
                stm.setString(1, cedula);
                 ResultSet rs = stm.executeQuery();
            while (rs.next()) {                
                int id = rs.getInt("id");
                Timestamp fecha_inicial = rs.getTimestamp("fecha_inicio");
                Timestamp fecha_apertura = rs.getTimestamp("fecha_apertura");
                Timestamp fecha_cierre = rs.getTimestamp("fecha_cierre");
                Timestamp fecha_final = rs.getTimestamp("fecha_final");
                int estado = rs.getInt("estado");
                v.add(new Votacion(id, fecha_inicial, fecha_apertura, fecha_cierre, fecha_final, estado));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }       
        return v;
        
    }
    
    public String mostrarVotacionesDisponibles(HttpSession sesion)throws SQLException{
        List<Votacion> registros = listarVotacionesDisponibles(sesion);
        StringBuilder strb = new StringBuilder();
        ListIterator<Votacion> r = registros.listIterator();
        strb.append("<table class=\"tablaVot\">\n");
        strb.append("\t<tr>\n");
        strb.append("<th>Id</th>");
        strb.append("<th>Fecha inicial</th>");
        strb.append("<th>Fecha de apertura</th>");
        strb.append("<th>Fecha de cierre</th>");
        strb.append("<th>Fecha final</th>");
        strb.append("<th>Estado</th>");
        strb.append("</tr>");
        while (r.hasNext()) {
            strb.append(String.format("%s", r.next().toString()));
        }
        strb.append("</table>");
        return strb.toString();
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
    private static final String CMD_VALIDAR_CLAVE_CAMB = "SELECT cedula FROM bd_votaciones.usuario WHERE cedula = ? AND cedula <> clave;";
    private static final String CMD_VER_VOTACION_ID = "SELECT id FROM bd_votaciones.votacion";
    private static final String CMD_LISTAR_VOT_DISP = "select v.id, v.fecha_inicio, v.fecha_apertura, v.fecha_cierre, v.fecha_final, v.estado\n" +
"from bd_votaciones.votacion v, bd_votaciones.votacion_usuario vu, bd_votaciones.usuario u \n" +
"where vu.usuario_cedula = u.cedula and u.cedula = ? and u.activo = 1;";
}
