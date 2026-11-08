# Prototyping – Übung 7: Deployment (Git, GitHub, Netlify)

Die Moodle-Beschreibung von Übung 7 ist leer (`Material/Moodle-Beschreibung.html`). Die Aufgabe ergibt sich
aus den zwei Tutorials in Woche 7:

1. Projekt mit Git versionieren und auf GitHub veröffentlichen (`Tutorial - Git, GitHub, VSCode.pdf`).
2. Projekt für Netlify konfigurieren und deployen (`Tutorial - Deployment mit SvelteKit und Netlify.pdf`).

Laut Moodle-Seite (Screenshot vom 8. November 2026) gibst du den Link zu deiner deployten App ab.
Fällig: Sonntag, 8. November 2026, 23:59. Die Abgabe erfolgt als Texteingabe online.

## Ordnerstruktur

| Ordner | Inhalt |
|---|---|
| `Material/` | Beide Tutorials (PDF) und die Moodle-Beschreibung |
| `Loesung/movie-watchlist/` | Movie Watchlist aus Übung 6, fertig konfiguriert für Netlify (ohne `node_modules`) |
| `Abgabe/movie-watchlist.zip` | Projekt als ZIP ohne `node_modules` |
| `Abgabe/Links.txt` | Vorlage für die Links zu GitHub-Repository und Netlify-App |
| `Screenshots/` | Kontroll-Screenshots der Netlify-Ausgabe, lokal getestet |
| `Pruefung/` | Testskripte, mit denen die Netlify-Funktion geprüft wurde |

## Bereits erledigt: Tutorial Netlify, Schritt b

Gegenüber Übung 6 haben sich nur drei Dateien geändert:

| Datei | Änderung |
|---|---|
| `package.json`, `package-lock.json` | `npm i -D @sveltejs/adapter-netlify` ausgeführt, installiert ist Version 7.0.1 |
| `vite.config.js` | Import `@sveltejs/adapter-netlify` und `adapter({ edge: false, split: false, publish: 'build' })`, genau wie im Tutorial |
| `README.md` (im Projekt) | Kurzbeschreibung für die Startseite des GitHub-Repositorys |

Hinweise dazu:

- Die Einstellung `compilerOptions.runes` aus dem Projekt-Template bleibt in `vite.config.js` stehen.
  Das Listing im Tutorial zeigt sie nicht, sie hat aber nichts mit dem Adapter zu tun.
- Die `.gitignore` enthielt bereits alle Zeilen aus dem Tutorial (`.output`, `.vercel`, `.netlify`,
  `/.svelte-kit`, `/build`). Sie schliesst ausserdem `.env` aus. Dein DB-Passwort landet also nicht auf GitHub.
- `@sveltejs/adapter-auto` bleibt installiert, wird aber nicht mehr verwendet. Das Tutorial sagt nichts zum Entfernen.

## Schnellweg: Deploy direkt aus dem Repository `re-friend-tracker`

Die GitHub-Integration konnte kein neues Repository für dich anlegen. Deshalb liegt die App fertig im
bestehenden öffentlichen Repository `haredbautista/re-friend-tracker` auf dem Branch
`claude/uebung-2-loesung-g29m9e`:

- `deploy/` ist eine Kopie von `ü7/Loesung/movie-watchlist` mit einem Pfad ohne Umlaute.
- `netlify.toml` im Hauptordner sagt Netlify: Base directory `deploy`, Build command `npm run build`,
  Publish directory `build`. Laut Netlify-Doku überschreiben diese Werte die Einstellungen in der Oberfläche.

Ein frischer Klon dieses Branches baut mit Node 24 fehlerfrei, und die Netlify-Funktion besteht alle 11 Prüfungen.

Deine Schritte auf diesem Weg:

1. MongoDB Atlas: IP-Freigabe `0.0.0.0/0` eintragen, siehe Schritt 2 weiter unten.
2. Auf https://app.netlify.com mit GitHub anmelden (Tutorial Netlify, Schritt c).
3. Projekt aus Git importieren, GitHub wählen und Netlify den Zugriff erlauben (Tutorial Netlify, Schritte d bis f).
4. Das Repository `re-friend-tracker` wählen.
5. Als Branch `claude/uebung-2-loesung-g29m9e` wählen. Die Build-Einstellungen kommen aus `netlify.toml`.
6. Umgebungsvariable anlegen: Key `DB_URI`, Value ist dein Connection String aus Atlas mit echtem DB-User und Passwort.
7. Namen vergeben und deployen. Danach die Adresse `https://<name>.netlify.app` öffnen und `/movies` prüfen.
8. Die Adresse auf Moodle bei «Texteingabe online» einfügen und abgeben.

