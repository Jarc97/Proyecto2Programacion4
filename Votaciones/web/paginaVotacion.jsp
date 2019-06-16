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
            <div id="tiempo"></div>
            <script>
                function startTimer(duration, display) {
                    var timer = duration, minutes, seconds;
                    setInterval(function () {
                        minutes = parseInt(timer / 60, 10);
                        seconds = parseInt(timer % 60, 10);

                        minutes = minutes < 10 ? "0" + minutes : minutes;
                        seconds = seconds < 10 ? "0" + seconds : seconds;

                        display.textContent = minutes + ":" + seconds;

                        if (--timer < 0) {
                            timer = duration;
                        }
                    }, 1000);
                }

            window.onload = function () {
                var dosMinutos = 60 * 2,
                    display = document.querySelector('#tiempo');
                startTimer(dosMinutos, display);
            };
            </script>
            <div>
                
            </div>
        </div>
        <jsp:directive.include file="footer.jsp" />
    </body>
</html>
