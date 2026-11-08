# ReelStack – Movie Watchlist

Prototyp aus dem Modul Prototyping (ZHAW), gebaut mit SvelteKit, Bootstrap und MongoDB.
Übung 7: Deployment auf Netlify.

## Lokal starten

1. Datei `.env` im Projektordner anlegen (Vorlage: `.env.example`) und den eigenen
   MongoDB Connection String eintragen:

   ```
   DB_URI=mongodb+srv://<db_username>:<db_password>@cluster0.xxxxx.mongodb.net/?retryWrites=true&w=majority
   ```

2. Abhängigkeiten installieren und starten:

   ```sh
   npm install
   npm run seed   # optional: Demodaten in ScreenStackDB.movies einfügen
   npm run dev
   ```

## Deployment auf Netlify

- Adapter: `@sveltejs/adapter-netlify`, konfiguriert in `vite.config.js`
  mit `edge: false`, `split: false`, `publish: 'build'`.
- Build command: `npm run build`, Publish directory: `build`.
- In Netlify muss die Umgebungsvariable `DB_URI` gesetzt sein.
  Die Datei `.env` wird nicht auf GitHub hochgeladen (siehe `.gitignore`).
- Die MongoDB muss Verbindungen von Netlify zulassen (IP Access List in MongoDB Atlas).

Doku zum Adapter: https://svelte.dev/docs/kit/adapter-netlify
