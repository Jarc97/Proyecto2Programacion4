<%-- 
    Document   : registrarPartidos
    Created on : 12/06/2019, 04:14:56 PM
    Author     : Feli
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/index.css" rel="stylesheet" type="text/css"/>
        <title>Registrar Partido</title>
    </head>
    <body>
        <jsp:directive.include file="headerAdministrador.jsp" />        
        <div id = "wrapper">
            <div class ="titRegis">
                <h1>
                    Por favor rellene el formulario para registrar un partido al sistema.
                </h1>
            </div>
            <div class="form">
                <form id="form1" action="ServicioAgregarPartido" method="post" enctype="multipart/form-data">
                    <table>
                        <tr>
                            <td>Siglas</td><td><input type="text"autocomplete="off" placeholder="Siglas" name="siglas"/></td>
                        </tr> 
                        <tr>
                            <td>Nombre</td><td><input type="text"autocomplete="off" placeholder="Nombre" name="nombre"/></td>
                        </tr>
                        <tr>
                            <td>Observaciones</td><td><input type="text" autocomplete="off" placeholder="Observaciones" name="observaciones"/></td>
                        </tr>  
                        <tr>
                            <td>Bandera</td><td><input type="file" name="bandera"/></td>
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