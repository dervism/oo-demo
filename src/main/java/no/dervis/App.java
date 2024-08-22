package no.dervis;

import no.dervis.hotel.Gjest;
import no.dervis.hotel.Hotel;
import no.dervis.parkingshus.Bil;
import no.dervis.parkingshus.Kjoretoy;
import no.dervis.parkingshus.Motorsykkel;
import no.dervis.parkingshus.Parkeringshus;
import no.dervis.sykehus.register.SykehusRegister;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static no.dervis.parkingshus.Kjoretoy.Type.BIL;
import static no.dervis.parkingshus.Kjoretoy.Type.LitenBil;

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

        hotel.sjekkInn(new Gjest("Dervis"), 2);
        boolean ok = hotel.sjekkInn(new Gjest("Tom"), 5);

        System.out.println(
                hotel.getRom()
        );

    }






    private static void kjoretoydemo() {
        Motorsykkel motorsykkel1 = new Motorsykkel("Ford");
        Motorsykkel motorsykkel2 = new Motorsykkel("MCLaren");

        Kjoretoy bil1 = new Bil("123424");
        Kjoretoy bil2 = new Bil("856332");
        Kjoretoy bil4 = new Bil("856332");
        Kjoretoy bil5 = new Bil("856332");
        Kjoretoy bil3 = new Bil("636322");

        List<Kjoretoy> kjoretoyListe = new ArrayList<>(
                List.of(bil1, bil2, bil4, bil5, motorsykkel1, motorsykkel2));

        for (Kjoretoy kjoretoy : kjoretoyListe) {
            if (kjoretoy instanceof Bil bil && bil.getType() == LitenBil) {
                System.out.println(bil.skiltnr());
            }
        }

        // write a stream to read the kjoretoyListe list
        for (Kjoretoy kjoretoy : kjoretoyListe) {
            if (kjoretoy instanceof Bil bil && bil.getType() == LitenBil) {
                System.out.println(bil.skiltnr());
            }
        }

        // can you refactor the code to Java streams instead
    }


    private static void sykehusdemo() {
        System.out.println( "Hello World!" );

        SykehusRegister register = new SykehusRegister();
        register.setSykehusNavn("Ullevål sykehus");

        System.out.println(register.getSykehusNavn());
        System.out.println("Byggeår: " + register.BYGGE_AR);
    }

    private static void parkeringdemo() {
        Parkeringshus parkeringshus = new Parkeringshus(10);

        parkeringshus.leggTilKjoretoy(new Bil(""));
        parkeringshus.leggTilKjoretoy(new Bil(""));
        parkeringshus.leggTilKjoretoy(new Bil(""));

        System.out.println(
                parkeringshus.getParkeringsPlasser()
        );
    }
}
