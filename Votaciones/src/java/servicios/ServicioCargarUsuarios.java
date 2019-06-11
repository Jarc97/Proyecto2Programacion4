/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios;

import control.GestorCargaUsuarios;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
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
 * @author julio
 */

@WebServlet
@MultipartConfig()
public class ServicioCargarUsuarios extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        response.setContentType("text/html;charset=UTF-8");
        try {
            for (Part part : request.getParts()) {
                String nombreArchivo = part.getSubmittedFileName();
                System.out.println(part.getContentType());
                
                String xmlStr = new Scanner(part.getInputStream()).useDelimiter("\\A").next();
                
                GestorCargaUsuarios gcu = GestorCargaUsuarios.obtenerInstancia();
                gcu.interpretar(xmlStr);
                
                if (nombreArchivo.isEmpty()) {
                    request.setAttribute("mensaje",
                            "Se omitió la selección del archivo.");
                    break;
                }
                /*
                if (Gallery.validate(nombreArchivo)) {
                    Gallery g1 = (Gallery) getServletContext().getAttribute("g1");
                    try {
                        g1.saveImage(nombreArchivo, part.getContentType(),
                                part.getInputStream(), (int) part.getSize());
                    } catch (Exception ex) {
                        request.setAttribute("mensaje",
                                String.format("Excepción: '%s'", ex.getMessage()));
                    }
                } else {
                    request.setAttribute("mensaje",
                            "El formato del archivo es incorrecto.");
                    break;
                }
                */
            }
        } catch (IOException | ServletException ex) {
            request.setAttribute("mensaje",
                    String.format("Ocurrió una excepción: '%s'", ex.getMessage()));
        }

        getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);

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
        } catch (Exception ex) {
            Logger.getLogger(ServicioCargarUsuarios.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (Exception ex) {
            Logger.getLogger(ServicioCargarUsuarios.class.getName()).log(Level.SEVERE, null, ex);
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
