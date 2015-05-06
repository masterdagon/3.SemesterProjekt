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
import java.util.Date;
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

        }
        return plane;
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

        }
        return airport;
    }

    public FlightInstance createFlightInstance(String airline, Date date, double price, Airport arrival, Airport departure, String planeType) {
        EntityManager em = null;
        FlightInstance flightInstance = null;
        try {
            em = getEntityManager();
            Plane plane = em.find(Plane.class, "planeType");
            flightInstance = new FlightInstance(airline, date, price, arrival, departure, plane);
            em.getTransaction().begin();
            em.persist(flightInstance);
            em.getTransaction().commit();
        } catch (Exception e) {

        }
        return flightInstance;
    }

    public void createFlightInstance(String sas, Date date, int i, boolean b, Object object, Object object0, Object object1) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
