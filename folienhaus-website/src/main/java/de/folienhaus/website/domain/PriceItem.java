package de.folienhaus.website.domain;

/**
 * Preisorientierung. {@code price} ist ein Text (z. B. "ab 1.800 €" oder "auf Anfrage"),
 * weil die endgültigen Preise je nach Fahrzeug und Folie individuell kalkuliert werden.
 */
public record PriceItem(String service, String price, String note) {
}
