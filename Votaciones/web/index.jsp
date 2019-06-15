<%-- 
    Document   : index
    Created on : 07/06/2019, 01:51:06 PM
    Author     : Feli
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/index.css" rel="stylesheet" type="text/css"/>
        <% response.setHeader("cache-control", "no-cache, no-store, must-revalidate"); %>
        <title>Sistema de Votaciones</title>
    </head>
    <body>      
        <%
            HttpSession sesionActual;
            sesionActual = request.getSession(true);
        %>
        <div id = "wrapperLogin">
            <div class = "formContent">
                <div class="fadeIn first">
                    <img src="img/logoUsua.jpg" alt="LogoUsuario">
                </div>
                <form name="loginForm" action="ServicioLogin" method="POST">
                    <h3>Usuario </h3>
                    <input type="text" name="usuario" placeholder="Cedula" autocomplete="off">
                    <input type="password" name="clave" placeholder="Contraseña">
                    <table>
                        <tr>
                            <input type="submit" value="Ingresar">
                        </tr>
                        <tr>
                            <a href="loginAdministrador.jsp">
                                <input type="button" value="Admin">
                            </a>
                        </tr>
                    </table>
                </form>
            </div>
        </div>
        <jsp:directive.include file="footer.jsp" />
    </body>
</html>