package de.folienhaus.website.domain;

import java.util.List;

/** Ein Preispaket (z. B. Scheibentönung 5 Scheiben oder Steinschlagschutz Front-Paket). */
public record PricePackage(String name, String price, List<String> includes, boolean highlighted) {
}
