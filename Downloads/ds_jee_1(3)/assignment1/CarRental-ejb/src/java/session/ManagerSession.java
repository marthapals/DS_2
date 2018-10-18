/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import rental.Car;
import rental.CarRentalCompany;
import rental.CarType;
import rental.Reservation;

/**
 *
 * @author Martha
 */
@Stateless
@LocalBean
public class ManagerSession {
    
    public Collection<CarType> getCarTypesByCRC(CarRentalCompany crc){
        return crc.getCarTypes();
    }
    
    public Map<CarType, Integer> getNbReservationsPerCarType(CarRentalCompany crc){
        Collection<Car> cars = crc.getCars();
        Map<CarType,Integer> map = new HashMap();
        for(CarType c : crc.getCarTypes()){
            map.put(c,0);
        }
        for(Car c : cars){
            for(Reservation r : c.getAllReservations()){
                Integer res = map.get(c.getType());
                map.put(c.getType(),res+1);
            }  
        } 
        return map;
    }
    public String getBestCustomer(CarRentalCompany crc){
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
                clien = client;
            }
        }
        return clien;
    }
}