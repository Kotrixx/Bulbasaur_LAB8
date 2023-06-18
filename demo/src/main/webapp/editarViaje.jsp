<%@ page import="com.example.demo.models.beans.Seguro" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.example.demo.models.beans.Viaje" %>
<%@ page import="java.math.BigDecimal" %><%--
  Created by IntelliJ IDEA.
  User: Kots
  Date: 6/17/2023
  Time: 3:59 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%ArrayList<Seguro> listaSeguros = (ArrayList<Seguro>) request.getAttribute("listaSeguros");%>
<%Viaje viaje = (Viaje)request.getAttribute("viaje");%>
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
                    <h1 class="mb-5">¡Edita tu viaje!</h1>
                    <!-- Signup form-->
                    <!-- * * * * * * * * * * * * * * *-->
                    <!-- * * SB Forms Contact Form * *-->
                    <!-- * * * * * * * * * * * * * * *-->
                    <!-- This form is pre-integrated with SB Forms.-->
                    <!-- To make this form functional, sign up at-->
                    <!-- https://startbootstrap.com/solution/contact-forms-->
                    <!-- to get an API token!-->

                    <!-- Busqueda-->


                </div>
            </div>
        </div>
    </div>
</header>


<!-- Icons Grid-->
<section class="features-icons bg-light text-center">
</section>


<section class="py-8" style="background-image: url('resources/img/Modelos de vehículos en mapas_ _ Foto Premium.jpg')">
    <br><br><br><br>

    <div class="container">

        <div class="row">
            <div class="col-sm-6">
                <div class="imagen-container">
                    <img src="resources/img/istockphoto-1153849876-612x612.jpg" alt="Imagen" class="card-img-top mb-5 mb-md-0">
                </div>


            </div>
            <div class="col-sm-6">
                <form class="form-signin" method="POST" action="<%=request.getContextPath()%>/main-page?action=edita&id=<%=viaje.getIdViaje()%>">
                    <!-- Contenido del formulario -->

                    <div class="form-group">
                        <div class="row">
                            <div class="col-sm-6">
                                <label for="ciudadOrigen">Cuidad origen:</label>
                                <input type="text" name="ciudadOrigen" required aria-label="ciudadOrigen" class="form-control" value="<%=viaje.getCiudadOrigen()%>">
                            </div>
                            <div class="col-sm-6">
                                <label for="cuidadDestino">Ciudad destino:</label>
                                <input type="text" name="cuidadDestino" required aria-label="cuidadDestino" class="form-control" value="<%=viaje.getCiudadDestino()%>">
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="row">
                            <div class="col-sm-8">

                                <label for="fechaViaje">Fecha de viaje:</label>
                                <input type="text" name="fechaViaje" required aria-label="fechaViaje" class="form-control" value="<%=viaje.getFechaViaje()%>">

                            </div>
                            <div class="col-sm-4">
                                <label for="numBoletos">Numero de boletos:</label>
                                <input type="text" name="numBoletos" required aria-label="numBoletos" class="form-control" value="<%=viaje.getCantBoletos()%>">
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="row">
                            <div class="col-sm-6">
                                <label for="costoTotal">Costo Boleto:</label>
                                <%BigDecimal numeroDecimal = new BigDecimal(viaje.getCantBoletos());%>
                                <input type="text" name="costoBoleto" required aria-label="costoBoleto" class="form-control" value="<%=viaje.getCostoTotal().divide(numeroDecimal)%>">
                            </div>
                            <div class="col-sm-6">
                                <label for="enmpresaSeguros">Empresa de Seguros:</label>
                                <select name="idSeguro" id="idSeguro" class="form-select">
                                    <% for (Seguro seguro : listaSeguros) {%>
                                    <option value="<%=seguro.getIdSeguro()%>" <%--<%= empleado.getJob().getJobId().equals(job.getJobId()) ? "selected" : "" %>--%> >
                                        <%=seguro.getNombre()%>
                                    </option>
                                    <% }%>
                                </select>
                            </div>

                        </div>
                    </div>
                    <div class="text-center">
                        <button type="submit" class="btn btn-success" name="editar">Guardar</button>
                        <a href="<%=request.getContextPath()%>/main-page" class="btn btn-danger">Cancelar</a>
                    </div>
                </form>
            </div>
        </div>



    </div>

    <br><br><br><br>
</section>
<!-- Image Showcases-->

<!-- Testimonials-->

<!-- Call to Action-->

<!-- Footer-->
<jsp:include page="/includes/footer.jsp"/>

</body>
</html>
