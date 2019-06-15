<%-- 
    Document   : votacion
    Created on : 15/06/2019, 01:19:10 PM
    Author     : Feli
--%>

<%@page import="control.GestorVotaciones"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/index.css" rel="stylesheet" type="text/css"/>
        <link href="css/tabla.css" rel="stylesheet" type="text/css"/>
        <% response.setHeader("cache-control", "no-cache, no-store, must-revalidate"); %>
        <title>JSP Page</title>
    </head>
    <body>
        <%
            GestorVotaciones ge = GestorVotaciones.obtenerInstancia();
            HttpSession sesionActual = request.getSession();
            long transcurrido = System.currentTimeMillis() - sesionActual.getLastAccessedTime();
            String id = "";

            if (transcurrido > (1000 * 60 * 5)) {
                request.getRequestDispatcher("errorLogin.jsp?error=1").forward(request, response);
            }         

        %>
        <jsp:directive.include file="headerUsuario.jsp" />
        <div id = "wrapper">
            <h3>
                Para votar debe cambiar su contraseña por defecto.
                Tenga en cuenta que dispone de dos minutos para votar.
            </h3>
            <div>
                <%=
                    ge.mostrarVotacionesDisponibles(sesionActual)
                %>
            </div>
        </div>
        <jsp:directive.include file="footer.jsp" />
    </body>
</html>
