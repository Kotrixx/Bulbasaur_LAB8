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

@WebServlet(name = "MainServlet", value = {"/MainServlet","/main-page"})
public class MainServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
        HttpSession session = request.getSession(false);
        SeguroDao seguroDao = new SeguroDao();
        //HttpSession session = httpRequest.getSession(false);
        //boolean isLoggedIn = (session != null && session.getAttribute("user") != null);
        Usuario usuario;

        ViajeDao viajeDao = new ViajeDao();
        String idViaje;
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
                    usuario = (Usuario) session.getAttribute("usuarioSession");


                    //aca se le setea el atributo lista que luego se va a importar con el usebean en el jsp
                    request.setAttribute("lista",viajeDao.listarViajes(usuario));

                    request.getRequestDispatcher("main.jsp").forward(request, response);


                    break;
                case "edit":

                    request.setAttribute("listaSeguros",seguroDao.listarSeguros());
                    //usuario = (Usuario) session.getAttribute("usuarioSession");

                    //request.getRequestDispatcher("editarViaje.jsp").forward(request, response);

                    idViaje = request.getParameter("id");
                    if (idViaje != null) {
                        //String employeeIdString = request.getParameter("id");
                        int idViajeInt = 0;
                        try {
                            idViajeInt = Integer.parseInt(idViaje);
                        } catch (NumberFormatException ex) {
                            response.sendRedirect("main-page");
                        }
                        Viaje viaje = null;
                        try {
                            viaje = viajeDao.obtenerViaje(idViajeInt);
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }

                        if (viaje != null) {
                            request.setAttribute("viaje", viaje);
                            //request.setAttribute("listaTrabajos", jobDao.listarTrabajos());
                            //request.setAttribute("listaDepartamentos", departmentDao.listaDepartamentos());
                            //request.setAttribute("listaJefes", employeeDao.listarEmpleados());
                            request.getRequestDispatcher("editarViaje.jsp").forward(request, response);
                        } else {
                            response.sendRedirect("main-page");
                        }

                    } else {
                        response.sendRedirect("main-page");
                    }
                    break;





                case "del":
                    //System.out.println("simula borrado");
                    //usuario = (Usuario) session.getAttribute("usuarioSession");

                    idViaje = request.getParameter("id");
                    if (idViaje != null) {

                        int idViajeInt = 0;
                        try {
                            idViajeInt = Integer.parseInt(idViaje);
                        } catch (NumberFormatException ex) {
                            response.sendRedirect("EmployeeServlet?err=Error al borrar el empleado");
                        }

                        Viaje viaje = null;
                        try {
                            viaje = viajeDao.obtenerViaje(idViajeInt);
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }

                        if (viaje != null) {
                            try {
                                viajeDao.borrarViaje(idViajeInt);
                                response.sendRedirect("EmployeeServlet?msg=Empleado borrado exitosamente");
                            } catch (SQLException e) {
                                response.sendRedirect("EmployeeServlet?err=Error al borrar el empleado");
                            }
                        }
                    } else {
                        response.sendRedirect("EmployeeServlet?err=Error al borrar el empleado");
                    }


                    request.getRequestDispatcher("main.jsp").forward(request, response);
                    break;
                case "add":
                    request.setAttribute("listaSeguros",seguroDao.listarSeguros());
                    usuario = (Usuario) session.getAttribute("usuarioSession");

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
        Viaje viaje = parseViaje(request);
        /*if(seleccion!=null){
            boolean centinela = false;
            //boolean prueba = true;
            for(Seleccion selec: seleccionDao.listarSelecciones()){
                if(selec.getNombre().equals(seleccion.getNombre())){
                    centinela = true;

                }
            }
            boolean prueba = seleccion.getNombre().isEmpty() || seleccion.getTecnico().isEmpty() || seleccion.getEstadio().getEstadios_idEstadios()==0;

            if(!centinela && !prueba){
                seleccionDao.crearSeleccion(seleccion);
                response.sendRedirect(request.getContextPath()+"/listaSelecciones");
            } else{
                response.sendRedirect(request.getContextPath()+"/creaSeleccion");
            }
        }
        else{
            response.sendRedirect(request.getContextPath()+"/creaSeleccion");
        }*/


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

    public Viaje parseViaje(HttpServletRequest request) {
        Viaje viaje = new Viaje();
        Seguro seguro = new Seguro();

        Random random = new Random();
        // Generar un número aleatorio de 8 dígitos
        int digitoRnadom = random.nextInt(90000000) + 10000000;
        //int idViaje = digitoRnadom;
        String ciudadOrigen = request.getParameter("ciudadOrigen");
        String cuidadDestino = request.getParameter("cuidadDestino");
        String fechaViaje = request.getParameter("fechaViaje");
        String numBoletos = request.getParameter("numBoletos");
        String costoTotal = request.getParameter("costoTotal");
        String idSeguro = request.getParameter("idSeguro");

        try {
            viaje.setCiudadOrigen(ciudadOrigen);
            viaje.setCiudadDestino(cuidadDestino);
            viaje.setFechaViaje(Date.valueOf(fechaViaje));
            viaje.setCantBoletos(Integer.parseInt(numBoletos));
            BigDecimal costoTotalBD = new BigDecimal(costoTotal);
            costoTotalBD = BigDecimal.valueOf(Double.parseDouble(costoTotal));
            viaje.setCostoTotal(costoTotalBD);
            viaje.setIdSeguro(Integer.parseInt(idSeguro));
            return viaje;
        } catch (NumberFormatException e) {

        }
        return viaje;
    }
}
