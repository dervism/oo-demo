package no.dervis.parkingshus;

import java.util.List;
import java.util.Random;
import java.util.function.Supplier;

public record KjoretoyFaker() {

    private static List<String> merke =
            List.of("Mercedes", "Volvo", "BMW", "NIO", "DS", "Renault", "Volvwagen");

    private static List<String> skiltBokstaver =
            List.of("A", "B", "C", "D", "E", "F", "G", "H", "I", "L", "M", "N");

    private static List<Kjoretoy.Type> bilTyper =
            List.of(Kjoretoy.Type.LitenBil, Kjoretoy.Type.PersonBil, Kjoretoy.Type.StorBil);

    private static List<Kjoretoy.Type> bussTyper =
            List.of(Kjoretoy.Type.BUS, Kjoretoy.Type.MINIBUSS);


    public static Kjoretoy lagFakeBuss() {
        String fakeMerke = lagFakeMerke();
        Kjoretoy.Type fakeType = lagFakeBussType();

        return new Buss(new KjoretoyInfo(fakeMerke, lagFakeSkiltNr(), fakeType));
    }

    public static Kjoretoy lagFakeBil() {
        String fakeMerke = lagFakeMerke();
        Kjoretoy.Type fakeType = lagFakeBilType();

        return new Bil(new KjoretoyInfo(fakeMerke, lagFakeSkiltNr(), fakeType));
    }

    private static Kjoretoy.Type lagFakeBussType() {
        return bussTyper.get((int) (Math.random() * bilTyper.size()));
    }

    private static Kjoretoy.Type lagFakeBilType() {
        return bilTyper.get((int) (Math.random() * bilTyper.size()));
    }

    private static String lagFakeMerke() {
        return merke.get((int) (Math.random() * merke.size()));
    }

    private static String lagFakeSkiltNr() {
        Supplier<String> bokstavFn = () -> skiltBokstaver.get((int) (Math.random() * skiltBokstaver.size()));
        Supplier<Integer> randomTallFn = () -> new Random().nextInt(1, 10);

        String fakeSkiltNr =
                bokstavFn.get()
                + bokstavFn.get()
                + randomTallFn.get()
                + randomTallFn.get()
                + randomTallFn.get()
                + randomTallFn.get()
                + randomTallFn.get();

        return fakeSkiltNr;
    }

}
