
package control;

import cr.ac.database.managers.DBManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

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
    public int totalVotantesRegistrados() {
        String formattedQuery = String.format(CMD_TOTAL_VOTANTES_REGISTRADOS);
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
    
    public int totalVotosEfectivos() {
        String formattedQuery = String.format(CMD_TOTAL_VOTOS_EFECTIVOS);
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
    
    public int totalAbstencionismo() {
        String formattedQuery = String.format(CMD_TOTAL_ABSTENCIONISMO);
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
    
    private DBManager bd = null;
    private static GestorReporteVotacion instancia = null;
    private static final String BASE_DATOS = "bd_votaciones";
    private static final String USUARIO_BD = "root";
    private static final String CLAVE_BD = "root";
    
    private static final String CMD_TOTAL_VOTANTES_REGISTRADOS = "select count(*) from usuario;";
    private static final String CMD_TOTAL_VOTOS_EFECTIVOS = "select count(*) from votacion_usuario where voto_completado = 1;";
    private static final String CMD_TOTAL_ABSTENCIONISMO = "select count(*) from votacion_usuario where voto_completado = 0;";
}
