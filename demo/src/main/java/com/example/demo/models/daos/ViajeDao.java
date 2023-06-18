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

    public void crearViaje( ) {
        Viaje viaje = new Viaje();
        String sql = "INSERT INTO viaje (idViaje,fechaViaje,fechaReserva,costoTotal,cantBoletos,\n" +
                "Seguro_idSeguro,ciudadOrigen,ciudadDestino,Usuario_idUsuario) "
                + "VALUES (?,?,?,?,?,?,?,?,?)";

        try (Connection conn = this.getConection();
             PreparedStatement pstmt = conn.prepareStatement(sql);) {
            pstmt.setInt(1, viaje.getIdViaje());
            pstmt.setDate(2, viaje.getFechaViaje());
            pstmt.setDate(3, viaje.getFechaReserva());
            pstmt.setBigDecimal(4, viaje.getCostoTotal());
            pstmt.setInt(5, viaje.getCantBoletos());
            pstmt.setInt(6, viaje.getIdSeguro());
            pstmt.setString(7,viaje.getCiudadOrigen());
            pstmt.setString(8,viaje.getCiudadDestino());
            pstmt.setInt(9,viaje.getIdUsuario());
            pstmt.executeUpdate();
        }catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void editarViaje(Viaje viaje) throws SQLException{
        String sql = "UPDATE viaje SET\n" +
                "fechaViaje=?,costoTotal=?,cantBoletos=?,\n" +
                "Seguro_idSeguro=?,ciudadOrigen=?,ciudadDestino=?\n" +
                "where idViaje = ?;";

        try (Connection conn = this.getConection();
             PreparedStatement pstmt = conn.prepareStatement(sql);) {

            pstmt.setDate(1, viaje.getFechaViaje());
            pstmt.setBigDecimal(2, viaje.getCostoTotal());
            pstmt.setInt(3, viaje.getCantBoletos());
            pstmt.setInt(4, viaje.getIdSeguro());
            pstmt.setString(5,viaje.getCiudadOrigen());
            pstmt.setString(6,viaje.getCiudadDestino());
            pstmt.setInt(7,viaje.getIdViaje());
            pstmt.executeUpdate();
        }
    }

    public void borrarViaje(int idViaje) throws SQLException {
        Viaje viaje = new Viaje();
        String sql = "DELETE FROM viaje WHERE  idViaje = ? ";

        try (Connection conn = this.getConection();
             PreparedStatement pstmt = conn.prepareStatement(sql);) {
            pstmt.setInt(1,idViaje);
            pstmt.executeUpdate();
        }catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public Viaje obtenerViaje(int idViaje) {
        Viaje viaje = new Viaje();
        String sql = "SELECT * FROM lab8_bulbasaur.viaje \n" +
                "WHERE idViaje = ?";

        try (Connection conn = this.getConection();
             PreparedStatement pstmt = conn.prepareStatement(sql);) {
            pstmt.setInt(1,idViaje);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {

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
            }
        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }
        return viaje;
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


