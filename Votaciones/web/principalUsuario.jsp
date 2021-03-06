<%-- 
// principalUsuario.jsp  
// 
// EIF209 - Programación 4 – Proyecto #2
// Abril 2019 
// 
// Autores: 
//  - 402360123 Luis Felipe Soto Cruz
//  - 116760031 Julio Rodriguez Chavarria
// 
// --%> 


<%@page import="control.GestorVotaciones"%>
<%@page import="control.GestorUsuarios"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/index.css" rel="stylesheet" type="text/css"/>
        <% response.setHeader("cache-control", "no-cache, no-store, must-revalidate"); %>
        <title>Inicio</title>
    </head>
    <body>
        <%
            GestorUsuarios ge = GestorUsuarios.obtenerInstancia();
            HttpSession sesionActual = request.getSession();
            String id = (String) sesionActual.getAttribute("usuario");
            if (sesionActual.getAttribute("usuario") != null) {
                id = sesionActual.getAttribute("usuario").toString();

            } else {
                request.getRequestDispatcher("errorLogin.jsp").forward(request, response);
            }
        %>
        <jsp:directive.include file="headerUsuario.jsp" />
        <div id = "wrapper">
            <h1>Bienvenido al sistema de votacion, <%= ge.obtenerNombre(sesionActual)%></h1>
            <h3>
                Para votar debe cambiar su contraseña por defecto.
            </h3>
            <div class = "form">
                <table>
                    <td>
                        <form action="cambiarClave.jsp" method ="post">
                            <input type="submit" value="Cambiar Clave"/>
                        </form>
                    </td>
                    <td>
                        <form action="ServicioValidarVoto" method ="post">
                            <input type="submit" value="Votar"/>
                        </form>
                    </td>

                </table>
                <div>               
                </div>
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
                                        mensaje = "No ha cambiado la clave.";
                                        break;
                                    case 2:
                                        mensaje = "Ya participó en esa votacion.";
                                        break;
                                    default:
                                        break;
                                }
                                out.println(mensaje);
                            %>
                        </span>
                    </p>
                </div>
            </div>
        </div>
        <jsp:directive.include file="footer.jsp" />
    </body>
</html>
