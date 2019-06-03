package org.tektutor;

import static org.junit.Assert.*;

import org.json.simple.JSONObject;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Steps {

    private RequestSpecification request;
    private Response response;
    private int actualResponseCode, expectedResponseCode;
    private String restEndpointURL;
    private Booking booking;

    @Given("the rest api is hosted at {string}")
    public void the_rest_api_is_hosted_at(String url) {
        RestAssured.baseURI = url;
        restEndpointURL = url;
    
        request = RestAssured.given();
        booking = new Booking();
    }

    @Given("the booking id is {int}")
    public void the_booking_id_is(Integer bookingId) {
        booking.setBookingId(bookingId);
    }

    @Given("the guest name is {string}")
    public void the_guest_name_is(String guestName) {
        booking.setGuestName(guestName);
    }

    @Given("the room booked is {string}")
    public void the_room_booked_is(String roomNo) {
        booking.setRoomNo(roomNo);;
    }

    @Given("the hotel checkin date is {string}")
    public void the_hotel_checkin_date_is(String fromDate) {
        booking.setFromDate(fromDate);
    }

    @Given("the hotel checkout date is {string}")
    public void the_hotel_checkout_date_is(String toDate) {
        booking.setToDate(toDate);
    }

    @When("I invoke the hotel booking REST API with GET method")
    public void i_invoke_the_hotel_booking_REST_API_with_GET_method() {
        request.header("Content-Type", "application/json");
        response = request.get();
    }

    @When("I invoke the hotel booking REST API with DELETE method")
    public void i_invoke_the_hotel_booking_REST_API_with_DELETE_method() {
        request.header("Content-Type", "application/json");
        response = request.delete();
    }

    @When("I invoke the hotel booking REST API with POST method")
    public void i_invoke_the_hotel_booking_REST_API_with_POST_method() {
        JSONObject requestParams = new JSONObject();

        requestParams.put("id", booking.getId() );
        requestParams.put("guest_name", booking.getGuestName());
        requestParams.put("room_no", booking.getRoomNo());
        requestParams.put("from_date", booking.getFromDate());
        requestParams.put("to_date", booking.getToDate());

        request.header( "Content-Type", "application/json" );
        request.body(requestParams.toJSONString() );

		response = request.post();
    }

    @When("I invoke the hotel booking REST API with PUT method")
    public void i_invoke_the_hotel_booking_REST_API_with_PUT_method() {
        JSONObject requestParams = new JSONObject();

        requestParams.put("id", booking.getId() );
        requestParams.put("guest_name", booking.getGuestName());
        requestParams.put("room_no", booking.getRoomNo());
        requestParams.put("from_date", booking.getFromDate());
        requestParams.put("to_date", booking.getToDate());

        request.header( "Content-Type", "application/json" );
        request.body(requestParams.toJSONString() );

		response = request.put();
    }

    @Then("I expect response code {string}")
    public void check_the_response_code(String strExpectedResponseCode) {
        actualResponseCode = response.getStatusCode();
        expectedResponseCode = Integer.parseInt(strExpectedResponseCode);
    
        assertEquals(expectedResponseCode, actualResponseCode);
    }

    @Then("I expect {string} booking records as response")
    public void check_if_the_record_count_matches(String strExpectedRecordCount) {
        Booking[] listOfBookings = response.as(Booking[].class);

        int actualRecordCount = listOfBookings.length;
        int expectedRecordCount = Integer.parseInt(strExpectedRecordCount);

        assertEquals(expectedRecordCount, actualRecordCount);
    }

    @Then("I expect {string} booking record as response")
    public void check_if_the_record_count_is_one(String strExpectedRecordCount) {
        Booking booking = response.as(Booking.class);
        assertNotNull(booking);
    }

    @Then("I expect {string} booking record with id {string} as response")
    public void i_expect_booking_record_with_id_as_response(String string, String string2) {
        request.header("Content-Type", "application/json");
        response = request.get();
    
        actualResponseCode = response.getStatusCode();
        expectedResponseCode = 204;
    
        assertEquals(expectedResponseCode, actualResponseCode);
    }

    @Then("I expect {string} booking record inserted")
    public void i_expect_booking_record_inserted(String strExpectedRecordCount) {
        RestAssured.baseURI = restEndpointURL + "/all";
        request = RestAssured.given();
        response = request.get();

        Booking[] listOfBookings = response.as(Booking[].class);
        int actualRecordCount = listOfBookings.length;
        int expectedRecordCount = Integer.parseInt(strExpectedRecordCount);

        assertEquals(expectedRecordCount, actualRecordCount);
    }

    @Then("I expect booking record updated")
    public void i_expect_booking_record_updated() {
        RestAssured.baseURI = restEndpointURL + "/all";
        request = RestAssured.given();
        response = request.get();

        Booking[] listOfBookings = response.as(Booking[].class);

        assertEquals ( 1, listOfBookings[0].getId() );
        assertEquals ( 2, listOfBookings[1].getId() );
        assertEquals ( 3, listOfBookings[2].getId() );
        assertEquals ( 4, listOfBookings[3].getId() );
        assertEquals ( 5, listOfBookings[4].getId() );

        assertEquals ( "B-103", listOfBookings[0].getRoomNo() );
        assertEquals ( "C-103", listOfBookings[1].getRoomNo() );
        assertEquals ( "D-103", listOfBookings[2].getRoomNo() );
        assertEquals ( "E-103", listOfBookings[3].getRoomNo() );
        assertEquals ( "F-103", listOfBookings[4].getRoomNo() );
    }    

}