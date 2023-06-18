package com.example.demo.controller;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
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
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

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
                            response.sendRedirect(request.getContextPath()+"/main-page");
                        }
                        Viaje viaje = null;
                        viaje = viajeDao.obtenerViaje(idViajeInt);
                        if (viaje != null) {
                            request.setAttribute("viaje", viaje);
                            request.getRequestDispatcher("editarViaje.jsp").forward(request, response);
                        } else {
                            response.sendRedirect(request.getContextPath()+"/main-page");
                        }
                    } else {
                        response.sendRedirect("main-page");
                    }
                    break;





                case "del":
                    //System.out.println("simula borrado");
                    usuario = (Usuario) session.getAttribute("usuarioSession");

                    String id = request.getParameter("id");

                    ViajeDao viajeDao1 = new ViajeDao();

                    //primero le haré meter su contraseña
                    request.setAttribute("id",Integer.parseInt(id));
                    request.getRequestDispatcher("confirmarContrasenha.jsp").forward(request, response);
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
        ViajeDao viajeDao = new ViajeDao();
        String action = request.getParameter("action") != null ? request.getParameter("action") : "buscar";
        HttpSession session = request.getSession(false);
        Usuario usuario = (Usuario) session.getAttribute("usuarioSession");
        switch (action) {
            case "buscar":
                //aca buscare y listare lo q haya
                String textoBuscar = request.getParameter("textoBuscar"); //asimismo
                //se guarda un parametro con name textoBuscar el cual se usará
                System.out.println(textoBuscar);
                //HttpSession session = request.getSession(false);
                //Usuario usuario = (Usuario) session.getAttribute("usuarioSession");

                request.setAttribute("lista", viajeDao.buscarPorTitle(textoBuscar,usuario));//ese texto lo meteré a la funcion
                //finalmente haré que lista sea una lista de solo lo que yo deseo
                //y esa mostraré
                request.getRequestDispatcher("main.jsp").forward(request, response);
                break;
            case "edita":
                System.out.println("entre a editar");
                Viaje viaje = parseViaje(request);
                try {
                    viajeDao.editarViaje(viaje);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                response.sendRedirect(request.getContextPath()+"/main-page");
                break;

            case "del":
                String id = request.getParameter("id");
                String contrasenha = request.getParameter("inputPassword");
                session = request.getSession(false);
                usuario = (Usuario) session.getAttribute("usuarioSession");

                System.out.println(contrasenha);
                System.out.println("contra buena es" + usuario.getContrasenha());
                String contrainput_hash = convertirAHash(contrasenha);
                System.out.println("la contra que se puso en hash es : "+ contrainput_hash);
                if (contrainput_hash.equals(usuario.getContrasenha())){

                    try {
                        viajeDao.borrarViaje(Integer.parseInt(id));
                        System.out.println("se borro creo");
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }

                }else{
                    response.sendRedirect(request.getContextPath()+"/main-page");
                    break;
                }
            case "add":
                viaje = parseViaje(request);
                if(viaje!=null){
                    boolean  centinela2 = false,centinela3 = false,centinela4=false;
                    iteracion: while(true){
                        Random random = new Random();
                        int numRandom = random.nextInt(90000000) + 10000000;
                        int idViaje = numRandom;
                        viaje.setIdViaje(idViaje);
                        for(Viaje v: viajeDao.listarViajes(usuario)){
                            if(v.getIdViaje()!=viaje.getIdViaje()){
                                centinela2 = true;
                                if(centinela2){
                                    break iteracion;
                                }
                            }
                        }
                    }
                    LocalDate fechaActual = LocalDate.now();
                    Date fechaActualSql = Date.valueOf(fechaActual);
                    viaje.setFechaReserva(fechaActualSql);
                    if (viaje.getFechaViaje().compareTo(fechaActualSql) > 0) {
                        centinela3 = true;
                    } else {
                        centinela3 = false;
                    }
                    centinela4 = viaje.getCantBoletos() >0? true : false;
                    //cambio de status
                    boolean comp = viaje.getCostoTotal().compareTo(BigDecimal.ZERO) != 0? true : false;
                    boolean prueba = viaje.getFechaViaje()==null ||viaje.getCantBoletos()==0 || viaje.getIdSeguro()==0||viaje.getCantBoletos()==0
                            || viaje.getCostoTotal()==null;


                    if((centinela2 && centinela3 && centinela4) && !prueba){
                        viajeDao.crearViaje(viaje);
                        response.sendRedirect(request.getContextPath()+"/main-page");
                    } else{
                        response.sendRedirect(request.getContextPath()+"/main-page?action=add");
                    }
                }
                else{
                    response.sendRedirect(request.getContextPath()+"/main-page?action=add");
                }
        }
        
    }

    public Viaje parseViaje(HttpServletRequest request) {
        Viaje viaje = new Viaje();
        //Random random = new Random();
        // Generar un número aleatorio de 8 dígitos
        //int digitoRnadom = random.nextInt(90000000) + 10000000;
        //int idViaje = digitoRnadom;
        String idViaje = request.getParameter("id");
        String ciudadOrigen = request.getParameter("ciudadOrigen");
        String cuidadDestino = request.getParameter("cuidadDestino");
        String fechaViaje = request.getParameter("fechaViaje");
        String numBoletos = request.getParameter("numBoletos");
        String costoBoleto = request.getParameter("costoBoleto");
        String costoBoletoStr = costoBoleto;
        BigDecimal costoBoletoInt = new BigDecimal(costoBoletoStr);
        String numeroString = numBoletos;
        BigDecimal numeroBoletosInt = new BigDecimal(numBoletos);

        String idSeguro = request.getParameter("idSeguro");
        BigDecimal costoTotal = costoBoletoInt.multiply(numeroBoletosInt);
        try {
            viaje.setIdViaje(Integer.parseInt(idViaje));
            viaje.setCiudadOrigen(ciudadOrigen);
            viaje.setCiudadDestino(cuidadDestino);
            viaje.setFechaViaje(Date.valueOf(fechaViaje));
            viaje.setCantBoletos(Integer.parseInt(numBoletos));
            viaje.setCostoTotal(costoTotal);
            viaje.setIdSeguro(Integer.parseInt(idSeguro));

        } catch (NumberFormatException e) {

        }
        return viaje;
    }



    public static String convertirAHash(String contrasenha) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(contrasenha.getBytes(StandardCharsets.UTF_8));

            // Convertir el hash a una representación en formato hexadecimal
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashBytes) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }

            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }



}
