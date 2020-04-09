package com.oocl;

import com.oocl.entity.Car;
import com.oocl.entity.ParkingBoy;
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
public class ParkingLot {
    private ParkingBoy parkingBoy;
    private static final String Car_NUMBER = "my-example-car-number";
    private Car exampleCar = new Car(Car_NUMBER);
    private Ticket exampleTicket = new Ticket(exampleCar);

    @Before
    public void setUp() throws Exception {
        parkingBoy = new ParkingBoy();
    }

    @Test
    public void should_return_a_ticket_when_parking_boy_park_a_car_successfully() {
        assertEquals(exampleTicket.getClass(),parkingBoy.parkCar(exampleCar).getClass());;
    }
    @Test
    public void should_return_a_ticket_with_correct_car_number_when_parking_boy_park_a_car_successfully() {
        assertEquals(exampleTicket.getCarNumber(),parkingBoy.parkCar(exampleCar).getCarNumber());;
    }


}
