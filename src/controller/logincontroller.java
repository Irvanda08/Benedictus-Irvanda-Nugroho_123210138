/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;
import koneksi.koneksi;
import model.akun;
import view.adminview;
import view.loginview;
import view.roomview;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
/**
 *
 * @author USER
 */
public class logincontroller implements ActionListener{
    loginview view;
    roomcontroller roomListController;
    admincontroller apc;
    
    public logincontroller(loginview view) {
        this.view = view;
    }
     
    @Override
    public void actionPerformed(ActionEvent e) {
        akun akun = new akun(view.getjTextField1(),view.getjPasswordField1());
        if(akun.GetAkunByUsername().equalsIgnoreCase("ADMIN")){
            view.setVisible(false);
            apc = admincontroller.getInstance();
            apc.showPage();
        }  else if(akun.GetAkunByUsername().equalsIgnoreCase("RENTER")){
            view.setVisible(false);
            roomListController = roomcontroller.getInstance();
            roomListController.showPage();
        }else{
            view.showMessage("login gagal");
        }
    }
}
