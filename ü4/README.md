# Prototyping – Übung 4: Guessing Game (Zahlenraten)

## Ordnerstruktur

| Ordner | Inhalt |
|---|---|
| `Material/` | Aufgabenstellung (PDF), Vorlesungsfolien 4, Moodle-Beschreibung |
| `Loesung/guessing-game/` | Komplettes SvelteKit-Projekt mit der Lösung (ohne `node_modules`) |
| `Abgabe/+page.svelte` | Die Datei, die laut Aufgabenblatt auf Moodle abzugeben ist |
| `Abgabe/guessing-game (ganzes Projekt).zip` | Optional: das ganze Projekt als ZIP |
| `Screenshots/` | Kontroll-Screenshots aus dem Browser |

## Projekt starten

```sh
cd Loesung/guessing-game
npm install
npm run dev
```

## Abgabe auf Moodle

Nur `Abgabe/+page.svelte` hochladen. Für die Zusatzaufgabe wurden keine weiteren Dateien geändert.

## Lösungsweg

Umgesetzt wurde genau das Verhalten aus den Screenshots im Aufgabenblatt:

| Situation | Verhalten | Umsetzung |
|---|---|---|
| Start | Input leer, «Raten» aktiv, «Spiel neu starten» deaktiviert | `initGame()` wird beim Laden aufgerufen; `disabled={gameFinished}` und `disabled={!gameFinished}` |
| «Raten» ohne Eingabe | «Bitte gib eine Zahl ein.» | `userInput` ist bei leerem Zahlenfeld `null` |
| Eingabe zu klein | «Die gesuchte Zahl ist grösser» | `secretNumber > userInput` |
| Eingabe zu gross | «Die gesuchte Zahl ist kleiner» | `secretNumber < userInput` |
| Treffer | «Getroffen!», «Raten» deaktiviert, «Spiel neu starten» aktiv | `gameFinished = true` |
| «Spiel neu starten» | Spiel wird neu initialisiert | `initGame()` |

Die vier Variablen `userInput`, `secretNumber`, `output`, `gameFinished` sind mit `$state` reaktiv.
Die Geheimzahl wird mit `Math.floor(Math.random() * (max - min + 1)) + min` erzeugt
(Quelle: https://www.w3schools.com/jsref/jsref_random.asp). Das Eingabefeld ist über `bind:value`
mit `userInput` verknüpft.

Zusatzaufgabe (optional), umgesetzt ohne die Grundanforderungen zu verändern:

- Anzahl Versuche wird unterhalb der Ausgabe angezeigt.
- Enter-Taste im Eingabefeld löst `guess()` aus (`onkeydown`).

## Was geprüft wurde

Die App wurde gebaut und mit Chromium (Playwright) durchgespielt. Da die Geheimzahl zufällig ist,
hat der Test die Rückmeldungen «grösser» / «kleiner» ausgewertet und sich so an die Zahl herangetastet.
Geprüft wurden: Startzustand, leere Eingabe, beide Hinweistexte, «Getroffen!» mit Button-Zuständen,
Neustart (Feld leer, Text leer, Buttons zurückgesetzt), Versuche-Zähler und Enter-Taste. Alle Prüfungen
bestanden. Beim Testlauf wurde die Zahl in 4 Versuchen gefunden, deshalb gibt es keinen Screenshot für
den Fall «grösser»; der Text selbst wurde im Test geprüft.
