<%@ page import="com.example.demo.models.beans.Viaje" %><%--
  Created by IntelliJ IDEA.
  User: Kots
  Date: 6/17/2023
  Time: 3:57 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="usuarioSession" type="com.example.demo.models.beans.Usuario" scope="session" class="com.example.demo.models.beans.Usuario"/>
<jsp:useBean id="lista" scope="request" type="java.util.ArrayList<com.example.demo.models.beans.Viaje>"/>
<html>
<head>
    <title>Televiajes</title>
    <jsp:include page="includes/headCSS.jsp"></jsp:include>
</head>
<body>
<jsp:include page="includes/navbar.jsp">
    <jsp:param name="page" value=""/>
</jsp:include>
<header class="masthead" style="background: url('<%=request.getContextPath()%>/resources/img/fondo.jpg');">
    <div class="container position-relative">
        <div class="row justify-content-center">
            <div class="col-xl-6">
                <div class="text-center text-white">
                    <!-- Page heading-->
                    <h1 class="mb-5">¡Busca tus viajes!</h1>
                    <!-- Signup form-->
                    <!-- * * * * * * * * * * * * * * *-->
                    <!-- * * SB Forms Contact Form * *-->
                    <!-- * * * * * * * * * * * * * * *-->
                    <!-- This form is pre-integrated with SB Forms.-->
                    <!-- To make this form functional, sign up at-->
                    <!-- https://startbootstrap.com/solution/contact-forms-->
                    <!-- to get an API token!-->

                    <!-- Busqueda-->
                    <div class="row">
                        <form method="post" action="<%=request.getContextPath()%>/main-page?action=buscar">
                            <div class="input-group mb-3">
                                <input type="text" class="form-control" placeholder="Buscar por nombre" name="textoBuscar"
                                       value="    "/>
                                <button class="input-group-text" type="submit">
                                    <i class="bi bi-search"></i>
                                </button>
                                <a class="input-group-text" href="<%=request.getContextPath()%>/main-page">
                                    <i class="bi bi-x-circle"></i>
                                </a>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</header>



<!-- Icons Grid-->
<section class="features-icons bg-light text-center" >
    <div class="container">
        <h1>¡Lista completa de tus viajes!</h1>
    </div>
</section>
<section class="py-8" style="background-image: url('resources/img/bg-showcase-2.jpg')">
    <br><br><br><br>


    <div class="container">
        <div class="row row-cols-1 row-cols-md-2">

            <% for (Viaje viaje : lista) { %>
            <div class="col">
                <!-- Tarjeta 1 -->
                <div class="card mb-3">
                    <!-- Contenido de la tarjeta -->
                    <div class="row g-0">
                        <div class="col-md-4">
                            <img src="resources/img/imagenViaje.png" class="img-fluid rounded-start" alt="...">
                            <p></p>
                            <p>fecha-reserva:</p>
                            <p><%=viaje.getFechaReserva()%></p>
                            <p>fecha-salida:</p>
                            <p><%=viaje.getFechaViaje()%></p>
                        </div>
                        <div class="col-md-8">
                            <div class="card-body">
                                <h5 class="card-title"><b><%=viaje.getCiudadDestino()%> - <%=viaje.getCiudadOrigen()%></b> </h5>
                                <p class="card-text"><small class="text-body-secondary">ID: <%=viaje.getIdViaje()%></small></p>
                                <p class="card-text">Asegurado por <%=viaje.getSeguro().getNombre()%></p>
                                <p class="card-text">Numero de boletos: <%=viaje.getCantBoletos()%></p>
                                <p class="card-text">Precio: <%=viaje.getCostoTotal()%></p>
                                <a  class="btn-ingresar" href="<%=request.getContextPath()%>/main-page?action=edit"> Editar viaje </a>
                                <a class="btn-ingresar"href="<%=request.getContextPath()%>/main-page?action=del"> Eliminar viaje </a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <% } %>
        </div>
    </div>

    <br></br><br></br>
</section>
<!-- Image Showcases-->

<!-- Testimonials-->

<!-- Call to Action-->

<!-- Footer-->
<jsp:include page="/includes/footer.jsp"/>

</body>
</html>
