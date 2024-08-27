package no.dervis;

import no.dervis.hotel.Gjest;
import no.dervis.hotel.Hotel;
import no.dervis.parkingshus.*;
import no.dervis.sykehus.register.SykehusRegister;

import java.util.ArrayList;
import java.util.List;

import static no.dervis.parkingshus.Kjoretoy.Type.*;

/**
 * Hello world!
 *
 */
public class App 
{
    void main() {
        //sykehusdemo();
        //parkeringdemo();
        //kjoretoydemo();

        Hotel hotel = new Hotel();
        hotel.lagHotelRom();

        System.out.println(
                hotel.getRom()
        );

        Gjest dervis = new Gjest("Tom", 25);
        Gjest tom = new Gjest("Tom", 25);

        System.out.println(dervis.hashCode());
        System.out.println(tom.hashCode());

        System.out.println(dervis.equals(tom));

        Bil bil = new Bil(new KjoretoyInfo("Volvo", "AB12345", LitenBil));
        Bil bil2 = new Bil(new KjoretoyInfo("BMW", "AB98765", LitenBil));

        System.out.println(bil.equals(bil2));

        hotel.sjekkInn(dervis, 2);
        hotel.sjekkInn(tom, 5);

        System.out.println(
                hotel.getRom()
        );

    }

    // eksempel på hvordan vi skriver pattern matching med Switch/Case i Java
    public boolean erBussEllerMotorsykkel(Kjoretoy kjoretoy) {
        return switch (kjoretoy) {
            // vi ønsker å mache på midterste konstruktør verdi
            // første og siste konstruktør parameter ignorerer vi med _
            case Motorsykkel(var _, var type, var _) when type.equals(MOTORSYKKEL) -> true;
            case Motorsykkel(var _, var type, var _) when type.equals(MOPED) -> false;

            case Buss _ -> true;

            // alt som er her matcher ikke, og er derfor satt i en felles case-gruppe
            case Bil _, Traktor _, Sykkel _ -> false;
            default -> false;
        };
    }


    private static void kjoretoyDemo() {
        Motorsykkel motorsykkel1 = new Motorsykkel("Ford", MOTORSYKKEL, "ABC123");
        Motorsykkel motorsykkel2 = new Motorsykkel("MCLaren", MOTORSYKKEL, "ABC321");

        Kjoretoy bil1 = new Bil(new KjoretoyInfo("Mercedes", "AB13435", LitenBil));
        Kjoretoy bil2 = new Bil(new KjoretoyInfo("BMW", "GS34532", LitenBil));
        Kjoretoy bil4 = new Bil(new KjoretoyInfo("NIO", "DS34533", LitenBil));
        Kjoretoy bil5 = new Bil(new KjoretoyInfo("Ford", "UF56454", LitenBil));

        // dette objektet er ikke lagt i listen med vilje
        // (for å se at skiltnr ikke skrives ut i konsollet)
        Kjoretoy bil3 = new Bil(new KjoretoyInfo("Volvo", "RT34563", LitenBil));

        List<Kjoretoy> kjoretoyListe = new ArrayList<>(
                List.of(bil1, bil2, bil4, bil5, motorsykkel1, motorsykkel2));



        for (Kjoretoy kjoretoy : kjoretoyListe) {
            if (kjoretoy instanceof Bil bil && bil.getKjoretoyInfo().type() == LitenBil) {
                System.out.println(bil.getKjoretoyInfo().skiltnr());
            }
        }

        // write a stream to read the kjoretoyListe list
        for (Kjoretoy kjoretoy : kjoretoyListe) {
            if (kjoretoy instanceof Bil bil && bil.getKjoretoyInfo().type() == LitenBil) {
                System.out.println(bil.getKjoretoyInfo().skiltnr());
            }
        }

        // remember to ask genai to suggest refactoring to streams:
        // "can you refactor the code to Java streams instead"
    }

    private static void sykehusDemo() {
        System.out.println( "Hello World!" );

        SykehusRegister register = new SykehusRegister();
        register.setSykehusNavn("Ullevål sykehus");

        System.out.println(register.getSykehusNavn());
        System.out.println("Byggeår: " + register.BYGGE_AR);
    }

    private static void parkeringDemo() {
        Parkeringshus parkeringshus = new Parkeringshus(10);

        parkeringshus.leggTilKjoretoy(new Bil(new KjoretoyInfo("DS", "AB12345", LitenBil)));
        parkeringshus.leggTilKjoretoy(new Bil(new KjoretoyInfo("Mercedes", "AB53246", LitenBil)));
        parkeringshus.leggTilKjoretoy(new Bil(new KjoretoyInfo("Volvo", "AB72563", LitenBil)));

        System.out.println(
                parkeringshus.getParkeringsPlasser()
        );
    }
}
