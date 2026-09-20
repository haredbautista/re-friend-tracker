package de.folienhaus.website.domain;

import java.util.List;

/**
 * Eine Leistung des Betriebs (z. B. Autofolierung, Steinschlagschutzfolierung).
 *
 * @param slug            URL-Teil unter /leistungen/
 * @param title           Bezeichnung
 * @param teaser          Kurzbeschreibung für Übersichten
 * @param icon            Name des SVG-Symbols (siehe fragments/icons.html)
 * @param intro           Einleitungstext der Detailseite
 * @param priceNote       Preisorientierung, z. B. "ab 2.200 €"
 * @param benefits        Vorteile/Argumente
 * @param variants        Angebotene Varianten
 * @param galleryCategory Kategorie der Galerie, deren Bilder auf der Detailseite gezeigt werden
 * @param metaTitle       HTML-Title für Suchmaschinen
 * @param metaDescription Beschreibung für Suchmaschinen
 */
public record ServiceOffering(
        String slug,
        String title,
        String teaser,
        String icon,
        String intro,
        String priceNote,
        List<String> benefits,
        List<Variant> variants,
        String galleryCategory,
        String metaTitle,
        String metaDescription
) {
    public String path() {
        return "/leistungen/" + slug;
    }

    public record Variant(String name, String description) {
    }
}
