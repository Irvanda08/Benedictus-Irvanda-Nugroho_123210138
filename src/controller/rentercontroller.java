/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import koneksi.koneksi;
import DAO.roomDAO;
import model.renter;
import DAO.renterDAO;
import view.renterview;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

/**
 *
 * @author USER
 */
public class rentercontroller implements ActionListener{
    private renterview view;
    private renter re;
    private roomcontroller roomcontroller;
    
    public rentercontroller(renter re) {
    this.re = re;
    view = new renterview();
    view.getbtnsubmit().addActionListener(this);
    }
    
    void showPage() {
    view.setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==view.getbtnsubmit()){
            re.setnama(view.gettxtnama());
            re.setid(view.gettxtid());
            re.setkontak(view.gettxtkontak());
            re.setdurasi(Integer.parseInt(view.gettxtdurasi()));
            renterDAO reDAO = new renterDAO(new koneksi());
            roomDAO roDAO = new roomDAO(new koneksi());
            try {
                reDAO.inputrenter(re);
                roDAO.setNameInStatus(re.getroom(),re.getnama());
            } catch (SQLException p) {
                System.out.println(p.getMessage());
            }
            roomcontroller rc = new roomcontroller();
            view.setVisible(false);
            rc.showPage();
        }
    }
}
