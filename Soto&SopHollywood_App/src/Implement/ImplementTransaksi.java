/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Implement;
import Entity.Entitas;
import Entity.EntitasPelanggan;
import Koneksi.KoneksiDB;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Interface.InterfaceTransaksi;
public class ImplementTransaksi implements InterfaceTransaksi{
    private String query;
    private KoneksiDB koneksi;
    private boolean status;
    private ResultSet rsDitempat;
    private List<Entitas> listDitempat;
    
     public ImplementTransaksi(){
        koneksi = new KoneksiDB();
        koneksi.getkoneksi();
    }
    public boolean insertDitempat(Entitas dt) {
    status = false;
    query = "insert into transaksi values ('"+dt.getNonota()+"','"+dt.getId_karyawan()+"','"+dt.getId_pelanggan()+"','"+dt.getTgl()+"')";
        status = koneksi.eksekusiQuery(query, false);
        return status;
    }


    public boolean insertDetailDitempat(Entitas dt) {
    status = false;
    query = "insert into detail_transaksi values ('"+dt.getNonota()+"','"+dt.getKd_menu()+"','"
           +dt.getMeja()+"','"+dt.getJml()+"')";
        status = koneksi.eksekusiQuery(query, false);
        return status;
    }

    public String kodeBaru() {
    String kode = "";
    int angka = 0;
    query = "select max(right(nota,4))as kode from transaksi";
    status = koneksi.eksekusiQuery(query, true);
    if(status){
        rsDitempat = koneksi.getRs();
    try{
    while(rsDitempat.next()){
    if(rsDitempat.first()==false){
    kode = "N0001";
    }else{
    rsDitempat.last();
    angka = rsDitempat.getInt(1)+1;
    kode = String.valueOf(angka);
    int noLong = kode.length();
    
    for(int a=0;a<4-noLong;a++){
    if(angka<10){
       kode = "N000"+angka;
    }else if(angka<100){
       kode = "N00"+angka;
   }else if(angka<1000){
        kode = "N0"+angka;}
  else{
       kode = "N"+String.valueOf(angka);
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
    public List selectDitempat(String kode, String tanggal) {
     query = "select b.nota, b.kd_menu, a.id_user, a.id_pelanggan, a.tanggal, c.harga_menu, b.jumlah , c.nama_menu from detail_transaksi b "
             + "inner join transaksi a on a.nota=b.nota inner join menu c on c.kd_menu=b.kd_menu "+
            "where b.nota like'%"+kode+"%' or a.tanggal like '%"+tanggal+"%'"+
            " order by a.tanggal ASC";
        status = koneksi.eksekusiQuery(query, true);
        if (status){
            rsDitempat = koneksi.getRs();
            listDitempat = new ArrayList<Entitas>();
            try{
                while (rsDitempat.next()){
                    Entitas e = new Entitas();
                    e.setNonota(rsDitempat.getString("nota"));
                    e.setKd_menu(rsDitempat.getString("kd_menu"));
                    e.setId_karyawan(rsDitempat.getString("id_user"));
                    e.setId_pelanggan(rsDitempat.getString("id_pelanggan"));
                    e.setTgl(rsDitempat.getString("tanggal"));
                    e.setNama_menu(rsDitempat.getString("nama_menu"));
                    e.setHarga_menu(rsDitempat.getInt("harga_menu"));
                    e.setJml(rsDitempat.getInt("jumlah"));
                    listDitempat.add(e);
                }
                rsDitempat.close();
                return listDitempat;
            }catch(SQLException ex){
                return null;
            }
        }
        return null;
    }

    @Override
    public boolean deleteDitempat(String kode) {
    status = false;
     query = "delete a.*, b.* from detail_transaksi a, transaksi b "
            + "where a.nota ='"+kode+"' and b.nota='"+kode+"'";
    status = koneksi.eksekusiQuery(query, false);
    return status;
    }
}
