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
import javax.persistence.OneToMany;

/**
 *
 * @author Dennnis
 */
@Entity
public class FlightInstance implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String flightID;
    private String airline;
    private Date date;
    private Airport depature = null;
    private Airport  destination = null;
    private int seats;
    private int available_seats;
    private Flight plane = null;
    private String[] freeSeats;
    private boolean bookingCode;
    @OneToMany(mappedBy = "flightInstance")
    private List<Reservation> reservations;

    public FlightInstance() {
    }

    public FlightInstance(String flightID, String airline, Date date, int seats, int available_seats, String[] freeSeats, boolean bookingCode) {
        this.flightID = flightID;
        this.airline = airline;
        this.date = date;
        this.seats = seats;
        this.available_seats = available_seats;
        this.freeSeats = freeSeats;
        this.bookingCode = bookingCode;
        this.reservations = new ArrayList();
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }
    
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Flight getPlane() {
        return plane;
    }

    public void setPlane(Flight plane) {
        this.plane = plane;
    }

    public String[] getFreeSeats() {
        return freeSeats;
    }

    public void setFreeSeats(String[] freeSeats) {
        this.freeSeats = freeSeats;
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

    public Airport getDepature() {
        return depature;
    }

    public void setDepature(Airport depature) {
        this.depature = depature;
    }

    public Airport getDestination() {
        return destination;
    }

    public void setDestination(Airport destination) {
        this.destination = destination;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public int getAvailable_seats() {
        return available_seats;
    }

    public void setAvailable_seats(int available_seats) {
        this.available_seats = available_seats;
    }

    public boolean isBookingCode() {
        return bookingCode;
    }

    public void setBookingCode(boolean bookingCode) {
        this.bookingCode = bookingCode;
    }
    
    
}
