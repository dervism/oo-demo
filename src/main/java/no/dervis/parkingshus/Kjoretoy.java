package no.dervis.parkingshus;

public interface Kjoretoy {
    

    enum Type {
        BIL,
        LitenBil,
        StorBil,
        CAMPINGVOGN,
        BUS,
        MOTORSYKKEL,
    }

    String skiltnr();
}
