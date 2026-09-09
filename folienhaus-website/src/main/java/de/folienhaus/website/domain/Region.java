package de.folienhaus.website.domain;

/**
 * Eine Region im Einzugsgebiet (Landingpage unter /autofolierung/{slug}).
 */
public record Region(String slug, String name, String distanceNote) {
    public String path() {
        return "/autofolierung/" + slug;
    }
}
