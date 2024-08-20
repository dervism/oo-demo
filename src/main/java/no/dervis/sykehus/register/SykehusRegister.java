package no.dervis.sykehus.register;

import no.dervis.sykehus.person.Person;

import java.util.ArrayList;
import java.util.List;

public class SykehusRegister {

    private String sykehusNavn;
    public final int BYGGE_AR = 2019;

    private List<Person> personRegister;

    public SykehusRegister() {
        this.personRegister = new ArrayList<>();
    }

    public SykehusRegister(List<Person> personRegister) {
        this.personRegister = personRegister;
    }

    public List<Person> getPersonRegister() {
        return personRegister;
    }

    public void leggTilPerson(Person person) {
        this.personRegister.add(person);
    }

    public String getSykehusNavn() {
        return sykehusNavn;
    }

    public void setSykehusNavn(String sykehusNavn) {
        this.sykehusNavn = sykehusNavn;
    }
}
