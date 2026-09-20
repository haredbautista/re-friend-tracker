package de.folienhaus.website.service;

import de.folienhaus.website.domain.FaqItem;
import de.folienhaus.website.domain.NavItem;
import de.folienhaus.website.domain.PriceItem;
import de.folienhaus.website.domain.PricePackage;
import de.folienhaus.website.domain.ProcessStep;
import de.folienhaus.website.domain.Region;
import de.folienhaus.website.domain.ServiceOffering;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Zentrale Stelle für redaktionelle Inhalte (Leistungen, Regionen, Preise, FAQ).
 * Texte und Preise folgen den Angaben der bisherigen Website das-folienhaus.de (Stand September 2026).
 */
@Service
public class ContentService {

    private final List<NavItem> navigation = List.of(
            new NavItem("Start", "/"),
            new NavItem("Leistungen", "/leistungen"),
            new NavItem("Preise", "/preise"),
            new NavItem("Galerie", "/galerie"),
            new NavItem("Über uns", "/ueber-uns"),
            new NavItem("Ratgeber", "/blog"),
            new NavItem("Kontakt", "/kontakt")
    );

    private final List<ServiceOffering> services = List.of(
            new ServiceOffering(
                    "autofolierung",
                    "Autofolierung",
                    "Vollfolierung, Teilfolierung oder Designfolierung – die günstige und nachhaltige Alternative zum Lackieren, in matt, seidenmatt oder mit Metallic-Effekt.",
                    "wrap",
                    "Die Autofolierung ist eine günstige und nachhaltige Alternative zum klassischen Lackieren. "
                            + "Sie sind nicht mehr an die Farbpalette des Herstellers gebunden, sondern können sich frei entfalten: "
                            + "Ihre Wunschfarbe in matt, seidenmatt oder mit Metallic-Effekt. Auf Wunsch folieren wir auch Türinnenkanten "
                            + "und Türeinstiege mit, sodass das komplette Auto in der gewünschten Farbe erscheint. Auf dem Originallack "
                            + "angebracht, lässt sich die Folie später rückstandslos entfernen.",
                    "Vollfolierung ab 2.200 €",
                    List.of(
                            "Lackschutz: Die Folie schützt den Originallack vor Steinschlag und Kratzern",
                            "Optik: Farbwechsel und Oberflächen, die es ab Werk nicht gibt",
                            "Individualisierung: Vollfolierung, Teilfolierung oder Designfolierung",
                            "Nur Premiumfolien von 3M, Avery Dennison, ORAFOL und weiteren Qualitätsherstellern",
                            "Finanzierung in Teilbeträgen möglich"
                    ),
                    List.of(
                            new ServiceOffering.Variant("Vollfolierung", "Das komplette Fahrzeug erhält eine neue Farbe oder Oberfläche – auf Wunsch inklusive Türeinstiegen."),
                            new ServiceOffering.Variant("Teilfolierung", "Einzelne Bauteile wie Dach, Motorhaube, Spiegel oder Zierleisten."),
                            new ServiceOffering.Variant("Designfolierung", "Individuelle Designs und Muster, umgesetzt ab 500 €."),
                            new ServiceOffering.Variant("Sportwagen & Premiumfahrzeuge", "Porsche, Ferrari, Mercedes, Tesla – vom Alltagsauto bis zum Supersportwagen.")
                    ),
                    "autofolierung",
                    "Autofolierung in Mühlhausen-Ehingen bei Singen | Das Folienhaus",
                    "Voll- und Teilfolierung mit Premiumfolien von 3M, Avery Dennison und ORAFOL. Vollfolierung ab 2.200 €, Finanzierung möglich. Fachbetrieb bei Singen am Hohentwiel."
            ),
            new ServiceOffering(
                    "steinschlagschutzfolierung",
                    "Steinschlagschutzfolierung",
                    "Selbstheilende Lackschutzfolie (PPF) von SunTek und STEK – unsichtbarer Schutz vor Steinschlag, Kratzern und Vogelkot mit 5 bis 12 Jahren Garantie.",
                    "shield",
                    "Die Steinschlagschutzfolierung schützt den Originallack Ihres Fahrzeugs dauerhaft vor den meisten Gefahren auf der Straße. "
                            + "Wir verarbeiten selbstheilende Lackschutzfolien von SunTek, deren Beschichtungen Steinschläge, Oberflächenkratzer, "
                            + "Vogelkot und andere Verschmutzungen abwehren – und dabei nahezu unsichtbar bleiben. Die neueste 2-in-1-Technologie "
                            + "verbindet die Schutzwirkung einer Lackschutzfolie mit den Eigenschaften einer Keramikbeschichtung.",
                    "Front-Paket ab 2.450 €",
                    List.of(
                            "Sicherheit: Schutz vor Oberflächenschäden und Gefahren im Alltag",
                            "Unversehrter Lack: Die Oberfläche bleibt über Jahre vorzeigbar",
                            "Werterhalt beim späteren Verkauf",
                            "Garantie: je nach Folienvariante 5 bis 12 Jahre Herstellergarantie",
                            "Einfache Reparatur: Beschädigte Folie lässt sich schnell ersetzen",
                            "Zahlung in Teilbeträgen möglich"
                    ),
                    List.of(
                            new ServiceOffering.Variant("Front-Paket", "Motorhaube, Kotflügel, Stoßstange und Rückspiegel – ab 2.450 €."),
                            new ServiceOffering.Variant("Erweitertes Front-Paket", "Front-Paket plus A-Säulen, Seitenschweller und Dach – ab 3.780 €."),
                            new ServiceOffering.Variant("Vollfolierung", "Alle Bauteile mit Lackschutzfolie – ab 5.200 €.")
                    ),
                    "steinschlagschutz",
                    "Steinschlagschutzfolierung mit SunTek PPF | Das Folienhaus",
                    "Steinschlagschutzfolie von SunTek: Front-Paket ab 2.450 €, erweitertes Front-Paket ab 3.780 €, Vollfolierung ab 5.200 €. Selbstheilend, 5–12 Jahre Garantie."
            ),
            new ServiceOffering(
                    "lackschutzfolierung",
                    "Lackschutzfolierung",
                    "Sicherheit, Langlebigkeit und Wertsteigerung: Komplett- oder Teilschutz für besonders gefährdete Bereiche wie Frontstoßstange, Motorhaube und Spiegel.",
                    "shield",
                    "Die Lackschutzfolierung schützt den Lack Ihres Fahrzeugs langfristig und steigert seinen Wert. Steinschläge, Kratzer "
                            + "und Umwelteinflüsse bedrohen die Neuwertigkeit nicht mehr. Während eine vollständige Lackschutzfolierung das gesamte "
                            + "Fahrzeug schützt, konzentriert sich die preiswertere Teilfolierung auf besonders gefährdete Bereiche wie Frontstoßstange, "
                            + "Motorhaube und Seitenspiegel. Für spezielle Anforderungen setzen wir individuelle Lackschutzlösungen um.",
                    "Preis nach Fahrzeug und Umfang",
                    List.of(
                            "Schutz vor äußeren Einflüssen: Steinschläge, Kratzer und Umweltschäden werden abgewehrt",
                            "Wertsteigerung: Der makellose Lack erhöht den Wiederverkaufswert",
                            "Langlebigkeit: Hochwertige Folien halten bei guter Pflege bis zu zehn Jahre",
                            "Nahezu unsichtbar – Design und Farbe bleiben unverändert",
                            "Rückstandslos entfernbar",
                            "Auch für Firmenflotten: kombinierbar mit Werbefolierung"
                    ),
                    List.of(
                            new ServiceOffering.Variant("Komplettschutz", "Vollständige Abdeckung aller lackierten Flächen."),
                            new ServiceOffering.Variant("Teilschutz", "Die preiswertere Variante für Frontstoßstange, Motorhaube und Seitenspiegel."),
                            new ServiceOffering.Variant("Individuelle Lösungen", "Maßgeschneiderter Schutz für spezielle Anforderungen oder Fahrzeugtypen.")
                    ),
                    "steinschlagschutz",
                    "Lackschutzfolierung | Das Folienhaus Mühlhausen-Ehingen",
                    "Lackschutzfolierung vom Fachbetrieb: Komplett- oder Teilschutz, nahezu unsichtbar, bis zu zehn Jahre haltbar. Beratung in Mühlhausen-Ehingen."
            ),
            new ServiceOffering(
                    "scheibentoenung",
                    "Scheibentönung",
                    "Tönungsfolien für mehr Privatsphäre, weniger Hitze und UV-Belastung – Pakete ab 220 €.",
                    "sun",
                    "Unsere Scheibenfolierung vereint Diskretion und Privatsphäre mit wirksamem Sonnenschutz: Die Folie reflektiert "
                            + "Sonnenstrahlen, reduziert die Hitze im Innenraum und minimiert die UV-Belastung. Wir arbeiten mit Tönungsfolien "
                            + "auf Nanotechnologie-Basis, die Bordelektronik nicht stören, und bieten verschiedene Tönungsgrade an – "
                            + "von 70 % bis 95 %.",
                    "ab 220 €",
                    List.of(
                            "Kühlerer Innenraum: weniger Hitze durch Sonneneinstrahlung",
                            "Weniger UV-Belastung für Insassen und Interieur",
                            "Weniger Kraftstoffverbrauch durch Entlastung der Klimaanlage",
                            "Mehr Privatsphäre und Blendschutz",
                            "Tönungsgrade 70 %, 85 % und 95 % wählbar"
                    ),
                    List.of(
                            new ServiceOffering.Variant("3 Scheiben", "Heckscheibe und hintere Seitenscheiben – 220 €."),
                            new ServiceOffering.Variant("3 Scheiben + Dreiecksfenster", "240 €."),
                            new ServiceOffering.Variant("5 Scheiben", "260 €."),
                            new ServiceOffering.Variant("7 Scheiben", "300 €.")
                    ),
                    "scheibentoenung",
                    "Scheibentönung ab 220 € | Das Folienhaus",
                    "Scheibenfolierung mit Tönungsgraden von 70 bis 95 %: 3 Scheiben 220 €, 5 Scheiben 260 €, 7 Scheiben 300 €. Fachbetrieb in Mühlhausen-Ehingen."
            ),
            new ServiceOffering(
                    "designerstellung",
                    "Designerstellung",
                    "Individuelle Designs für Ihr Fahrzeug – erstellt in enger Zusammenarbeit zwischen Grafiker und Kunde, mit Vorschlägen aus verschiedenen Blickwinkeln.",
                    "sparkle",
                    "Unsere Partner erstellen individuelle Designs für Ihr Fahrzeug. Die Designerstellung geschieht in enger Zusammenarbeit "
                            + "zwischen Grafiker und Kunde: Sie erhalten Vorschläge aus verschiedenen Blickwinkeln und bringen Ihre Wünsche und Ideen ein. "
                            + "Im Anschluss setzen wir das Design als Designfolierung auf Ihrem Fahrzeug um.",
                    "individuell",
                    List.of(
                            "Zufriedenheitsgarantie: Wir sind erst zufrieden, wenn Sie es sind",
                            "Ihre Wünsche und Ideen fließen direkt ins Design ein",
                            "Vorschläge aus mehreren Perspektiven vor der Umsetzung",
                            "Passende Designfolierung aus einer Hand"
                    ),
                    List.of(),
                    "design",
                    "Designerstellung für Fahrzeugfolierung | Das Folienhaus",
                    "Individuelle Fahrzeugdesigns vom Grafiker – Entwürfe aus mehreren Blickwinkeln, anschließend Umsetzung als Designfolierung."
            ),
            new ServiceOffering(
                    "werbefolierung",
                    "Werbefolierung",
                    "Ihr Design in höchster Qualität auf dem Firmenfahrzeug – vergleichsweise günstige Werbung, die täglich unterwegs ist.",
                    "spray",
                    "Sie haben ein Design entworfen oder erhalten und möchten es in höchster Qualität auf Ihrem Fahrzeug anbringen? "
                            + "Mit unserer Werbefolierung sind alle Farben realitätsnah und sehen aus wie erwartet. Gerne erstellen wir vorab ein "
                            + "Design für Sie. Für Firmen ist die Werbefolierung besonders attraktiv, weil sie eine vergleichsweise günstige "
                            + "Werbung darstellt – und nebenbei den Lack schützt.",
                    "individuell",
                    List.of(
                            "Günstige Werbung im Vergleich zu klassischen Kanälen",
                            "Lackschutz inklusive – wie bei jeder Folierung",
                            "Hebt Ihre Flotte hervor",
                            "Designerstellung vorab möglich"
                    ),
                    List.of(),
                    "design",
                    "Werbefolierung für Firmenfahrzeuge | Das Folienhaus",
                    "Professionelle Werbefolierung für Transporter, Pkw und Flotten – realitätsnahe Farben, Designerstellung vorab möglich. Fachbetrieb bei Singen."
            )
    );

