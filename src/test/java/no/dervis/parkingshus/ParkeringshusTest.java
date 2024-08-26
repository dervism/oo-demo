package no.dervis.parkingshus;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParkeringshusTest {

    @Test
    void leggTilKjoretoy() {
        Parkeringshus parkeringshus = new Parkeringshus(3);

        parkeringshus.leggTilKjoretoy(KjoretoyFaker.lagFakeBil());
        parkeringshus.leggTilKjoretoy(KjoretoyFaker.lagFakeBil());
        parkeringshus.leggTilKjoretoy(KjoretoyFaker.lagFakeBil());

        assertEquals(3, parkeringshus.getParkeringsPlasser().size());
    }

    @Test
    void testHarIkkeMerPlass() {
        Parkeringshus parkeringshus = new Parkeringshus(3);

        parkeringshus.leggTilKjoretoy(KjoretoyFaker.lagFakeBil());
        parkeringshus.leggTilKjoretoy(KjoretoyFaker.lagFakeBil());
        parkeringshus.leggTilKjoretoy(KjoretoyFaker.lagFakeBil());

        assertThrows(RuntimeException.class, () -> {
            parkeringshus.leggTilKjoretoy(KjoretoyFaker.lagFakeBil());
        });
    }

    @Test
    void getParkeringsPlasser() {
    }
}