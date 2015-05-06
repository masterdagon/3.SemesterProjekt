/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import entity.Airport;
import entity.Customer;
import entity.FlightInstance;
import facade.Facade;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Muggi
 */
public class PopulateTables {
    
    static Facade f = new Facade();
    
    public static void main(String[] args) {
        f.createCityInfo("Kokkedal", 2980);
        f.createCityInfo("Hundested", 3390);
        f.createAirport("CPH", "Copenhagen","Denmark");
        f.createAirport("FBB", "Berlin", "Germany");
        f.createPlane("Airbus A350", 253);
        Customer c1 = f.createCustomer("Martin", "Rasmussen", "Egedalsvænge", "Denmark", 2980);
        Customer c2 = f.createCustomer("Dennis", "Jensen", "Jernbanegade", "Denmark", 3390);
        ArrayList<Customer> customerList = new ArrayList();
        customerList.add(c1);
        customerList.add(c2);
        Date date = new Date();
        FlightInstance fi1 = f.createFlightInstance("SAS", date, 800, "CPH", "FBB", "Airbus A350");
        f.createReservation(customerList,fi1.getFlightID());
    }
    
}
