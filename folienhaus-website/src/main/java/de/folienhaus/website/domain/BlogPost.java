package de.folienhaus.website.domain;

import java.time.LocalDate;

/**
 * Ein Ratgeber-Beitrag. {@code contentHtml} ist bereinigtes HTML (nur Absätze, Überschriften, Listen, Bilder).
 */
public record BlogPost(
        String slug,
        String title,
        LocalDate date,
        String excerpt,
        String contentHtml,
        String image,
        String imageAlt
) {
    public String path() {
        return "/blog/" + slug;
    }
}
