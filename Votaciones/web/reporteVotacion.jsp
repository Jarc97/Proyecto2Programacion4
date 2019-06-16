
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="control.GestorReporteVotacion"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <%
            GestorReporteVotacion grv = GestorReporteVotacion.obtenerInstancia();
            //HttpSession sesion = request.getSession(true);
            //int idVotacion = Integer.valueOf((String) sesion.getAttribute("idVotacion"));
            int idVotacion = 1;
        %>
        
        <p>Total de votantes registrados: <%= grv.totalVotantesRegistrados(idVotacion) %></p>
        
        <p>Total de votos efectivos: <%= grv.toStringVotosEfectivos(idVotacion) %></p>
        
        <p>Total de abstencionismo: <%= grv.toStringAbstencionismo(idVotacion) %></p>
        
        <%= grv.votosPorPartido(idVotacion) %>
        
        <h2> <%= grv.resultadoVotacion(idVotacion) %> </h2>
                
        
    </body>
</html>
