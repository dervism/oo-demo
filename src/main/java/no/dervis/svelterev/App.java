package no.dervis.svelterev;

import no.dervis.svelterev.api.HttpApi;
import no.dervis.svelterev.kort.Kort;

import java.util.List;

public class App {
    void main() {

        List<Kort> kortstokk = new HttpApi().get(Kort.class, "https://sveltethefox.ekstern.dev.nav.no/shuffle");

        System.out.println(kortstokk.size());
        System.out.println(kortstokk);

    }
}
