package no.dervis.parkingshus;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParkeringshusTest {

    @Test
    void leggTilKjoretoy() {
        Parkeringshus parkeringshus = new Parkeringshus(3);

        parkeringshus.leggTilKjoretoy(new Bil());
        parkeringshus.leggTilKjoretoy(new Bil());
        parkeringshus.leggTilKjoretoy(new Bil());

        assertEquals(3, parkeringshus.getParkeringsPlasser().size());
    }

    @Test
    void testHarIkkeMerPlass() {
        Parkeringshus parkeringshus = new Parkeringshus(3);

        parkeringshus.leggTilKjoretoy(new Bil());
        parkeringshus.leggTilKjoretoy(new Bil());
        parkeringshus.leggTilKjoretoy(new Bil());

        assertThrows(RuntimeException.class, () -> {
            parkeringshus.leggTilKjoretoy(new Bil());
        });
    }

    @Test
    void getParkeringsPlasser() {
    }
}