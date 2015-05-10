/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import entity.Airport;
import entity.Customer;
import entity.FlightInstance;
import entity.Reservation;
import facade.Facade;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.persistence.Persistence;
import rest2.exception.SoldOutException;

/**
 *
 * @author Muggi
 */
public class PopulateTables {
    
    static Facade f = new Facade();
    
    public static void main(String[] args) throws SoldOutException {
        f.createAirport("CPH", "Copenhagen","Denmark");
        f.createAirport("BER", "Berlin", "Germany");
        f.createAirport("MAD", "Madrid", "Spain");
        f.createAirport("PAR", "Paris", "France");
        f.createAirport("JFK", "New York", "United States");
        f.createPlane("Airbus A330-200", 335);//New York Once every Friday
        f.createPlane("Airbus A320-200", 180);//Paris once every Monday Wedensday and Friday
        f.createPlane("Boeing 737-800", 186);// Madrid every Thursday and Thuesday
        f.createPlane("Q400", 74);// Copenhagen once a day 

        Long start = 1431360000000L;
        System.out.println(new Date(start));
        Long end = 1443974400000L;
        Long day = 86400000L; 
        int count = 1;
        while(start<end){
            f.createFlightInstance("Air Berlin", new Date(start), 41, "CPH", "BER", "Q400");
            f.createFlightInstance("Air Berlin", new Date(start), 41, "BER", "CPH", "Q400");
            start += day;
            System.out.println(start<end);
            System.out.println(start);
            System.out.println(count);
            count++;
        }
        
        //monday        
        start = 1431360000000L;
        end = 1443456000000L;
        Long week = 604800000L;
        
        count = 1;

        while(start<end){
            f.createFlightInstance("Air Berlin", new Date(start), 81, "PAR", "BER", "Airbus A320-200");
            f.createFlightInstance("Air Berlin", new Date(start), 81, "BER", "PAR", "Airbus A320-200");
            start += week;
            System.out.println(start<end);
            System.out.println(start);
            System.out.println(count);
            count++;
        }
        
        //Thuesday
        start = 1431446400000L;
        end = 1443542400000L;
        count = 1;
        while(start<end){
            f.createFlightInstance("Air Berlin", new Date(start), 51, "MAD", "BER", "Boeing 737-800");
            f.createFlightInstance("Air Berlin", new Date(start), 51, "BER", "MAD", "Boeing 737-800");
            start += week;
            System.out.println(start<end);
            System.out.println(start);
            System.out.println(count);
            count++;
        }
        
          //Wedensday      
        start = 1431532800000L;
        end = 1443628800000L;
        count = 1;
        while(start<end){
            f.createFlightInstance("Air Berlin", new Date(start), 51, "PAR", "BER", "Airbus A320-200");
            f.createFlightInstance("Air Berlin", new Date(start), 51, "BER", "PAR", "Airbus A320-200");
            start += week;
            System.out.println(start<end);
            System.out.println(start);
            System.out.println(count);
            count++;
        }
        
        //Thursday
        start = 1431619200000L;
        end = 1443715200000L;
        count = 1;
        while(start<end){
            f.createFlightInstance("Air Berlin", new Date(start), 51, "MAD", "BER", "Boeing 737-800");
            f.createFlightInstance("Air Berlin", new Date(start), 51, "BER", "MAD", "Boeing 737-800");
            start += week;
            System.out.println(start<end);
            System.out.println(start);
            System.out.println(count);
            count++;
        }
        
        //friday
        start = 1431705600000L;
        end = 1443801600000L;  
        count = 1;
        while(start<end){
            f.createFlightInstance("Air Berlin", new Date(start), 81, "JFK", "BER", "Airbus A330-200");
            f.createFlightInstance("Air Berlin", new Date(start), 81, "BER", "JFK", "Airbus A330-200");
            f.createFlightInstance("Air Berlin", new Date(start), 51, "PAR", "BER", "Airbus A320-200");
            f.createFlightInstance("Air Berlin", new Date(start), 51, "BER", "PAR", "Airbus A320-200");
            start += week;
            System.out.println(start<end);
            System.out.println(start);
            System.out.println(count);
            count++;
        }
//        Customer c1 = f.createCustomer("Martin", "Rasmussen", "Egedalsvænge", "Denmark", "Kokkedal");
//        Customer c2 = f.createCustomer("Dennis", "Jensen", "Jernbanegade", "Denmark", "Hundested");
//        ArrayList<Customer> customerList = new ArrayList();
//        customerList.add(c1);
//        customerList.add(c2);
//        Date date = new Date();
//        FlightInstance fi1 = f.createFlightInstance("SAS", date, 800, "CPH", "FBB", "Airbus A350");
//        Reservation res = f.createReservation(customerList,fi1.getFlightID());
//        System.out.println("id" +res.getId());
//        System.out.println(f.getFlightWithFromToDate("FBB","CPH",date).size());
//        Calendar c = Calendar.getInstance(); 
//        c.setTime(date); 
//        c.add(Calendar.DATE, -1);
//        Date date1 =c.getTime();
//        c.add(Calendar.DATE, 2);
//        Date date2 = c.getTime();
//        System.out.println(f.getFlightWithDates(date1, date2).size());
//        System.out.println(f.getFlightWithDatesAndDepature(date1, date2,"FBB").size());
//        System.out.println(f.getFlightOnDateFromDepature(date,"FBB").size());
//        System.out.println(f.deleteReservation(res.getId()).getFreeSeats().size());
    }
    
}
