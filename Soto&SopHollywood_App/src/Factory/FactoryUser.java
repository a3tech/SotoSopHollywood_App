/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Factory;

import Implement.ImplementUser;
import Interface.InterfaceUser;
public class FactoryUser {
    
    private static InterfaceUser userDAO;

public static InterfaceUser getUserDAO() {
        userDAO = new ImplementUser() {};
        return userDAO;
    }
}
