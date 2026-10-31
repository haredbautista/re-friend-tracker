# Prototyping – Übung 6: Movie Watchlist mit SvelteKit (Teil 2, MongoDB)

Fällig laut Moodle: Dienstag, 27. Oktober 2026, 23:59.

## Ordnerstruktur

| Ordner | Inhalt |
|---|---|
| `Material/` | Aufgabenstellung (PDF), Vorlesungsfolien 6, `db.js`, `movies.json`, `placeholder.jpg`, README aus Moodle, MusicDB-Demo und -Starter, Moodle-Beschreibung |
| `Loesung/movie-watchlist/` | Komplettes SvelteKit-Projekt (ohne `node_modules`), aufbauend auf der eigenen Lösung von Übung 5 |
| `Abgabe/movie-watchlist.zip` | Abgabe für Moodle: Projekt als ZIP ohne `node_modules` (per Listing geprüft) |
| `Screenshots/` | Kontroll-Screenshots aus dem Browser |

## Bevor die App läuft: MongoDB-Zugang eintragen

Die Zugangsdaten zu deiner MongoDB sind nicht Teil der Lösung. Du musst sie einmalig eintragen:

1. In MongoDB Atlas (oder Compass) die Datenbank `ScreenStackDB` mit der Collection `movies` anlegen (Aufgabe a).
2. Connection String kopieren (Aufgabe b) und in der Datei `.env` im Projektordner eintragen,
   `<db_username>` und `<db_password>` durch die Daten eines Datenbank-Users ersetzen:

   ```
   DB_URI=mongodb+srv://<db_username>:<db_password>@cluster0.xxxxx.mongodb.net/?retryWrites=true&w=majority
   ```

   Die Datei `.env` enthält bereits diese Platzhalterzeile. `.env.example` ist eine Kopie zum Nachschlagen.
3. Abhängigkeiten installieren und App starten:

   ```sh
   cd Loesung/movie-watchlist
   npm install
   npm run seed     # Demodaten einfügen, siehe unten (optional)
   npm run dev
   ```

### Demodaten einfügen

Zwei Wege, beide führen zum gleichen Ergebnis:

- **Manuell** wie im Aufgabenblatt: In Atlas unter `movies` auf «Add Data» → «Insert Document» und den Inhalt
  von `movies.seed.json` einfügen. Das ist die Datei `movies.json` aus Moodle, nur die Poster-Pfade zeigen auf
  die mitgelieferten SVG-Poster (`/images/Altura.svg` usw.), weil die PNG-Bilder der Beispiellösung nicht auf
  Moodle liegen.
- **Per Skript**: `npm run seed` liest `DB_URI` aus `.env` und fügt die 10 Filme aus `movies.seed.json` ein.
  Ist die Collection bereits gefüllt, fügt das Skript nichts ein.

## Lösungsweg

| Teil | Anforderung | Umsetzung |
|---|---|---|
| a) | DB `ScreenStackDB`, Collection `movies`, Demodaten | Daten in `movies.seed.json` plus `seed.js` (`npm run seed`) |
| b) | Connection String | wird in `.env` als `DB_URI` eingetragen |
| c) | `src/env.js` und `.env` anlegen, `db.js` in `lib` speichern | `src/env.js` exakt wie im Aufgabenblatt (`defineEnvVars({ DB_URI: {} })`). `src/lib/db.js` aus Moodle, eine Zeile angepasst: `import { DB_URI } from "$app/env/private"` statt `$env/static/private`, so wie auf Folie 10 der Vorlesung. Mit diesem Import baut das Projekt mit SvelteKit 3 fehlerfrei. |
| d) | `npm install mongodb` | `mongodb` ist in `package.json` als Dependency eingetragen |
| e) | Daten mit der `load`-Funktion laden | `src/routes/movies/+page.server.js` ruft `db.getMovies()` auf, die Page liest `data.movies` |
| f) | Detailseite mit ID als Pfadparameter | Ordner `src/routes/movies/[movie_id]/`. Die `load`-Funktion ruft `db.getMovie(params.movie_id)` auf, bei unbekannter ID gibt es einen 404-Fehler. Titel und Poster auf der Card verlinken auf `/movies/<id>`. |
| g) | Seite `/movies/create` mit Formular (Name, Jahr, Dauer) | Form Action `?/create` in `src/routes/movies/create/+page.server.js`, Erfolgsmeldung über `form?.success` (Folie 16). `createMovie()` ergänzt Platzhalterbild, leere Cast-Liste und `watchlist: false`. Button «Add New Movie» auf `/movies`. |
| h) (optional) | Film auf der Detailseite löschen, danach Redirect | Form Action `?/delete` mit verstecktem `id`-Feld (Folie 17), dann `redirect(303, "/movies")` |
| Zusatz | Watchlist-Flag aus der DB nutzen | Button auf jeder Card ist ein Formular (`?/toggleWatchlist`), das `db.updateMovie()` aufruft. Der Filter «Nur Watchlist» wertet das Feld `watchlist` aus. |

Entfernt gegenüber Übung 5: die statische Datei `src/lib/data/movies.js`. Die Daten kommen jetzt
ausschliesslich aus der Datenbank.

## Was geprüft wurde

Da hier kein Zugang zu MongoDB Atlas besteht, lief der Test gegen eine lokale MongoDB
(`mongodb-memory-server`, Verbindung `mongodb://127.0.0.1:27017`). Der Code ist für Atlas identisch,
nur `DB_URI` ist anders. Geprüft mit Chromium (Playwright), alle 16 Prüfungen bestanden:

- Startseite zeigt die Anzahl Filme aus der DB.
- `/movies` zeigt die 10 Demodaten, Poster laden, Button «Add New Movie» vorhanden.
- Klick auf einen Titel öffnet `/movies/<24-stellige ID>` mit Titel, Jahr, Dauer, Cast und Back-Link.
- Unbekannte ID liefert 404.
- Formular «Add a Movie» mit «Barbie, 2023, 114 Minuten»: Erfolgsmeldung, neuer Film mit Platzhalter-Poster
  und leerem Cast, Liste zeigt 11 Filme.
- Watchlist-Button bleibt nach einem Reload gesetzt (in der DB gespeichert), Filter zeigt nur diesen Film.
- «Delete Movie» löscht den Film, leitet auf `/movies` weiter, Liste zeigt wieder 10 Filme.
- Keine JavaScript-Fehler im Browser.

Die Bootstrap-Links in `src/app.html` sind unverändert aus Übung 5 (Version 5.3.2 wie auf den Folien,
Integrity-Hashes gegen die CDN-Dateien geprüft).
