package no.dervis.parkingshus;

import java.util.ArrayList;

public class Parkeringshus {

    private int plasser;

    private ArrayList<Parkingsplass> parkeringsPlasser;


    public Parkeringshus(int plasser) {
        this.plasser = plasser;
        parkeringsPlasser = new ArrayList<>(plasser);
    }

    public void leggTilKjoretoy(Kjoretoy kjoretoy) {
        if (parkeringsPlasser.size() == plasser) {
            throw new RuntimeException("Har ikke mer plass");
        }

        parkeringsPlasser.add(new Parkingsplass(kjoretoy));
    }

    public ArrayList<Parkingsplass> getParkeringsPlasser() {
        return parkeringsPlasser;
    }
}
