
// Partido.java
// 
// EIF209 - Programación 4 – Proyecto #2
// Abril 2019 
// 
// Autores: 
//  - 402360123 Luis Felipe Soto Cruz
//  - 116760031 Julio Rodriguez Chavarria 
package modelo;

import java.awt.image.BufferedImage;


public class Partido {

    public Partido(String nombre, String siglas, BufferedImage bandera, String observaciones) {
        this.nombre = nombre;
        this.siglas = siglas;
        this.bandera = bandera;
        this.observaciones = observaciones;
    }

    public String getNombre() {
        return nombre;
    }

    public String getSiglas() {
        return siglas;
    }

    public BufferedImage getBandera() {
        return bandera;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    @Override
    public String toString() {
//        return "Votacion{" + "id=" + id + ", fecha_inicial=" + fecha_inicial + ", fecha_apertura=" + fecha_apertura + ", fecha_cierre=" + fecha_cierre + ", fecha_final=" + fecha_final + '}';
        StringBuilder strb = new StringBuilder();
        strb.append("<tr><td>");
        strb.append(siglas);
        strb.append("</td>");
        strb.append("<td>");
        strb.append(String.format(FORMATO_BTN_VOTAR, siglas));
        strb.append("</td>");
        /*strb.append("<td>");
        strb.append();
        strb.append("</td>");
        strb.append("<td>");
        strb.append();
        strb.append("</td>");
        strb.append("<td>");
        strb.append();
        strb.append("</td>");
        strb.append("<td>");
        strb.append();
        strb.append("</td>");
        strb.append("<td>");
        strb.append();
        strb.append("</td>");*/
        strb.append("</tr>");
        return strb.toString();
    }
    
    private String nombre;
    private String siglas;
    private BufferedImage bandera;
    private String observaciones;    
    
    private static final String FORMATO_BTN_VOTAR = "<form action=\"ServicioVotar\" method=\"POST\"><input type=\"hidden\" name=\"id\" value=\"%s\"><input type=\"submit\" name=\"idvotacion\" value=\"Votar aqui\"></form>";
}
