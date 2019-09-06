/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Implement;

import Entity.EntitasUser;
import Interface.InterfaceUser;
import Koneksi.KoneksiDB;
import java.util.List;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
public class ImplementUser implements InterfaceUser{
    private String query;
    private KoneksiDB koneksi;
    private boolean status;
    private ResultSet rsUser;
    private List<EntitasUser> listUser;
    
     public ImplementUser(){
        koneksi = new KoneksiDB();
        koneksi.getkoneksi();
    }

    @Override
    public String kodeBaru() {
   String kode = "";
    int angka = 0;
    query = "select max(right(id_user,4))as kode from user";
    status = koneksi.eksekusiQuery(query, true);
    if(status){
        rsUser = koneksi.getRs();
        try{
   while(rsUser.next()){
        if(rsUser.first()==false){
        kode = "A0001";
       }else{
    rsUser.last();
    angka = rsUser.getInt(1)+1;
    kode = String.valueOf(angka);
    int noLong = kode.length();
    
    for(int a=0;a<4-noLong;a++){
    if(angka<10){
       kode = "A000"+angka;
    }else if(angka<100){
       kode = "A00"+angka;
   }else if(angka<1000){
        kode = "A0"+angka;}
  else{
       kode = "A"+String.valueOf(angka);
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
    public boolean insertUser(EntitasUser k) {
     status = false;
    query = "insert into user values ('"+k.getId_karyawan()+"','"+k.getNama_user()+"','"
           +k.getUsername()+"','"+k.getPassword()+"','"+k.getJabatan()+"')";
        status = koneksi.eksekusiQuery(query, false);
        return status;
    }

    @Override
    public boolean updateUser(EntitasUser k) {
     status = false;
    query = "update user set nama_user ='"+k.getNama_user()+"',username='"+k.getUsername()
            +"',password='"+k.getPassword()+"' where id_user ='"+k.getId_karyawan()+"'";
    status = koneksi.eksekusiQuery(query, false);
    return status;    
    }

    @Override
    public boolean deleteUser(String kode) {
    status = false;
    query = "delete from user where id_user ='"+kode+"'";
    status = koneksi.eksekusiQuery(query, false);
    return status;
    }

    @Override
    public List selectUser(String kode, String nama_user) {
     query = "select * from user "+
            "where id_user like'%"+kode+"%' or nama_user like '%"+nama_user+"%' AND jabatan='karyawan'"+
                " order by id_user ASC";
        status = koneksi.eksekusiQuery(query, true);
        if (status){
            rsUser = koneksi.getRs();
            listUser = new ArrayList<EntitasUser>();
            try{
                while (rsUser.next()){
                    EntitasUser k = new EntitasUser();
                    k.setId_karyawan(rsUser.getString(1));
                    k.setNama_user(rsUser.getString(2));
                    k.setUsername(rsUser.getString(3));
                    k.setPassword(rsUser.getString(4));
                    k.setJabatan(rsUser.getString(5));
                    listUser.add(k);
                }
                rsUser.close();
                return listUser;
            }catch(SQLException ex){
                return null;
            }
        }
        return null;
    }
    
}



