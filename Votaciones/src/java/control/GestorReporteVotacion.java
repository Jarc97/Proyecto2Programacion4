/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import cr.ac.database.managers.DBManager;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DecimalFormat;

/**
 *
 * @author Feli
 */
public class GestorReporteVotacion {
        
    private GestorReporteVotacion()
            throws InstantiationException, ClassNotFoundException, IllegalAccessException {
        bd = DBManager.getDBManager(DBManager.DB_MGR.MYSQL_SERVER);
    }

    public static GestorReporteVotacion obtenerInstancia()
            throws InstantiationException, ClassNotFoundException, IllegalAccessException {
        if (instancia == null) {
            instancia = new GestorReporteVotacion();
        }
        return instancia;
    }
    
    // Para verificar que aun hay miembros en un grupo
    public int totalVotantesRegistrados(int votacionID) {
        String formattedQuery = String.format(CMD_TOTAL_VOTANTES_REGISTRADOS, votacionID);
        try (
                Connection cnx = bd.getConnection(BASE_DATOS, USUARIO_BD, CLAVE_BD);
                Statement stm = cnx.createStatement();
                ResultSet rs = stm.executeQuery(formattedQuery)) {

            while (rs.next()) {
                String count = rs.getString("count(*)");
                int c = Integer.parseInt(count);
                return c;
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return 0;
    }
    
    private int totalVotosEfectivos(int votacionID) {
        String formattedQuery = String.format(CMD_TOTAL_VOTOS_EFECTIVOS, votacionID);
        try (
                Connection cnx = bd.getConnection(BASE_DATOS, USUARIO_BD, CLAVE_BD);
                Statement stm = cnx.createStatement();
                ResultSet rs = stm.executeQuery(formattedQuery)) {

            while (rs.next()) {
                String count = rs.getString("count(*)");
                int c = Integer.parseInt(count);
                return c;
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return 0;
    }
    
    public String toStringVotosEfectivos(int votacionID) {
        StringBuilder strb = new StringBuilder();
        String ve = String.valueOf(this.totalVotosEfectivos(votacionID));
        double porcentaje = (double) totalVotosEfectivos(votacionID) * 100 / totalVotantesRegistrados(votacionID);
        strb.append(ve);
        strb.append(" (");
        strb.append(String.format("%.2f", porcentaje));
        strb.append("%");
        strb.append(")");
        return strb.toString();
    }
    
    private int totalAbstencionismo(int votacionID) {
        String formattedQuery = String.format(CMD_TOTAL_ABSTENCIONISMO, votacionID);
        try (
                Connection cnx = bd.getConnection(BASE_DATOS, USUARIO_BD, CLAVE_BD);
                Statement stm = cnx.createStatement();
                ResultSet rs = stm.executeQuery(formattedQuery)) {

            while (rs.next()) {
                String count = rs.getString("count(*)");
                int c = Integer.parseInt(count);
                return c;
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return 0;
    }
    
    public String toStringAbstencionismo(int votacionID) {
        StringBuilder strb = new StringBuilder();
        String ve = String.valueOf(this.totalVotantesRegistrados(votacionID) - this.totalVotosEfectivos(votacionID));
        double porcentaje = (double) totalAbstencionismo(votacionID) * 100 / totalVotantesRegistrados(votacionID);
        strb.append(ve);
        strb.append(" (");
        strb.append(String.format("%.2f", porcentaje));
        strb.append("%");
        strb.append(")");
        return strb.toString();
    }
    
    public String votosPorPartido(int votacionID) {
        StringBuilder strb = new StringBuilder();
        
        String formattedQuery = String.format(CMD_VOTOS_POR_PARTIDO, votacionID);
        
        int totalVotosEfectivos = this.totalVotosEfectivos(votacionID);
        
        try (
                Connection cnx = bd.getConnection(BASE_DATOS, USUARIO_BD, CLAVE_BD);
                Statement stm = cnx.createStatement();
                ResultSet rs = stm.executeQuery(formattedQuery);
            ) {
            strb.append("<ul>");
            while (rs.next()) {
                String partido_siglas = rs.getString("partido_siglas");
                int votos_obtenidos = rs.getInt("votos_obtenidos");
                double porcentaje = (double) votos_obtenidos * 100 / totalVotosEfectivos;
                String li = String.format("<li>Partido: %s, Votos: %d (%.2f%%) </li>", partido_siglas, votos_obtenidos, porcentaje);
                strb.append(li);
            }
            strb.append("</ul>");
            
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        
        return strb.toString();
    }
    
    public String resultadoVotacion(int votacionID) {
        String siglasPartidoGanador = "";
        String formattedQuery = String.format(CMD_VOTOS_POR_PARTIDO, votacionID);
        int votosMax = 0;
        boolean empate = false;
        
        if (totalAbstencionismo(votacionID) == totalVotantesRegistrados(votacionID)) {
            return "El abstencionismo fue del 100%";
        }
        
        try (
                Connection cnx = bd.getConnection(BASE_DATOS, USUARIO_BD, CLAVE_BD);
                Statement stm = cnx.createStatement();
                ResultSet rs = stm.executeQuery(formattedQuery);
            ) {
            
            while (rs.next()) {
                int votos = rs.getInt("votos_obtenidos");
                if (votosMax <= votos) {         
                    if (votosMax == votos) {    // hasta ahora existe un empate
                        empate = true;
                        votosMax = votos;
                    } else {
                        empate = false;
                        votosMax = votos;
                        siglasPartidoGanador = rs.getString("partido_siglas");
                    }
                }
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        
        if (empate) {
            return "No hay ganador, hubo un empate";
        }
        
        return "Partido ganador: " + siglasPartidoGanador;
    }
    
    private DBManager bd = null;
    private static GestorReporteVotacion instancia = null;
    private static final String BASE_DATOS = "bd_votaciones";
    private static final String USUARIO_BD = "root";
    private static final String CLAVE_BD = "root";
    
    private static final String CMD_TOTAL_VOTANTES_REGISTRADOS = "select count(*) from votacion_usuario where votacion_id = %d;";
    private static final String CMD_TOTAL_VOTOS_EFECTIVOS = "select count(*) from votacion_usuario where voto_completado = 1 and votacion_id = %d;";
    private static final String CMD_TOTAL_ABSTENCIONISMO = "select count(*) from votacion_usuario where voto_completado = 0 and votacion_id = %d;";
    
    private static final String CMD_VOTOS_POR_PARTIDO = "select * from votacion_partido where votacion_id = %d;";
}
