package com.example.demo.models.daos;

import com.example.demo.models.beans.Seguro;
import com.example.demo.models.beans.Usuario;
import com.example.demo.models.beans.Viaje;

import java.sql.*;
import java.util.ArrayList;

public class ViajeDao extends BaseDao{


    public ArrayList<Viaje> listarViajes(Usuario usuario){

        ArrayList<Viaje> listaViajes = new ArrayList<>();

        String sql = "SELECT idViaje,fechaViaje,fechaReserva,costoTotal,precioBoletO,cantBoletos,Seguro_idSeguro,ciudadOrigen,ciudadDestino,Usuario_idUsuario, s.nombre FROM viaje v\n" +
                "inner join seguro s on (s.idSeguro = v.Seguro_idSeguro)\n" +
                "where Usuario_idUsuario = ?";


        try (Connection conn = getConection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, usuario.getIdUsuario());
            ResultSet rs = pstmt.executeQuery();

            //creare cada viaje
            while (rs.next()) {
                Viaje viaje = new Viaje();
                viaje.setIdViaje(rs.getInt(1));
                viaje.setFechaViaje(rs.getDate(2));
                viaje.setFechaReserva(rs.getDate(3));
                viaje.setCostoTotal(rs.getBigDecimal(4));
                viaje.setPrecioBoleto(rs.getBigDecimal(5));
                viaje.setCantBoletos(rs.getInt(6));
                viaje.setIdSeguro(rs.getInt(7));

                viaje.setCiudadOrigen(rs.getString(8));
                viaje.setCiudadDestino(rs.getString(9));
                viaje.setIdUsuario(rs.getInt(10));

                Seguro seguro = new Seguro();
                seguro.setNombre(rs.getString(11));

                viaje.setSeguro(seguro);

                listaViajes.add(viaje);
                System.out.println(viaje.getIdViaje());
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }


        return listaViajes;

    }


    public ArrayList<Viaje> buscarPorTitle(String textoBuscar, Usuario usuario){

        ArrayList<Viaje> listaViajes = new ArrayList<>();

        String sql = "SELECT idViaje,fechaViaje,fechaReserva,costoTotal,precioBoletO,cantBoletos,Seguro_idSeguro,ciudadOrigen,ciudadDestino,Usuario_idUsuario, s.nombre FROM viaje v\n" +
                "inner join seguro s on (s.idSeguro = v.Seguro_idSeguro)\n" +
                "where ciudadDestino = ? or ciudadOrigen = ? and Usuario_idUsuario = ?";



        try (Connection conn = getConection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, textoBuscar);
            pstmt.setString(2,textoBuscar);
            pstmt.setInt(3,usuario.getIdUsuario());

            ResultSet rs = pstmt.executeQuery();

            //creare cada viaje
            while (rs.next()) {
                Viaje viaje = new Viaje();
                viaje.setIdViaje(rs.getInt(1));
                viaje.setFechaViaje(rs.getDate(2));
                viaje.setFechaReserva(rs.getDate(3));
                viaje.setCostoTotal(rs.getBigDecimal(4));
                viaje.setPrecioBoleto(rs.getBigDecimal(5));
                viaje.setCantBoletos(rs.getInt(6));
                viaje.setIdSeguro(rs.getInt(7));

                viaje.setCiudadOrigen(rs.getString(8));
                viaje.setCiudadDestino(rs.getString(9));
                viaje.setIdUsuario(rs.getInt(10));

                Seguro seguro = new Seguro();
                seguro.setNombre(rs.getString(11));

                viaje.setSeguro(seguro);

                listaViajes.add(viaje);
                System.out.println(viaje.getIdViaje());
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }


        return listaViajes;


    }


}
