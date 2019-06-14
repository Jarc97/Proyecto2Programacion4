<%-- 
    Document   : cargarUsuarios
    Created on : 14/06/2019, 04:13:48 PM
    Author     : Feli
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/index.css" rel="stylesheet" type="text/css"/>
        <title>Cargar Usuarios</title>
    </head>
    <body>
        <jsp:directive.include file="headerAdministrador.jsp" />        
        <div id = "wrapper">
            <div class ="titRegis">
                <h1>
                    Por favor seleccione un archivo para cargar datos.
                </h1>
            </div>
            <div class="form">
                <form id="form1" action="ServicioCargarUsuarios" method="POST" enctype="multipart/form-data">
                    <p>Seleccionar archivo</p>
                    <input type="file" id="archivo" name="archivo" />
                    <input type="submit">
                </form>
            </div>
        </div>        
        <jsp:directive.include file="footer.jsp" />   

    </body>
</html>