    private final List<Region> regions = List.of(
            new Region("singen", "Singen", "nur wenige Minuten von unserer Werkstatt entfernt"),
            new Region("konstanz", "Konstanz", "Bodensee"),
            new Region("bodensee", "Bodensee", "Konstanz, Radolfzell, Überlingen, Friedrichshafen und Umgebung"),
            new Region("villingen-schwenningen", "Villingen-Schwenningen", "Schwarzwald-Baar-Kreis"),
            new Region("tuttlingen", "Tuttlingen", "Landkreis Tuttlingen"),
            new Region("donaueschingen", "Donaueschingen", "Schwarzwald-Baar-Kreis"),
            new Region("ravensburg", "Ravensburg", "Oberschwaben"),
            new Region("waldshut", "Waldshut", "Hochrhein"),
            new Region("balingen", "Balingen", "Zollernalbkreis"),
            new Region("tuebingen", "Tübingen", "Raum Tübingen und Reutlingen"),
            new Region("freiburg", "Freiburg", "Raum Freiburg im Breisgau"),
            new Region("schweiz", "Schweiz", "Zürich, Schaffhausen, Winterthur – viele Kunden reisen aus der Schweiz an")
    );

    private final List<PriceItem> prices = List.of(
            new PriceItem("Vollfolierung", "ab 2.200 €", "Je nach Folie und Fahrzeug; auf Wunsch inklusive Türeinstiegen."),
            new PriceItem("Teilfolierung", "auf Anfrage", "Abhängig von Bauteil, Folie und Fahrzeug."),
            new PriceItem("Designfolierung", "ab 500 €", "Je nach Aufwand des Designs."),
            new PriceItem("Scheibentönung", "ab 220 €", "Pakete nach Anzahl der Scheiben, siehe unten."),
            new PriceItem("Steinschlagschutz", "ab 2.450 €", "Pakete nach Umfang, siehe unten."),
            new PriceItem("Designerstellung", "individuell", "Von wenig aufwendig bis zum großen Projekt."),
            new PriceItem("Werbefolierung", "individuell", "Variiert wie alle Folierungen stark nach Umfang.")
    );

