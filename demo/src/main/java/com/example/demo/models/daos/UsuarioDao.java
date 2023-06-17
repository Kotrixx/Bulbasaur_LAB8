package com.example.demo.models.daos;

import com.example.demo.models.beans.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDao extends BaseDao{


    public Usuario obtenerUsuario(int userId) {

        Usuario usuario = null;

        String sql = "select * from usuario where idUsuario = ?";

        try (Connection conn = this.getConection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    usuario = new Usuario();
                    fetchUsuarioData(usuario, rs);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return usuario;
    }

    private void fetchUsuarioData(Usuario usuario, ResultSet rs) throws SQLException {
        usuario.setIdUsuario(rs.getInt("idUsuario"));
        usuario.setCorreo(rs.getString("correo"));
        usuario.setNombre(rs.getString("nombre"));
        usuario.setApellido(rs.getString("apellido"));
        usuario.setContrasenha(rs.getString("contrasenha_hashed"));
        usuario.setStatus(rs.getString("status"));
        usuario.setEdad(rs.getInt("edad"));
        usuario.setCodigo(rs.getInt("codigo"));
        usuario.setEspecialidad(rs.getString("especialidad"));
        //usuario.setContrasenhaHashed();
        /*
        Job job = new Job();
        job.setJobId(rs.getString(7));
        job.setJobTitle(rs.getString("job_title"));
        employee.setJob(job);

        employee.setSalary(rs.getBigDecimal(8));
        employee.setCommissionPct(rs.getBigDecimal(9));

        Employee manager = new Employee();
        manager.setEmployeeId(rs.getInt("e.manager_id"));
        manager.setFirstName(rs.getString("m.first_name"));
        manager.setLastName(rs.getString("m.last_name"));
        employee.setManager(manager);

        Department department = new Department();
        department.setDepartmentId(rs.getInt(11));
        department.setDepartmentName(rs.getString("d.department_name"));
        employee.setDepartment(department); */
    }


    public Usuario validateUsernameAndPassword(String username, String password) {

        Usuario usuario = null;

        String sql = "select * from usuario\n" +
                "where correo = ? and contrasenha_hashed = SHA2(?,256) ;";
        try (Connection connection = getConection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    usuario = obtenerUsuario(rs.getInt(1));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return usuario;
    }


}
