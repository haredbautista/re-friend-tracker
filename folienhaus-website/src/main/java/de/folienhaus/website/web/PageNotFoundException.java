package de.folienhaus.website.web;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/** Wird ausgelöst, wenn eine Leistung oder Region nicht existiert – führt zur 404-Seite. */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class PageNotFoundException extends RuntimeException {
    public PageNotFoundException(String message) {
        super(message);
    }
}
