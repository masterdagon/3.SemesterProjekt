/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
public class Plane implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String type;
    @ElementCollection()
    private List<String> totalSeats;
    @OneToMany(mappedBy = "plane")
    private List<FlightInstance> flightInstance;

    public Plane() {
    }

    public Plane(String type, int totalSeats) {
        this.type = type;
        this.totalSeats = new ArrayList();
        for(int i = 0;i<totalSeats;i++){
            String seat = String.valueOf(i);
            this.totalSeats.add(seat);
        }
        
        this.flightInstance = new ArrayList();
    }

    public void addFlightInstance(FlightInstance flightInstance){
        this.flightInstance.add(flightInstance);
    }
    
    public void removeFlightInstance(FlightInstance flightInstance){
        this.flightInstance.remove(flightInstance);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<String> getTotalSeats() {
        return totalSeats;
    }

    public void setTotalSeats(List<String> totalSeats) {
        this.totalSeats = totalSeats;
    }

  

    public List<FlightInstance> getFlightInstance() {
        return flightInstance;
    }

    public void setFlightInstance(List<FlightInstance> flightInstance) {
        this.flightInstance = flightInstance;
    }
    
}