    private final List<PricePackage> tintPackages = List.of(
            new PricePackage("3 Scheiben", "220 €", List.of("Heckscheibe", "Hintere Seitenscheiben"), false),
            new PricePackage("3 Scheiben + Dreiecksfenster", "240 €", List.of("Heckscheibe", "Hintere Seitenscheiben", "Dreiecksfenster"), false),
            new PricePackage("5 Scheiben", "260 €", List.of("Heckscheibe", "Alle hinteren Scheiben"), true),
            new PricePackage("7 Scheiben", "300 €", List.of("Heckscheibe", "Alle hinteren Scheiben", "Zusätzliche Seitenscheiben"), false)
    );

    private final List<PricePackage> ppfPackages = List.of(
            new PricePackage("Front-Paket", "ab 2.450 €", List.of("Motorhaube", "Kotflügel", "Stoßstange", "Rückspiegel"), false),
            new PricePackage("Erweitertes Front-Paket", "ab 3.780 €", List.of("Front-Paket", "A-Säule", "Seitenschweller", "Dach"), true),
            new PricePackage("Vollfolierung", "ab 5.200 €", List.of("Alle Bauteile"), false)
    );

    private final List<ProcessStep> processSteps = List.of(
            new ProcessStep(1, "Anfrage", "Sie schicken uns Fahrzeugdaten und Wunsch – per Formular, E-Mail oder Telefon."),
            new ProcessStep(2, "Beratung & Angebot", "Wir beraten zu Folie, Farbe und Umfang, gerne auch vor Ort mit Folienmustern, und erstellen ein individuelles Angebot."),
            new ProcessStep(3, "Termin & Umsetzung", "Wir arbeiten mit Hochdruck an Ihrer Folierung – in der Regel 3 bis 5 Werktage."),
            new ProcessStep(4, "Abholung", "Sie holen Ihr Fahrzeug ab und erhalten Pflegehinweise für eine lange Haltbarkeit.")
    );

