<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
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
            width: 400px;
            height: 400px;
            background-color: black;
            display: flex;
            justify-content: center;
            align-items: center;
            flex-direction: column;
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

        .form-group {
            margin-bottom: 20px;
        }

        .btn-ingresar {
            background-color: #007bff;
            color: white;
            margin-bottom: 20px;
        }

        .register-link {
            color: white;
            text-align: center;
        }
    </style>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-md-12 center-block">
            <div class="square">
                <div>
                    <img src="logo.png" class="logo">
                    <div class="welcome-text">Bienvenido Televiajero</div>
                    <form>
                        <div class="form-group">
                            <input type="text" class="form-control" placeholder="Usuario">
                        </div>
                        <div class="form-group">
                            <input type="password" class="form-control" placeholder="Contraseña">
                        </div>
                        <div class="text-center">
                            <button type="submit" class="btn btn-primary btn-ingresar">Ingresar</button>
                            <div class="register-link">
                                <a href="#">Soy nuevo y quiero registrarme</a>
                            </div>
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