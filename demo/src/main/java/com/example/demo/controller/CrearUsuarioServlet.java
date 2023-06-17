package com.example.demo.controller;


import com.example.demo.models.beans.Usuario;
import com.example.demo.models.daos.UsuarioDao;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "CrearUsuarioServlet", value = "/CrearUsuarioServlet")
public class CrearUsuarioServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.getRequestDispatcher("nuevoUsuario.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {





    }




    public Usuario parseJugador(HttpServletRequest request) {

        Usuario usuario = new Usuario();
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String edadstr = request.getParameter("edad");
        String codigostr = request.getParameter("codigo");
        String correo = request.getParameter("correo");
        String especialidad = request.getParameter("especialidad");
        String contrasenha = request.getParameter("contrasenha");

        String contrasenha_repetida =request.getParameter("contrasenha_repetida");


        try {
            int edad = Integer.parseInt(edadstr);
            int codigo = Integer.parseInt(codigostr);

            usuario.setNombre(nombre);
            usuario.setApellido(apellido);
            usuario.setEdad(edad);
            usuario.setCodigo(codigo);
            usuario.setCorreo(correo);
            usuario.setEspecialidad(especialidad);
            usuario.setContrasenha(contrasenha);


            if(contrasenha_repetida.equals(contrasenha)){
                return null;
            }


            return usuario;

        } catch (NumberFormatException e) {
            return null;
        }
    }


}
