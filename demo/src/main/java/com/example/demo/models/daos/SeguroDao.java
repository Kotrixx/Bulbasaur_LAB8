package com.example.demo.models.daos;

import com.example.demo.models.beans.Seguro;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class SeguroDao extends BaseDao{
    public ArrayList<Seguro> listarSeguros() {
        ArrayList<Seguro> listaSeguros = new ArrayList<>();

        String sql = "SELECT * FROM lab8_bulbasaur.seguro;";
        try (Connection conn = this.getConection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Seguro seguro = new Seguro();
                seguro.setIdSeguro(rs.getInt("idSeguro"));
                seguro.setNombre(rs.getString("nombre"));
                listaSeguros.add(seguro);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return listaSeguros;
    }
}
