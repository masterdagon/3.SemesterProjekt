/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

/**
 *
 * @author Dennnis
 */
@Entity
public class Seat implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    private String seatNumber;
    @OneToOne(cascade = CascadeType.ALL)
    private Customer customer;
    @ManyToOne
    private Reservation reservation;
    
    public Seat() {
    }

    public Seat(String seatNumber, Customer customer ,Reservation reservation ) {
        this.seatNumber = seatNumber;
        this.customer = customer;
        this.reservation = reservation;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

    
    
    public String getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    
    
}
