// Demodaten in die MongoDB einfügen (Aufgabe a).
// Alternative zum manuellen «Insert Document» in Atlas oder Compass.
//
// Aufruf im Projektordner (liest DB_URI aus .env):
//   npm run seed
//
// Läuft ausserdem automatisch bei "npm run build", also auch beim Deploy auf Netlify.
// Dort kommt DB_URI aus den Umgebungsvariablen von Netlify.
//
// Es werden nur Daten eingefügt, wenn die Collection "movies" noch leer ist.
import { MongoClient } from 'mongodb';
import { readFileSync } from 'node:fs';

const uri = process.env.DB_URI;
if (!uri) {
	console.error('DB_URI fehlt. Bitte .env im Projektordner anlegen (siehe README).');
	process.exit(1);
}

const movies = JSON.parse(readFileSync(new URL('./movies.seed.json', import.meta.url), 'utf8'));

const client = new MongoClient(uri);
await client.connect();
const collection = client.db('ScreenStackDB').collection('movies');

const existing = await collection.countDocuments();
if (existing > 0) {
	console.log(`Collection "movies" enthält bereits ${existing} Dokumente. Es wurde nichts eingefügt.`);
} else {
	const result = await collection.insertMany(movies);
	console.log(`${result.insertedCount} Filme in ScreenStackDB.movies eingefügt.`);
}

await client.close();
