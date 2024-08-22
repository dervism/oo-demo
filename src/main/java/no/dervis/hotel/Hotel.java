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

    public boolean sjekkUt(int romNummer) {
        if (rom.containsKey(romNummer)) {
            // vi lager en ny tom liste i dette rommet (for å tømme gjestelisten)
            rom.put(romNummer, new Rom(new ArrayList<>()));
            return true;
        }
        return false;
    }
}
