/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;
import koneksi.koneksi;
import DAO.renterDAO;
import DAO.roomDAO;
import model.renter;
import view.adminview;
import view.renterview;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author USER
 */
public class admincontroller implements ActionListener{
    private static admincontroller instance;
    renter rent;
    private adminview view;
    private renterview renterview;
    
    public admincontroller(){
        rent = new renter();
        view = new adminview();
        view.getbtnlogout().addActionListener(this);
        loadAllRenter();
    }
    
    public static admincontroller getInstance(){
        if(instance==null){
            instance = new admincontroller();
        }
        return instance;
    }
    
    public void loadAllRenter(){
        List<renter> rents = rent.getallrent();
        DefaultTableModel dtm = new DefaultTableModel();
        dtm.addColumn("Name");
        dtm.addColumn("ID");
        dtm.addColumn("Contact");
        dtm.addColumn("Duration");
        dtm.addColumn("Bill");
        dtm.addColumn("Status");
        dtm.addColumn("Room");
        
        for(renter r:rents){
            Object[] row = {
            r.getnama(), r.getid(), r.getkontak(), r.getdurasi(),r.getbill(),r.getstatus(),r.getroom()
            };
            dtm.addRow(row);
        }
        view.setTableModel(dtm);
        view.getTable().addMouseListener(new MouseAdapter() {
        
        @Override
        public void mouseClicked(MouseEvent e) {
            int row = view.getTable().getSelectedRow();
            rent.setnama((String) view.getTable().getValueAt(row, 0));
            rent.setid((String) view.getTable().getValueAt(row, 1));
            rent.setkontak((String) view.getTable().getValueAt(row, 2));
            rent.setdurasi((int) view.getTable().getValueAt(row, 3));
            rent.setstatus((String) view.getTable().getValueAt(row, 5));
            rent.setroom((String) view.getTable().getValueAt(row, 6));
            renterDAO reDAO = new renterDAO(new koneksi());
            roomDAO roDAO = new roomDAO(new koneksi());
            if("notPaid".equals(rent.getstatus())){
                int jawab = JOptionPane.showOptionDialog(null, 
                    "ubah ke paid?", 
                    "Option", 
                    JOptionPane.YES_NO_OPTION, 
                    JOptionPane.QUESTION_MESSAGE, null, null, null);
                    if(jawab == JOptionPane.YES_OPTION){
                    try {
                        reDAO.setpaid(rent.getid());
                        loadAllRenter();
                    } catch (SQLException ex) {
                        System.out.println(ex.getMessage());//Logger.getLogger(admincontroller.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    }
            }else{
                Object[] options = { "ubah", "hapus" };
                int jawab = JOptionPane.showOptionDialog(null, "silahkan pilih", "Option",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
                if (jawab == JOptionPane.YES_OPTION) {
                    System.out.println("ID = "+rent.getid()); 
                    editcontroller erdc = new editcontroller(rent);
                    view.setVisible(false);
                    erdc.showPage();
                } else if (jawab == JOptionPane.NO_OPTION) {
                    try {
                        reDAO.hapusbyid(rent.getid());
                        roDAO.setemptyroom(rent.getroom());
                        loadAllRenter();
                    } catch (SQLException ex) {
                        System.out.println(ex.getMessage());//Logger.getLogger(admincontroller.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }});
    }
                 
    void showPage() {
    view.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==view.getbtnlogout()){
         System.exit(0);
        }
    }

}  
