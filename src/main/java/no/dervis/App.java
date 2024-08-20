package no.dervis;

import no.dervis.parkingshus.Bil;
import no.dervis.parkingshus.Parkeringshus;
import no.dervis.sykehus.register.SykehusRegister;

/**
 * Hello world!
 *
 */
public class App 
{
    void main() {
        System.out.println( "Hello World!" );

        SykehusRegister register = new SykehusRegister();
        register.setSykehusNavn("Ullevål sykehus");

        System.out.println(register.getSykehusNavn());
        System.out.println("Byggeår: " + register.BYGGE_AR);


        Parkeringshus parkeringshus = new Parkeringshus(10);

        parkeringshus.leggTilKjoretoy(new Bil());
        parkeringshus.leggTilKjoretoy(new Bil());
        parkeringshus.leggTilKjoretoy(new Bil());

        System.out.println(
                parkeringshus.getParkeringsPlasser()
        );
    }
}
