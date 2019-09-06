/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import Entity.EntitasPelanggan;
import java.util.List;


public interface InterfacePelanggan {
    boolean insertPelanggan(EntitasPelanggan p);
    boolean updatePelanggan(EntitasPelanggan p);
    boolean deletePelanggan(String kode);
    List selectPelanggan(String kode, String nama_pelanggan);
    String kodeBaru();
}
