<%--
    Document   : paginaVotacion
    Created on : 15/06/2019, 08:55:54 PM
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
        <script src="scripts/contador.js" type="text/javascript"></script>
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
                Por favor, efectue su voto.
            </h1>
            <h3>
                Recuerde que dispone de 2 minutos. Al acabarse el tiempo se cerrará la sesión.
            </h3>
            <p>Tiempo restante: 
            <a id="tiempo"></a> 
            </p>           
            <div id = "votacion">
                
            </div>
        </div>
        <jsp:directive.include file="footer.jsp" />
    </body>
</html>
