package de.folienhaus.website.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * Firmendaten, gepflegt in {@code application.yml} unter dem Präfix {@code company}.
 * Alle Angaben lassen sich ohne Codeänderung anpassen.
 */
@ConfigurationProperties(prefix = "company")
public record CompanyProperties(
        String name,
        String legalName,
        String owner,
        String tagline,
        String foundedYear,
        String experienceYears,
        Address address,
        Contact contact,
        List<OpeningHour> openingHours,
        Legal legal,
        Social social,
        String baseUrl
) {
    public record Address(String street, String zip, String city, String region, String country) {
        public String oneLine() {
            return street + ", " + zip + " " + city;
        }
    }

    public record Contact(String phone, String phoneDisplay, String email) {
    }

    public record OpeningHour(String days, String times) {
    }

    public record Legal(String vatId, String responsiblePerson, String supervisoryAuthority) {
    }

    public record Social(String instagram, String instagramHandle) {
    }
}
