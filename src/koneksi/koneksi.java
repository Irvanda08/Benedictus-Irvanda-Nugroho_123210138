/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package koneksi;


import java.sql.*;
import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author USER
 */
public class koneksi {
    String dbUrl = "jdbc:mysql://localhost/almaul_db";
    String username = "root";
    String password = "";
    public Connection kon;
    Statement statement;
    
    private static final java.util.logging.Logger LOG = java.util.logging.Logger.getLogger(koneksi.class.getName());
    
    public koneksi(){
        try{
            kon = DriverManager.getConnection(dbUrl, username, password);
            createStatement();
            //LOG.info("Koneksi DB Dibuat");
        }
        catch(SQLException exception){
            System.out.println(exception.getMessage());
        }
    }
    
    public void createStatement(){
        try {
            this.statement = kon.createStatement();
        } catch (SQLException e) {
            System.out.println(e.getMessage());//java.util.logging.Logger.getLogger(koneksi.class.getName()).log(java.util.logging.Level.SEVERE, null, e);
        }
    }
    
    public Statement getStatement() {
        return statement;
    }

    public Connection getConnection() {
      Connection conn = null;
        try {
            conn = DriverManager.getConnection(dbUrl, username, password);
            System.out.println("Berhasil terhubung ke database");
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
        }
        return conn;
    }

}
