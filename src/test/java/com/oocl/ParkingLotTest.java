package com.oocl;

import com.oocl.entity.*;
import com.oocl.exception.FullParkingTicket;
import com.oocl.exception.UnrecognizedParkingTicket;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

import org.junit.rules.ExpectedException;

public class ParkingLotTest {
    private static final String Car_NUMBER = "my-example-car-number";
    private static final String FIRST_PARKING_LOT_NAME = "FIRST_PARKING_LOT_NAME";
    private static final String SECOND_PARKING_LOT_NAME = "SECOND_PARKING_LOT_NAME";

    private ParkingLot firstParkingLot;
    private ParkingLot secondParkingLot;
    private ParkingBoy parkingBoy;
    private SmartParkingBoy smartParkingBoy;
    private SuperSmartParkingBoy superSmartParkingBoy;
    private Car firstCar;
    private Car secondCar;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Before
    public void setUp() {
        firstParkingLot = new ParkingLot(FIRST_PARKING_LOT_NAME);
        secondParkingLot = new ParkingLot(SECOND_PARKING_LOT_NAME);

        parkingBoy = new ParkingBoy(firstParkingLot, secondParkingLot);
        smartParkingBoy = new SmartParkingBoy(firstParkingLot, secondParkingLot);
        superSmartParkingBoy = new SuperSmartParkingBoy(firstParkingLot, secondParkingLot);

        firstCar = new Car(Car_NUMBER);
        secondCar = new Car(Car_NUMBER);
    }


    @Test
    public void should_return_a_ticket_when_parking_boy_park_a_car_successfully() {
        assertEquals(Ticket.class, parkingBoy.parkCar(firstCar).getClass());
    }

    @Test
    public void should_return_a_ticket_with_correct_car_number_when_parking_boy_park_a_car_successfully() {
        assertEquals(Car_NUMBER, parkingBoy.parkCar(firstCar).getCar().getCarNumber());
    }

    @Test
    public void should_parking_lot_access_multiply_car() {
        parkingBoy.parkCar(firstCar);
        parkingBoy.parkCar(secondCar);
        assertEquals(firstParkingLot.getTicketList().size(), 2);
    }

    @Test
    public void should_fetch_the_right_car_when_there_are_two_cars_in_a_parking_lot() {
        Ticket firstTicket = parkingBoy.parkCar(firstCar);
        assertEquals(firstCar, parkingBoy.fetchCar(firstTicket));
    }

    @Test
    public void should_get_the_car_and_remove_ticket_when_fetch_car() {
        Ticket firstTicket = parkingBoy.parkCar(firstCar);
        assertEquals(1, firstParkingLot.getTicketList().size());
        assertEquals(1, firstParkingLot.getCarList().size());

        Ticket secondTicket = parkingBoy.parkCar(secondCar);
        assertEquals(2, firstParkingLot.getTicketList().size());
        assertEquals(2, firstParkingLot.getCarList().size());


        assertEquals(firstCar, parkingBoy.fetchCar(firstTicket));

        assertEquals(1, firstParkingLot.getTicketList().size());
        assertEquals(1, firstParkingLot.getCarList().size());

    }

    @Test
    public void should_throw_exception_when_parking_lot_is_full() {
        expectedException.expect(FullParkingTicket.class);
        expectedException.expectMessage(FullParkingTicket.FULL_POSITION_ERROR);
        for (int index = 0; index < ParkingLot.MAX_POSITION * parkingBoy.getParkingLotList().size() + 1; index++) {
            parkingBoy.parkCar(new Car("testcar"));
        }
    }

    @Test
    public void should_throw_exception_when_customer_give_wrong_ticket()
            throws UnrecognizedParkingTicket, FullParkingTicket {
        expectedException.expect(UnrecognizedParkingTicket.class);
        expectedException.expectMessage(UnrecognizedParkingTicket.Wrong_TICKET_ERROR);
        assertEquals(null, parkingBoy.fetchCar(new Ticket(firstParkingLot, secondCar)));
    }

    @Test
    public void should_throw_exception_when_customer_give_no_ticket()
            throws UnrecognizedParkingTicket {
        expectedException.expect(UnrecognizedParkingTicket.class);
        expectedException.expectMessage(UnrecognizedParkingTicket.NO_TICKET_ERROR);
        parkingBoy.fetchCar();
    }

    @Test
    public void should_park_two_second_parking_lot_only_if_first_parking_lot_is_full() {
        for (int index = 0; index < ParkingLot.MAX_POSITION; index++) {
            parkingBoy.parkCar(new Car("first car"));
        }
        parkingBoy.parkCar(new Car("second car"));
        assertEquals("second car", parkingBoy.getParkingLotList().get(1).getCarList().get(0).getCarNumber());
    }

    @Test
    public void should_smart_parking_boy_park_correct_parking_lot() {
        for (int index = 0; index < 2; index++) {
            parkingBoy.parkCar(new Car("test car"));
        }
        smartParkingBoy.parkCar(new Car("test car"));
        assertEquals(1, smartParkingBoy.getParkingLotList().get(1).getCarList().size());
        smartParkingBoy.parkCar(new Car("test car"));
        assertEquals(2, smartParkingBoy.getParkingLotList().get(1).getCarList().size());
        smartParkingBoy.parkCar(new Car("test car"));
        assertEquals(3, smartParkingBoy.getParkingLotList().get(0).getCarList().size());
        smartParkingBoy.parkCar(new Car("test car"));
        assertEquals(3, smartParkingBoy.getParkingLotList().get(1).getCarList().size());
    }

    @Test
    public void should_super_smart_parking_boy_park_correct_parking_lot() {
        for (int index = 0; index < 2; index++) {
            parkingBoy.parkCar(new Car("test car"));
        }
        superSmartParkingBoy.parkCar(new Car("test car"));
        assertEquals(1, superSmartParkingBoy.getParkingLotList().get(1).getCarList().size());
        superSmartParkingBoy.parkCar(new Car("test car"));
        assertEquals(2, superSmartParkingBoy.getParkingLotList().get(1).getCarList().size());
        superSmartParkingBoy.parkCar(new Car("test car"));
        assertEquals(3, superSmartParkingBoy.getParkingLotList().get(0).getCarList().size());
        superSmartParkingBoy.parkCar(new Car("test car"));
        assertEquals(3, superSmartParkingBoy.getParkingLotList().get(1).getCarList().size());
    }
}
