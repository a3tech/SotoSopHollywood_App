/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import Entity.EntitasMenu;
import Entity.EntitasUser;
import java.util.List;
public interface InterfaceMenu {
    boolean insertMenu(EntitasMenu m, EntitasUser u);
    boolean updateMenu(EntitasMenu m);
    boolean deleteMenu(String kode);
    List selectMenu(String kode, String nama_menu);  
    String kodeBaru();
}
