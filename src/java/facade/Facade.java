/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entity.Airport;
import entity.Customer;
import entity.FlightInstance;
import entity.Plane;
import entity.Reservation;
import entity.Seat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import rest2.exception.FlightNotFoundException;
import rest2.exception.ReservationNotFoundException;
import rest2.exception.SoldOutException;

/**
 *
 * @author Muggi
 */
public class Facade {

    private EntityManagerFactory emf;

    public Facade() {
        emf = Persistence.createEntityManagerFactory("3_SemesterProjektPU");
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public Customer getCustomer(int id) {
        EntityManager em = null;
        Customer customer = null;
        try {
            em = getEntityManager();
            customer = em.find(Customer.class, id);
            if(customer == null){
//                throw new EntityNotFoundException("The PhoneNumber: " + phoneNumber + " does not exist");
            }
        } catch (Exception e) {
            System.out.println("Error in getCustomer:" + e);
        }
        return customer;
    }

    public Customer createCustomer(String fname, String lname, String street, String country, String city) {
        EntityManager em = null;
        Customer customer = null;
        try {
            em = getEntityManager();
            customer = new Customer(fname, lname, street, country, city);
            em.getTransaction().begin();
            em.persist(customer);
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error in createCustomer:" + e);
        }
        return customer;
    }

    public Plane getPlane(String type) {
        EntityManager em = null;
        Plane plane = null;
        try {
            em = getEntityManager();
            plane = em.find(Plane.class, type);
            if(plane == null){
                throw new FlightNotFoundException("The planetype =: " + type + " does not exist");
            }
        } catch (Exception e) {
            System.out.println("Error in getPlane:" + e);
        }
        return plane;
    }

    public Plane createPlane(String type, int seats) {
        EntityManager em = null;
        Plane plane = null;
        try {
            em = getEntityManager();
            plane = new Plane(type, seats);
            em.getTransaction().begin();
            em.persist(plane);
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error in createPlane:" + e);
        }
        return plane;
    }

    public Airport getAirport(String code) {
        EntityManager em = null;
        Airport airport = null;
        try {
            em = getEntityManager();
            airport = em.find(Airport.class, code);
        } catch (Exception e) {
            System.out.println("Error in getAirport:" + e);
        }
        return airport;
    }

    public Airport createAirport(String code, String city, String country) {
        EntityManager em = null;
        Airport airport = null;
        try {
            em = getEntityManager();
            airport = new Airport(code, city, country);
            em.getTransaction().begin();
            em.persist(airport);
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error in createAirport:" + e);
        }
        return airport;
    }

    public FlightInstance getFlightInstance(int flightID) {
        EntityManager em = null;
        FlightInstance flightInstance = null;
        try {
            em = getEntityManager();
            flightInstance = em.find(FlightInstance.class, flightID);
            if(flightInstance == null){
                throw new FlightNotFoundException("The flight with ID : " + flightID + " does not exist");
            }
        } catch (Exception e) {
            System.out.println("Error in getFlightInstance:" + e);
        }
        return flightInstance;
    }

    public FlightInstance createFlightInstance(String airline, Date date, double price, String arrival, String departure, String planeType) {
        EntityManager em = null;
        FlightInstance flightInstance = null;
        try {
            em = getEntityManager();
            Plane plane = em.find(Plane.class, planeType);
            Airport a = em.find(Airport.class, arrival);
            Airport d = em.find(Airport.class, departure);
            flightInstance = new FlightInstance(airline, date, price, a, d, plane);
            flightInstance.setArrival(a);
            flightInstance.setDepature(d);
          
            em.getTransaction().begin();
            em.persist(flightInstance);
            a.addFlightInstanceToArrivalList(flightInstance);
            d.addFlightInstanceTodepartureList(flightInstance);
            em.merge(a);
            em.merge(d);
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error in createFlightInstance:" + e);
        }
        return flightInstance;
    }
    
    public Reservation getReservation(int id) throws ReservationNotFoundException{//Finnish
        EntityManager em = null;
        Reservation reservation = null;
        try {
            em = getEntityManager();
            reservation = em.find(Reservation.class, id);
            if(reservation == null){
                throw new ReservationNotFoundException("Reservation does not exist");
            }
        return reservation;
         } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    public Reservation createReservation(ArrayList<Customer> customer, String flightID) throws SoldOutException {//finnish
        EntityManager em = null;
        Reservation reservation = null;
        try {
            em = getEntityManager();
            FlightInstance fi = em.find(FlightInstance.class, flightID);
            if(fi.getFreeSeats().size()<customer.size()){
                throw new SoldOutException("There are not enough tickets left");
            }
            reservation = new Reservation(customer,fi);
            em.getTransaction().begin();
            em.persist(reservation);
            for (Seat seat : reservation.getSeatList()) {
                em.persist(seat);
            }
            em.getTransaction().commit();
        return reservation;
         } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public Reservation deleteReservation(int reservationID) throws ReservationNotFoundException {//finnish
        EntityManager em = null;
        Reservation reservation = null;
        FlightInstance flightInstance = null;
        try {
            em = getEntityManager();
            reservation = em.find(Reservation.class, reservationID);
            if(reservation == null){
                throw new ReservationNotFoundException("Reservation does not exist");
            }
            flightInstance = reservation.getFlightInstance();
           
            em.getTransaction().begin();
            for (Seat seat : reservation.getSeatList()) {
                flightInstance.addFreeSeat(seat.getSeatNumber());
                em.remove(seat.getCustomer());
                em.remove(seat);
            }
            em.remove(reservation);
            em.getTransaction().commit();
         
        return reservation;
         } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    
    public List<FlightInstance> getFlightWithFromToDate(String from,String to,Date date) throws FlightNotFoundException{             
        EntityManager em = null;
        List<FlightInstance> flightInstanceList = null;
        try {
            em = getEntityManager();
            Airport fa = em.find(Airport.class, from);
            Airport ta = em.find(Airport.class, to);
            flightInstanceList = new ArrayList();
            flightInstanceList = em.createQuery("select f from FlightInstance f where f.arrival=:arrival AND f.depature=:depature AND f.flyDate=:date")
                    .setParameter("depature", fa)
                    .setParameter("arrival", ta)
                    .setParameter("date", date)
                    .getResultList();
            if(flightInstanceList.isEmpty()){
                throw new FlightNotFoundException("No availaible flights from: " + from + " to: "+to+" on: "+date+" does not exist");
            }
            return flightInstanceList;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    
    public List<FlightInstance> getFlightWithDatesAndDepature(Date date1,Date date2,String from) throws FlightNotFoundException{             
        EntityManager em = null;
        List<FlightInstance> flightInstanceList = null;
        try {
            em = getEntityManager();
            Airport fa = em.find(Airport.class, from);
            flightInstanceList = new ArrayList();
            flightInstanceList = em.createQuery("select f from FlightInstance f where f.depature=:depature AND f.flyDate BETWEEN :date1 AND :date2")
                    .setParameter("depature", fa)
                    .setParameter("date1", date1)
                    .setParameter("date2", date2)
                    .getResultList();
          if(flightInstanceList.isEmpty()){
                throw new FlightNotFoundException("No availaible flights from: " + from + " within: "+date1+" - "+date2);
            }
            return flightInstanceList;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    
    public List<FlightInstance> getFlightOnDateFromDepature(Date date,String from) throws FlightNotFoundException{             
        EntityManager em = null;
        List<FlightInstance> flightInstanceList = null;
        try {
            em = getEntityManager();
            Airport fa = em.find(Airport.class, from);
            flightInstanceList = new ArrayList();
            flightInstanceList = em.createQuery("select f from FlightInstance f where f.depature=:depature AND f.flyDate = :date")
                    .setParameter("depature", fa)
                    .setParameter("date", date)
                    .getResultList();
        if(flightInstanceList.isEmpty()){
                throw new FlightNotFoundException("No availaible flights from: " + from + " on: "+date);
            }
            return flightInstanceList;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
}

//public List<FlightInstance> getFlightWithDates(Date date1,Date date2){             
//        EntityManager em = null;
//        List<FlightInstance> flightInstanceList = null;
//        try {
//            em = getEntityManager();
//            flightInstanceList = new ArrayList();
//            flightInstanceList = em.createQuery("select f from FlightInstance f where f.date BETWEEN :date1 AND :date2")
//                    .setParameter("date1", date1)
//                    .setParameter("date2", date2)
//                    .getResultList();
//        if(flightInstanceList.isEmpty()){
//                throw new FlightNotFoundException("No availaible flights from: " + from + " to: "+to+" on: "+date+" does not exist");
//            }
//            return flightInstanceList;
//        } finally {
//            if (em != null) {
//                em.close();
//            }
//        }
//    }