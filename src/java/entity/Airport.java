/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;

/**
 *
 * @author Dennnis
 */
@Entity
public class Airport implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "CODE")
    private String code;
    private String city;
    private String country;
    
    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "ArrivalList",
               joinColumns = @JoinColumn(name = "CODE"),
               inverseJoinColumns = @JoinColumn(name = "FLIGHTID") 
              )
    private List<FlightInstance> arrivalList;
    
    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "DepartureList",
               joinColumns = @JoinColumn(name = "CODE"),
               inverseJoinColumns = @JoinColumn(name = "FLIGHTID") 
              )
    private List<FlightInstance> departureList;
    
    public Airport() {
    }
        
    public Airport(String code, String city, String country) {
        this.code = code;
        this.city = city;
        this.country = country;
        this.arrivalList = new ArrayList();
        this.departureList = new ArrayList();
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
