<%-- 
// paginaVotacion.jsp  
// 
// EIF209 - Programación 4 – Proyecto #2
// Abril 2019 
// 
// Autores: 
//  - 402360123 Luis Felipe Soto Cruz
//  - 116760031 Julio Rodriguez Chavarria
// 
// --%> 


<%@page import="control.GestorPartidos"%>
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
            GestorPartidos gp = GestorPartidos.obtenerInstancia();
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
                <%= gp.mostrarPartidosDisponibles(session) %>
            </div>
        </div>
        <jsp:directive.include file="footer.jsp" />
    </body>
</html>
