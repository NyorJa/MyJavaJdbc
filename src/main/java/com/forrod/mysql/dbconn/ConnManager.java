package com.forrod.mysql.dbconn;

import com.forrod.mysql.model.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConnManager {
    private Connection conn;
    private Statement stmt;
    private ResultSet rs;
    private PreparedStatement ps;

    private static final Logger LOG = LoggerFactory.getLogger(ConnManager.class);

    public ConnManager() {
        try {
            LOG.info("Connecting database!!!!");
            Class.forName(DBParams.DB_DRIVER_DIALECT);
            conn = DriverManager.getConnection(DBParams.DB_URL, DBParams.DB_USER, null);
//            stmt = conn.createStatement();
        } catch (SQLException | ClassNotFoundException ex) {
            LOG.error(ex.getMessage());
        }
    }

    public Integer countPersons() {
        LOG.info("Count query");
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
        LOG.info("Find One query, params: {}", id);
        Person p = null;
        try {
            ps = conn.prepareStatement("SELECT * FROM person AS p where p.id = ?");
            ps.setInt(1, id);
            rs = ps.executeQuery();
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
        LOG.info("Update Query, params: {}", p.toString());
        try {
            stmt.executeUpdate("UPDATE person AS p SET p.name = '" + p.getName() + "'," +
                    " p.address = '" + p.getAddress() + "' where p.id = '" + p.getId() + "'");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void create(Person p) {
        LOG.info("Create query, params: {}", p.toString());
        try {
            ps = conn.prepareStatement("INSERT INTO person (name, address) VALUES(?, ?)");
            ps.setString(1, p.getName());
            ps.setString(2, p.getAddress());
            ps.executeUpdate();
            LOG.info("Created!!!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(Integer id) {
        LOG.info("Deleting person id: " + id);
        try {
            stmt.execute("DELETE FROM person p WHERE id = '" + id + "'");
            LOG.info("Deleted!!!!!!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Person> getAll() {
        LOG.info("Get all query");
        List<Person> personList = new ArrayList<>();
        try {
            ps = conn.prepareStatement("SELECT * FROM person");
            rs = ps.executeQuery();
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
        LOG.info("Get all via name and address query, params, name: {}, address: {}", name, address);
        List<Person> personList = new ArrayList<>();
        String query = "SELECT * FROM person AS p  WHERE p.name LIKE ? AND p.address LIKE ? ";
        try {
            ps = conn.prepareStatement(query);
            ps.setString(1, "%" + name + "%");
            ps.setString(2, "%" + address + "%");
            rs = ps.executeQuery();
            while(rs.next()) {
                personList.add(new Person(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("address")));
            }
        } catch (SQLException ex) {
            LOG.error(ex.getMessage());
        }
        return personList;
    }

    public String getName(Integer id) {
        LOG.info("Get only name query, params, id: {}", id);
        String name = "";
        try {
            ps = conn.prepareStatement("SELECT p.name FROM person AS p WHERE p.id = ? ");
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                name = rs.getString("name");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return name;
    }

    public Map<String, Object> getCustomField(Integer id) {
        LOG.info("Get custom return map payload query, params: {}", id);
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
