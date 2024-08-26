package no.dervis.hotel;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class HotelTest {

    @Test
    void testSjekkInnValidRoomNumber() {
        Hotel hotel = new Hotel();
        hotel.lagHotelRom();
        Gjest gjest = new Gjest("Test Gjesteson", 18);
        assertTrue(hotel.sjekkInn(gjest, 1));
        assertTrue(hotel.getRom().get(1).gjester().contains(gjest));
    }

    @Test
    void testSjekkInnInvalidRoomNumber() {
        Hotel hotel = new Hotel();
        hotel.lagHotelRom();
        Gjest gjest = new Gjest("Test Gjesteson", 18);
        assertFalse(hotel.sjekkInn(gjest, 4));
    }
}
