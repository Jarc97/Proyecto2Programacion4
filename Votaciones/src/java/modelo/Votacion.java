
// Votacion.java
// 
// EIF209 - Programación 4 – Proyecto #2
// Abril 2019 
// 
// Autores: 
//  - 402360123 Luis Felipe Soto Cruz
//  - 116760031 Julio Rodriguez Chavarria 
package modelo;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;


public class Votacion {

    public Votacion() {
    }

    public Votacion(int id, Timestamp fecha_inicial, Timestamp fecha_apertura, Timestamp fecha_cierre, Timestamp fecha_final, int estado) {
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

    public Timestamp getFecha_inicial() {
        return fecha_inicial;
    }

    public void setFecha_inicial(Timestamp fecha_inicial) {
        this.fecha_inicial = fecha_inicial;
    }

    public Timestamp getFecha_apertura() {
        return fecha_apertura;
    }

    public void setFecha_apertura(Timestamp fecha_apertura) {
        this.fecha_apertura = fecha_apertura;
    }

    public Timestamp getFecha_cierre() {
        return fecha_cierre;
    }

    public void setFecha_cierre(Timestamp fecha_cierre) {
        this.fecha_cierre = fecha_cierre;
    }

    public Timestamp getFecha_final() {
        return fecha_final;
    }

    public void setFecha_final(Timestamp fecha_final) {
        this.fecha_final = fecha_final;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }
    
    public String dateToString(Timestamp fecha) {
        String fechaS = formato.format(fecha);
        return fechaS;
    }
    public String estaActivo(int estado){
        String activo;
        activo = (estado != 1) ? "<span style=\"color:red;\"><strong>Inactivo</strong></span>" 
                : "<span style=\"color:green;\"><strong>Activo</strong></span>";        
        return activo;
    }
    
    public String desahabBtn(int estado){
    String btn;
    btn = (estado != 1) ? FORMATO_BTN_DES : FORMATO_BTN_VOTAR;
        return btn;
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
        strb.append(String.format(desahabBtn(estado), id));
        strb.append("</td>");      
        strb.append("</tr>");
        return strb.toString();
    }

    private int id;
    private Timestamp fecha_inicial;
    private Timestamp fecha_apertura;
    private Timestamp fecha_cierre;
    private Timestamp fecha_final;
    private int estado;
    SimpleDateFormat formato = new SimpleDateFormat("HH:mm dd/MM/yyyy");
    private static final String FORMATO_BTN_VOTAR = "<form action=\"ServicioVerVotaciones\" method=\"POST\"><"
            + "input type=\"hidden\" name=\"id\" value=\"%d\"><input type=\"submit\" name=\"idvotacion\" value=\"Votar aqui\"></form>";
    private static final String FORMATO_BTN_DES = "<form action=\"ServicioVerVotaciones\" method=\"POST\"><"
            + "input type=\"hidden\" name=\"id\" value=\"%d\"><input disabled type=\"submit\" name=\"idvotacion\" value=\"No disponible\"></form>";
}