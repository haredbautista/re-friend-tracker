# Prototyping – Übung 2: CSS, Bootstrap

Fällig: Dienstag, 29. September 2026, 23:59 (Moodle).

## Ordnerstruktur

| Ordner | Inhalt |
|---|---|
| `Material/` | Unveränderte Originale aus Moodle: Aufgabenstellung (PDF), Vorlesungsfolien (PDF), Moodle-Beschreibung, beide Starter-Ordner |
| `Loesung/Zombie Apocalypse/` | Aufgabe 1, fertig gelöst (nur `style.css` geändert) |
| `Loesung/Special Travel Bootstrap/` | Aufgabe 2, fertig gelöst |
| `Abgabe/` | Fertige ZIP-Dateien für die Moodle-Abgabe |
| `Screenshots/` | Kontroll-Screenshots der gelösten Seiten (Chromium, verschiedene Breiten) |

## Öffnen in Visual Studio Code

1. Ordner `Loesung/Zombie Apocalypse` bzw. `Loesung/Special Travel Bootstrap` in VS Code öffnen.
2. `index.html` mit der Live Preview (oder Live Server) öffnen.
3. Für Aufgabe 2 wird Bootstrap vom CDN geladen, es braucht also eine Internetverbindung.

## Abgabe auf Moodle

Gemäss Aufgabenblatt muss die ZIP-Datei die beiden kompletten Ordner enthalten, jeweils als ZIP.
`Abgabe/Uebung2_Abgabe.zip` ist genau so aufgebaut und kann direkt hochgeladen werden:

```
Uebung2_Abgabe.zip
├── Zombie Apocalypse.zip            (kompletter Ordner Aufgabe 1)
└── Special Travel Bootstrap.zip     (kompletter Ordner Aufgabe 2)
```

## Aufgabe 1 – CSS (Zombie Apocalypse)

Bearbeitet wurde nur `style.css`. `index.html` und `img/` sind unverändert (Vorgabe im Aufgabenblatt).

| Teil | Anforderung | Umsetzung in `style.css` |
|---|---|---|
| b) | Überschrift rot | `h1 { color: red; }` |
| b) | Klasse `important` in Grossbuchstaben | `.important { text-transform: uppercase; }` |
| c) | Hintergrund grau, Text weiss | `body { background-color: gray; color: white; }` |
| c) | `#main`: schwarz, roter Rahmen, 50 % breit, 50 px Abstand oben, 10 px Padding | `#main { background-color: black; border: 2px solid red; width: 50%; margin: 50px auto; padding: 10px; }` |
| d) | Ecken abrunden, mit `margin: auto` zentrieren | `border-radius: 10px;` und `margin: 50px auto;` (oben/unten 50 px, links/rechts auto) |
| e) | List Items: 10 px Abstand oben, fett | `li { margin-top: 10px; font-weight: bold; }` |
| e) | Aufzählungszeichen durch `img/bullet.png` ersetzen | `ul { list-style-image: url("img/bullet.png"); }` |
| f) | Bilder rund | `img { border-radius: 50%; }` (Bilder sind 100 × 100 px, 50 % ergibt einen Kreis) |
| g) | Bilder horizontal mit Flexbox | `.image-list { display: flex; justify-content: space-between; }` |

Hinweis zu c) und d): Das Aufgabenblatt verlangt in c) `margin` 50 px nach oben und in d) `margin: auto`
zum Zentrieren. `margin: 50px auto;` erfüllt beides in einer Deklaration.

## Aufgabe 2 – Bootstrap (Special Travel)

| Teil | Anforderung | Umsetzung |
|---|---|---|
| b) | Bootstrap CSS und JS Bundle einbinden | `<link>` im `<head>`, `<script>` am Ende von `<body>`. Version 5.3.3 vom jsDelivr-CDN, identisch mit dem Minimalbeispiel auf Folie 31/32. |
| c) | Grid mit zwei Zeilen (2 bzw. 3 Zellen), responsive | Zeile 1: `row` mit zwei `col-sm-6`. Zeile 2: `row` mit drei `col-md-4 tile`. |
| d) | Bootstrap Navigation Bar | `navbar navbar-expand-md bg-body-tertiary` mit `navbar-brand`, `navbar-nav`, `nav-link` und Toggler. Auf allen drei Seiten. |
| e) | Carousel für die Bilder | Beispiel «With controls» aus der Bootstrap-Doku, angepasst auf `pyramids.jpg` und `sphinx.jpg`, mit `data-bs-ride="carousel"`. |
| f) | Kontaktformular in `contact.html` | `form-label`, `form-control`, `form-select`, Layout mit `row` / `col-md-6` / `mb-3`, Button `btn btn-primary`. Felder wie im Vorgabebild: Name, Email address, Phone, Country (France, Germany, Switzerland vorausgewählt), Message, Submit. |

Breakpoints (aus dem Aufgabenblatt abgeleitet):

| Fensterbreite | Zeile 1 (Angebote / Tipps) | Zeile 2 (Hotels / Apartments / Villas) |
|---|---|---|
| ≥ 768 px | nebeneinander | nebeneinander |
| 576 bis 767 px | nebeneinander | untereinander |
| < 576 px | untereinander | untereinander |

`col-sm-6` schaltet ab 576 px (Breakpoint `sm`), `col-md-4` ab 768 px (Breakpoint `md`).
Quelle: https://getbootstrap.com/docs/5.3/layout/breakpoints/

`destinations.html` war im Starter ohne `style.css`-Link. Bootstrap und die Navbar wurden auch dort
ergänzt, damit die Navigation auf allen Seiten gleich aussieht. Der Inhalt («Under construction») blieb.

## Was geprüft wurde

- Die SHA-384-Integrity-Hashes in den `<link>`- und `<script>`-Tags wurden aus den vom CDN
  heruntergeladenen Dateien neu berechnet und stimmen mit den Folien überein.
- Beide Seiten wurden mit Chromium (Playwright) gerendert. Für Aufgabe 1 wurden alle berechneten
  CSS-Werte ausgelesen (z. B. `#main` exakt zentriert, alle vier Bilder auf derselben Höhe).
  Für Aufgabe 2 wurde die Spaltenanordnung bei 1000 px, 700 px und 500 px Breite geprüft
  (siehe Tabelle oben) sowie Navbar, Carousel und die fünf Formularfelder.
- Die Screenshots dazu liegen in `Screenshots/`.
