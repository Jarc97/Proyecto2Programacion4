// ServicioVotar.java
// 
// EIF209 - Programación 4 – Proyecto #2
// Abril 2019 
// 
// Autores: 
//  - 402360123 Luis Felipe Soto Cruz
//  - 116760031 Julio Rodriguez Chavarria 
package servicios;

import control.GestorUsuarios;
import control.GestorVotaciones;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Feli
 */
public class ServicioVotar extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, InstantiationException, ClassNotFoundException, IllegalAccessException {
        response.setContentType("text/html;charset=UTF-8");
       response.setHeader("cache-control", "no-cache, no-store, must-revalidate");

            HttpSession sesionActual = request.getSession();
            
            String idvotacion = (String) sesionActual.getAttribute("idvotacion");
            sesionActual.setAttribute("idvotacion", idvotacion);
        
            System.out.println(idvotacion);
            
         String usuario = (String) sesionActual.getAttribute("usuario");
        System.out.println("usuario en servicio votar:"+usuario);
        String listaDisponibles;
            try {
            listaDisponibles = GestorVotaciones.obtenerInstancia().mostrarVotacionesDisponibles(sesionActual);
            if(GestorVotaciones.obtenerInstancia().validarEstadoVotar(sesionActual) == 1){
                //Aqui va el metodo de votar                 
                
                //======================
                //Para cambiar el estado del usuario en la votacion.
                GestorVotaciones.obtenerInstancia().cambiarEstadoVotar(sesionActual);
            }else{
                response.sendRedirect("principalUsuario.jsp?error=2");
            }
            
        } catch (Exception e) {
                System.out.println(e.getMessage());
        }

            response.sendRedirect("principalUsuario.jsp");
//        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(ServicioVotar.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(ServicioVotar.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ServicioVotar.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(ServicioVotar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(ServicioVotar.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(ServicioVotar.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ServicioVotar.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(ServicioVotar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
