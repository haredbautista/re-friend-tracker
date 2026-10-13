# Prototyping – Übung 3: Taschenrechner (JavaScript Funktionen, SvelteKit)

## Ordnerstruktur

| Ordner | Inhalt |
|---|---|
| `Material/` | Aufgabenstellung (PDF), Vorlesungsfolien 3, Starter-Datei `+page.svelte`, Moodle-Beschreibung, PT Cheat Sheet |
| `Loesung/calculator/` | Komplettes SvelteKit-Projekt mit der Lösung (ohne `node_modules`) |
| `Abgabe/+page.svelte` | Die Datei, die laut Aufgabenblatt auf Moodle abzugeben ist |
| `Abgabe/calculator (ganzes Projekt).zip` | Optional: das ganze Projekt als ZIP |
| `Screenshots/` | Kontroll-Screenshots aus dem Browser |

## Projekt starten (Visual Studio Code)

Das Projekt wurde so erstellt, wie auf Folie 31 der Vorlesung beschrieben:

```sh
npx sv create calculator      # SvelteKit minimal, kein TypeScript, keine Add-ons, npm
```

Zum Starten im Ordner `Loesung/calculator`:

```sh
npm install
npm run dev
```

## Abgabe auf Moodle

Nur die Datei `Abgabe/+page.svelte` hochladen (Aufgabenblatt: «Gib die Datei src/routes/+page.svelte auf Moodle ab»).

## Lösungsweg

Gemäss Hinweis im Aufgabenblatt wurde nur der `<script>`-Teil angepasst. HTML und `<style>` sind
unverändert aus dem Starter übernommen (per `diff` geprüft, nur die Zeilenenden unterscheiden sich).

| Teil | Anforderung | Umsetzung |
|---|---|---|
| a) | Variablen `a = 0`, `b = 0`, `operation = null` | Als normale Variablen im `<script>`. Nur `output` ist `$state`, weil nur dieser Wert angezeigt wird. |
| a) | Vier Rechenfunktionen | `add`, `subtract`, `multiply`, `divide` |
| a) | `setDigit(digit)` | Ohne Operation wird `a` gesetzt, mit Operation `b`. |
| a) | `setOperation(op)` | Speichert den Operator als String in `operation`. |
| a) | `calculate()` | Wählt je nach `operation` eine der vier Funktionen und schreibt das Ergebnis in `output`. |
| a) | `allClear()` | Setzt `a`, `b`, `operation`, `output` auf die Anfangswerte. |
| b) | Rechnung im Display anzeigen | `output += digit` bzw. `output += op`. Die Start-0 wird vor der ersten Ziffer gelöscht. |
| c) | Mehrstellige Zahlen | `a = a * 10 + digit` bzw. `b = b * 10 + digit` |
| d) | Mit dem Ergebnis weiterrechnen | In `calculate()`: `a = result`, `b = 0`, `operation = null` |
| e) | Zwischenergebnis automatisch bei zweiter Operation | In `setOperation()`: falls bereits eine Operation gesetzt ist, zuerst `calculate()` aufrufen. |
| f) (optional) | Kein AC nach einer Berechnung nötig | Hilfsvariable `resultDisplayed`. Folgt auf `=` eine Ziffer, wird `allClear()` aufgerufen (Fall 2). Folgt eine Operation, wird das Ergebnis in `a` weiterverwendet (Fall 1). |

## Was geprüft wurde

Die App wurde gebaut (`npm run build`) und mit Chromium (Playwright) bedient. Jede Eingabetabelle aus
dem Aufgabenblatt wurde Taste für Taste nachgespielt und die Anzeige nach jeder Taste verglichen:

| Testfall | Erwartete Endanzeige | Ergebnis |
|---|---|---|
| b) `1 + 2 =` | 3 | bestanden |
| d) `1 2 + 3 4 = * 2 =` | 92 | bestanden |
| e) `1 2 + 3 4 * 2 =` | 92 | bestanden |
| `5 6 / 2 = + 8 7 =` | 115 | bestanden |
| `3 - 1 * 4 =` (kein Punkt vor Strich) | 8 | bestanden |
| f) Tabelle Seite 7 ohne AC (`7-3=`, `13*2=`, `+5-4=`, `AC`) | 27, dann 0 | bestanden |
| f) Fall 2: `5 6 / 2 =` dann `8 7` | 87 | bestanden |

Alle Zwischenanzeigen entsprachen den Tabellen im Aufgabenblatt.
