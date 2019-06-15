<%-- 
    Document   : cambiarClave
    Created on : 15/06/2019, 12:40:43 PM
    Author     : Feli
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="scripts/validarClave.js" type="text/javascript"></script>
        <link href="css/index.css" rel="stylesheet" type="text/css"/>
        <title>JSP Page</title>
    </head>
    <body>
        <jsp:directive.include file="headerUsuario.jsp" />
        <%
            HttpSession sesionActual = request.getSession();
            long transcurrido = System.currentTimeMillis() - sesionActual.getLastAccessedTime();
            String id = "";

            if (transcurrido > (1000 * 60 * 5)) {
                request.getRequestDispatcher("errorLogin.jsp?error=1").forward(request, response);
            }

            if (sesionActual.getAttribute("usuario") != null) {
                id = sesionActual.getAttribute("usuario").toString();

            } else {
                request.getRequestDispatcher("errorLogin.jsp").forward(request, response);
            }
        %>
        
              <div id = "wrapperLogin">
                <h1>
                    Pagina de solicitud de cambio de contraseña
                </h1>
                <div class = "formContent">
                    <div class="fadeIn first">
                        <h5><br />
                            Por favor llene los siguientes espacios:
                        </h5>
                    </div>
                    <form name="formName" action="ServicioCambiarClave" method="POST" onsubmit='return validarClave()'>
                        <input type="password" name="passwordNew1" placeholder="Contraseña Nueva">
                        <div id="epasswordNew1" style="color:#f00;"></div>                   
                        <input type="password" name="passwordNew2" placeholder="Repita la contraseña">
                        <div id="epasswordNew2" style="color:#f00;"></div>  
                        <input type="submit" id ="ingresar" value="Ingresar">
                    </form>
                </div>
            </div>
            <jsp:directive.include file="footer.jsp" />
    </body>
</html>
