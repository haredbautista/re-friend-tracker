package de.folienhaus.website.service;

import de.folienhaus.website.domain.FaqItem;
import de.folienhaus.website.domain.NavItem;
import de.folienhaus.website.domain.PriceItem;
import de.folienhaus.website.domain.ProcessStep;
import de.folienhaus.website.domain.Region;
import de.folienhaus.website.domain.ServiceOffering;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Zentrale Stelle für redaktionelle Inhalte (Leistungen, Regionen, Preise, FAQ).
 * Die Inhalte sind bewusst im Code hinterlegt, damit die Website ohne Datenbank läuft.
 */
@Service
public class ContentService {

    private final List<NavItem> navigation = List.of(
            new NavItem("Start", "/"),
            new NavItem("Leistungen", "/leistungen"),
            new NavItem("Preise", "/preise"),
            new NavItem("Über uns", "/ueber-uns"),
            new NavItem("Kontakt", "/kontakt")
    );

    private final List<ServiceOffering> services = List.of(
            new ServiceOffering(
                    "autofolierung",
                    "Autofolierung",
                    "Vollfolierung, Teilfolierung und Spezialeffekte – ein neuer Look für Ihr Fahrzeug, ohne den Originallack anzutasten.",
                    "wrap",
                    "Die KFZ-Folierung ist eine nachhaltige und günstige Alternative zur klassischen Lackierung. "
                            + "Wir verwandeln Ihr Fahrzeug mit passgenau zugeschnittenen Folien namhafter Hersteller "
                            + "und bewährten Verarbeitungsmethoden.",
                    List.of(
                            "Schützt den Originallack und lässt sich rückstandsfrei entfernen",
                            "Große Auswahl an Farben, Oberflächen und Effekten",
                            "Deutlich günstiger und schneller als eine Neulackierung",
                            "Ideal auch für Firmenfahrzeuge und Werbefolierung"
                    ),
                    List.of(
                            new ServiceOffering.Variant("Vollfolierung", "Das komplette Fahrzeug erhält eine neue Farbe oder Oberfläche."),
                            new ServiceOffering.Variant("Teilfolierung", "Einzelne Bauteile wie Dach, Motorhaube, Spiegel oder Zierleisten."),
                            new ServiceOffering.Variant("Spezialeffekte", "Matt, Satin, Chrom, Carbon-Optik oder Farbwechsel-Folien."),
                            new ServiceOffering.Variant("Design & Werbefolierung", "Individuelle Gestaltung und Beschriftung für Firmenfahrzeuge.")
                    ),
                    "Autofolierung in Mühlhausen-Ehingen | Das Folienhaus",
                    "Voll- und Teilfolierung, Spezialeffekte und Werbefolierung vom Fachbetrieb in Mühlhausen-Ehingen bei Singen."
            ),
            new ServiceOffering(
                    "lackschutzfolierung",
                    "Lackschutzfolierung",
                    "Steinschlagschutz mit hochwertigen SunTek-Folien – erhält den Originallack und den Wert Ihres Fahrzeugs.",
                    "shield",
                    "Steinschläge, Insekten, Streusalz und Kratzer setzen dem Lack täglich zu. "
                            + "Eine Lackschutzfolie aus hochwertigem SunTek-Material schützt zuverlässig vor diesen Gefahren "
                            + "und bewahrt den Originallack Ihres Fahrzeugs.",
                    List.of(
                            "Unsichtbarer Schutz vor Steinschlag und Kratzern",
                            "Werterhalt beim Wiederverkauf",
                            "Selbstheilende Oberfläche bei leichten Kratzern (je nach Folie)",
                            "Voll- oder Teilschutz je nach Budget"
                    ),
                    List.of(
                            new ServiceOffering.Variant("Komplettschutz", "Vollständige Abdeckung aller lackierten Flächen mit Lackschutzfolie."),
                            new ServiceOffering.Variant("Teilschutz", "Die günstigere Variante für gefährdete Bereiche wie Frontstoßstange, Motorhaube und Außenspiegel.")
                    ),
                    "Lackschutzfolierung & Steinschlagschutz | Das Folienhaus",
                    "Lackschutzfolie mit SunTek-Folien: Komplett- oder Teilschutz für Frontstoßstange, Motorhaube und Spiegel. Fachbetrieb bei Singen."
            ),
            new ServiceOffering(
                    "scheibentoenung",
                    "Scheibentönung",
                    "Hochwertige Tönungsfolien für mehr Privatsphäre, Sonnenschutz und einen sportlichen Look.",
                    "sun",
                    "Getönte Scheiben sorgen für mehr Privatsphäre im Innenraum, reduzieren die Aufheizung durch Sonneneinstrahlung "
                            + "und werten das Erscheinungsbild Ihres Fahrzeugs auf. Wir verarbeiten hochwertige Tönungsfolien "
                            + "passgenau auf Ihre Scheiben.",
                    List.of(
                            "Mehr Privatsphäre und Blendschutz",
                            "Weniger Aufheizung des Innenraums",
                            "Schutz des Interieurs vor UV-Strahlung",
                            "Verschiedene Tönungsgrade wählbar"
                    ),
                    List.of(
                            new ServiceOffering.Variant("Hintere Scheiben", "Seitenscheiben hinten und Heckscheibe."),
                            new ServiceOffering.Variant("Einzelne Scheiben", "Der Preis richtet sich nach Größe und Anzahl der Scheiben.")
                    ),
                    "Scheibentönung | Das Folienhaus Mühlhausen-Ehingen",
                    "Scheibentönung mit hochwertiger Tönungsfolie für Privatsphäre und Sonnenschutz. Fachbetrieb in Mühlhausen-Ehingen."
            ),
            new ServiceOffering(
                    "detailing",
                    "Detailing",
                    "Professionelle Fahrzeugaufbereitung – damit Ihr Fahrzeug innen und außen wieder wie neu wirkt.",
                    "sparkle",
                    "Beim Detailing wird Ihr Fahrzeug gründlich und schonend aufbereitet: Lack, Felgen, Scheiben und Innenraum. "
                            + "Die ideale Vorbereitung vor einer Folierung oder als eigenständige Leistung.",
                    List.of(
                            "Schonende Reinigung von Lack und Innenraum",
                            "Optimale Vorbereitung für Folierung und Lackschutz",
                            "Frischer Auftritt für Verkauf oder Alltag"
                    ),
                    List.of(),
                    "Fahrzeug-Detailing | Das Folienhaus",
                    "Professionelle Fahrzeugaufbereitung innen und außen in Mühlhausen-Ehingen."
            ),
            new ServiceOffering(
                    "lackierung-pulverbeschichtung",
                    "Lackierung & Pulverbeschichtung",
                    "Lackarbeiten und Pulverbeschichtung, z. B. für Felgen und Anbauteile.",
                    "spray",
                    "Ergänzend zur Folierung bieten wir Lackarbeiten und Pulverbeschichtung an – etwa für Felgen, "
                            + "Zierleisten und andere Anbauteile. So erhält Ihr Fahrzeug ein rundum stimmiges Erscheinungsbild.",
                    List.of(
                            "Robuste, langlebige Oberflächen",
                            "Passend zum Folierungskonzept abgestimmt",
                            "Alles aus einer Hand"
                    ),
                    List.of(),
                    "Lackierung & Pulverbeschichtung | Das Folienhaus",
                    "Lackarbeiten und Pulverbeschichtung für Felgen und Anbauteile – ergänzend zur Fahrzeugfolierung."
            )
    );

