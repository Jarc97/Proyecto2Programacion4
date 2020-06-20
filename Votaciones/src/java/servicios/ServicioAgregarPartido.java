
// ServicioAgregarPartido.java
// 
// EIF209 - Programación 4 – Proyecto #2
// Abril 2019 
// 
// Autores: 
//  - 402360123 Luis Felipe Soto Cruz
//  - 116760031 Julio Rodriguez Chavarria 
package servicios;

import control.GestorPartidos;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 *
 * @author Feli
 */
@WebServlet
@MultipartConfig
public class ServicioAgregarPartido extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, InstantiationException, ClassNotFoundException, IllegalAccessException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
            String nombre = request.getParameter("nombre");
            String siglas = request.getParameter("siglas");
            String observaciones = request.getParameter("observaciones");
            Part filePart = request.getPart("bandera");
            String cedulaCandidato = request.getParameter("cedulaCandidato");
            Part fotoCandidato = request.getPart("foto");
            
            InputStream in = null;
            InputStream in2 = null;
            
            if (filePart != null) {
                System.out.println(filePart.getName());
                System.out.println(filePart.getSize());
                System.out.println(filePart.getContentType());
                in = filePart.getInputStream();
                
                System.out.println(fotoCandidato.getName());
                System.out.println(fotoCandidato.getSize());
                System.out.println(fotoCandidato.getContentType());
                in2 = filePart.getInputStream();
            }            
            
            GestorPartidos g1 = GestorPartidos.obtenerInstancia();
            GestorPartidos.nombre = nombre;
            GestorPartidos.siglas = siglas;
            GestorPartidos.observaciones = observaciones;            
            GestorPartidos.bandera = in;
            GestorPartidos.cedulaCandidato = cedulaCandidato;
            GestorPartidos.fotoCandidato = in2;
            g1.crearPartido();                      
                     
            System.out.println(nombre);
            System.out.println(siglas);
            System.out.println(observaciones);
            
            boolean partidoValido = false;
            
            try {
                partidoValido = GestorPartidos.obtenerInstancia().validarCandidato(cedulaCandidato);
                
                GestorPartidos.obtenerInstancia().insertarVotacionPartido();
            } catch (ClassNotFoundException | IllegalAccessException | InstantiationException ex) {
                System.err.println(ex.getMessage());
            }

            if (partidoValido) {
                response.sendRedirect("principalAdministrador.jsp");
            } else {
                response.sendRedirect("registrarPartidos.jsp?error=1");
            }
        }
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
        } catch (InstantiationException ex) {
            Logger.getLogger(ServicioAgregarPartido.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ServicioAgregarPartido.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(ServicioAgregarPartido.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ServicioAgregarPartido.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (InstantiationException ex) {
            Logger.getLogger(ServicioAgregarPartido.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ServicioAgregarPartido.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(ServicioAgregarPartido.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ServicioAgregarPartido.class.getName()).log(Level.SEVERE, null, ex);
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
