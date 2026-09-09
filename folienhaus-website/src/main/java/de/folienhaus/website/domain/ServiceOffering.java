package de.folienhaus.website.domain;

import java.util.List;

/**
 * Eine Leistung des Betriebs (z. B. Autofolierung, Lackschutzfolierung).
 *
 * @param slug        URL-Teil unter /leistungen/
 * @param title       Bezeichnung
 * @param teaser      Kurzbeschreibung für Übersichten
 * @param icon        Name des SVG-Symbols (siehe fragments/icons.html)
 * @param intro       Einleitungstext der Detailseite
 * @param benefits    Vorteile/Argumente
 * @param variants    Angebotene Varianten
 * @param metaTitle   HTML-Title für Suchmaschinen
 * @param metaDescription Beschreibung für Suchmaschinen
 */
public record ServiceOffering(
        String slug,
        String title,
        String teaser,
        String icon,
        String intro,
        List<String> benefits,
        List<Variant> variants,
        String metaTitle,
        String metaDescription
) {
    public String path() {
        return "/leistungen/" + slug;
    }

    public record Variant(String name, String description) {
    }
}
