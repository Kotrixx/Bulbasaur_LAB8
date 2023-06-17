package com.example.demo.models.daos;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

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



    public void guardar (Usuario usuario){

        String sql = "INSERT INTO usuario (correo,nombre,apellido,contrasenha, contrasenha_hashed, status, edad, codigo, especialidad) \n" +
                "VALUES (?,?,?,?,?,?,?,?,?)";

        try (Connection connection = getConection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setString(1,usuario.getCorreo());
            pstmt.setString(2,usuario.getNombre());
            pstmt.setString(3,usuario.getApellido());
            pstmt.setString(4,usuario.getContrasenha());

            //creo el hash
            pstmt.setString(5,convertirAHash(usuario.getContrasenha()));
            pstmt.setString(6,usuario.getStatus());
            pstmt.setInt(7,usuario.getEdad());
            pstmt.setInt(8,usuario.getCodigo());
            pstmt.setString(9,usuario.getEspecialidad());

            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }





    //esta funcion cambia un string aun hash y luego te devuelve un string de ese hash
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
                "where correo = ? and contrasenha_hashed = SHA2(?,256) and  especialidad = 'Ingeniería de Telecomunicaciones';";
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
