<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Login Televiajes</title>
    <jsp:include page="includes/headCSS.jsp"></jsp:include>
</head>
<body class="d-flex">
<jsp:useBean id="usuarioSession" type="com.example.demo.models.beans.Usuario" scope="session"
             class="com.example.demo.models.beans.Usuario"/>

<div class="container">
    <div class="container flex-grow-1">
        <div class="col-md-12 center-block">
            <div class="square">
                <div>
                    <br>
                    <img src="resources/img/logo.png" class="logo">
                    <div class="welcome-text">Bienvenido Televiajero</div>
                    <form class="form-signin" method="POST" action="<%=request.getContextPath()%>/LoginServlet">
                        <div class="form-group">
                            <input type="text" name="inputEmail" required aria-label="inputEmail" class="form-control" placeholder="Usuario">
                        </div>
                        <div class="form-group">
                            <input type="password" name="inputPassword" required aria-label="inputPassword" class="form-control" placeholder="Contraseña">
                            <%if(request.getAttribute("error")!=null){%>
                            <p class="text-danger">Credenciales incorrectas</p>
                            <%}%>
                        </div>
                        <div class="text-center">
                            <button type="submit" class="btn-ingresar">Ingresar</button>
                            <div class="register-link">
                                <a href="<%=request.getContextPath()%>/CrearUsuarioServlet">Soy nuevo y quiero registrarme</a>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<%--<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>--%>
<jsp:include page="/includes/footer2.jsp"/>
</body>
</html>
