<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Página con cuadrado negro centrado</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        body {
            background-image: url("fondo.jpg");
            background-repeat: no-repeat;
            background-size: cover;
        }

        .center-block {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }

        .square {
            max-width: 500px;
            background-color: black;
            display: flex;
            justify-content: flex-start;
            align-items: center;
            flex-direction: column;
            overflow: hidden;
            padding: 40px;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.3);
        }

        .logo {
            max-width: 200px;
            max-height: 200px;
            height: auto;
            width: auto;
            margin-bottom: 20px;
        }

        .welcome-text {
            color: white;
            font-size: 20px;
            margin-bottom: 20px;
        }

        .btn-ingresar {
            background-color: #00ccff;
            color: white;
            border-radius: 20px;
            border: none;
            margin-bottom: 20px;
            padding: 10px 20px;
            font-size: 16px;
            cursor: pointer;
        }

        .btn-ingresar:hover {
            background-color: #0099cc;
        }

        .register-link {
            color: white;
            text-align: center;
        }

        .register-link a {
            color: #00ccff;
            text-decoration: none;
            background-color: white;
            padding: 5px 10px;
            border-radius: 20px;
            transition: background-color 0.3s ease;
        }

        .register-link a:hover {
            background-color: #00ccff;
            color: white;
        }
    </style>
</head>
<body class="d-flex">
<div class="container">
    <div class="container flex-grow-1">
        <div class="col-md-12 center-block">
            <div class="square">
                <div>
                    <br>
                    <div class="welcome-text">Crea un nuevo usuario</div>
                    <form class="form-signin" method="POST" action="<%=request.getContextPath()%>/CrearUsuarioServlet">
                        <div class="mb-3">
                            <label class='text-light' for="nombre">Nombre</label>
                            <input type="text" class="form-control" name="nombre" id="nombre">
                        </div>
                        <div class="mb-3">
                            <label class='text-light' for="apellido">Apellido</label>
                            <input type="text" class="form-control" name="apellido" id="apellido">
                        </div>
                        <div class="mb-3">
                            <label class='text-light' for="edad">Edad</label>
                            <input type="text" class="form-control" name="edad" id="edad">
                        </div>
                        <div class="mb-3">
                            <label class='text-light' for="codigo">Codigo PUCP</label>
                            <input type="text" class="form-control" name="codigo" id="codigo">
                        </div>
                        <div class="mb-3">
                            <label class='text-light' for="correo">Correo PUCP</label>
                            <input type="text" class="form-control" name="correo" id="correo">
                        </div>
                        <div class="mb-3">
                            <label class='text-light' for="especialidad">Especialidad</label>
                            <input type="text" class="form-control" name="especialidad" id="especialidad">
                        </div>
                        <div class="mb-3">
                            <label class='text-light' for="contrasenha">Contraseña</label>
                            <input type="text" class="form-control" name="contrasenha" id="contrasenha">
                        </div>
                        <div class="mb-3">
                            <label class='text-light' for="contrasenha_repetida">Confirme contraseña</label>
                            <input type="text" class="form-control" name="contrasenha_repetida" id="contrasenha_repetida">
                        </div>
                        <div class="text-center">
                            <button type="submit" class="btn-ingresar btn-lg">Crear</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
