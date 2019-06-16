
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
        return "Partido{" + "nombre=" + nombre + ", siglas=" + siglas + ", bandera=" + bandera + ", observaciones=" + observaciones + '}';
    }
    
    private String nombre;
    private String siglas;
    private BufferedImage bandera;
    private String observaciones;    
}
