<%@ page import="com.example.demo.models.beans.Seguro" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: Kots
  Date: 6/17/2023
  Time: 3:59 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%ArrayList<Seguro> listaSeguros = (ArrayList<Seguro>) request.getAttribute("listaSeguros");%>
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


<section class="py-8" style="background-image: url('resources/img/fondoViaje2.jpg')">
    <br><br><br><br>

    <div class="container">

        <div class="row">
            <div class="col-sm-6">
                <div class="imagen-container">
                    <img src="resources/img/Nueva-York-NIGHT-2.jpg" alt="Imagen" class="card-img-top mb-5 mb-md-0">
                </div>


            </div>
            <div class="col-sm-6">
                <form class="form-signin" method="POST" action="<%=request.getContextPath()%>/CrearUsuarioServlet">
                    <!-- Contenido del formulario -->

                    <div class="form-group">
                        <div class="row">
                            <div class="col-sm-6">
                                <label for="ciudadOrigen">Cuidad origen:</label>
                                <input type="text" name="ciudadOrigen" required aria-label="ciudadOrigen" class="form-control" placeholder="Cuidad origen">
                            </div>
                            <div class="col-sm-6">
                                <label for="cuidadOrigen">Ciudad destino:</label>
                                <input type="email" name="cuidadOrigen" required aria-label="cuidadOrigen" class="form-control" placeholder="Cuidad destino">
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="row">
                            <div class="col-sm-8">

                                <label for="fechaViaje">Fecha de viaje:</label>
                                <input type="text" name="fechaViaje" required aria-label="fechaViaje" class="form-control" placeholder="Fecha de viaje">

                            </div>
                            <div class="col-sm-4">
                                <label for="numBoletos">Numero de boletos:</label>
                                <input type="text" name="numBoletos" required aria-label="numBoletos" class="form-control" placeholder="Numero de boletos">
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="row">
                            <div class="col-sm-6">
                                <label for="costoTotal">Costo total:</label>
                                <input type="text" name="costoTotal" required aria-label="costoTotal" class="form-control" placeholder="Costo total">
                            </div>
                            <div class="col-sm-6">
                                <label for="enmpresaSeguros">Empresa de Seguros:</label>
                                <select name="idSeguro" id="idSeguro" class="form-select">
                                    <option value="0">Elija una empresa de seguros</option>
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
                        <button type="submit" class="btn btn-success">Guardar</button>
                        <a type="submit" class="btn btn-danger">Cancelar</a>
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
