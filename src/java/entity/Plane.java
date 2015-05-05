/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


/**
 *
 * @author Dennnis
 */
@Entity
public class Plane implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String flightID;
    private String type;
    private String[] totalSeats;

    public Plane() {
    }
    
    public Plane(String flightID, String type, String[] totalSeats) {
        this.flightID = flightID;
        this.type = type;
        this.totalSeats = totalSeats;
    }

    public String getFlightID() {
        return flightID;
    }

    public void setFlightID(String flightID) {
        this.flightID = flightID;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String[] getTotalSeats() {
        return totalSeats;
    }

    public void setTotalSeats(String[] totalSeats) {
        this.totalSeats = totalSeats;
    }
    
    

    
}
