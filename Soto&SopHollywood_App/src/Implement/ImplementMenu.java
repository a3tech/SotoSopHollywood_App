/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Implement;

import Entity.EntitasMenu;
import Entity.EntitasUser;
import Interface.InterfaceMenu;
import Koneksi.KoneksiDB;
import java.util.List;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
public class ImplementMenu implements InterfaceMenu{
    private String query;
    private KoneksiDB koneksi;
    private boolean status;
    private ResultSet rsMenu;
    private List<EntitasMenu> listMenu;
    
    public ImplementMenu(){
        koneksi = new KoneksiDB();
        koneksi.getkoneksi();
    }

    public boolean updateMenu(EntitasMenu m) {
    status = false;
    query = "update menu set nama_menu ='"+m.getNama_menu()+"',harga_menu='"+m.getHarga_menu()
            +"' where kd_menu ='"+m.getKd_menu()+"'";
    status = koneksi.eksekusiQuery(query, false);
    return status;
    }

    public boolean deleteMenu(String kode) {
    status = false;
    query = "delete from menu where kd_menu ='"+kode+"'";
    status = koneksi.eksekusiQuery(query, false);
    return status;
    }

    public List selectMenu(String kode, String nama_menu) {
     query = "select * from menu "+
            "where kd_menu like '%"+kode+"%' or nama_menu like '%"+nama_menu+"%' order by kd_menu ASC";
        status = koneksi.eksekusiQuery(query, true);
        if (status){
            rsMenu = koneksi.getRs();
            listMenu = new ArrayList<EntitasMenu>();
            try{
                while (rsMenu.next()){
                    EntitasMenu m = new EntitasMenu();
                    m.setKd_menu(rsMenu.getString(1));
                    m.setNama_menu(rsMenu.getString(3));
                    m.setHarga_menu(rsMenu.getInt(4));
                    listMenu.add(m);
                }
                rsMenu.close();
                return listMenu;
            }catch(SQLException ex){
                return null;
            }
        }
        return null;
    }

    @Override
    public String kodeBaru() {
    String kode="";
    int angka = 0;
    query = "select max(right(kd_menu,4))as kode from menu";
    status = koneksi.eksekusiQuery(query, true);
    if(status){
        rsMenu = koneksi.getRs();
        try{
            while(rsMenu.next()){
                if(rsMenu.first()==false){
                 kode = "M0001";
                }else{
    rsMenu.last();
    angka = rsMenu.getInt(1)+1;
    kode = String.valueOf(angka);
    int noLong = kode.length();
    for(int a=0;a<4-noLong;a++){
    if(angka<10){
       kode = "M000"+angka;
    }else if(angka<100){
       kode = "M00"+angka;
   }else if(angka<1000){
        kode = "M0"+angka;}
  else{
       kode = "M"+String.valueOf(angka);
        }
    }
                }}
    }catch(SQLException ex){
            System.out.println(ex.getMessage());
            return null;
    }
    }
    return kode; 
    }

    @Override
    public boolean insertMenu(EntitasMenu m, EntitasUser u) {
    status = false;
    query = "insert into menu values ('"+m.getKd_menu()+"','"+u.getId_admin()+"','"+m.getNama_menu()+"','"
           +m.getHarga_menu()+"')";
        status = koneksi.eksekusiQuery(query, false);
        return status;
    }
    
}
