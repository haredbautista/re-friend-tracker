package de.folienhaus.website.domain;

/** Folienhersteller/Partner, dessen Logo gezeigt wird. */
public record Partner(String name, String logo) {
    public String path() {
        return "/img/partner/" + logo;
    }
}
