package no.dervis.parkingshus;

import java.util.Objects;

public record Motorsykkel(String type) implements Kjoretoy {

    @Override
    public String toString() {
        return "Motorsykkel{}";
    }

    @Override
    public String skiltnr() {
        return "";
    }
}
