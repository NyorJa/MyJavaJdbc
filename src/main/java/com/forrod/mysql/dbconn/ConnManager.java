package com.forrod.mysql.dbconn;

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
}
