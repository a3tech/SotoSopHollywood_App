/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import Entity.Entitas;
import java.util.List;
public interface InterfaceTransaksi {
    boolean insertDitempat(Entitas dt);
    boolean insertDetailDitempat(Entitas dt);
    boolean deleteDitempat(String kode);
    List selectDitempat(String kode, String tanggal);
    String kodeBaru();
    
}
