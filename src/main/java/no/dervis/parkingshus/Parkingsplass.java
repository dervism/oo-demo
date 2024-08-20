package no.dervis.parkingshus;

public record Parkingsplass(Kjoretoy kjoretoy) {

    @Override
    public String toString() {
        return "Parkingsplass{" +
                "kjoretoy=" + kjoretoy.toString() +
                '}';
    }
}
