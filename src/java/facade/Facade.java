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
//    public void deletePerson(int personId) throws EntityNotFoundException {//finnish
//        EntityManager em = null;
//        try {
//            em = getEntityManager();
//            Person p = em.find(Person.class, personId);
//            if (p == null) {
//                throw new EntityNotFoundException("The person does not exist in database");
//            }
//            int aId = 0;
//            if (p.getAddress() != null) {
//                aId = p.getAddress().getId();
//            }
//            List<Phone> phones = p.getPhones();
//            List<Hobby> hobbies = p.getHobbies();
//            if (p.getAddress() != null) {
//                if (!p.getAddress().getPersons().isEmpty()) {
//                    if (p.getAddress().getPersons().contains(p)) {
//                        p.getAddress().removePerson(p);
//                    }
//                } else {
//                }
//            }
//
//            em.getTransaction().begin();
//            for (Hobby hb : hobbies) {
//
//                hb.removePerson(p);
//
//                em.merge(hb);
//            }
//            for (Phone ph : phones) {
//                em.remove(ph);
//            }
//            p.getPhones().clear();
//            p.getHobbies().clear();
//            em.merge(p);
//            em.remove(p);
//            em.getTransaction().commit();
//            if (p.getAddress() != null) {
//                deleteAddress(aId);
//            }
//        } finally {
//            if (em != null) {
//                em.close();
//            }
//        }
//    }
    public FlightInstance deleteReservation(int reservationID) {//finnish
        EntityManager em = null;
        Reservation reservation = null;
        FlightInstance flightInstance = null;
        try {
            em = getEntityManager();
            reservation = em.find(Reservation.class, reservationID);
            flightInstance = reservation.getFlightInstance();
           
            em.getTransaction().begin();
            for (Seat seat : reservation.getSeatList()) {
                flightInstance.addFreeSeat(seat.getSeatNumber());
                em.remove(seat.getCustomer());
                em.remove(seat);
            }
            em.remove(reservation);
            em.getTransaction().commit();
         } catch (Exception e) {
            System.out.println("Error in  createReservation:" + e);
        }
        return flightInstance;
    }
    
    public List<FlightInstance> getFlightWithFromToDate(String from,String to,Date date){             
        EntityManager em = null;
        List<FlightInstance> flightInstanceList = null;
        try {
            em = getEntityManager();
            Airport fa = em.find(Airport.class, from);
            Airport ta = em.find(Airport.class, to);
            flightInstanceList = new ArrayList();
            flightInstanceList = em.createQuery("select f from FlightInstance f where f.arrival=:arrival AND f.depature=:depature AND f.date=:date")
                    .setParameter("arrival", fa)
                    .setParameter("depature", ta)
                    .setParameter("date", date)
                    .getResultList();
        } catch (Exception e) {
            System.out.println("Error in getFlightInstance:" + e);
        }
        return flightInstanceList;
    }
    
    public List<FlightInstance> getFlightWithDates(Date date1,Date date2){             
        EntityManager em = null;
        List<FlightInstance> flightInstanceList = null;
        try {
            em = getEntityManager();
            flightInstanceList = new ArrayList();
            flightInstanceList = em.createQuery("select f from FlightInstance f where f.date BETWEEN :date1 AND :date2")
                    .setParameter("date1", date1)
                    .setParameter("date2", date2)
                    .getResultList();
        } catch (Exception e) {
            System.out.println("Error in getFlightInstance:" + e);
        }
        return flightInstanceList;
    }
    
    public List<FlightInstance> getFlightWithDatesAndDepature(Date date1,Date date2,String from){             
        EntityManager em = null;
        List<FlightInstance> flightInstanceList = null;
        try {
            em = getEntityManager();
            Airport fa = em.find(Airport.class, from);
            flightInstanceList = new ArrayList();
            flightInstanceList = em.createQuery("select f from FlightInstance f where f.depature=:depature AND f.date BETWEEN :date1 AND :date2")
                    .setParameter("depature", fa)
                    .setParameter("date1", date1)
                    .setParameter("date2", date2)
                    .getResultList();
        } catch (Exception e) {
            System.out.println("Error in getFlightInstance:" + e);
        }
        return flightInstanceList;
    }
    
    public List<FlightInstance> getFlightOnDateFromDepature(Date date,String from){             
        EntityManager em = null;
        List<FlightInstance> flightInstanceList = null;
        try {
            em = getEntityManager();
            Airport fa = em.find(Airport.class, from);
            flightInstanceList = new ArrayList();
            flightInstanceList = em.createQuery("select f from FlightInstance f where f.depature=:depature AND f.date = :date")
                    .setParameter("depature", fa)
                    .setParameter("date", date)
                    .getResultList();
        } catch (Exception e) {
            System.out.println("Error in getFlightInstance:" + e);
        }
        return flightInstanceList;
    }
}
