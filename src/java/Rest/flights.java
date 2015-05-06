/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Rest;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import entity.FlightInstance;
import facade.Facade;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
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
        gson = new Gson();
    }

    /**
     * Retrieves representation of an instance of Rest.flights
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("application/json")
    @Path("{from}/{to}")
    public String getFlightsTOFromDate(@PathParam("from")String from,@PathParam("to")String to) {
        System.out.println("from= "+from+" to= "+to+" date= ");
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        List<FlightInstance> fiList = f.getFlightWithFromToDate(from, to, new Date());
            JsonArray flightList = new JsonArray();
                for (FlightInstance fl : fiList) {
                    JsonObject flight = new JsonObject();
                    flight.addProperty("airport", fl.getAirline());
                    flight.addProperty("price", fl.getPrice());
                    flight.addProperty("flightId", fl.getFlightID());
                    flight.addProperty("takeOffDate", df.format(fl.getDate()));
                    flight.addProperty("landingDate", df.format(fl.getDate()));
                    flight.addProperty("depature", fl.getDepature().getCode());
                    flight.addProperty("desttination", fl.getArrival().getCode());
                    flight.addProperty("seats", fl.getPlane().getTotalSeats().size());
                    flight.addProperty("available seats", fl.getFreeSeats().size());
                    flight.addProperty("bookingCode", Boolean.FALSE);
                    flightList.add(flight);
                }
              return gson.toJson(flightList);
    }

    /**
     * PUT method for updating or creating an instance of flights
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/xml")
    public void putXml(String content) {
    }
}
