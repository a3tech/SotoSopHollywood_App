/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Implement;

import Entity.EntitasPelanggan;
import Interface.InterfacePelanggan;
import Koneksi.KoneksiDB;
import java.util.List;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
public class ImplementPelanggan implements InterfacePelanggan {
    private String query;
    private KoneksiDB koneksi;
    private boolean status;
    private ResultSet rsPelanggan;
    private List<EntitasPelanggan> listPelanggan;
    
     public ImplementPelanggan(){
        koneksi = new KoneksiDB();
        koneksi.getkoneksi();
    }


    public boolean insertPelanggan(EntitasPelanggan p) {
   status = false;
   query = "insert into pelanggan values ('"+p.getId_pelanggan()+"','"+p.getNama_pelanggan()+"')";
        status = koneksi.eksekusiQuery(query, false);
        return status;
    }


    public boolean updatePelanggan(EntitasPelanggan p) {
    status = false;
    query = "update pelanggan set nama_pelanggan ='"+p.getNama_pelanggan()
            +"' where id_pelanggan ='"+p.getId_pelanggan()+"'";
    status = koneksi.eksekusiQuery(query, false);
    return status;
    }


    public boolean deletePelanggan(String kode) {
    status = false;
    query = "delete from pelanggan where id_pelanggan ='"+kode+"'";
    status = koneksi.eksekusiQuery(query, false);
    return status;
    }

 
    public List selectPelanggan(String kode, String nama_pelanggan) {
    query = "select * from pelanggan "+
            "where id_pelanggan like'%"+kode+"%' or nama_pelanggan like '%"+nama_pelanggan+"%'"+
                " order by id_pelanggan ASC";
        status = koneksi.eksekusiQuery(query, true);
        if (status){
            rsPelanggan = koneksi.getRs();
            listPelanggan = new ArrayList<EntitasPelanggan>();
            try{
                while (rsPelanggan.next()){
                    EntitasPelanggan p = new EntitasPelanggan();
                    p.setId_pelanggan(rsPelanggan.getString(1));
                    p.setNama_pelanggan(rsPelanggan.getString(2));
                    listPelanggan.add(p);
                }
                rsPelanggan.close();
                return listPelanggan;
            }catch(SQLException ex){
                return null;
            }
        }
        return null;
    }

    @Override
    public String kodeBaru() {
    String kode = "";
    int angka = 0;
    query = "select max(right(id_pelanggan,4))as kode from pelanggan";
    status = koneksi.eksekusiQuery(query, true);
    if(status){
        rsPelanggan = koneksi.getRs();
        try{
   while(rsPelanggan.next()){
      if(rsPelanggan.first()==false){
      kode = "P0001";
     }else{
    rsPelanggan.last();
    angka = rsPelanggan.getInt(1)+1;
    kode = String.valueOf(angka);
    int noLong = kode.length();
    for(int a=0;a<4-noLong;a++){
    if(angka<10){
       kode = "P000"+angka;
    }else if(angka<100){
       kode = "P00"+angka;
   }else if(angka<1000){
        kode = "P0"+angka;}
  else{
       kode = "P"+String.valueOf(angka);
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
    }
     
