/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import entity.Airport;
import entity.Customer;
import entity.FlightInstance;
import entity.Plane;
import entity.Reservation;
import facade.Facade;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import rest2.exception.FlightNotFoundException;

/**
 *
 * @author Muggi
 */
public class FacadeTest {

    private static Facade f;
    private static EntityManagerFactory emf;
    private static EntityManager em;
    private static Date date;
    private static Airport arrival;
    private static Airport departure;
    private static Plane plane;
    private static FlightInstance flightInstance;
    private static Customer customer1;
    private static Customer customer2;
   

    public FacadeTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        f = new Facade();
        date = new Date();
        arrival = f.createAirport("arrival", "arrival", "arrival");
        departure = f.createAirport("departure", "departure", "departure");
        plane = f.createPlane("plane", 25);
        flightInstance = f.createFlightInstance("airline", date, 100, arrival.getCode(), departure.getCode(), plane.getType());
        customer1 = f.createCustomer("customer1", "customer1", "customer1", "customer1", "customer1");
        customer2 = f.createCustomer("customer2", "customer2", "customer2", "customer2", "customer2");

    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        emf = Persistence.createEntityManagerFactory("3_SemesterProjektPU");
        em = emf.createEntityManager();

    }

    @After
    public void tearDown() {
        try {
            em.close();
        } catch (Exception e) {
            System.out.println("Error in closing entity manager");
        }
    }

    @Test
    public void createAirport() {
        f.createAirport("code1", "city1", "country1");
        Airport a = em.find(Airport.class, "code1");
        assertEquals("code1", a.getCode());

    }

    @Test
    public void createCustomer() {
        int id = f.createCustomer("fname", "lname", "data", "data", "data").getId();
        Customer c = em.find(Customer.class, id);
        assertEquals("fname", c.getfName());

    }

    @Test
    public void createPlane() {
        f.createPlane("testplane", 20);
        Plane p = em.find(Plane.class, "testplane");
        assertEquals("testplane", p.getType());

    }

    @Test
    public void createFlightInstance() {
        Airport a = f.createAirport("code1", "city1", "country1");
        Airport d = f.createAirport("code2", "city2", "country2");
        Plane plane = f.createPlane("testplane", 25);
        FlightInstance flightInstance = f.createFlightInstance("airline", date, 100, a.getCode(), d.getCode(), plane.getType());
        assertEquals(flightInstance.getDepature().getCode(), d.getCode());

    }

    @Test
    public void createReservation() {
        Airport a = f.createAirport("code1", "city1", "country1");
        Airport d = f.createAirport("code2", "city2", "country2");
        Plane plane = f.createPlane("testplane", 25);
        FlightInstance flightInstance = f.createFlightInstance("airline", date, 100, a.getCode(), d.getCode(), plane.getType());
        Customer c1 = f.createCustomer("fname1", "test", "test", "test", "test");
        Customer c2 = f.createCustomer("fname2", "test", "test", "test", "test");
        ArrayList<Customer> list = new ArrayList();
        list.add(c1);
        list.add(c2);
        Reservation reservation = f.createReservation(list, flightInstance.getFlightID());
        assertEquals(reservation.getSeatList().get(0).getCustomer().getfName(), c1.getfName());
    }

    @Test
    public void getFlightOnDateFromDepature() {
        List<FlightInstance> fi = f.getFlightOnDateFromDepature(date, "departure");
        assertEquals("departure", fi.get(0).getDepature().getCode());

    }

    @Test
    public void getFlightWithFromToDate() throws FlightNotFoundException {
        List<FlightInstance> fi = f.getFlightWithFromToDate("departure", "arrival", date);
        assertEquals(fi.get(0).getDepature().getCode(), "departure");
    }

    @Test
    public void getFlightWithDatesAndDepature() throws FlightNotFoundException {
        List<FlightInstance> fi = f.getFlightWithDatesAndDepature(new Date(date.getTime() - 1000), new Date(date.getTime() + 1000), "departure");
        assertEquals(fi.get(0).getDepature().getCode(), "departure");
    }
    
    @Test
    public void getReservation() throws FlightNotFoundException {
        em.getTransaction().begin();
        Reservation r = new Reservation();
        em.persist(r);
        em.getTransaction().commit();
        Reservation r1 = f.getReservation(r.getId());
        assertEquals(r.getId(),r1.getId());
    }
    
    @Test
    public void deleteReservation() throws FlightNotFoundException {
        ArrayList<Customer> list = new ArrayList();
        list.add(customer1);
        list.add(customer2);
        
        Reservation r = f.createReservation(list,flightInstance.getFlightID());
        f.deleteReservation(r.getId());
        Reservation r2 = em.find(Reservation.class,r.getId());
        System.out.println(r2);
        assertNull(r2);
    }
    
    
}
