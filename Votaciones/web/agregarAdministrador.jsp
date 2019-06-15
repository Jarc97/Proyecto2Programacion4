<%-- 
    Document   : agregarAdministrador
    Created on : 14/06/2019, 06:43:23 PM
    Author     : Feli
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">        
        <link href="css/index.css" rel="stylesheet" type="text/css"/>
        <title>JSP Page</title>
    </head>
    <body>
        <jsp:directive.include file="headerAdministrador.jsp" />        
        <div id = "wrapper">
            <div class ="titRegis">
                <h1>
                    Por favor rellene el formulario para agregar un administrador.
                </h1>
            </div>
            <div class="form">
                <form id="form1" action="ServicioAgregarAdministrador" method="get" enctype="multipart/form-data">
                    <table>
                        <tr>
                            <td>Cedula:</td><td><input type="text"autocomplete="off" placeholder="Cedula" name="cedula"/></td>
                        </tr> 
                        <tr>
                            <td>Nombre:</td><td><input type="text"autocomplete="off" placeholder="Nombre" name="nombre"/></td>
                        </tr>
                        <tr>
                            <td>Primer apellido:</td><td><input type="text"autocomplete="off" placeholder="Primer apellido" name="apellido1"/></td>
                        </tr>
                        <tr>
                            <td>Segundo apellido:</td><td><input type="text"autocomplete="off" placeholder="Segundo apellido" name="apellido2"/></td>
                        </tr>
                        <tr>
                            <td>Nombre de usuario:</td><td><input type="text" autocomplete="off" placeholder="Usuario" name="usuario"/></td>
                        </tr>  
                        <tr>
                            <td>Clave:</td><td><input type="password" autocomplete="off" placeholder="Clave" name="clave"/></td>
                        </tr>
                        <tr>
                            <td></td><td><input type="submit"></td>
                        </tr>
                    </table>
                </form>     
            </div>
        </div>        
        <jsp:directive.include file="footer.jsp" />   
    </body>
</html>
