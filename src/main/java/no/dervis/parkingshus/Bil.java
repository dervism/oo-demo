package no.dervis.parkingshus;

public class Bil implements Kjoretoy {

    private String skiltnr;

    private Type type;

    public Bil(String skiltnr) {
        this.skiltnr = skiltnr;
        this.type = Type.LitenBil;
    }

    @Override
    public String toString() {
        return "Bil{" +
                "skiltnr='" + skiltnr + '\'' +
                '}';
    }

    public Type getType() {
        return type;
    }

    @Override
    public String skiltnr() {
        return skiltnr;
    }
}
