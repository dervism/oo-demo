package no.dervis.svelterev.kort;

import java.util.List;

public class Kortstokk {

    private List<Kort> kortstokk;

    public Kortstokk(List<Kort> kortstokk) {
        this.kortstokk = kortstokk;
    }

    public List<Kort> getKortstokk() {
        return kortstokk;
    }

    public void setKortstokk(List<Kort> kortstokk) {
        this.kortstokk = kortstokk;
    }
}
