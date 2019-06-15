/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.Date;
import java.text.SimpleDateFormat;

/**
 *
 * @author Feli
 */
public class Votacion {

    public Votacion() {
    }

    public Votacion(int id, Date fecha_inicial, Date fecha_apertura, Date fecha_cierre, Date fecha_final, int estado) {
        this.id = id;
        this.fecha_inicial = fecha_inicial;
        this.fecha_apertura = fecha_apertura;
        this.fecha_cierre = fecha_cierre;
        this.fecha_final = fecha_final;
        this.estado = estado;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getFecha_inicial() {
        return fecha_inicial;
    }

    public void setFecha_inicial(Date fecha_inicial) {
        this.fecha_inicial = fecha_inicial;
    }

    public Date getFecha_apertura() {
        return fecha_apertura;
    }

    public void setFecha_apertura(Date fecha_apertura) {
        this.fecha_apertura = fecha_apertura;
    }

    public Date getFecha_cierre() {
        return fecha_cierre;
    }

    public void setFecha_cierre(Date fecha_cierre) {
        this.fecha_cierre = fecha_cierre;
    }

    public Date getFecha_final() {
        return fecha_final;
    }

    public void setFecha_final(Date fecha_final) {
        this.fecha_final = fecha_final;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }
    
    public String dateToString(Date fecha) {
        String fechaS = formato.format(fecha);
        return fechaS;
    }
    public String estaActivo(int estado){
        String activo;
        activo = (estado != 1) ? "<span style=\"color:red;\"><strong>Inactivo</strong></span>" 
                : "<span style=\"color:green;\"><strong>Activo</strong></span>";        
        return activo;
    }
    @Override
    public String toString() {
//        return "Votacion{" + "id=" + id + ", fecha_inicial=" + fecha_inicial + ", fecha_apertura=" + fecha_apertura + ", fecha_cierre=" + fecha_cierre + ", fecha_final=" + fecha_final + '}';
        StringBuilder strb = new StringBuilder();
        strb.append("<tr><td>");
        strb.append(Integer.toString(id));
        strb.append("</td>");
        strb.append("<td>");
        strb.append(dateToString(fecha_inicial));
        strb.append("</td>");
        strb.append("<td>");
        strb.append(dateToString(fecha_apertura));
        strb.append("</td>");
        strb.append("<td>");
        strb.append(dateToString(fecha_cierre));
        strb.append("</td>");
        strb.append("<td>");
        strb.append(dateToString(fecha_final));
        strb.append("</td>");
        strb.append("<td>");
        strb.append(estaActivo(estado));
        strb.append("</td>");
        strb.append("<td>");
        strb.append(FORMATO_BTN_VOTAR);
        strb.append("</td>");
        strb.append("</td>");
        return strb.toString();
    }

    private int id;
    private Date fecha_inicial;
    private Date fecha_apertura;
    private Date fecha_cierre;
    private Date fecha_final;
    private int estado;
    SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    private static final String FORMATO_BTN_VOTAR = "<button name=\"idvotacion\" onclick=\"votacion.jsp\">Votar aqui</button>";
}
