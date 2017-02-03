package com.forrod.mysql.dbconn;

import com.forrod.mysql.model.Person;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnManager {
    private Connection conn;
    private Statement stmt;
    private ResultSet rs;

    public ConnManager() {
        try {
            conn = DriverManager.getConnection(DBParams.DB_URL, DBParams.DB_USER, null);
            stmt = conn.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(ConnManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Integer countPersons() {
        Integer num = 0;
        try {
            rs = stmt.executeQuery("SELECT * FROM person");
            while (rs.next()) {
                num++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return num;
    }

    public Person findOne(Integer id) {
        Person p = null;
        try {
            rs = stmt.executeQuery("SELECT * FROM person AS p WHERE p.id = '" +id+ "'");
            while (rs.next()) {
                p = new Person(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("address"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return p;
    }

    public String getName(Integer id) {
        String name = "";
        try {
            rs = stmt.executeQuery("SELECT p.name FROM person AS p WHERE p.id = '"+id+"'");
            while (rs.next()) {
                name = rs.getString("name");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return name;
    }
}
