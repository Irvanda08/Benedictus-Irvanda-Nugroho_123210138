/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import DAO.akunDAO;
import koneksi.koneksi;
import java.sql.*;
/**
 *
 * @author USER
 */
public class akun {
    private String username;
    private String password;
    private String role;
    
    public akun(String username, String password) {
        this.username = username;
        this.password = password;
    }
    public String getrole() {
        return role;
    }

    public void setrole(String role) {
        this.role = role;
    }
    
    public String getpassword() {
        return password;
    }

    public void setpassword(String password) {
        this.password = password;
    }
    public String getusername() {
        return username;
    }

    public void setusername(String username) {
        this.username = username;
    }

    public String GetAkunByUsername() {
        koneksi conn = new koneksi();

        akunDAO aDAO = new akunDAO(conn);

        try {
            ResultSet rs = aDAO.getaccountbyusername(username, password);
            if (rs != null && rs.next()) {
                this.username = rs.getString("username");
                this.password = rs.getString("password");
                this.role = rs.getString("role");
                return role;
            } else {
                return "";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } 
    return null;
    }
}
