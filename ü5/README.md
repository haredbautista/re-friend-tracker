# Prototyping – Übung 5: Movie Watchlist mit SvelteKit (Teil 1)

## Ordnerstruktur

| Ordner | Inhalt |
|---|---|
| `Material/` | Aufgabenstellung (PDF), Vorlesungsfolien 5, Routing-Starter aus der Kurzübung, Moodle-Beschreibung |
| `Loesung/movie-watchlist/` | Komplettes SvelteKit-Projekt (ohne `node_modules`) |
| `Loesung/generate-posters.mjs` | Hilfsskript, mit dem die SVG-Poster erzeugt wurden (nicht Teil der App) |
| `Abgabe/movie-watchlist.zip` | Abgabe für Moodle: das Projekt als ZIP, ohne `node_modules` (per Listing geprüft) |
| `Screenshots/` | Kontroll-Screenshots aus dem Browser |

## Projekt starten

```sh
cd Loesung/movie-watchlist
npm install
npm run dev
```

Bootstrap wird vom CDN geladen, es braucht eine Internetverbindung.

## Aufbau der App «ReelStack»

| Datei | Zweck |
|---|---|
| `src/app.html` | Bootstrap 5.3.2 CSS und JS eingebunden, exakt wie auf Folie 6. Dunkles Theme über `data-bs-theme="dark"`. |
| `src/routes/+layout.svelte` | Bootstrap-Navbar, gilt für alle Pages. Pages werden mit `{@render children()}` eingefügt. Importiert `styles.css`. |
| `src/routes/+page.svelte` | Hauptseite `/` mit Begrüssung und Link zu den Movies |
| `src/routes/movies/+page.svelte` | Page `/movies`: Suchfeld (`bind:value`), Checkbox «Nur Watchlist» (`bind:checked`), Grid mit `{#each}` |
| `src/routes/about/+page.svelte` | Page `/about` mit Kurzbeschreibung |
| `src/lib/components/MovieCard.svelte` | Komponente für einen Film. Props: `movie` (Objekt), `inWatchlist`, `onToggle` |
| `src/lib/data/movies.js` | 10 fiktive Filme mit `title`, `year`, `length`, `genre`, `poster`, `actors` |
| `static/images/*.svg` | Poster, lokal generiert |

Das Grid verwendet `col-sm-6 col-md-4 col-lg-3`: 1 Spalte auf dem Handy, 2 ab 576 px, 3 ab 768 px,
4 ab 992 px.

Zu den Beispieldaten: Das Aufgabenblatt schlägt ChatGPT für Daten und DALL-E für Bilder vor.
Die Filme hier wurden direkt beim Lösen der Übung erfunden, die Poster sind einfache SVG-Grafiken
(Farbverlauf plus Titel) aus `generate-posters.mjs`. So enthält die Abgabe keine fremden Bilder.

## Was geprüft wurde

Die App wurde gebaut (`npm run build`) und mit Chromium (Playwright) getestet:

- Startseite rendert, Bootstrap ist aktiv (Navbar-Layout), Navigation zu `/movies` und `/about` funktioniert.
- `/movies` zeigt 10 Cards, alle Poster laden, bei 1200 px stehen 4 Cards pro Zeile.
- Suche nach «stern» liefert genau «Sternenwanderer», leeres Suchfeld zeigt wieder alle 10.
- Watchlist-Button wechselt den Zustand, Zähler stimmt, Filter «Nur Watchlist» zeigt nur die markierten Filme.
- Bei 420 px Breite: eine Spalte, Navbar-Toggler sichtbar.
- Keine Fehler in der Browser-Konsole.

Die Integrity-Hashes der Bootstrap-Links wurden aus den heruntergeladenen CDN-Dateien neu berechnet
und stimmen mit Folie 6 überein.
