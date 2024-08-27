package no.dervis.svelterev;

import no.dervis.api.HttpApi;
import no.dervis.svelterev.kort.Kort;
import no.dervis.svelterev.kort.Kortstokk;

import java.util.List;

public class App {
    void main() {

        List<Kort> kortliste = HttpApi.getList(Kort.class, "https://sveltethefox.ekstern.dev.nav.no/shuffle");

        System.out.println(kortliste.size());
        System.out.println(kortliste);

        Kortstokk kortstokk = new Kortstokk(kortliste);

        // new SvelteRev(kortstokk).spill();

    }
}
