/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entity.Airport;
import entity.CityInfo;
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

//    public Person getPersonFromPhone(int phoneNumber) throws EntityNotFoundException {//Finnish
//        EntityManager em = null;
//        try {
//            em = getEntityManager();
//            Phone phone = em.find(Phone.class, phoneNumber);
//            if (phone == null) {
//                throw new EntityNotFoundException("The PhoneNumber: " + phoneNumber + " does not exist");
//            }
//            Person p = phone.getPerson();
//            return p;
//        } finally {
//            if (em != null) {
//                em.close();
//            }
//        }
//    }
//public Person createPerson(String fName, String lName, String email) {//finnish
//        EntityManager em = null;
//        try {
//            em = getEntityManager();
//            Person p = new Person(fName, lName);
//            p.setEmail(email);
//            em.getTransaction().begin();
//            em.persist(p);
//            em.getTransaction().commit();
//            return p;
//        } finally {
//            if (em != null) {
//                em.close();
//            }
//        }
//    }
    public CityInfo getCityInfo(int zip) {
        EntityManager em = null;
        CityInfo cityinfo = null;
        try {
            em = getEntityManager();
            cityinfo = em.find(CityInfo.class, zip);
        } catch (Exception e) {
            System.out.println("Error in getCityInfo:" + e);
        }
        return cityinfo;
    }

    public CityInfo createCityInfo(String city, int zip) {
        EntityManager em = null;
        CityInfo cityinfo = null;
        try {
            em = getEntityManager();
            cityinfo = new CityInfo(city, zip);
            em.getTransaction().begin();
            em.persist(cityinfo);
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error in createCityInfo:" + e);
        }
        return cityinfo;
    }

    public Customer getCustomer(int id) {
        EntityManager em = null;
        Customer customer = null;
        try {
            em = getEntityManager();
            customer = em.find(Customer.class, id);
        } catch (Exception e) {
            System.out.println("Error in getCustomer:" + e);
        }
        return customer;
    }

    public Customer createCustomer(String fname, String lname, String street, String country, int zip) {
        EntityManager em = null;
        Customer customer = null;
        try {
            em = getEntityManager();
            CityInfo cityinfo = em.find(CityInfo.class, zip);
            customer = new Customer(fname, lname, street, country, cityinfo);
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
            em.getTransaction().begin();
            em.persist(flightInstance);
            em.persist(flightInstance.getArrival());
            em.persist(flightInstance.getDepature());
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error in createFlightInstance:" + e);
        }
        return flightInstance;
    }
    
    public Reservation getReservation(int id){//Finnish
        EntityManager em = null;
        Reservation reservation = null;
        try {
            em = getEntityManager();
            reservation = em.find(Reservation.class, id);
        } catch (Exception e) {
            System.out.println("Error in  getReservation:" + e);
        }
        return reservation;
    }
    public Reservation createReservation(ArrayList<Customer> customer, int flightID) {//finnish
        EntityManager em = null;
        Reservation reservation = null;
        try {
            em = getEntityManager();
            FlightInstance fi = em.find(FlightInstance.class, flightID);
            reservation = new Reservation(customer,fi);
            em.getTransaction().begin();
            em.persist(reservation);
            for (Seat seat : reservation.getSeatList()) {
                em.persist(seat);
            }
            em.getTransaction().commit();
         } catch (Exception e) {
            System.out.println("Error in  createReservation:" + e);
        }
        return reservation;
    }
}
