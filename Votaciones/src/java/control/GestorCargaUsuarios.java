/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.io.File;
import java.io.StringReader;
import java.util.ArrayList;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import modelo.Usuario;
import org.xml.sax.InputSource;

/**
 *
 * @author julio
 */
public class GestorCargaUsuarios {
    
    private static GestorCargaUsuarios instancia = null;
    
    private GestorCargaUsuarios() {
        
    }
    
    public static GestorCargaUsuarios obtenerInstancia()
            throws InstantiationException, ClassNotFoundException, IllegalAccessException {
        if (instancia == null) {
            instancia = new GestorCargaUsuarios();
        }
        return instancia;
    }
    
    // leer el contenido del xml y crear lista de Usuarios
    public void interpretar(String content) throws Exception {
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        SAXParser saxParser = saxParserFactory.newSAXParser();
        UsuariosHandler handler = new UsuariosHandler();
        //saxParser.parse(file, handler);
        saxParser.parse(new InputSource(new StringReader(content)), handler);
        // handler.getDivisiones();
    }
    
    // guardar los usuarios en la BD
    private void guardar(ArrayList <Usuario> usuarios) {
        
    }
}
