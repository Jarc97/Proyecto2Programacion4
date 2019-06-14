/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.Date;

/**
 *
 * @author Feli
 */
public class Votacion {

    public Votacion() {
    }

    public Votacion(int id, Date fecha_inicial, Date fecha_apertura, Date fecha_cierre, Date fecha_final) {
        this.id = id;
        this.fecha_inicial = fecha_inicial;
        this.fecha_apertura = fecha_apertura;
        this.fecha_cierre = fecha_cierre;
        this.fecha_final = fecha_final;
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

    @Override
    public String toString() {
        return "Votacion{" + "id=" + id + ", fecha_inicial=" + fecha_inicial + ", fecha_apertura=" + fecha_apertura + ", fecha_cierre=" + fecha_cierre + ", fecha_final=" + fecha_final + '}';
    }

    private int id;
    private Date fecha_inicial;
    private Date fecha_apertura;
    private Date fecha_cierre;
    private Date fecha_final;
}
