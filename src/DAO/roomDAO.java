/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import model.room;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import koneksi.koneksi;
/**
 *
 * @author USER
 */
public class roomDAO {
    koneksi kon;
    private String name;
    private String room;
    
    public roomDAO(koneksi db) {
        this.kon = db;
    }
    
    public List<room> getAllRooms(){
        String query="SELECT * FROM ROOMS";
        List<room> room = new ArrayList<>();
        try {
            ResultSet rs = this.kon.getStatement().executeQuery(query);
            while (rs.next()) {                
                room ro = new room();
                ro.setName(rs.getString("name"));
                ro.setPrice(rs.getInt("price"));
                ro.setSize(rs.getString("size"));
                ro.setStatus(rs.getString("status"));
                room.add(ro);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());//Logger.getLogger(roomDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return room;
    }
    public void closeConnection() throws SQLException{
        kon.kon.close();
    }

    public void setNameInStatus(String room,String name) throws SQLException {
        this.name = name;
        this.room = room;
        String query = "UPDATE `rooms` SET `status`='"+name+"' WHERE name = '"+room+"'";
        kon.getStatement().executeUpdate(query);
    }

    public void setemptyroom(String room) throws SQLException {
        this.room = room;
        String query = "UPDATE `rooms` SET `status`= 'empty' WHERE name = '"+room+"'";
        kon.getStatement().executeUpdate(query);
    }
}
