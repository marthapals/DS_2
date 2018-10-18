/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import rental.CarType;

/**
 *
 * @author Martha
 */
public interface ManagerSessionRemote {
    public Collection<CarType> getCarTypesByCRC(String crc);
    public String getBestCustomer(String crc);
    public Map<String, Integer> getNbReservationsPerCarType(String crc);
    public Integer getNumberOfReservationsBy(Set<String> crcs , String client);
}
