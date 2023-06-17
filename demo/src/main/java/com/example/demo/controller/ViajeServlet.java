package com.example.demo.controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "ViajeServlet", value = {"/ViajeServlet","/viaje"})
public class ViajeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action") != null ? request.getParameter("action") : "main";
        switch (action) {
            case "main":
                request.getRequestDispatcher("main.jsp").forward(request, response);
                break;
            case "edit":
                request.getRequestDispatcher("editarViaje.jsp").forward(request, response);
                break;
            case "del":
                request.getRequestDispatcher("main.jsp").forward(request, response);
                break;
            case "add":
                request.getRequestDispatcher("nuevoViaje.jsp").forward(request, response);
                break;
        }




    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
