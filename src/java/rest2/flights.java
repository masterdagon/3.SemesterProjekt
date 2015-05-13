/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest2;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import entity.Customer;
import entity.FlightInstance;
import entity.Reservation;
import entity.Seat;
import facade.Facade;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import static javax.ws.rs.HttpMethod.POST;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import oracle.jdbc.proxy.annotation.Post;
import rest2.exception.FlightNotFoundException;
import rest2.exception.InvalidArgumentException;
import rest2.exception.ReservationNotFoundException;
import rest2.exception.SoldOutException;

/**
 * REST Web Service
 *
 * @author Dennnis
 */
@Path("flights")
public class flights {

    @Context
    private UriInfo context;
    private Facade f;
    private Gson gson;

    /**
     * Creates a new instance of flights
     */
    public flights() {
        this.f = new Facade();
        this.gson = new Gson();
    }

    /**
     * Retrieves representation of an instance of Rest.flights
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("application/json")
    @Path("test")
    public String test(){
        return gson.toJson("{/'test/':/'test/'}");
    }
    
    @GET
    @Produces("application/json")
    @Path("{departure}/{date}")
    public String getFlightsFromDates(@PathParam("departure") String dep,@PathParam("date") String date) throws FlightNotFoundException, InvalidArgumentException {
        boolean isNumeric = true;
        Long parsedDate=null;
        try {
            parsedDate = Long.parseLong(date);
        } catch (NumberFormatException nfe) {
            isNumeric = false;
            throw new InvalidArgumentException("Date is not a number(Long)");
        }
        if(isNumeric){
            List<FlightInstance> fiList = f.getFlightOnDateFromDepature(new Date(parsedDate),dep);
        JsonArray flightList = new JsonArray();
        for (FlightInstance fl : fiList) {
            JsonObject flight = new JsonObject();
            flight.addProperty("airline", fl.getAirline());
            flight.addProperty("price", fl.getPrice());
            flight.addProperty("flightId", fl.getFlightID());
            flight.addProperty("takeOffDate", fl.getDate().getTime());
            flight.addProperty("landingDate", fl.getDate().getTime());
            flight.addProperty("depature", fl.getDepature().getCode());
            flight.addProperty("destination", fl.getArrival().getCode());
            flight.addProperty("seats", fl.getPlane().getTotalSeats().size());
            flight.addProperty("available seats", fl.getFreeSeats().size());
            flight.addProperty("bookingCode", Boolean.TRUE);
            flightList.add(flight);
        }
        return gson.toJson(flightList);
        }
        return null;
    }
    
    @GET
    @Produces("application/json")
    @Path("/{from}/{to}/{date}")
    public String getFlightsFromTOOnDate(@PathParam("from") String from, @PathParam("to") String to,@PathParam("date") String date) throws FlightNotFoundException, InvalidArgumentException{
        boolean isNumeric = true;
        Long parsedDate=null;
        try {
            parsedDate = Long.parseLong(date);
        } catch (NumberFormatException nfe) {
            isNumeric = false;
            throw new InvalidArgumentException("Date is not a number(Long)");
        }
        if(isNumeric){
        List<FlightInstance> fiList = f.getFlightWithFromToDate(from,to,new Date(parsedDate));
        JsonArray flightList = new JsonArray();
        for (FlightInstance fl : fiList) {
            JsonObject flight = new JsonObject();
            flight.addProperty("airline", fl.getAirline());
            flight.addProperty("price", fl.getPrice());
            flight.addProperty("flightId", fl.getFlightID());
            flight.addProperty("takeOffDate", fl.getDate().getTime());
            flight.addProperty("landingDate", fl.getDate().getTime());
            flight.addProperty("depature", fl.getDepature().getCode());
            flight.addProperty("destination", fl.getArrival().getCode());
            flight.addProperty("seats", fl.getPlane().getTotalSeats().size());
            flight.addProperty("available seats", fl.getFreeSeats().size());
            flight.addProperty("bookingCode", Boolean.TRUE);
            flightList.add(flight);
        }
        return gson.toJson(flightList);
        }
        return null;
    }
    
    @GET
    @Produces("application/json")
    @Path("/dates/{start}/{end}/{from}")
    public String getFlightsTOFromDate(@PathParam("from") String from, @PathParam("start") String start,@PathParam("end") String end) throws FlightNotFoundException, InvalidArgumentException {
        boolean isNumeric = true;
        Long parsedDate1=null;
        Long parsedDate2=null;
        try {
            parsedDate1 = Long.parseLong(start);
            parsedDate2 = Long.parseLong(end);
        } catch (NumberFormatException nfe) {
            isNumeric = false;
            throw new InvalidArgumentException("Date is not a number(Long)");
        }
        if(isNumeric){
        List<FlightInstance> fiList = f.getFlightWithDatesAndDepature(new Date(parsedDate1),new Date(parsedDate2),from);
        JsonArray flightList = new JsonArray();
        for (FlightInstance fl : fiList) {
            JsonObject flight = new JsonObject();
            flight.addProperty("airline", fl.getAirline());
            flight.addProperty("price", fl.getPrice());
            flight.addProperty("flightId", fl.getFlightID());
            flight.addProperty("takeOffDate", fl.getDate().getTime());
            flight.addProperty("landingDate", fl.getDate().getTime());
            flight.addProperty("depature", fl.getDepature().getCode());
            flight.addProperty("destination", fl.getArrival().getCode());
            flight.addProperty("seats", fl.getPlane().getTotalSeats().size());
            flight.addProperty("available seats", fl.getFreeSeats().size());
            flight.addProperty("bookingCode", Boolean.TRUE);
            flightList.add(flight);
        }
        return gson.toJson(flightList);
        }
        return null;
    }
    
    @GET
    @Produces("application/json")
    @Path("/{reservationId}")
    public String getReservation(@PathParam("reservationId") String reservationId) throws ParseException, ReservationNotFoundException{
         Reservation r = f.getReservation(Integer.parseInt(reservationId));
 
          JsonObject json = new JsonObject();
          json.addProperty("reservationID", r.getId());
          json.addProperty("flightID", r.getFlightInstance().getFlightID());
          JsonArray ja = new JsonArray();
          for(Seat seat:r.getSeatList()){
              JsonObject jo = new JsonObject();
              jo.addProperty("firstName",seat.getCustomer().getfName());
              jo.addProperty("lastName", seat.getCustomer().getlName());
              jo.addProperty("city", seat.getCustomer().getCity());
              jo.addProperty("country", seat.getCustomer().getCountry());
              jo.addProperty("street", seat.getCustomer().getStreet());
              ja.add(jo);
          }
          json.add("Passengers",ja);
          
        return gson.toJson(json);
    }
    
    @POST
    @Consumes("application/json")
    @Path("/{flightID}")
    public Reservation createReservation(String content,@PathParam("flightID")String flightID) throws SoldOutException {
        JsonObject res = new JsonParser().parse(content).getAsJsonObject();
        ArrayList<Customer> clist = new ArrayList();
        JsonArray aList = res.get("Passengers").getAsJsonArray();
         for (int i = 0; i < aList.size(); i++) {
             JsonObject cust = aList.get(i).getAsJsonObject();
             Customer c = new Customer(cust.get("firstName").getAsString(), cust.get("lastName").getAsString(), cust.get("street").getAsString(), cust.get("country").getAsString(), cust.get("city").getAsString());
             clist.add(c);
         }
        System.out.println(clist.size());
        return f.createReservation(clist, flightID);
    }
    
    @DELETE
    @Produces("application/json")
    @Path("/{reservationId}")
    public String deleteReservation(@PathParam("reservationId")String reservationId) throws ReservationNotFoundException{
        Reservation r = f.deleteReservation(Integer.parseInt(reservationId));
        JsonObject json = new JsonObject();
        json.addProperty("reservationID",r.getId());
        json.addProperty("flightID",r.getFlightInstance().getFlightID());
        JsonArray ja = new JsonArray();
        for(Seat seat:r.getSeatList()){
            JsonObject jo = new JsonObject();
            jo.addProperty("firstName",seat.getCustomer().getfName());
            jo.addProperty("lastName",r.getCustomer().getlName());
            jo.addProperty("city",r.getCustomer().getCity());
            jo.addProperty("country",r.getCustomer().getCountry());
            jo.addProperty("street",r.getCustomer().getStreet());
            ja.add(jo);
        }
        json.add("Passengers",ja);
        json.addProperty("totalPrice",(r.getFlightInstance().getPrice())*r.getSeatList().size());
        return gson.toJson(json);
    }
    
}
