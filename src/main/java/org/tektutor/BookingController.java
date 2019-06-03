package org.tektutor;

import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.POST;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;

import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jettison.json.JSONObject;

@Path("bookings")
public class BookingController {

    private BookingService bookingService = new BookingService();

    @Produces(MediaType.APPLICATION_JSON)
    @GET
    @Path("all")
    public ArrayList<Booking> getAllBookings() {
        return bookingService.getAllBookings();
    }

    @Produces(MediaType.APPLICATION_JSON)
    @GET
    @Path("{id}")
    public Booking getBookingById(@PathParam("id") int id) {
        return bookingService.getBookingById(id);
    }

    @Produces(MediaType.APPLICATION_JSON)
    @DELETE
    @Path("{id}")
    public int deleteBookingById(@PathParam("id") int id) {
        return bookingService.deleteBookingById(id);
    }

    @Produces(MediaType.APPLICATION_JSON)
    @DELETE
    public int deleteAllBooking() {
        return bookingService.deleteAllBookings();
    }

    @Consumes(MediaType.APPLICATION_JSON)
    @POST
    public Response bookAHotelRoom(Booking booking) {
        String result = "Booking created : " + booking;
        bookingService.insertBooking(booking);
        return Response.status(201).entity(result).build();
    }      
    
    @Consumes(MediaType.APPLICATION_JSON)
    @PUT
    public Response updateBookingDetails(Booking booking) {
        String result = "Booking updated : " + booking;
        bookingService.updateBooking(booking);
        return Response.status(201).entity(result).build();
    }        
}