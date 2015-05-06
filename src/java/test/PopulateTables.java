/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import entity.Airport;
import entity.Customer;
import facade.Facade;
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
        f.createCustomer("Martin", "Rasmussen", "Egedalsvænge", "Denmark", 2980);
        f.createCustomer("Dennis", "Jensen", "Jernbanegade", "Denmark", 3390);
//        Arraylist<Customer> custumerList
        Date date = new Date();
//        Airport departure = f.getAirport("CPH");
//        Airport arrival = f.getAirport("FBB");
        f.createFlightInstance("SAS", date, 800, "CPH", "FBB", "Airbus A350");
    }
    
}
