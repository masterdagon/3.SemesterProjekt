/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Rest;

import facade.Facade;
import java.util.Date;
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

    /**
     * Creates a new instance of flights
     */
    public flights() {
        this.f = new Facade();
    }

    /**
     * Retrieves representation of an instance of Rest.flights
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("application/json")
    @Path("/{from}/{to}/{date}")
    public String getFlightsTOFromDate(@PathParam("from")String from,@PathParam("to")String to,@PathParam("date")Date date) {
//        JsonObject flight = new JsonObject();
//            flight.addProperty("airLine", c.getId());
//            company.addProperty("name", c.getName());
//            company.addProperty("description", c.getDescription());
//            company.addProperty("cvr", c.getCvr());
//            company.addProperty("email", c.getEmail());
//            company.addProperty("street", c.getAddress().getStreet());
//            company.addProperty("additionalinfo", c.getAddress().getAdditionalinfo());
//            company.addProperty("zipcode", c.getAddress().getCityInfo().getZipCode());
//            company.addProperty("city", c.getAddress().getCityInfo().getCity());
//            
//            JsonArray phones = new JsonArray();
//            List<Phone> phs = c.getPhones();
//            for (Phone ph : phs) {
//                JsonObject phone = new JsonObject();
//                phone.addProperty("number", ph.getNumber());
//                phone.addProperty("description", ph.getDescription());
//                phones.add(phone); 
//            }
//            company.add("phones", phones);
//            company.addProperty("numemployees", c.getNumEmployees());
//            company.addProperty("marketvalue", c.getMarketValue());
        throw new UnsupportedOperationException();
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
