package com.example.demo.models.daos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class BaseDao {

    public Connection getConection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }

        String username = "root";
        String password = "root";
        String database = "lab8_bulbasaur";
        String url = "jdbc:mysql://localhost:3306/" + database;

        return DriverManager.getConnection(url, username, password);

    }
}