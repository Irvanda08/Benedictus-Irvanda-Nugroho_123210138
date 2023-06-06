/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DAOImplements.implements_renter;
import model.renter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import koneksi.koneksi;

/**
 *
 * @author USER
 */
public class renterDAO implements implements_renter{
    koneksi kon;
    renter re;
    private String id;
    
    public renterDAO(koneksi db) {
        this.kon = db;
    }
    
    @Override
    public int hitungTotalHarga(int durasi, int bill) {
        return durasi * bill;
    }
    
    public void inputrenter(renter re) throws SQLException{
        this.re = re;
        int totalHarga = hitungTotalHarga(re.getdurasi(), re.getbill());
        String query="INSERT INTO `renter`(`name`, `id`, `contact`, `duration`, `bill`, `room`) VALUES ('"+re.getnama()+"','"+re.getid()+"','"+re.getkontak()+"','"+re.getdurasi()+"','"+ totalHarga+"','"+re.getroom()+"')";
        kon.getStatement().executeUpdate(query);
    } 
    
    public List<renter> getallrent(){
        String query="SELECT * FROM RENTER";
        List<renter> rent = new ArrayList<>();
        try {
            ResultSet rs = this.kon.getStatement().executeQuery(query);
            while (rs.next()) {                
                renter r = new renter();
                r.setnama(rs.getString("name"));
                r.setid(rs.getString("id"));
                r.setkontak(rs.getString("contact"));
                r.setdurasi(rs.getInt("duration"));
                r.setbill(rs.getInt("bill"));
                r.setstatus(rs.getString("status"));
                r.setroom(rs.getString("room"));
                rent.add(r);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());//Logger.getLogger(renterDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return rent;
    }
    public void closeConnection() throws SQLException{
        kon.kon.close();
    }

    public void setpaid(String id) throws SQLException {
        this.id = id;
        String query="UPDATE `renter` SET `status`='Paid' WHERE id='"+id+"'";
        kon.getStatement().executeUpdate(query);
    }

    public void hapusbyid(String id) throws SQLException {
        this.id = id;
        String query="DELETE FROM `renter` WHERE id='"+id+"'";
        kon.getStatement().executeUpdate(query);
    }

    public void updaterenter(renter re) throws SQLException {
        this.re = re;
        System.out.println("ID = " + re.getid());

        String query = "UPDATE `renter` SET `name`='" + re.getnama() + "', `contact`='" + re.getkontak() + "', `id`='" + re.getid() + "', `duration`='" + re.getdurasi() + "' WHERE id='" + re.getid() + "'";
        kon.getStatement().executeUpdate(query);
    }


}
