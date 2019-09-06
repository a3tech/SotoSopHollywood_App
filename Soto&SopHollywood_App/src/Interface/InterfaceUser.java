/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import Entity.EntitasUser;
import java.util.List;
public interface InterfaceUser {
    boolean insertUser(EntitasUser k);
    boolean updateUser(EntitasUser k);
    boolean deleteUser(String kode);
    List selectUser(String kode, String nama_user);
    String kodeBaru();
}
