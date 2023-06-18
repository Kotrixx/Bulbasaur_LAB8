<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>CONFIRME CONTRASENHA</title>
    <jsp:include page="includes/headCSS.jsp"></jsp:include>
</head>
<body class="d-flex">
<jsp:useBean id="usuarioSession" type="com.example.demo.models.beans.Usuario" scope="session"
             class="com.example.demo.models.beans.Usuario"/>
<jsp:useBean id="usuarioSession" type="com.example.demo.models.beans.Usuario" scope="session"
             class="com.example.demo.models.beans.Usuario"/>
<jsp:useBean id="id" scope="request" type="java.lang.Integer"/>

<div class="container">
    <div class="container flex-grow-1">
        <div class="col-md-12 center-block">
            <div class="square">
                <div>
                    <br>
                    <img src="resources/img/logo.png" class="logo">
                    <div class="welcome-text">CONFIRME CONTRASENHA para borrar el viaje con id = <%=id%></div>
                    <form class="form-signin" method="POST" action="<%=request.getContextPath()%>/MainServlet?action=del&id=<%=id%>">
                        <div class="form-group">
                            <input type="password" name="inputPassword" required aria-label="inputPassword" class="form-control" placeholder="Contraseña">
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

