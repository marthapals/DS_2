/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import javax.ejb.Remote;
import rental.CarType;
import rental.ReservationException;

/**
 *
 * @author Martha
 */
@Remote
public interface ManagerSessionRemote {
    public Collection<CarType> getCarTypesByCRC(String crc) throws ReservationException;
    public String getBestCustomer(String crc)throws ReservationException;
    public Map<String, Integer> getNbReservationsPerCarType(String crc)throws ReservationException;
    public Integer getNumberOfReservationsBy(Set<String> crcs , String client)throws ReservationException;
}
