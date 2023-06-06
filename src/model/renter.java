/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import koneksi.koneksi;
import DAO.renterDAO;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author USER
*/
public class renter {
    private String nama;
    private String id;
    private String contact;
    private String status;
    private String room;
    private int durasi;
    private int bill;
    
    renterDAO reDAO;
    
    public renter() {
        reDAO = new renterDAO(new koneksi());
    }

    public String getnama() {
        return nama;
    }

    public void setnama(String nama) {
        this.nama = nama;
    }

    public String getid() {
        return id;
    }

    public void setid(String id) {
        this.id = id;
    }

    public String getkontak() {
        return contact;
    }

    public void setkontak(String contact) {
        this.contact = contact;
    }

    public int getdurasi() {
        return durasi;
    }

    public void setdurasi(int durasi) {
        this.durasi = durasi;
    }

    public int getbill() {
        return bill;
    }

    public void setbill(int bill) {
        this.bill = bill;
    }

    public String getstatus() {
        return status;
    }

    public void setstatus(String status) {
        this.status = status;
    }

    public String getroom() {
        return room;
    }

    public void setroom(String room) {
        this.room = room;
    }
    public List<renter> getallrent(){
       List<renter> rent = reDAO.getallrent();
       return rent;
    }
    public void closeConnection() throws SQLException{
        reDAO.closeConnection();
    }
}
