<%-- 
// cargarUsuarios.jsp  
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
        <title>Cargar Usuarios</title>
    </head>
    <body>
        <jsp:directive.include file="headerAdministrador.jsp" />     
        <%
            HttpSession sesionActual = request.getSession();
            String id = (String) sesionActual.getAttribute("usuario");
            if (sesionActual.getAttribute("usuario") != null) {
                id = sesionActual.getAttribute("usuario").toString();

            } else {
                request.getRequestDispatcher("errorLogin.jsp").forward(request, response);
            }
        %>
        <div id = "wrapper">
            <div class ="titRegis">
                <h1>
                    Por favor seleccione un archivo para cargar datos.
                </h1>
            </div>
            <div class="form">
                <form id="form1" action="ServicioCargarUsuarios" method="POST" enctype="multipart/form-data">
                    <p>Seleccionar archivo</p>
                    <input type="file" id="archivo" name="archivo" />
                    <input type="submit">
                </form>
            </div>
        </div>        
        <jsp:directive.include file="footer.jsp" />   

    </body>
</html>
