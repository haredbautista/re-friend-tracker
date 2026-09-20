package de.folienhaus.website.domain;

/** Kundenstimme (Google-Bewertung), wie auf der bisherigen Website veröffentlicht. */
public record Review(String author, String date, String text) {
}