    private final List<Region> regions = List.of(
            new Region("singen", "Singen", "nur wenige Minuten von unserer Werkstatt entfernt"),
            new Region("bodensee", "Bodensee", "Konstanz, Radolfzell, Überlingen und Umgebung"),
            new Region("villingen-schwenningen", "Villingen-Schwenningen", "Schwarzwald-Baar-Kreis"),
            new Region("tuttlingen", "Tuttlingen", "Landkreis Tuttlingen"),
            new Region("donaueschingen", "Donaueschingen", "Schwarzwald-Baar-Kreis"),
            new Region("balingen", "Balingen", "Zollernalbkreis"),
            new Region("tuebingen", "Tübingen", "Raum Tübingen und Reutlingen"),
            new Region("freiburg", "Freiburg", "Raum Freiburg im Breisgau")
    );

    private final List<PriceItem> prices = List.of(
            new PriceItem("Vollfolierung", "ab ca. 1.800 €", "Der Preis variiert je nach Fahrzeug, Aufwand und Folie."),
            new PriceItem("Teilfolierung", "auf Anfrage", "Abhängig von Bauteil, Folie und Fahrzeug."),
            new PriceItem("Lackschutzfolierung (Teilschutz)", "auf Anfrage", "Frontstoßstange, Motorhaube, Außenspiegel."),
            new PriceItem("Lackschutzfolierung (Komplettschutz)", "auf Anfrage", "Vollständige Abdeckung aller Lackflächen."),
            new PriceItem("Scheibentönung", "auf Anfrage", "Je nach Größe und Anzahl der Scheiben.")
    );

