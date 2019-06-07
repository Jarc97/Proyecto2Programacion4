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
    private String nombre;
    private String abreviatura;
    private BufferedImage bandera;
    private Usuario candidato;
    private BufferedImage fotoCandidato;
    private String observaciones;

    public String getNombre() {
        return nombre;
    }

    public String getAbreviatura() {
        return abreviatura;
    }

    public BufferedImage getBandera() {
        return bandera;
    }

    public Usuario getCandidato() {
        return candidato;
    }

    public BufferedImage getFotoCandidato() {
        return fotoCandidato;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
}
