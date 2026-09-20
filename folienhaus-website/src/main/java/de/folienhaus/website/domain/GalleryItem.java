package de.folienhaus.website.domain;

/**
 * Ein Bild der Referenz-Galerie (Datei unter /img/galerie/).
 *
 * @param file     Dateiname, z. B. "audi-r8.jpg"
 * @param alt      Bildbeschreibung (Alt-Text)
 * @param category Schlüssel der Kategorie, z. B. "autofolierung"
 * @param width    Breite in Pixel
 * @param height   Höhe in Pixel
 */
public record GalleryItem(String file, String alt, String category, int width, int height) {
    public String path() {
        return "/img/galerie/" + file;
    }
}
