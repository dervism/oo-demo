package no.dervis.parkingshus;

public sealed interface Kjoretoy permits Bil, Buss, Motorsykkel, Traktor, Sykkel {

    enum Type {
        PersonBil,
        LitenBil,
        StorBil,
        CAMPINGVOGN,
        BUS,
        MOTORSYKKEL,
        MOPED,
        MINIBUSS
    }

}
