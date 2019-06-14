/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios;

import control.GestorVotaciones;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Feli
 */
public class ServicioCrearVotacion extends HttpServlet {

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
            throws ServletException, IOException, ParseException, InstantiationException, ClassNotFoundException, IllegalAccessException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            GestorVotaciones gv = GestorVotaciones.obtenerInstancia();

            Date inicial = new Date(System.currentTimeMillis());
            String fechaI = formato.format(inicial);
            System.out.println("Inicial: " + fechaI);

            String fapertura = request.getParameter("fechaApertura");
            String hapertura = request.getParameter("horaApertura");
            String aperturaS = (fapertura + " " + hapertura);
            java.util.Date apertura = formato.parse(aperturaS);
            String fechaA = formato.format(apertura);
            System.out.println("Apertura: " + fechaA);

            String fcierre = request.getParameter("fechaCierre");
            String hcierre = request.getParameter("horaCierre");
            String cierreS = (fcierre + " " + hcierre);
            java.util.Date cierre = formato.parse(cierreS);
            String fechaC = formato.format(cierre);
            System.out.println("Cierre: " + fechaC);

            String ffinal = request.getParameter("fechaFinal");
            String hfinal = request.getParameter("horaFinal");
            String finalS = (ffinal + " " + hfinal);
            java.util.Date finall = formato.parse(finalS);
            String fechaF = formato.format(finall);
            System.out.println("Final: " + fechaF);

            if (inicial.getDate() <= apertura.getDate() && inicial.getTime() < apertura.getTime()) {
                if (apertura.getDate() <= cierre.getDate() && apertura.getTime() < cierre.getTime()) {
                    if (cierre.getDate() <= finall.getDate() && cierre.getTime() < finall.getTime()) {
                        gv.finicio = fechaI;
                        gv.fapertura = fechaA;
                        gv.fcierre = fechaC;
                        gv.ffinal = fechaF;
                        gv.crearVotacion();
                        
                    } else {
                        System.out.println("La hora o la fecha de cierre es mayor a la hora final");
                    }
                } else {
                    System.out.println("La hora o la fecha de apertura es mayor a la hora de cierre");
                }
            } else {
                System.out.println("La hora o la fecha de inicial es mayor a la hora de apertura");
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
        } catch (ParseException ex) {
            Logger.getLogger(ServicioCrearVotacion.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(ServicioCrearVotacion.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ServicioCrearVotacion.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(ServicioCrearVotacion.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ServicioCrearVotacion.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (ParseException ex) {
            Logger.getLogger(ServicioCrearVotacion.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(ServicioCrearVotacion.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ServicioCrearVotacion.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(ServicioCrearVotacion.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ServicioCrearVotacion.class.getName()).log(Level.SEVERE, null, ex);
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
