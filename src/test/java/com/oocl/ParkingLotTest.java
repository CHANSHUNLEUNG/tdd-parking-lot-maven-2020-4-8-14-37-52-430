package com.oocl;

import com.oocl.entity.*;
import com.oocl.exception.UnrecognizedParkingTicket;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;
import org.junit.rules.ExpectedException;

//    AC1: The parking boy can park a car into the parking lot and returns a parking ticket.
//    The customer can give the parking ticket back to the parking boy to fetch the car.

//    AC2: The parking boy can park multiple cars into the parking lot. And can fetch right car using correspond ticket.

//    AC3: If the customer gives a wrong ticket (the parking boy did not provide the ticket) or does not give a ticket.
//    Then no car should be fetched.

//    AC4: If the customer gives a ticket that has already been used. Then no car should be fetched.

//    AC5: The parking lot has a capacity (the default capacity of a parking lot is 10).
//    If there is no position, then the user cannot park the car into it. Thus (s)he will not get any ticket.
//    There are some cases which are not a requirement but may happen technically
//    Passing a parked car to a parking boy.
//    Passing a?null?car to a parking boy.


// ------------------- done --------------------
//    As a customer, I would like to get some response message from the parking boy when I cannot fetch the car.
//    So that I can know what happens.

//    AC1: When the customer gives a wrong ticket (the parking boy does not provide the ticket / the ticket has been used).
//    Then no car should be fetched. If I query the error message, I can get an "Unrecognized parking ticket.".

//    AC2: When the customer does not provide a ticket when fetching a car.
//    The error message should be "Please provide your parking ticket."

//    AC3: When the parking boy attempt to park a car into a parking lot without a position.
//    The error message should be "Not enough position."

//    As a parking lots service manager, I would like to have a parking boy parking cars to multiple parking lots.
//    So that I can provide more parking positions.

//    AC1. The parking boy is not that clever, and he will always park cars sequentially
//    (suppose that there are two parking lots managed by the parking boy.
//    The parking boy will park cars to the second parking lot when the first parking lot is full).

//    AC2: All the requirement in?Story 1?and?Story 2?MUST?be satisfied.

public class ParkingLotTest {
    private static final String Car_NUMBER = "my-example-car-number";
    private static final String PARKING_LOT_NAME = "my-example-parking-name";

    private ParkingLot parkingLot;
    private ParkingBoy parkingBoy;
    private Car firstCar;
    private Car secondCar;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Before
    public void setUp() throws Exception {
        parkingLot = new ParkingLot(PARKING_LOT_NAME);
        parkingBoy = new ParkingBoy(parkingLot);
        firstCar = new Car(Car_NUMBER);
        secondCar = new Car(Car_NUMBER);
    }


    @Test
    public void should_return_a_ticket_when_parking_boy_park_a_car_successfully() {
        assertEquals(Ticket.class, parkingBoy.parkCar(firstCar).getClass());
        ;
    }

    @Test
    public void should_return_a_ticket_with_correct_car_number_when_parking_boy_park_a_car_successfully() {
        assertEquals(Car_NUMBER, parkingBoy.parkCar(firstCar).getCar().getCarNumber());
        ;
    }

    @Test
    public void should_parking_lot_access_multiply_car() {
        parkingBoy.parkCar(firstCar);
        parkingBoy.parkCar(secondCar);
        assertEquals(parkingLot.getTicketList().size(), 2);
    }

    @Test
    public void should_fetch_the_right_car_when_there_are_two_cars_in_a_parking_lot() throws UnrecognizedParkingTicket {
        Ticket firstTicket = parkingBoy.parkCar(firstCar);
        Ticket secondTicket = parkingBoy.parkCar(secondCar);
        assertEquals(firstCar, parkingBoy.fetchCar(firstTicket));
    }

    @Test
    public void should_get_the_car_and_remove_ticket_when_fetch_car() throws UnrecognizedParkingTicket {
        Ticket firstTicket = parkingBoy.parkCar(firstCar);
        assertEquals(1, parkingLot.getTicketList().size());
        assertEquals(1, parkingLot.getCarList().size());

        Ticket secondTicket = parkingBoy.parkCar(secondCar);
        assertEquals(2, parkingLot.getTicketList().size());
        assertEquals(2, parkingLot.getCarList().size());


        assertEquals(firstCar, parkingBoy.fetchCar(firstTicket));

        assertEquals(1, parkingLot.getTicketList().size());
        assertEquals(1, parkingLot.getCarList().size());

    }

//    @Test
//    public void should_return_null_when_customer_gives_the_wrong_ticket() throws UnrecognizedParkingTicket {
//        parkingBoy.parkCar(firstCar);
//        assertEquals(null, parkingBoy.fetchCar());
//        assertEquals(null, parkingBoy.fetchCar(new Ticket(parkingLot, secondCar)));
//    }
//
//    @Test
//    public void should_return_null_when_give_the_same_ticket_twice() throws UnrecognizedParkingTicket {
//        Ticket firstTicket = parkingBoy.parkCar(firstCar);
//        parkingBoy.fetchCar(firstTicket);
//        assertEquals(null, parkingBoy.fetchCar(firstTicket));
//    }

    @Test
    public void should_return_no_ticket_when_parking_lot_full() {
        for (int index = 0; index < ParkingLot.MAX_POSITION; index++) {
            parkingBoy.parkCar(new Car("testcar"));
        }
        assertEquals(null, parkingBoy.parkCar(firstCar));
    }

    @Test
    public void should_throw_unrecognizedparkingticket_exception_with_correct_message_when_customer_give_wrong_ticket() throws UnrecognizedParkingTicket {
        expectedException.expect(UnrecognizedParkingTicket.class);
        expectedException.expectMessage(ParkingBoy.UNRECOGNIZED_PARKING_TICKET_ERROR);
        Ticket firstTicket = parkingBoy.parkCar(firstCar);
        parkingBoy.fetchCar(firstTicket);
        assertEquals(null, parkingBoy.fetchCar(firstTicket));

    }
}
