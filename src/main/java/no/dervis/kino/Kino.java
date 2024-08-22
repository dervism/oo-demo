package no.dervis.kino;

import no.dervis.hotel.Gjest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Kino {


    // salNr -> liste av gjester
    private Map<Integer, List<Gjest>> kinoMap;

    public Kino() {
        this.kinoMap = new HashMap<>();
    }
}
