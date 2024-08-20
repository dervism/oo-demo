package no.dervis;

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

    }
}
