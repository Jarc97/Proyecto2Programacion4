<%-- 
    Document   : principalUsuario
    Created on : 07/06/2019, 05:05:27 PM
    Author     : Feli
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/index.css" rel="stylesheet" type="text/css"/>
        <% response.setHeader("cache-control", "no-cache, no-store, must-revalidate"); %>
        <title>Inicio</title>
    </head>
    <body>
        <%
            HttpSession sesionActual;
            sesionActual = request.getSession(true);
        %>
        <jsp:directive.include file="headerUsuario.jsp" />
        <div id = "wrapper">
            <h1>Bienvenido al sistema de votacion, NOMBRE</h1>  
        </div>
        <jsp:directive.include file="footer.jsp" />
    </body>
</html>
