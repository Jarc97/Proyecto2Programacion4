<%-- 
// crearVotacion.jsp  
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
        <script src="scripts/crearVotacion.js" type="text/javascript"></script>
        <% response.setHeader("cache-control", "no-cache, no-store, must-revalidate"); %>
        <title>Crear Votacion</title>
    </head>
    <body onload ="horaActual()">
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
                    Por favor rellene el formulario para crear una votacion.
                </h1>
            </div>
            <div class="form">
                <form id="form1" action="ServicioCrearVotacion" method="get" enctype="multipart/form-data">
                    <table>
                        <tr>
                            <td>Fecha inicial: </td>
                            <td>
                                <div id="hora">hora</div>                               
                            </td>
                        </tr> 
                        <tr>
                            <td>Fecha de apertura: </td>
                            <td>
                                 <input type="date" name="fechaApertura" min=sysdate/>
                                 <input type="time" name="horaApertura"/>                               
                            </td>
                        </tr>
                        <tr>
                            <td>Fecha de cierre: </td>
                            <td>
                                <input type="date" name="fechaCierre" min=sysdate/>
                                 <input type="time" name="horaCierre"/>   
                            </td>
                        </tr>  
                        <tr>
                            <td>Fecha final: </td>
                            <td>
                                <input type="date" name="fechaFinal" min=sysdate/>
                                 <input type="time" name="horaFinal"/>   
                            </td>
                        </tr>
                        <tr>
                            <td></td><td><input type="submit"></td>
                        </tr>
                    </table>
                </form>     
            </div>
        </div>        
        <jsp:directive.include file="footer.jsp" />   
    </body>
</html>
