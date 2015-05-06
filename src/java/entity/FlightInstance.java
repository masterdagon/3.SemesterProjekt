/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Dennnis
 */
@Entity
public class FlightInstance implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer flightID;
    private String airline;
    @Temporal(TemporalType.DATE)
    private Date date;
    @ManyToOne
    private Airport arrival = null;
    @ManyToOne
    private Airport  depature = null;
    private double price;
    @ManyToOne
    private Plane plane = null;
    private List<String> freeSeats;
    @OneToMany(mappedBy = "flightInstance")
    private List<Reservation> reservations;
    private boolean bookingCode;
    
    public FlightInstance() {
    }

    public FlightInstance(String airline, Date date, double price, List<Reservation> reservations, boolean bookingCode,Airport arrival,Airport depature,Plane plane) {
        this.airline = airline;
        this.date = date;
        this.price = price;
        this.freeSeats = plane.getTotalSeats();
        this.reservations = reservations;
        this.bookingCode = bookingCode;
        this.arrival = arrival;
        this.depature = depature;
        this.plane = plane;
    }
    
    
}
