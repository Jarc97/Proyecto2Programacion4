/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.awt.image.BufferedImage;

/**
 *
 * @author julio
 */
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
