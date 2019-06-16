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
                Por favor seleccione el partido.
            </h1>
            <div id="countdown"></div>
            <script>
                var end = new Date('12/17/2100 9:30 AM');

                var _second = 1000;
                var _minute = _second * 60;
                var _hour = _minute * 60;
                var _day = _hour * 24;
                var timer;

                function showRemaining() {
                    var now = new Date();
                    var distance = end - now;
                    if (distance < 0) {

                        clearInterval(timer);
                        document.getElementById('countdown').innerHTML = 'EXPIRED!';

                        return;
                    }
                    var days = Math.floor(distance / _day);
                    var hours = Math.floor((distance % _day) / _hour);
                    var minutes = Math.floor((distance % _hour) / _minute);
                    var seconds = Math.floor((distance % _minute) / _second);

                    document.getElementById('countdown').innerHTML = days + ' dias, ';
                    document.getElementById('countdown').innerHTML += hours + ' horas, ';
                    document.getElementById('countdown').innerHTML += minutes + ' minutos y ';
                    document.getElementById('countdown').innerHTML += seconds + ' segundos';
                }

                timer = setInterval(showRemaining, 1000);
            </script>
            <div>
                <%= gv.mostrarVotacionesDisponibles(sesionActual)%>
            </div>
        </div>
        <jsp:directive.include file="footer.jsp" />
    </body>
</html>
