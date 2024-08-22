package no.dervis.hotel;

import java.util.*;

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
        return Optional.ofNullable(rom.get(romNummer))
                .map(room -> room.gjester().add(gjest))
                .orElse(false);
    }
}
