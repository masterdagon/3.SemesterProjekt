/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest2;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import entity.FlightInstance;
import entity.Reservation;
import entity.Seat;
import facade.Facade;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;

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
    @Path("/")
    public String test(){
        return gson.toJson("{/'test/':/'test/'}");
    }
    
    @GET
    @Produces("application/json")
    @Path("{date1}/{date2}")
    public String getFlightsFromDates(@PathParam("date1") Date date1,@PathParam("date2") Date date2) {;
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        List<FlightInstance> fiList = f.getFlightWithDates(date1, date2);
        JsonArray flightList = new JsonArray();
        for (FlightInstance fl : fiList) {
            JsonObject flight = new JsonObject();
            flight.addProperty("airport", fl.getAirline());
            flight.addProperty("price", fl.getPrice());
            flight.addProperty("flightId", fl.getFlightID());
            flight.addProperty("takeOffDate", df.format(fl.getDate()));
            flight.addProperty("landingDate", df.format(fl.getDate()));
            flight.addProperty("depature", fl.getDepature().getCode());
            flight.addProperty("destination", fl.getArrival().getCode());
            flight.addProperty("seats", fl.getPlane().getTotalSeats().size());
            flight.addProperty("available seats", fl.getFreeSeats().size());
            flight.addProperty("bookingCode", Boolean.FALSE);
            flightList.add(flight);
        }
        return gson.toJson(flightList);

    }
    
    @GET
    @Produces("application/json")
    @Path("{from}/{to}/{Date}")
    public String getFlightsTOFromDate(@PathParam("from") String from, @PathParam("to") String to,@PathParam("date") Date date) {
        System.out.println("from= " + from + " to= " + to + " date= ");
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        List<FlightInstance> fiList = f.getFlightWithFromToDate(from, to, date);
        JsonArray flightList = new JsonArray();
        for (FlightInstance fl : fiList) {
            JsonObject flight = new JsonObject();
            flight.addProperty("airport", fl.getAirline());
            flight.addProperty("price", fl.getPrice());
            flight.addProperty("flightId", fl.getFlightID());
            flight.addProperty("takeOffDate", df.format(fl.getDate()));
            flight.addProperty("landingDate", df.format(fl.getDate()));
            flight.addProperty("depature", fl.getDepature().getCode());
            flight.addProperty("destination", fl.getArrival().getCode());
            flight.addProperty("seats", fl.getPlane().getTotalSeats().size());
            flight.addProperty("available seats", fl.getFreeSeats().size());
            flight.addProperty("bookingCode", Boolean.FALSE);
            flightList.add(flight);
        }
        return gson.toJson(flightList);

    }
    
    @GET
    @Produces("application/json")
    @Path("/{reservationId}")
    public String getReservation(@PathParam("reservationId") String reservationId) throws ParseException{
         Reservation r = f.getReservation(Integer.parseInt(reservationId));
 
          JsonObject json = new JsonObject();
          json.addProperty("reservationID", r.getId());
          json.addProperty("flightID", r.getFlightInstance().getFlightID());
          JsonArray ja = new JsonArray();
          for(Seat seat:r.getSeatList()){
              JsonObject jo = new JsonObject();
              jo.addProperty("firstName",seat.getCustomer().getfName());
              jo.addProperty("lastName", seat.getCustomer().getlName());
              jo.addProperty("city", seat.getCustomer().getCityInfo().getCity());
              jo.addProperty("country", seat.getCustomer().getCountry());
              jo.addProperty("street", seat.getCustomer().getStreet());
              ja.add(jo);
          }
          json.add("Passengers",ja);
        
          
        return gson.toJson(json);
    }
    
    @DELETE
    @Produces("application/json")
    @Path("/{reservationId}")
    public String deleteReservation(@PathParam("reservationId")String reservationId){
        Reservation r = f.deleteReservation(Integer.parseInt(reservationId));
        JsonObject json = new JsonObject();
        json.addProperty("reservationID",r.getId());
        json.addProperty("flightID",r.getFlightInstance().getFlightID());
        JsonArray ja = new JsonArray();
        for(Seat seat:r.getSeatList()){
            JsonObject jo = new JsonObject();
            jo.addProperty("firstName",seat.getCustomer().getfName());
            jo.addProperty("lastName",r.getCustomer().getlName());
            jo.addProperty("city",r.getCustomer().getCityInfo().getCity());
            jo.addProperty("country",r.getCustomer().getCountry());
            jo.addProperty("street",r.getCustomer().getStreet());
            ja.add(jo);
        }
        json.add("Passengers",ja);
        json.addProperty("totalPrice",(r.getFlightInstance().getPrice())*r.getSeatList().size());
        return gson.toJson(json);
    }
    
    
}

//@GET
//    @Produces("application/json")
//    @Path("/{startAirport}/{:endAirport}/{date}")
//    public String getAvailibleFlights_Start_End_Date(@PathParam("startAirport") String startAirport,@PathParam("endAirport") String endAirport,@PathParam("date") String date) throws ParseException{
//        DateFormat df = DateFormat.getInstance();
//        Date d = df.parse(date);
//        List<FlightInstance> list = f.getFlightWithFromToDate(startAirport, endAirport, d);
//        JsonArray ja = new JsonArray();
//        for(FlightInstance flight:list){
//          JsonObject json = new JsonObject();
//          json.addProperty("airline", flight.getAirline());
//          json.addProperty("price", flight.getPrice());
//          json.addProperty("flightId",flight.getFlightID());
//          json.addProperty("takeOffDate",df.format(flight.getDate()));
//          json.addProperty("landingDate",df.format(flight.getDate()));
//          json.addProperty("depature",flight.getDepature().getCode());
//          json.addProperty("destination",flight.getArrival().getCode());
//          json.addProperty("seats",String.valueOf(flight.getPlane().getTotalSeats()));
//          json.addProperty("available seats",String.valueOf(flight.getFreeSeats()));
//          json.addProperty("bookingCode",flight.getBookingCode());
//          ja.add(json);
//          
//        }
//        
//        return gson.toJson(ja);
//    }
