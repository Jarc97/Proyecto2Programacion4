/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios;

import control.GestorVotaciones;
import java.io.IOException;
import java.io.PrintWriter;
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
public class ServicioValidarVoto extends HttpServlet {

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
            throws ServletException, IOException, SQLException { 
        response.setContentType("text/html;charset=UTF-8");
        response.setHeader("cache-control", "no-cache, no-store, must-revalidate");
        
        HttpSession sesionActual = request.getSession();
         String usuario = (String) sesionActual.getAttribute("usuario");
        boolean usuarioValido = false;
//        String usuario = (String) request.getAttribute("usuario");
        System.out.println("usuario1: "+usuario);  
        
        
//        Enumeration keys = sesionActual.getAttributeNames();
//while (keys.hasMoreElements())
//{
//  String key = (String)keys.nextElement();
//  System.out.println(key + ": " + sesionActual.getValue(key) + "<br>");
//}
        try {
            usuarioValido = GestorVotaciones.obtenerInstancia().validarClaveCambio(sesionActual);
            //GestorVotaciones.obtenerInstancia().mostrarVotacionesDisponibles(sesionActual);
            
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException ex) {
            System.err.println(ex.getMessage());
        }

        if (usuarioValido) {      
            //HttpSession sesion = request.getSession();
            //sesion.setAttribute("usuario", usuario);
//            System.out.println("SESION:"+sesion.getId());
//            sesion.setMaxInactiveInterval(60 * 3);
            
            System.out.println("Puede votar");
//            request.getRequestDispatcher("votacionDisponibles.jsp").forward(request, response);
           response.sendRedirect("votacionDisponibles.jsp");
        } else {
            response.sendRedirect("principalUsuario.jsp?error=1");
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
        } catch (SQLException ex) {
            Logger.getLogger(ServicioValidarVoto.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(ServicioValidarVoto.class.getName()).log(Level.SEVERE, null, ex);
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
