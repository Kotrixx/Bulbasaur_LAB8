<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Login Televiajes</title>
    <jsp:include page="includes/headCSS2.jsp"></jsp:include>
    <style>
        .form-signin label,
        .form-signin input {
            color: #f2f2f2; /* Gris claro */
        }
    </style>
</head>
<body class="d-flex">
<div class="container">
    <div class="center-block">
        <div class="black-box">
            <img src="resources/img/logo.png" class="logo">
            <div class="welcome-text"><b>Bienvenido Televiajero</b></div>
            <form class="form-signin" method="POST" action="<%=request.getContextPath()%>/CrearUsuarioServlet">
                <div class="form-group">
                    <div class="row">
                        <div class="col-sm-6">
                            <%--@declare id="nombre"--%><label for="nombre">Nombre:</label>
                            <input type="text" name="nombre" required aria-label="nombre" class="form-control" placeholder="Nombre">

                        </div>
                        <div class="col-sm-6">
                            <label for="apellido">Apellido:</label>
                            <input type="text" name="apellido" required aria-label="apellido" class="form-control" placeholder="Apellido">
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="row">
                        <div class="col-sm-8">
                            <label for="correo">Correo PUCP:</label>
                            <input type="email" name="correo" required aria-label="correo" class="form-control" placeholder="Correo electrónico">
                        </div>
                        <div class="col-sm-4">
                            <label for="edad">Edad:</label>
                            <input type="text" name="edad" required aria-label="edad" class="form-control" placeholder="Edad">
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="row">
                        <div class="col-sm-6">
                            <label for="codigo">Código PUCP:</label>
                            <input type="text" name="codigo" required aria-label="codigo" class="form-control" placeholder="Código PUCP">
                        </div>
                        <div class="col-sm-6">
                            <label for="especialidad">Especialidad:</label>
                            <input type="text" name="especialidad" required aria-label="especialidad" class="form-control" placeholder="Especialidad">
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="row">
                        <div class="col-sm-6">
                            <label for="contrasenha">Contraseña:</label>
                            <input type="password" name="contrasenha" required aria-label="contrasenha" class="form-control" placeholder="Contraseña">
                        </div>
                        <div class="col-sm-6">
                            <label for="contrasenha_repetida">Confirmar Contraseña:</label>
                            <input type="password" name="contrasenha_repetida" required aria-label="contrasenha_repetida" class="form-control" placeholder="Confirmar Contraseña">
                        </div>
                    </div>
                </div>
                <div class="text-center">
                    <button type="submit" class="btn-ingresar">Registrarse</button>
                    <div class="register-link">
                        <a href="<%=request.getContextPath()%>/">Volver a inicio de sesión</a>
                    </div>
                </div>
            </form>
        </div>
    </div>

</div>




<%--<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>--%>
<jsp:include page="/includes/footer2.jsp"/>
</body>
</html>
