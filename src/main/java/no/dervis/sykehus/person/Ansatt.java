package no.dervis.sykehus.person;

public record Ansatt(AnsattInfo info, TilgangStyring tilganger, AnsettelsesForhold ansettelsesForhold) implements Person {

}