Der ausführliche Weg nach Tutorial, mit eigenem Repository über VS Code, folgt hier.

## Deine Schritte (Weg nach Tutorial)

Diese Schritte brauchen deine eigenen Konten bei GitHub, Netlify und MongoDB Atlas. Ich konnte sie nicht für dich ausführen.

### 1. Projekt öffnen und lokal testen

1. `ü7.zip` in `projects` entpacken.
2. In VS Code «File → Open Folder» und genau diesen Ordner wählen:
   `/Users/schenkli/Desktop/4_ZHAW/3. Semester/Prototyping/projects/ü7/Loesung/movie-watchlist`
3. Die Zeile `DB_URI=...` aus deiner funktionierenden `.env` von Übung 6 in die `.env` dieses Projekts kopieren.
4. Terminal in VS Code öffnen («Terminal → New Terminal»). Es startet direkt im Projektordner. Dann:

   ```sh
   npm install
   npm run dev
   ```

   Läuft die App lokal mit deinen Filmen, ist der Connection String korrekt.

### 2. MongoDB Atlas: Zugriff von Netlify erlauben

Atlas lässt nur Verbindungen von Adressen auf der IP Access List zu. Laut Netlify-Forum hat Netlify keine festen
ausgehenden IP-Adressen. Deshalb braucht es für Netlify den Eintrag `0.0.0.0/0`. Zusätzlich verbindet sich `db.js`
schon während des Builds mit der Datenbank. Ohne Freigabe schlägt also bereits der Build auf Netlify fehl.

So geht es laut MongoDB-Dokumentation:

1. In Atlas links unter «Security» auf «Database & Network Access» klicken.
2. «IP Access List» öffnen. Steht dort schon `0.0.0.0/0`, ist nichts zu tun.
3. «Add IP Address» klicken, `0.0.0.0/0` eintragen, «Confirm» klicken.

MongoDB warnt, dass `0.0.0.0/0` Zugriff von überall erlaubt, und empfiehlt dann starke Passwörter für alle
Datenbank-User. Entferne den Eintrag wieder, wenn du den Prototyp nicht mehr brauchst.

### 3. Git einrichten (Tutorial Git, Schritte 2 und 3)

```sh
git --version
```

Kommt eine Fehlermeldung, Git laut Tutorial über Homebrew installieren. Danach mit den Daten deines GitHub-Kontos:

```sh
git config --global user.email "deine_email"
git config --global user.name "dein_username"
```

### 4. Auf GitHub veröffentlichen (Tutorial Git, Schritt 4)

1. In VS Code den Tab «Source Control» öffnen und «Publish to GitHub» wählen.
2. Namen vergeben, zum Beispiel `movie-watchlist`, und «Publish to public repository» wählen.
3. Werden Dateien zur Auswahl angezeigt, alle übernehmen. Prüfe, dass `.env` **nicht** in der Liste steht.
4. Auf github.com kontrollieren, dass `package.json` direkt im Hauptordner des Repositorys liegt.

### 5. Auf Netlify deployen (Tutorial Netlify, Schritte c bis i)

1. Netlify-Konto mit GitHub-Anmeldung erstellen und das Repository `movie-watchlist` verknüpfen.
2. Name für die App vergeben.
3. Umgebungsvariable anlegen: Key `DB_URI`, Value ist der Text nach `DB_URI=` aus deiner `.env`.
4. Falls Netlify Build-Einstellungen anzeigt: Build command `npm run build`, Publish directory `build`.
5. Deploy starten und warten, bis die Erfolgsmeldung aus Tutorial Schritt i erscheint.

Die Node-Version musst du nicht einstellen. Netlify verwendet laut Doku im aktuellen Build-Image standardmässig Node 24.
SvelteKit 3 braucht mindestens Node 22.17.

### 6. Online testen

Die Netlify-Adresse öffnen und prüfen:

