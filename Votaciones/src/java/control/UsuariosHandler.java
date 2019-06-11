/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.util.ArrayList;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

import modelo.Usuario;

public class UsuariosHandler extends DefaultHandler {
    
    private ArrayList<Usuario> usuarios;
    
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        switch (qName) {
            case "usuario":
                System.out.println("BEGIN");
                break;
            case "/usuario":
                System.out.println("END");
                break;
            case "nombre":
                System.out.println("NOMBRE");
                break;
            case "apellido1":
                System.out.println("APELLIDO1");
                break;
            case "apellido2":
                System.out.println("APELLIDO2");
                break;
            case "cedula":
                System.out.println("CEDULA");
                break;
            case "clave":
                System.out.println("CLAVE");
                break;
        }
    }
    
    public ArrayList<Usuario> getUsuarios() {
        return usuarios;
    }

}
