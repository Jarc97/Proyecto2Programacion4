/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
            InputStream in = null;
            
            if (filePart != null) {
                System.out.println(filePart.getName());
                System.out.println(filePart.getSize());
                System.out.println(filePart.getContentType());
                in = filePart.getInputStream();
            }            
            
            GestorPartidos g1 = GestorPartidos.obtenerInstancia();
            GestorPartidos.nombre = nombre;
            GestorPartidos.siglas = siglas;
            GestorPartidos.observaciones = observaciones;            
            GestorPartidos.bandera = in;
            g1.crearPartido();                      
                     
            System.out.println(nombre);
            System.out.println(siglas);
            System.out.println(observaciones);            
            
            response.sendRedirect("principalAdministrador.jsp");
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
