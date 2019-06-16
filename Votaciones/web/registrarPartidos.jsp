<%-- 
    Document   : registrarPartidos
    Created on : 12/06/2019, 04:14:56 PM
    Author     : Feli
--%>

<%@page import="control.GestorPartidos"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/index.css" rel="stylesheet" type="text/css"/>
        <script src="scripts/validarEspacios.js" type="text/javascript"></script>
        <% response.setHeader("cache-control", "no-cache, no-store, must-revalidate"); %>
        <title>Registrar Partido</title>
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
                    Por favor rellene el formulario para registrar un partido al sistema.
                </h1>
            </div>
            <div class="form">
                <form id="form1" action="ServicioAgregarPartido" onsubmit='return validarEspaciosRegistroPartido()' method="post" enctype="multipart/form-data">
                    <table name="tabla">
                        <tr>
                            <td>Siglas</td>
                            <td>
                                <input id="in" type="text"autocomplete="off" placeholder="Siglas" name="siglas"/>
                                <div id="esiglas" style="color:#f00;"></div> 
                            </td>                            
                        </tr> 
                        <tr>
                            <td>Nombre</td><td><input type="text"autocomplete="off" placeholder="Nombre" name="nombre"/></td>
                        </tr>
                        <tr>
                            <td>Observaciones</td><td><input type="text" autocomplete="off" placeholder="Observaciones" name="observaciones"/></td>
                        </tr>  
                        <tr>
                            <td>Bandera</td><td><input type="file" name="bandera"/></td>
                        </tr>
                        <tr>
                            <td>Cedula Candidato</td><td><input type="text" autocomplete="off" placeholder="Cedula Candidato" name="cedulaCandidato"/></td>
                        </tr>
                        <tr>
                            <td>Foto Candidato</td><td><input type="file" name="foto"/></td>
                        </tr>
                        <tr>
                            <td></td><td><input type="submit"></td>
                        </tr>
                        <div id = "error">
                            <p>
                                <span style="color:red">
                                    <% int codError = 0;
                                        String mensaje = "";
                                        try {
                                            codError = Integer.parseInt(request.getParameter("error"));
                                        } catch (Exception e) {
                                        }
                                        switch (codError) {
                                            case 1:
                                                mensaje = "No existe un usuario con esa cédula";
                                                break;
                                            default:
                                                break;
                                        }
                                        out.println(mensaje);
                                    %>
                                </span>
                            </p>
                        </div>
                    </table>
                </form>     
            </div>
        </div>        
        <jsp:directive.include file="footer.jsp" />   
    </body>
</html>