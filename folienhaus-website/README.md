# Das Folienhaus – Website (Java / Spring Boot)

Neue Firmenwebsite für **Das Folienhaus**, Hohenkräher-Brühl 15a, 78259 Mühlhausen-Ehingen
(Autofolierung, Lackschutzfolierung, Scheibentönung, Detailing, Lackierung & Pulverbeschichtung).

Die Seite ist eine serverseitig gerenderte Java-Anwendung: **Spring Boot 3.5 + Thymeleaf**, ohne Datenbank,
ohne externe Skripte oder Tracking. Alle Firmendaten liegen zentral in `src/main/resources/application.yml`.

## Seiten

| Pfad | Inhalt |
|---|---|
| `/` | Startseite: Hero, Leistungen, Ablauf, Vorteile, Regionen, FAQ |
| `/leistungen` | Leistungsübersicht |
| `/leistungen/{slug}` | Detailseiten: `autofolierung`, `lackschutzfolierung`, `scheibentoenung`, `detailing`, `lackierung-pulverbeschichtung` |
| `/preise` | Preisorientierung + FAQ |
| `/ueber-uns` | Betrieb, Gründer, Arbeitsweise |
| `/kontakt` | Kontaktdaten, Öffnungszeiten, Anfrageformular (Validierung, Honeypot, optionaler E-Mail-Versand) |
| `/autofolierung/{region}` | Regionale Landingpages: Singen, Bodensee, Villingen-Schwenningen, Tuttlingen, Donaueschingen, Balingen, Tübingen, Freiburg |
| `/impressum`, `/datenschutz` | Rechtliches |
| `/sitemap.xml`, `/robots.txt` | Für Suchmaschinen (Basis-URL aus `company.base-url`) |
| `/actuator/health` | Health-Check für Hosting/Monitoring |

Jede Seite enthält Meta-Description, Open-Graph-Tags und JSON-LD (`schema.org/AutoBodyShop`) mit Adresse, Telefon und Öffnungszeiten.

## Starten

Voraussetzung: Java 21 und Maven 3.9.

```bash
cd folienhaus-website
mvn spring-boot:run          # Entwicklung, http://localhost:8080
mvn package                  # Tests + ausführbares JAR in target/
java -jar target/folienhaus-website-1.0.0.jar
```

Mit Docker:

```bash
docker build -t folienhaus-website .
docker run -p 8080:8080 folienhaus-website
```

## Kontaktformular per E-Mail weiterleiten

Ohne Mailserver werden Anfragen nur im Log protokolliert. Für den Versand an `info@das-folienhaus.de`
SMTP-Zugangsdaten als Umgebungsvariablen setzen:

```bash
SPRING_MAIL_HOST=smtp.example.de
SPRING_MAIL_PORT=587
SPRING_MAIL_USERNAME=...
SPRING_MAIL_PASSWORD=...
SPRING_MAIL_PROPERTIES_MAIL_SMTP_AUTH=true
SPRING_MAIL_PROPERTIES_MAIL_SMTP_STARTTLS_ENABLE=true
```

## Vor dem Livegang zu prüfen

Die Firmendaten stammen aus öffentlich auffindbaren Angaben (Suchmaschinen-Snippets der Seiten
`das-folienhaus.de/kontakt` und `/impressum`, Stand September 2026) und konnten nicht direkt auf der Website gegengeprüft werden.
Bitte vom Betrieb bestätigen lassen:

- [ ] **Telefonnummer**: Kontaktseite nennt `+49 151 70666511`, das Impressum-Snippet `+49 176 71183405`. Aktuell ist die 151-Nummer hinterlegt.
- [ ] **Öffnungszeiten** (Mo–Fr 09:00–12:00 und 13:00–18:00 Uhr)
- [ ] **Impressum**: USt-IdNr. und ggf. Aufsichtsbehörde sind Platzhalter in `application.yml`
- [ ] **Datenschutzerklärung**: Entwurf ohne Tracking/Cookies – bei Änderungen (Hosting-Anbieter, Analytics) anpassen und juristisch prüfen lassen
- [ ] **Preise**: „Vollfolierung ab ca. 1.800 €“ und „3–5 Werktage“ laut Preisseite des Betriebs; alle weiteren Positionen stehen auf „auf Anfrage“
- [ ] **Leistungsbeschreibungen** in `ContentService.java` fachlich freigeben; eigene Fotos (Referenzen, Werkstatt, Team) ergänzen

## Projektstruktur

```
src/main/java/de/folienhaus/website
├── config/      CompanyProperties (Firmendaten), globale Model-Attribute, Ressourcen-Caching
├── domain/      Records: ServiceOffering, Region, PriceItem, FaqItem, ProcessStep, ContactRequest
├── service/     ContentService (redaktionelle Inhalte), ContactService (Anfragen, Mailversand)
└── web/         Controller (Seiten, Leistungen, Regionen, Kontakt, SEO), ContactForm, StructuredData
src/main/resources
├── templates/   Thymeleaf-Seiten + fragments/layout.html (Header, Footer, CTA, FAQ, Karten)
├── static/      style.css, main.js, logo.svg, favicon.svg
└── application.yml
src/test/java    MockMvc-Tests für alle Seiten, das Kontaktformular und die JSON-LD-Ausgabe
```
