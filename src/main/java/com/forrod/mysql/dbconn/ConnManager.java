package com.forrod.mysql.dbconn;

import com.forrod.mysql.model.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnManager {
    private Connection conn;
    private Statement stmt;
    private ResultSet rs;

    private static final Logger LOG = Logger.getLogger(ConnManager.class.getName());

    public ConnManager() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(DBParams.DB_URL, DBParams.DB_USER, null);
            stmt = conn.createStatement();
        } catch (SQLException | ClassNotFoundException ex) {
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
            rs = stmt.executeQuery("SELECT * FROM person AS p WHERE p.id = '" + id + "'");
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

    public void update(Person p) {
        try {
            stmt.executeUpdate("UPDATE person AS p SET p.name = '" + p.getName() + "'," +
                    " p.address = '" + p.getAddress() + "' where p.id = '" + p.getId() + "'");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void create(Person p) {
        try {
            stmt.executeUpdate("INSERT INTO person (name, address) VALUES('" + p.getName() + "', '" + p.getAddress() + "')");
            LOG.info("Created!!!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(Integer id) {
        LOG.info("Deleting person id: " + id);
        try {
            stmt.execute("DELETE FROM person AS p WHERE p.id = '" + id + "'");
            LOG.info("Deleted!!!!!!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Person> getAll() {
        List<Person> personList = new ArrayList<>();
        try {
            rs = stmt.executeQuery("SELECT * FROM person");
            while (rs.next()) {
                Integer id = rs.getInt("id");
                String name = rs.getString("name");
                String address = rs.getString("address");
                personList.add(new Person(id, name, address));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return personList;
    }
    
    public List<Person> getAllViaNameAndAddress(String name, String address) {
        List<Person> personList = new ArrayList<>();
        String query = "SELECT * FROM person AS p "
                + " WHERE p.name LIKE '" + "%" +name+ "%"+ "' AND "
                + " p.address LIKE '" + "%" +address+ "%" + "'";
        try {
            rs = stmt.executeQuery(query);
            while(rs.next()) {
                personList.add(new Person(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("address")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConnManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return personList;
    }

    public String getName(Integer id) {
        String name = "";
        try {
            rs = stmt.executeQuery("SELECT p.name FROM person AS p WHERE p.id = '" + id + "'");
            while (rs.next()) {
                name = rs.getString("name");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return name;
    }

    public Map<String, Object> getCustomField(Integer id) {
        Map<String, Object> payload = new HashMap<>();
        try {
            rs = stmt.executeQuery("SELECT p.id, p.name FROM person AS p WHERE p.id = '" + id + "'");
            while (rs.next()) {
                payload.put("id", rs.getInt("id"));
                payload.put("name", rs.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return payload;
    }
}
