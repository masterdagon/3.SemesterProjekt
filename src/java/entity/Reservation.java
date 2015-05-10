/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

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
    @OneToMany(mappedBy = "reservation")
    private List<Seat> seatList;
    @OneToOne
    private Customer customer;
    @ManyToOne
    private FlightInstance flightInstance;

    public Reservation() {
    }

    public Reservation( ArrayList<Customer> customerList, FlightInstance flightInstance) {
        this.seatList = new ArrayList();
        for (int i = 0; i < customerList.size(); i++) {
            seatList.add(new Seat(flightInstance.getFreeSeats().get(0),customerList.get(i),this));
            flightInstance.removeFreeSeat();
        }
        this.customer = customerList.get(0);
        this.flightInstance = flightInstance;
    }

    public List<Seat> getSeatList() {
        return seatList;
    }

    public void setSeatList(List<Seat> seatList) {
        this.seatList = seatList;
    }
    
    public void addtoSeatList(Seat seat){
        this.seatList.add(seat);
    }
    
    public void removefromSeatList(Seat seat){
        this.seatList.remove(seat);
    }

    public FlightInstance getFlightInstance() {
        return flightInstance;
    }

    public void setFlightInstance(FlightInstance flightInstance) {
        this.flightInstance = flightInstance;
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
   
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
