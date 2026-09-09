package de.folienhaus.website.domain;

/** Ein Schritt im Ablauf "Von der Anfrage bis zur Abholung". */
public record ProcessStep(int number, String title, String description) {
}
