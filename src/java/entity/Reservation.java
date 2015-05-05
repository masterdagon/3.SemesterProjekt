/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author Dennnis
 */
@Entity
public class Reservation implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    
    @ManyToOne
    private List<Seat> SeatList;
    private Customer customer;
    @ManyToOne
    private FlightInstance flightInstance;

    public Reservation() {
    }

    public Reservation(Integer id, List<Seat> SeatList, Customer customer) {
        this.id = id;
        this.SeatList = SeatList;
        this.customer = customer;
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    public List<Seat> getSeatList() {
        return SeatList;
    }

    public void setSeatList(List<Seat> SeatList) {
        this.SeatList = SeatList;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
