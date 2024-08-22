package no.dervis.hotel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Hotel {

    private Map<Integer, Rom> rom;

    public Hotel() {
        rom = new HashMap<>();
    }

    public void lagHotelRom() {
        rom.put(1, new Rom(new ArrayList<>()));
        rom.put(2, new Rom(new ArrayList<>()));
        rom.put(3, new Rom(new ArrayList<>()));
    }

    public Map<Integer, Rom> getRom() {
        return rom;
    }

    public boolean sjekkInn(Gjest gjest, int romNummer) {
        if (rom.containsKey(romNummer)) {
            rom.get(romNummer).gjester().add(gjest);
            return true;
        }
        return false;
    }
}
