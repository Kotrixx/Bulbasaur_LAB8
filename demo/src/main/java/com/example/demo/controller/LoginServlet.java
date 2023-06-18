package com.example.demo.controller;

import com.example.demo.models.beans.Usuario;
import com.example.demo.models.daos.UsuarioDao;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "LoginServlet", value = "/LoginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action") != null ? request.getParameter("action") : "login";

        if (action.equals("login")) {

            HttpSession session = request.getSession(false);

            if(session != null && session.getAttribute("usuarioSession") != null){

                Usuario usuario = (Usuario) session.getAttribute("usuarioSession");

                if(usuario.getIdUsuario()>0){ //estoy loggedIn
                    response.sendRedirect(request.getContextPath() + "/main-page");
                }else{ // no estoy loggedId
                    RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
                    dispatcher.forward(request, response);
                }
            }
        }else{ //logout
            HttpSession session = request.getSession(false);
            session.invalidate();
            response.sendRedirect(request.getContextPath());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("inputEmail");
        String pass = request.getParameter("inputPassword");

        UsuarioDao usuarioDao = new UsuarioDao();
        Usuario usuario = usuarioDao.validateUsernameAndPassword(email, pass);


        if (usuario != null) { //usuario y password correctos
            HttpSession session = request.getSession();
            System.out.println("bien");

            session.setAttribute("usuarioSession", usuario);
            session.setMaxInactiveInterval(60*10);//en segundos

            response.sendRedirect(request.getContextPath() + "/main-page");
        } else { //usuario o password incorrectos
            System.out.println("mal");
            request.setAttribute("error", "Usuario o password incorrectos");
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
    }

}
