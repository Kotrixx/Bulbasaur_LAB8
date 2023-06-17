package com.example.demo.controller;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

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

    UsuarioDao usuarioDao = new UsuarioDao();

    Usuario usuario = parseUsuario(request);

    //si pasa lo de q la contraseña se ha confirmado
    if (usuario!= null){

        boolean valida_usuario = validarCampos(usuario.getNombre(),usuario.getApellido(),usuario.getEdad(),usuario.getCodigo(),usuario.getCorreo(),usuario.getEspecialidad(),usuario.getContrasenha());

        if (valida_usuario){
            //si se ha validado que el usuario cumple con lo q pide creare el user
            usuarioDao.guardar(usuario);

        }else{
            System.out.println("no");
        }



    }else{
        response.sendRedirect(request.getContextPath() + "/CrearUsuarioServlet");
        System.out.println("la contra seguro no coincidia");
    }






    }




    public Usuario parseUsuario(HttpServletRequest request) {

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
            usuario.setStatus("Normal");


            if(!contrasenha_repetida.equals(contrasenha)){
                return null;
            }


            return usuario;

        } catch (NumberFormatException e) {
            return null;
        }
    }



    //PARTE DE VALIDACION DEL USUARIO CREADO

    private static final String NOMBRE_REGEX = "^[^\\d].*";
    private static final String APELLIDO_REGEX = "^[^\\d].*";
    private static final String EDAD_REGEX = "^1[8-9]|2[0-9]$";
    private static final String CODIGO_PUCP_REGEX = "^\\d{8}$";
    private static final String CONTRASENA_REGEX = "^(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*()\\[\\]{}\\-_+=]).*$";

    public static boolean validarCampos(String nombre, String apellido, int edad, int codigoPUCP, String correoPUCP, String especialidad, String contrasena) {
        boolean isValid = true;

        // Validar nombre
        isValid &= validarRegex(NOMBRE_REGEX, nombre);

        // Validar apellido
        isValid &= validarRegex(APELLIDO_REGEX, apellido);

        // Validar edad
        isValid &= validarRegex(EDAD_REGEX, String.valueOf(edad));

        // Validar código PUCP
        isValid &= validarRegex(CODIGO_PUCP_REGEX, String.valueOf(codigoPUCP));

        // Validar correo PUCP
        String correoPUCPRegex = "^a" + (codigoPUCP) + "@pucp.edu.pe$";
        isValid &= validarRegex(correoPUCPRegex, correoPUCP);

        // Validar contraseña
        isValid &= validarRegex(CONTRASENA_REGEX, contrasena);

        return isValid;
    }

    private static boolean validarRegex(String regex, String input) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        return matcher.matches();
    }


}