    private final List<ProcessStep> processSteps = List.of(
            new ProcessStep(1, "Anfrage", "Sie schicken uns Fahrzeugdaten und Wunsch – per Formular, E-Mail oder Telefon."),
            new ProcessStep(2, "Beratung & Angebot", "Wir beraten zu Folie, Farbe und Umfang und erstellen ein individuelles Angebot."),
            new ProcessStep(3, "Termin & Umsetzung", "Wir arbeiten mit Hochdruck an Ihrer Folierung – in der Regel 3–5 Werktage."),
            new ProcessStep(4, "Abholung", "Sie holen Ihr Fahrzeug ab und erhalten Pflegehinweise für eine lange Haltbarkeit.")
    );

    private final List<FaqItem> faq = List.of(
            new FaqItem("Wie lange dauert eine Folierung?",
                    "Wir arbeiten mit Hochdruck an Ihrer Folierung. Rechnen Sie in der Regel mit 3–5 Werktagen – je nach Umfang und Fahrzeug."),
            new FaqItem("Was kostet eine Vollfolierung?",
                    "Eine Vollfolierung beginnt bei etwa 1.800 €. Der genaue Preis hängt von Fahrzeug, Aufwand und gewählter Folie ab – wir erstellen Ihnen gerne ein individuelles Angebot."),
            new FaqItem("Schadet die Folie dem Lack?",
                    "Nein. Eine fachgerecht verarbeitete Folie schützt den Originallack und lässt sich später wieder entfernen."),
            new FaqItem("Welche Folien verwendet ihr?",
                    "Wir verarbeiten Folien namhafter Qualitätshersteller, für den Lackschutz zum Beispiel SunTek."),
            new FaqItem("Wo finde ich euch?",
                    "Unsere Werkstatt liegt in Mühlhausen-Ehingen, nur wenige Minuten von Singen entfernt – gut erreichbar aus dem gesamten Hegau und vom Bodensee.")
    );

    public List<NavItem> navigation() {
        return navigation;
    }

    public List<ServiceOffering> services() {
        return services;
    }

    public Optional<ServiceOffering> serviceBySlug(String slug) {
        return services.stream().filter(s -> s.slug().equals(slug)).findFirst();
    }

    public List<Region> regions() {
        return regions;
    }

    public Optional<Region> regionBySlug(String slug) {
        return regions.stream().filter(r -> r.slug().equals(slug)).findFirst();
    }

    public List<PriceItem> prices() {
        return prices;
    }

    public List<ProcessStep> processSteps() {
        return processSteps;
    }

    public List<FaqItem> faq() {
        return faq;
    }
}
