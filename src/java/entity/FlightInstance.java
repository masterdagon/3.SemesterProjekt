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
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
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
    @Column(name = "FLIGHTID")
    private String flightID;
    private String airline;
    @Temporal(TemporalType.DATE)
    private Date flyDate;
    @ManyToOne
    private Airport arrival = null;
    @ManyToOne
    private Airport  depature = null;
    private double price;
    @ManyToOne
    private Plane plane = null;
    @ElementCollection()
    private List<String> freeSeats;
    @OneToMany(mappedBy = "flightInstance")
    private List<Reservation> reservations;
    private boolean bookingCode;
    
    public FlightInstance() {
    }

    public FlightInstance(String airline, Date date, double price,Airport arrival,Airport departure,Plane plane) {
        this.airline = airline;
        this.flyDate = date;
        this.price = price;
        this.freeSeats = plane.getTotalSeats();
        this.reservations = new ArrayList();
        this.bookingCode = true;
        this.arrival = arrival;
        this.depature = departure;
        this.plane = plane;
    }
    
    public String removeFreeSeat(){
        String seat = freeSeats.get(0);
        freeSeats.remove(0);
        return seat;
    }
    
    public void addFreeSeat(String seat){
        freeSeats.add(seat);
    }

    public void addReservation(Reservation reservation){
        reservations.add(reservation);
    }
    
    public void removeReservation(Reservation reservation){
        reservations.remove(reservation);
    }
    
    public String getFlightID() {
        return flightID;
    }

    public void setFlightID(String flightID) {
        this.flightID = flightID;
    }

    public String getAirline() {
        return airline;
    }

    public void setAirline(String airline) {
        this.airline = airline;
    }

    public Date getDate() {
        return flyDate;
    }

    public void setDate(Date date) {
        this.flyDate = date;
    }

    public Airport getArrival() {
        return arrival;
    }

    public void setArrival(Airport arrival) {
        this.arrival = arrival;
    }

    public Airport getDepature() {
        return depature;
    }

    public void setDepature(Airport depature) {
        this.depature = depature;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Plane getPlane() {
        return plane;
    }

    public void setPlane(Plane plane) {
        this.plane = plane;
    }

    public List<String> getFreeSeats() {
        return freeSeats;
    }

    public void setFreeSeats(List<String> freeSeats) {
        this.freeSeats = freeSeats;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    public boolean getBookingCode() {
        return bookingCode;
    }

    public void setBookingCode(boolean bookingCode) {
        this.bookingCode = bookingCode;
    }
    
    
}
