/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import koneksi.koneksi;
import model.renter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import model.room;
import view.roomview;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

/**
 *
 * @author USER
 */
public class roomcontroller implements ActionListener{
    private static roomcontroller instance;
    room room;
    private roomview view;
    private rentercontroller rc;
    private renter rent;
    
    public roomcontroller() {
        room = new room();
        view = new roomview();
        loadallroom();
        view.getjButton1().addActionListener(this);
    }
    public static roomcontroller getInstance(){
        if(instance==null){
            instance = new roomcontroller();
        }
        return instance;
    }
public void loadallroom() {
    List<room> rooms = room.getallroom();
    DefaultTableModel dtm = new DefaultTableModel();
    dtm.addColumn("Name");
    dtm.addColumn("Size");
    dtm.addColumn("Price");
    dtm.addColumn("Status");
    for (room r : rooms) {
        Object[] row = { r.getName(), r.getSize(), r.getPrice(), r.getStatus() };
        dtm.addRow(row);
    }
    view.setTableModel(dtm);
    
    view.getTable().addMouseListener(new MouseAdapter() {
        
        @Override
        public void mouseClicked(MouseEvent e) {
            rent = new renter();
            try {
                rent.closeConnection();
                int row = view.getTable().getSelectedRow();
                rent.setroom((String) view.getTable().getValueAt(row, 0));
                rent.setbill((int) view.getTable().getValueAt(row, 2));
                if("empty".equals((String) view.getTable().getValueAt(row, 3))){
                    rentercontroller rdc = new rentercontroller(rent);
                    view.setVisible(false);
                    rdc = new rentercontroller(rent);
                    rdc.showPage();    
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    });
}

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==view.getjButton1()){
            System.exit(0);
        }
    }

    void showPage() {
    view.setVisible(true);
    }
    
}
