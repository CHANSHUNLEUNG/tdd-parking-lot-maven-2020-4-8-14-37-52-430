package com.oocl;

import com.oocl.entity.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

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
public class ParkingLotTest {
    private ParkingLot parkingLot;
    private ParkingBoy parkingBoy;
    private static final String Car_NUMBER = "my-example-car-number";
    private static final String PARKING_LOT_NAME = "my-example-parking-name";
    private Car firstCar;
    private Car secondCar;

    @Before
    public void setUp() throws Exception {
        parkingLot = new ParkingLot(PARKING_LOT_NAME);
        parkingBoy = new ParkingBoy(parkingLot);
        firstCar = new Car(Car_NUMBER);
        secondCar = new Car(Car_NUMBER);
    }

    @Test
    public void should_return_a_ticket_when_parking_boy_park_a_car_successfully() {
        assertEquals(Ticket.class, parkingBoy.parkCar(parkingLot, firstCar).getClass());
        ;
    }

    @Test
    public void should_return_a_ticket_with_correct_car_number_when_parking_boy_park_a_car_successfully() {
        assertEquals(Car_NUMBER, parkingBoy.parkCar(parkingLot, firstCar).getCar().getCarNumber());
        ;
    }

    @Test
    public void should_parking_lot_access_multiply_car() {
        parkingBoy.parkCar(parkingLot, firstCar);
        parkingBoy.parkCar(parkingLot, secondCar);
        assertEquals(parkingLot.getTicketList().size(), 2);
    }

    @Test
    public void should_fetch_the_right_car_when_there_are_two_cars_in_a_parking_lot() {
        Ticket firstTicket = parkingBoy.parkCar(parkingLot, firstCar);
        Ticket secondTicket = parkingBoy.parkCar(parkingLot, secondCar);
        assertEquals(firstCar, parkingBoy.fetchCar(firstTicket));
    }

    @Test
    public void should_get_the_car_and_remove_ticket_when_fetch_car() {
        Ticket firstTicket = parkingBoy.parkCar(parkingLot, firstCar);
        assertEquals(1, parkingLot.getTicketList().size());
        assertEquals(1, parkingLot.getCarList().size());

        Ticket secondTicket = parkingBoy.parkCar(parkingLot, secondCar);
        assertEquals(2, parkingLot.getTicketList().size());
        assertEquals(2, parkingLot.getCarList().size());


        assertEquals(firstCar, parkingBoy.fetchCar(firstTicket));

        assertEquals(1, parkingLot.getTicketList().size());
        assertEquals(1, parkingLot.getCarList().size());

    }

    @Test
    public void should_return_null_when_customer_gives_the_wrong_ticket() {
        parkingBoy.parkCar(parkingLot, firstCar);
        assertEquals(null, parkingBoy.fetchCar());
        assertEquals(null, parkingBoy.fetchCar(new Ticket(parkingLot, secondCar)));
    }

    @Test
    public void should_return_null_when_give_the_same_ticket_twice() {
        Ticket firstTicket = parkingBoy.parkCar(parkingLot, firstCar);
        parkingBoy.fetchCar(firstTicket);
        assertEquals(null, parkingBoy.fetchCar(firstTicket));
    }

    @Test
    public void should_return_no_ticket_when_parking_lot_full() {
        for (int index = 0; index < ParkingLot.MAX_POSITION; index++) {
            parkingBoy.parkCar(parkingLot, new Car("testcar"));
        }
        assertEquals(null,parkingBoy.parkCar(parkingLot,firstCar));
    }
}
