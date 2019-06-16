<%-- 
// reporteVotacion.jsp  
// 
// EIF209 - Programación 4 – Proyecto #2
// Abril 2019 
// 
// Autores: 
//  - 402360123 Luis Felipe Soto Cruz
//  - 116760031 Julio Rodriguez Chavarria
// 
// --%> 
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="control.GestorReporteVotacion"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <% response.setHeader("cache-control", "no-cache, no-store, must-revalidate"); %>
        <link href="css/index.css" rel="stylesheet" type="text/css"/>
        <title>JSP Page</title>
    </head>
    <body>
        <%
            HttpSession sesionActual = request.getSession();
            String id = (String) sesionActual.getAttribute("usuario");
            if (sesionActual.getAttribute("usuario") != null) {
                id = sesionActual.getAttribute("usuario").toString();

            } else {
                request.getRequestDispatcher("errorLogin.jsp").forward(request, response);
            }
            GestorReporteVotacion grv = GestorReporteVotacion.obtenerInstancia();
            //HttpSession sesion = request.getSession(true);
            //int idVotacion = Integer.valueOf((String) sesion.getAttribute("idVotacion"));
            int idVotacion = 1;
        %>
        <jsp:directive.include file="headerAdministrador.jsp" />
        <div id = "wrapper">
            <h1>
                A continuación se genera el reporte de la votación actual.
            </h1>
            <div>
                <p>Total de votantes registrados: <%= grv.totalVotantesRegistrados(idVotacion)%></p>
                <p>Total de votos efectivos: <%= grv.toStringVotosEfectivos(idVotacion)%></p>
                <p>Total de abstencionismo: <%= grv.toStringAbstencionismo(idVotacion)%></p>
                <%= grv.votosPorPartido(idVotacion)%>
                <h2> <%= grv.resultadoVotacion(idVotacion)%> </h2>
            </div>
        </div>
        <jsp:directive.include file="footer.jsp" />
    </body>
</html>
