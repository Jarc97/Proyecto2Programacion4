<%-- 
    Document   : votacion
    Created on : 15/06/2019, 01:19:10 PM
    Author     : Feli
--%>

<%@page import="control.GestorUsuarios"%>
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
           GestorVotaciones gv = GestorVotaciones.obtenerInstancia();
            HttpSession sesionActual = request.getSession();
            String id = null;
            if (sesionActual.getAttribute("usuario") != null) {
                id = sesionActual.getAttribute("usuario").toString();

            } else {
                request.getRequestDispatcher("errorLogin.jsp").forward(request, response);
            }              
        %>
        <jsp:directive.include file="headerUsuario.jsp" />
        <div id = "wrapper">
            <h1>
                Por favor seleccione la votacion en la que desea participar.
            </h1>
            <h3>
                Tenga en cuenta que dispone de dos minutos para votar.
            </h3>
            <div>
                <%= gv.mostrarVotacionesDisponibles(sesionActual)
               %>
            </div>
        </div>
        <jsp:directive.include file="footer.jsp" />
    </body>
</html>
