package com.example.demo.controller;

import java.sql.Date;
import java.sql.SQLException;
import java.util.Random;
import com.example.demo.models.beans.Seguro;
import com.example.demo.models.beans.Usuario;
import com.example.demo.models.beans.Viaje;
import com.example.demo.models.daos.SeguroDao;
import com.example.demo.models.daos.UsuarioDao;
import com.example.demo.models.daos.ViajeDao;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.math.BigDecimal;
import java.io.IOException;



@WebServlet(name = "BorrarServlet", value = {"/BorrarServlet","/Borrar"})
public class BorrarServlet extends HttpServlet{



    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action") != null ? request.getParameter("action") : "main";





    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }





}
