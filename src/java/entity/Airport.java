/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author Dennnis
 */
@Entity
public class Airport implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String code;
    private String city;
    private String country;
    @ElementCollection()
    @OneToMany(mappedBy = "arrival")
    private List<FlightInstance> arrivalList;
    @ElementCollection()
    @OneToMany(mappedBy = "depature")
    private List<FlightInstance> departureList;
    
    public Airport() {
    }
        
    public Airport(String code, String city, String country) {
        this.code = code;
        this.city = city;
        this.country = country;
    }
    
    public void addFlightInstanceToArrivalList(FlightInstance flightinstance){
        arrivalList.add(flightinstance);
    }
    
    public void removeFlightInstanceFromArrivalList(FlightInstance flightinstance){
        arrivalList.remove(flightinstance);
              
    }
    
        public void addFlightInstanceTodepartureList(FlightInstance flightinstance){
        departureList.add(flightinstance);
    }
    
    public void removeFlightInstanceFromdepartureList(FlightInstance flightinstance){
        departureList.remove(flightinstance);
              
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
    
    public String getCity(){
        return city;
    }
    
    public void setCity(String city){
        this.city = city;
    }
    
    public String getCountry(){
        return country;
    }
    
    public void setCountry(String country){
        this.country = country;
    }
}
