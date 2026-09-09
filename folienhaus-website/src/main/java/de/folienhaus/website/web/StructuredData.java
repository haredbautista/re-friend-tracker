package de.folienhaus.website.web;

import de.folienhaus.website.config.CompanyProperties;

/**
 * Erzeugt JSON-LD (schema.org/AutoBodyShop) für Google & Co. aus den Firmendaten.
 */
public final class StructuredData {

    private StructuredData() {
    }

    public static String localBusiness(CompanyProperties c) {
        return """
                {"@context":"https://schema.org","@type":"AutoBodyShop","name":%s,"url":%s,"telephone":%s,"email":%s,\
                "address":{"@type":"PostalAddress","streetAddress":%s,"postalCode":%s,"addressLocality":%s,"addressRegion":%s,"addressCountry":"DE"},\
                "openingHours":["Mo-Fr 09:00-12:00","Mo-Fr 13:00-18:00"],"sameAs":[%s],"founder":{"@type":"Person","name":%s}}"""
                .formatted(json(c.name()), json(c.baseUrl()), json(c.contact().phone()), json(c.contact().email()),
                        json(c.address().street()), json(c.address().zip()), json(c.address().city()),
                        json(c.address().region()), json(c.social().instagram()), json(c.owner()));
    }

    static String json(String value) {
        if (value == null) {
            return "\"\"";
        }
        StringBuilder sb = new StringBuilder("\"");
        for (char ch : value.toCharArray()) {
            switch (ch) {
                case '"' -> sb.append("\\\"");
                case '\\' -> sb.append("\\\\");
                case '<' -> sb.append("\\u003c");
                case '>' -> sb.append("\\u003e");
                case '&' -> sb.append("\\u0026");
                default -> {
                    if (ch < 0x20) {
                        sb.append(String.format("\\u%04x", (int) ch));
                    } else {
                        sb.append(ch);
                    }
                }
            }
        }
        return sb.append('"').toString();
    }
}
