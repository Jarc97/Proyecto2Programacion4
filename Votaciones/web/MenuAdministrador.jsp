<%-- 
    Document   : MenuAdministrador
    Created on : Jun 7, 2019, 4:51:07 PM
    Author     : julio
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form id="form1" action="ServicioCargarUsuarios" method="POST" enctype="multipart/form-data">
            <p>Seleccionar archivo</p>
            <input type="file" id="archivo" name="archivo" />
            <input type="submit">
        </form>
    </body>
</html>
