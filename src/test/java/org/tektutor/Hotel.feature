Feature: Develop a Hotel Booking REST API

    @InsertBookings
    Scenario Outline: Should be able to book a hotel room booking
      Given the rest api is hosted at "http://localhost:8080/tektutor-hotelcrud-rest/rest/bookings"
      And the booking id is <id>
      And the guest name is <guestName>
      And the room booked is <roomNo>
      And the hotel checkin date is <fromDate>
      And the hotel checkout date is <toDate>
      When I invoke the hotel booking REST API with POST method
      Then I expect response code "201"
      And I expect <recordCount> booking record inserted

    Examples:
        | id | guestName  | roomNo  |  fromDate    |  toDate      | recordCount  |
        | 1  | "Jegan"    | "B-103" | "01-01-2019" | "03-01-2019" | "1"          |
        | 2  | "Nitesh"   | "B-103" | "04-01-2019" | "04-01-2019" | "2"          |
        | 3  | "Sriram"   | "B-103" | "05-01-2019" | "05-01-2019" | "3"          |
        | 4  | "Rishi"    | "B-103" | "05-01-2019" | "05-01-2019" | "4"          |
        | 5  | "ArunRaaj" | "B-103" | "05-01-2019" | "05-01-2019" | "5"          |

    @UpdateBookings
    Scenario Outline: Should be able to update hotel room booking
      Given the rest api is hosted at "http://localhost:8080/tektutor-hotelcrud-rest/rest/bookings"
      And the booking id is <id>
      And the guest name is <guestName>
      And the room booked is <roomNo>
      And the hotel checkin date is <fromDate>
      And the hotel checkout date is <toDate>
      When I invoke the hotel booking REST API with PUT method
      Then I expect response code "201"
      And I expect booking record updated

    Examples:
        | id | guestName  | roomNo  |  fromDate    |  toDate      |
        | 1  | "Jegan"    | "B-103" | "01-01-2019" | "03-01-2019" |
        | 2  | "Nitesh"   | "C-103" | "04-01-2019" | "04-01-2019" |
        | 3  | "Sriram"   | "D-103" | "05-01-2019" | "05-01-2019" |
        | 4  | "Rishi"    | "E-103" | "05-01-2019" | "05-01-2019" |
        | 5  | "ArunRaaj" | "F-103" | "05-01-2019" | "05-01-2019" |

    @GetAllBookings
    Scenario: Should be able to retrieve all hotel room booking done as on today
      Given the rest api is hosted at "http://localhost:8080/tektutor-hotelcrud-rest/rest/bookings/all"
      When I invoke the hotel booking REST API with GET method
      Then I expect response code "200"
      And I expect "5" booking records as response

    @GetBookingById
    Scenario: Should be able to retrieve a hotel room booking by id
      Given the rest api is hosted at "http://localhost:8080/tektutor-hotelcrud-rest/rest/bookings/2"
      When I invoke the hotel booking REST API with GET method
      Then I expect response code "200"
      And I expect "1" booking record as response

    @DeleteBookingById
    Scenario: Should be able to delete a hotel room booking by id
      Given the rest api is hosted at "http://localhost:8080/tektutor-hotelcrud-rest/rest/bookings/2"
      When I invoke the hotel booking REST API with DELETE method
      Then I expect response code "200"
      And I expect "0" booking record with id "2" as response

    @DeleteAllBookings
    Scenario: Should be able to delete all hotel room bookings
      Given the rest api is hosted at "http://localhost:8080/tektutor-hotelcrud-rest/rest/bookings"
      When I invoke the hotel booking REST API with DELETE method
      Then I expect response code "200"