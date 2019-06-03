package org.tektutor;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Booking {

    @JsonProperty
    public int id;

    @JsonProperty
    public String guest_name;

    @JsonProperty
    public  String room_no;

    @JsonProperty
    public String from_date;

    @JsonProperty
    public String to_date;

    @JsonCreator
    public Booking(
        @JsonProperty("id") int id,
        @JsonProperty("guest_name") String guest_name,
        @JsonProperty("room_no") String room_no,
        @JsonProperty("from_date") String from_date,
        @JsonProperty("to_date") String to_date
    ) {
        this.id = id;
        this.guest_name = guest_name;
        this.room_no = room_no;
        this.from_date = from_date;
        this.to_date = to_date;
    }

    public Booking() {

    }

    public void setBookingId( int id ) {
        this.id = id;
    }

    public void setGuestName ( String guestName ) {
        this.guest_name = guestName;
    }

    public void setRoomNo(String roomNo) {
        this.room_no = roomNo;
    }

    public void setFromDate(String fromDate) {
        this.from_date = fromDate;
    }

    public void setToDate(String toDate) {
        this.to_date = toDate;
    }

    public int getId() {
        return id;
    }

    public String getGuestName() {
        return guest_name;
    }

    public String getRoomNo() {
        return room_no;
    }

    public String getFromDate() {
        return from_date;
    }

    public String getToDate() {
        return to_date;
    }


}