
// UsuariosHandler.java
// 
// EIF209 - Programación 4 – Proyecto #2
// Abril 2019 
// 
// Autores: 
//  - 402360123 Luis Felipe Soto Cruz
//  - 116760031 Julio Rodriguez Chavarria 

package control;

import java.util.ArrayList;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

import modelo.Usuario;

public class UsuariosHandler extends DefaultHandler {
    
    private ArrayList<Usuario> usuarios = new ArrayList<>();
    
    boolean boolNombre = false;
    boolean boolApellido1 = false;
    boolean boolApellido2 = false;
    boolean boolCedula = false;
    boolean boolClave = false;
    boolean boolActivo = false;
    
    String nombreStr;
    String apellido1Str;
    String apellido2Str;
    String cedulaStr;
    String claveStr;
    int activoInt;

    public void startElement(String uri, String localName,String qName, Attributes attributes) throws SAXException {

        System.out.println("Start Element: " + "<" + qName + ">");

        if (qName.equalsIgnoreCase("nombre")) {
                boolNombre = true;
        }

        if (qName.equalsIgnoreCase("apellido1")) {
                boolApellido1 = true;
        }

        if (qName.equalsIgnoreCase("apellido2")) {
                boolApellido2 = true;
        }

        if (qName.equalsIgnoreCase("cedula")) {
                boolCedula = true;
        }

        if (qName.equalsIgnoreCase("clave")) {
                boolClave = true;
        }
        
        if (qName.equalsIgnoreCase("activo")) {
                boolActivo = true;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        System.out.println("End Element: " + "<" + qName + ">");
        if (qName.equalsIgnoreCase("usuario")) {
            Usuario usuario = new Usuario();
            usuario.setNombre(nombreStr);
            usuario.setApellido1(apellido1Str);
            usuario.setApellido2(apellido2Str);
            usuario.setCedula(cedulaStr);
            usuario.setClave(claveStr);
            usuario.setActivo(activoInt);
            
            usuarios.add(usuario);
        }
    }
    
    @Override
    public void characters(char ch[], int start, int length) throws SAXException {
        if (boolNombre) {
            nombreStr = new String(ch, start, length);
            System.out.println("Nombre: " + nombreStr);
            boolNombre = false;
        }

        if (boolApellido1) {
            apellido1Str = new String(ch, start, length);
            System.out.println("Apellido1: " + new String(ch, start, length));
            boolApellido1 = false;
        }

        if (boolApellido2) {
            apellido2Str = new String(ch, start, length);
            System.out.println("Apellido2: " + new String(ch, start, length));
            boolApellido2 = false;
        }

        if (boolCedula) {
            cedulaStr = new String(ch, start, length);
            System.out.println("Cedula: " + new String(ch, start, length));
            boolCedula = false;
        }

        if (boolClave) {
            claveStr = new String(ch, start, length);
            System.out.println("Clave: " + new String(ch, start, length));
            boolClave = false;
        }
        
        if (boolActivo) {
            activoInt = Integer.parseInt(new String(ch, start, length));
            System.out.println("Activo: " + new String(ch, start, length));
            boolActivo = false;
        }
    }
    
    public ArrayList<Usuario> getUsuarios() {
        return usuarios;
    }

}
