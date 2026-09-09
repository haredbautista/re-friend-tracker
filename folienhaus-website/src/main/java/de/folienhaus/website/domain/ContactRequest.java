package de.folienhaus.website.domain;

import java.time.LocalDateTime;

/** Eine über das Kontaktformular eingegangene Anfrage. */
public record ContactRequest(
        LocalDateTime receivedAt,
        String name,
        String email,
        String phone,
        String vehicle,
        String service,
        String message
) {
}
