/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import rental.Car;
import rental.CarRentalCompany;
import rental.CarType;
import rental.RentalStore;
import rental.Reservation;

/**
 *
 * @author Martha
 */
@Stateless
@LocalBean
public class ManagerSession {
    
    public Collection<CarType> getCarTypesByCRC(String crc1){
        CarRentalCompany crc = RentalStore.getRental(crc1);
        return crc.getCarTypes();
    }
    
    public Map<String, Integer> getNbReservationsPerCarType(String crc1){
        CarRentalCompany crc = RentalStore.getRental(crc1);
        Collection<Car> cars = crc.getCars();
        Map<String,Integer> map = new HashMap();
        for(CarType c : crc.getCarTypes()){
            map.put(c.getName(),0);
        }
        for(Car c : cars){
            for(Reservation r : c.getAllReservations()){
                Integer res = map.get(c.getType().getName());
                map.put(c.getType().getName(),res+1);
            }  
        } 
        return map;
    }
    public String getBestCustomer(String crc1){
        CarRentalCompany crc = RentalStore.getRental(crc1);
        Collection<Car> cars = crc.getCars();
        Map<String,Integer> map = new HashMap();
        for(Car c : cars){
            for(Reservation r : c.getAllReservations()){
                if(map.containsKey(r.getCarRenter())){
                    Integer res = map.get(r.getCarRenter());
                    map.put(r.getCarRenter(), res +1);
                }
                else{
                    map.put(r.getCarRenter(),1);
                    
                }
            }
        }
        Integer max = 0;
        String clien="";
        for(String client : map.keySet()){
            if(map.get(client)>max){
                max = map.get(client);
                clien = client;
            }
        }
        return clien;
    }
    
    public Integer getNumberOfReservationsBy(Set<String> crcs , String client){
        Set<Reservation> reservations = new HashSet<Reservation>();
        
        //alle reservaties van een client opvragen per crc
        for(String crc: crcs){
            CarRentalCompany crcObj = RentalStore.getRental(crc);
            reservations.addAll(crcObj.getReservationsBy(client));
        }

        return reservations.size();
    }
}