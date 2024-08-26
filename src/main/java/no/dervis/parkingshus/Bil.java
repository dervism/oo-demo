package no.dervis.parkingshus;

// vi skriver "non-sealed" fordi denne klassen ikke er
// er abstract. Dvs vi tillater at denne klassen kan
// utvides av andre klasser gjennom arv i Java
// (feks "class ElektriskBil extends Bil {}")
public non-sealed class Bil implements Kjoretoy {

    private KjoretoyInfo kjoretoyInfo;

    public Bil(KjoretoyInfo kjoretoyInfo) {
        this.kjoretoyInfo = kjoretoyInfo;
    }

    @Override
    public String toString() {
        return "Bil{" +
                "kjoretoyInfo=" + kjoretoyInfo +
                '}';
    }

    public KjoretoyInfo getKjoretoyInfo() {
        return kjoretoyInfo;
    }
}
