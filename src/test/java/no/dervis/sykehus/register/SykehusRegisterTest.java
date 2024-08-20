package no.dervis.sykehus.register;

import no.dervis.sykehus.person.Pasient;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SykehusRegisterTest {

    @Test
    void getPersonRegister() {

        SykehusRegister sykehusRegister = new SykehusRegister();

        sykehusRegister.leggTilPerson(new Pasient("Test Navn", 20));
        sykehusRegister.leggTilPerson(new Pasient("Test Navn2", 20));
        sykehusRegister.leggTilPerson(new Pasient("Test Navn3", 20));

        System.out.println(
                sykehusRegister.getPersonRegister()
        );

        assertEquals(3, sykehusRegister.getPersonRegister().size());
    }
}