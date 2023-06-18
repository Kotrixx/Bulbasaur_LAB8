
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="usuarioSession" type="com.example.demo.models.beans.Usuario" scope="session" class="com.example.demo.models.beans.Usuario"/>
<!-- Navigation-->
<nav class="navbar navbar-expand-lg navbar-light bg-light"
     <%--
        <%String status; switch (status){ %>
        <%case "Normal":%>
          style='background-color: #bf930d!important;'>
        <%break; case "Silver": %>
          style='background-color: #bf930d!important;'>
        --%>


  <div class="container px-4 px-lg-5">
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>

    <a class="navbar-brand" href="<%=request.getContextPath()%>/main-page"><img class="logo" src="resources/favicon.ico" style="max-width: 50px; margin-bottom: 0px;" alt="Logo">Televiajero</a>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
      <ul class="navbar-nav me-auto mb-2 mb-lg-0 ms-lg-4">
        <li class="nav-item"><a class="nav-link" href="<%=request.getContextPath()%>/main-page?action=add">Añadir viajes</a></li>


      </ul>

      <ul class="navbar-nav ml-auto">
        <li class="nav-item"><a class="nav-link">Hola! <b><i><%=usuarioSession.getNombre()+" "+usuarioSession.getApellido()%></i></b>,
          usted actualmente es usuario: <b><i><%=usuarioSession.getStatus()%></i></b></a></li>
        <li class="nav-item dropdown">
          <a class="nav-link"  href="<%=request.getContextPath()%>/LoginServlet?action=logout" style="color: darkred;"><b>Cerrar sesion</b></a>

        </li>
      </ul>
    </div>
  </div>
</nav>

<%--
<% String currentPage = request.getParameter("currentPage"); %>
<jsp:useBean id="usuarioLog" scope="session" type="com.example.demo.models.beans.Usuario"
             class="com.example.demo.models.beans.Viaje"/>
--%>

