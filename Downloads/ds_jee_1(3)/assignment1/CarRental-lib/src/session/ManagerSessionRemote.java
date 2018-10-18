/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import java.util.Collection;
import java.util.Map;
import rental.CarType;

/**
 *
 * @author Martha
 */
public interface ManagerSessionRemote {
    public Collection<CarType> getCarTypesByCRC(String crc);
    public String getBestCustomer(String crc);
    public Map<CarType, Integer> getNbReservationsPerCarType(String crc);
}
