package com.oocl;

import com.oocl.entity.Car;
import com.oocl.entity.ParkingBoy;
import com.oocl.entity.ParkingLot;
import com.oocl.entity.Ticket;
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
    private Ticket firstTicket;
    private Ticket secondTicket;

    @Before
    public void setUp() throws Exception {
        parkingBoy = new ParkingBoy();
        parkingLot = new ParkingLot(PARKING_LOT_NAME);
        firstCar = new Car(Car_NUMBER);
        secondCar = new Car(Car_NUMBER);
        firstTicket = new Ticket(parkingLot, firstCar);
        secondTicket = new Ticket(parkingLot, firstCar);
    }

    @Test
    public void should_return_a_ticket_when_parking_boy_park_a_car_successfully() {
        assertEquals(firstTicket.getClass(),parkingBoy.parkCar(parkingLot, firstCar).getClass());;
    }
    @Test
    public void should_return_a_ticket_with_correct_car_number_when_parking_boy_park_a_car_successfully() {
        assertEquals(firstTicket.getCar(),parkingBoy.parkCar(parkingLot, firstCar).getCar());;
    }

    @Test
    public void should_parking_lot_access_multiply_car() {
        parkingBoy.parkCar(parkingLot,firstCar);
        parkingBoy.parkCar(parkingLot,secondCar);
        assertEquals(parkingLot.getTicketList().size(),2);
    }

    @Test
    public void should_fetch_the_right_car_when_there_are_two_cars_in_a_parking_lot() {
        parkingBoy.parkCar(parkingLot,firstCar);
        parkingBoy.parkCar(parkingLot,secondCar);
        assertEquals(firstCar,parkingBoy.fetchCar(firstTicket));
    }
}
