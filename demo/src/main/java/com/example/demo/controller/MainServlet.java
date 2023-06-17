package com.example.demo.controller;

import com.example.demo.models.daos.SeguroDao;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "MainServlet", value = {"/MainServlet","/main-page"})
public class MainServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
        HttpSession session = request.getSession(false);
        SeguroDao seguroDao = new SeguroDao();
        //HttpSession session = httpRequest.getSession(false);
        //boolean isLoggedIn = (session != null && session.getAttribute("user") != null);




        if (session == null) {
            response.sendRedirect(request.getContextPath() + "/LoginServlet");
        } else {
            //RequestDispatcher dispatcher = request.getRequestDispatcher("main.jsp");
            //dispatcher.forward(request, response);

            String action = request.getParameter("action") != null ? request.getParameter("action") : "main";
            switch (action) {
                case "main":
                    request.getRequestDispatcher("main.jsp").forward(request, response);
                    break;
                case "edit":

                    request.setAttribute("listaSeguros",seguroDao.listarSeguros());
                    request.getRequestDispatcher("editarViaje.jsp").forward(request, response);
                    break;
                case "del":
                    System.out.println("simula borrado");
                    request.getRequestDispatcher("main.jsp").forward(request, response);
                    break;
                case "add":
                    request.setAttribute("listaSeguros",seguroDao.listarSeguros());
                    request.getRequestDispatcher("nuevoViaje.jsp").forward(request, response);
                    break;
            }

        }






    }
    public void destroy() {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
