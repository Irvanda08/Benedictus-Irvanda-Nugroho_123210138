/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import koneksi.koneksi;
import DAO.renterDAO;
import model.renter;
import view.renterview;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

/**
 *
 * @author USER
 */
public class editcontroller implements ActionListener{
    private renterview view;
    private renter re;
    private renterDAO reDAO;
    
    public editcontroller(renter re) {
        this.re = re;
        reDAO = new renterDAO(new koneksi());
        view = new renterview();
        view.getbtnsubmit().addActionListener(this);
        view.settxtdurasi(re.getdurasi());
        view.settxtkontak(re.getkontak());
        view.settxtid(re.getid());
        view.settxtnama(re.getnama());
    }
    
    void showPage() {
    view.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==view.getbtnsubmit()){
            try {
                re.setnama(view.gettxtnama());
                re.setkontak(view.gettxtkontak());
                reDAO.updaterenter(re);
                view.setVisible(false);
                admincontroller ac = new admincontroller();
                ac.showPage();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        
    }
    
}
