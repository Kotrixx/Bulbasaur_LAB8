package com.example.demo.controller;

import com.example.demo.models.beans.Usuario;
import com.example.demo.models.daos.SeguroDao;
import com.example.demo.models.daos.UsuarioDao;
import com.example.demo.models.daos.ViajeDao;
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
                    //si esta en el main va a listar las selecciones

                    //voy a introducir el atributo usuarioSession debido a que ese sera el usaurio al que buscarle viajes
                    Usuario usuario = (Usuario) session.getAttribute("usuarioSession");
                    ViajeDao viajeDao = new ViajeDao();

                    //aca se le setea el atributo lista que luego se va a importar con el usebean en el jsp
                    request.setAttribute("lista",viajeDao.listarViajes(usuario));

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

        String action = request.getParameter("action") != null ? request.getParameter("action") : "buscar";

        ViajeDao viajeDao = new ViajeDao();

        switch (action) {
            case "buscar":
                //aca buscare y listare lo q haya

                String textoBuscar = request.getParameter("textoBuscar"); //asimismo
                //se guarda un parametro con name textoBuscar el cual se usará

                System.out.println(textoBuscar);
                HttpSession session = request.getSession(false);
                Usuario usuario = (Usuario) session.getAttribute("usuarioSession");
                request.setAttribute("lista", viajeDao.buscarPorTitle(textoBuscar,usuario));//ese texto lo meteré a la funcion
                //finalmente haré que lista sea una lista de solo lo que yo deseo
                //y esa mostraré

                request.getRequestDispatcher("main.jsp").forward(request, response);
                break;


        }




    }
}
