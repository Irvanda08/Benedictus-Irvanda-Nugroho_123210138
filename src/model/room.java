/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import koneksi.koneksi;
import java.util.List;
import DAO.roomDAO;
import java.sql.SQLException;
/**
 *
 * @author USER
 */
public class room {
    private String name;
    private String size;
    private int price;
    private String status;
    roomDAO roDAO;
    
    public room() {
        roDAO = new roomDAO(new koneksi());
    }

    public void closeConnection() throws SQLException{
        roDAO.closeConnection();
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public List<room> getallroom(){
       List<room> room = roDAO.getAllRooms();
        return room;
    }
}
