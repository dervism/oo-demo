package no.dervis.parkingshus;

public record Motorsykkel(String merke, Type type, String skiltnr) implements Kjoretoy {

    @Override
    public String toString() {
        return "Motorsykkel{}";
    }
}