    private final List<FaqItem> faq = List.of(
            new FaqItem("Was kostet eine Vollfolierung?",
                    "Eine Vollfolierung kann je nach Folie und Fahrzeug im Preis stark variieren. Bei uns beginnt sie bei 2.200 €. Auf Wunsch können Sie die Folierung in Teilbeträgen zahlen."),
            new FaqItem("Wie lange dauert die Folierung?",
                    "Je nach Auslastung kann Ihre Folierung schon nach 3 Tagen fertig sein. Wir arbeiten mit Hochdruck und geben eine Wartezeit von 3 bis 5 Werktagen an."),
            new FaqItem("Wie lange hält die Folie?",
                    "Je nach Herstellerangabe und Pflege hält eine Folierung 3 bis 6 Jahre. Durch die professionelle Montage gewährleisten wir eine lange Lebensdauer. Lackschutzfolien halten bei guter Pflege bis zu zehn Jahre."),
            new FaqItem("Erleidet der Lack Schäden durch eine Folierung?",
                    "Nein. Der Lack wird durch die Folie geschützt. Auf dem Originallack angebracht, lässt sich die Folie später rückstandslos entfernen."),
            new FaqItem("Welche Folien verwendet ihr?",
                    "Ausschließlich Premiumfolien, darunter 3M, Avery Dennison und ORAFOL. Für den Lackschutz verarbeiten wir PPF von SunTek und STEK."),
            new FaqItem("Kann ich die Folierung finanzieren?",
                    "Ja. Kommen Sie vorbei und erhalten Sie unverbindliche Informationen über eine Finanzierung in Teilbeträgen."),
            new FaqItem("Wo finde ich euch?",
                    "Unsere Werkstatt liegt im Hohenkräher-Brühl 15a in Mühlhausen-Ehingen, nur wenige Minuten von Singen entfernt – gut erreichbar aus dem Hegau, vom Bodensee, aus Villingen-Schwenningen und aus der Schweiz.")
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

    public List<PricePackage> tintPackages() {
        return tintPackages;
    }

    public List<PricePackage> ppfPackages() {
        return ppfPackages;
    }

    public List<ProcessStep> processSteps() {
        return processSteps;
    }

    public List<FaqItem> faq() {
        return faq;
    }
}
