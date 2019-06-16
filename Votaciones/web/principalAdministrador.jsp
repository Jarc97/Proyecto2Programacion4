<%-- 
    Document   : principalAdministrador
    Created on : 07/06/2019, 05:40:46 PM
    Author     : Feli
--%>

<%@page import="control.GestorAdministradores"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/index.css" rel="stylesheet" type="text/css"/>
        <% response.setHeader("cache-control", "no-cache, no-store, must-revalidate"); %>
        <title>JSP Page</title>
    </head>
    <body>
        <%
            GestorAdministradores ge = GestorAdministradores.obtenerInstancia();
            HttpSession sesionActual = request.getSession();
            long transcurrido = System.currentTimeMillis() - sesionActual.getLastAccessedTime();
            String id = "";

            if (transcurrido > (1000 * 60 * 5)) {
                request.getRequestDispatcher("errorLogin.jsp?error=1").forward(request, response);
            }

            if (sesionActual.getAttribute("usuario") != null) {
                id = sesionActual.getAttribute("usuario").toString();

            } else {
                request.getRequestDispatcher("errorLogin.jsp").forward(request, response);
            }

        %>
        <jsp:directive.include file="headerAdministrador.jsp" />        
        <div id = "wrapper">
            <h1>Bienvenido al sistema de administración de votaciones.</h1>
        </div>        
        <jsp:directive.include file="footer.jsp" />   
    </body>
</html>
