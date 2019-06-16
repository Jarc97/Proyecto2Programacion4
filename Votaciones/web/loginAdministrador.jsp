<%-- 
// loginAdministrador.jsp  
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
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/index.css" rel="stylesheet" type="text/css"/>
        <% response.setHeader("cache-control", "no-cache, no-store, must-revalidate"); %>
        <title>Login</title>
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
                <form name="loginForm" action="ServicioLoginAdmin" method="post">
                    <h3>Administrador</h3>
                    <input type="text" name="usuario" placeholder="Nombre de usuario" autocomplete="off">
                    <input type="password" name="clave" placeholder="Contraseña">
                    <input type="submit" value="Ingresar">
                </form>
            </div>
        </div>
        <jsp:directive.include file="footer.jsp" />
    </body>
</html>
