
// Usuario.java
// 
// EIF209 - Programación 4 – Proyecto #2
// Abril 2019 
// 
// Autores: 
//  - 402360123 Luis Felipe Soto Cruz
//  - 116760031 Julio Rodriguez Chavarria 
package modelo;


public class Usuario {

    public Usuario() {
    }

    public Usuario(String cedula, String apellido1, String apellido2, String nombre, String clave, int activo) {
        this.cedula = cedula;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.nombre = nombre;
        this.clave = clave;
        this.activo = activo;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public int getActivo() {
        return activo;
    }

    public void setActivo(int activo) {
        this.activo = activo;
    }    

    @Override
    public String toString() {
        return "Usuario{" + "cedula=" + cedula + ", apellido1=" + apellido1 + ", apellido2=" + apellido2 + ", nombre=" + nombre + ", clave=" + clave + ", activo=" + activo + '}';
    }
    
    private String cedula;
    private String apellido1;
    private String apellido2;
    private String nombre;
    private String clave;
    private int activo;    
}