- Die Startseite zeigt die Anzahl Filme aus deiner Datenbank.
- `/movies` zeigt alle Filme mit Postern.
- Ein Klick auf einen Titel öffnet die Detailseite.
- «Add New Movie» speichert einen Film.
- «Delete Movie» löscht ihn wieder und leitet auf `/movies` weiter.

Optional laut Tutorial, Schritt j: Netlify deployt jeden Push auf den Haupt-Branch (`main` oder `master`) automatisch. Das lässt sich in den
Deploy Settings abschalten.

Danach die beiden Links in `Abgabe/Links.txt` eintragen.

## Fehlersuche

Die folgenden Meldungen habe ich lokal reproduziert:

| Meldung im Build-Log oder Function-Log | Ursache | Lösung |
|---|---|---|
| `env_invalid` … `DB_URI` … `Value is missing` | Umgebungsvariable fehlt in Netlify | `DB_URI` in Netlify eintragen, dann neu deployen |
| `MongoServerSelectionError: Server selection timed out` | Datenbank nicht erreichbar | IP Access List in Atlas prüfen (Schritt 2) |
| `querySrv ENODATA _mongodb._tcp.cluster0.xxxxx.mongodb.net` | Platzhalter statt echtem Connection String | Echten Wert aus Atlas eintragen |

## Was geprüft wurde

Ich habe keinen Zugang zu deinen Konten bei GitHub, Netlify und Atlas. Den echten Deploy auf Netlify konnte
ich deshalb nicht ausführen und kann ihn nicht bestätigen. Geprüft ist die Netlify-Ausgabe, die der Adapter
lokal erzeugt, gegen eine lokale MongoDB mit den 10 Demofilmen.

| Prüfung | Ergebnis |
|---|---|
| Frische Kopie ohne `.env`, `npm ci` und `npm run build` mit Node 24.22.1, `DB_URI` nur als Umgebungsvariable | Build erfolgreich, Ausgabe in `build/` und `.netlify/v1/` |
| Gleicher Build mit Node 22.23.4 | erfolgreich |
| Netlify-Funktion `sveltekit-render.mjs` direkt aufgerufen (`Pruefung/fn-test.mjs`), Node 24 und Node 22 | je 11 von 11 bestanden |
| `DB_URI` steht nicht im Build-Ergebnis | bestätigt, der Wert wird zur Laufzeit aus der Umgebung gelesen |
| Browsertest mit Chromium über einen lokalen Server, der wie Netlify zuerst `build/` ausliefert (`Pruefung/netlify-sim.mjs`) | 8 von 8 bestanden, keine JavaScript- oder Netzwerkfehler |

Die 11 Funktionsprüfungen: Startseite, Filmliste mit allen Titeln aus der DB, Detailseite, 404 bei unbekannter ID,
Film erfassen, Erfolgsmeldung, Watchlist setzen, Löschen mit Weiterleitung 303 auf `/movies`, Film aus der DB entfernt.

## Abgabe

Laut Moodle-Seite: «Gib den Link zu deiner deployten App ab.» Fällig am Sonntag, 8. November 2026, 23:59.
Abgegeben wird also nur die Netlify-Adresse, als Texteingabe online.

- `Abgabe/Links.txt`: Vorlage für den Link.
- `Abgabe/movie-watchlist.zip`: Projekt ohne `node_modules`, falls zusätzlich der Code verlangt wird.

## Quellen

- Tutorials von Moodle in `Material/`
- SvelteKit-Doku zum Netlify-Adapter: https://svelte.dev/docs/kit/adapter-netlify
- MongoDB Atlas, IP Access List: https://www.mongodb.com/docs/atlas/security/ip-access-list/
- Netlify, Node-Version festlegen: https://docs.netlify.com/build/configure-builds/manage-dependencies/
- Netlify, Standard-Software im Build-Image (Node 24): https://docs.netlify.com/build/configure-builds/available-software-at-build-time/
- GitHub Pages ist reines Static Hosting und kann die Server-Teile der App nicht ausführen: https://docs.github.com/en/pages/getting-started-with-github-pages/what-is-github-pages
- Netlify-Forum zu festen IP-Adressen, Community-Antwort, keine offizielle Doku: https://answers.netlify.com/t/netlify-functions-ip/54054
