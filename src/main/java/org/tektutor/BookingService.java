package org.tektutor;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.util.ArrayList;

public class BookingService {

    private ArrayList<Booking> listOfBookings;
    private Connection connection;
    private String sqlQuery;
    private int status;

    public BookingService() {
        listOfBookings = new ArrayList<Booking>();
        status = 0;
    }

    public ArrayList<Booking> getAllBookings() {
        connection = new DatabaseConnectionManager().getConnection();
        sqlQuery = "select * from Booking";

        Booking booking = null;

        try {
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            ResultSet rs = statement.executeQuery();

            while ( rs.next() ) {
                booking = new Booking();

                booking.setBookingId(  rs.getInt(1) );
                booking.setGuestName( rs.getString(2) );
                booking.setRoomNo( rs.getString(3) );
                booking.setFromDate( rs.getString(4) );
                booking.setToDate( rs.getString(5) );

                listOfBookings.add( booking );

            }
        }
        catch( Exception e ) {
            e.printStackTrace();
        }
        return listOfBookings;
    }

    public Booking getBookingById(int id) {
        connection = new DatabaseConnectionManager().getConnection();
        sqlQuery = "select * from Booking where id=" + id;

        Booking booking = null;

        try {
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            ResultSet rs = statement.executeQuery();

            if ( rs.next() ) {
                booking = new Booking();

                booking.setBookingId(  rs.getInt(1) );
                booking.setGuestName( rs.getString(2) );
                booking.setRoomNo( rs.getString(3) );
                booking.setFromDate( rs.getString(4) );
                booking.setToDate( rs.getString(5) );
            }
        }
        catch( Exception e ) {
            e.printStackTrace();
        }
        return booking;
    }

    public int deleteBookingById(int id) {
        connection = new DatabaseConnectionManager().getConnection();
        sqlQuery = "delete from Booking where id=" + id;

        try {
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            status = statement.executeUpdate();
        }
        catch( Exception e ) {
            e.printStackTrace();
        }
        return status;
    }

    public int deleteAllBookings() {
        connection = new DatabaseConnectionManager().getConnection();
        sqlQuery = "delete from Booking";

        try {
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            status = statement.executeUpdate();
        }
        catch( Exception e ) {
            e.printStackTrace();
        }
        return status;
    }    

    public int insertBooking( Booking booking ) {

        connection = new DatabaseConnectionManager().getConnection();
        
        sqlQuery = "insert into Booking values (" + booking.getId() +
                   ",'" + booking.getGuestName() + "'" +
                   ",'" + booking.getRoomNo() + "'" +
                   ",'" + booking.getFromDate() + "'" +
                   ",'" + booking.getToDate() + "')" ;

        try {
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            status = statement.executeUpdate();
            connection.commit();
        }
        catch( Exception e ) {
            e.printStackTrace();
        }
        return status;
    }    

    public int updateBooking( Booking booking ) {

        connection = new DatabaseConnectionManager().getConnection();
        
        sqlQuery = "update Booking " +
                   " set guest_name='" + booking.getGuestName() + "'" +
                   ", room_no='" + booking.getRoomNo() + "'" +
                   ", from_date='" + booking.getFromDate() + "'" +
                   ", to_date='" + booking.getToDate() + "'" +
                   " where id=" + booking.getId(); 

        System.out.println ( "##############" + sqlQuery );
        try {
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            status = statement.executeUpdate();
        }
        catch( Exception e ) {       

            e.printStackTrace();
        }
        return status;
    }        
}