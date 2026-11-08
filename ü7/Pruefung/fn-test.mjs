// Ruft die von adapter-netlify erzeugte Funktion direkt auf (wie Netlify es zur Laufzeit tut).
import { pathToFileURL } from 'node:url';
import { createRequire } from 'node:module';
const proj = process.argv[2];
const require = createRequire(proj + '/package.json');
const { MongoClient, ObjectId } = require('mongodb');
const fn = (await import(pathToFileURL(proj + '/.netlify/v1/functions/sveltekit-render.mjs'))).default;
const ctx = { ip: '127.0.0.1' };
const base = 'http://localhost:8888';
let pass = 0, fail = 0;
const check = (name, ok, extra = '') => { ok ? pass++ : fail++; console.log((ok ? 'PASS ' : 'FAIL ') + name + (extra ? '  ' + extra : '')); };
const get = (p) => fn(new Request(base + p), ctx);
const post = (p, data) => fn(new Request(base + p, { method: 'POST', body: new URLSearchParams(data), headers: { origin: base, accept: 'text/html,application/xhtml+xml,*/*;q=0.8', 'content-type': 'application/x-www-form-urlencoded' }, redirect: 'manual' }), ctx);

const client = new MongoClient(process.env.DB_URI); await client.connect();
const col = client.db('ScreenStackDB').collection('movies');

let r = await get('/'); let html = await r.text();
check('GET / -> 200', r.status === 200, String(r.status));
check('Startseite zeigt "10 Filme"', html.includes('10 Filme'));

r = await get('/movies'); html = await r.text();
check('GET /movies -> 200', r.status === 200);
const titles = (await col.find().toArray()).map(m => m.title);
check('alle 10 Titel aus der DB im HTML', titles.every(t => html.includes(t)), titles.length + ' Titel');

const altura = await col.findOne({ title: 'Das Geheimnis von Altura' });
r = await get('/movies/' + altura._id); html = await r.text();
check('GET /movies/<id> -> 200 mit Titel und Cast', r.status === 200 && html.includes('Das Geheimnis von Altura') && html.includes('Lena Herzog'));

r = await get('/movies/000000000000000000000000');
check('unbekannte ID -> 404', r.status === 404, String(r.status));

r = await post('/movies/create?/create', { title: 'Netlify Test', year: '2026', length: '90 Minuten' }); html = await r.text();
const created = await col.findOne({ title: 'Netlify Test' });
check('POST ?/create speichert Film in DB', r.status === 200 && !!created, String(r.status));
check('Erfolgsmeldung im HTML', html.includes('Netlify Test'));

r = await post('/movies?/toggleWatchlist', { id: String(created._id), watchlist: 'true' });
const toggled = await col.findOne({ _id: created._id });
check('POST ?/toggleWatchlist setzt watchlist=true', toggled.watchlist === true, String(r.status));

r = await post('/movies/' + created._id + '?/delete', { id: String(created._id) });
check('POST ?/delete -> 303 nach /movies', r.status === 303 && r.headers.get('location') === '/movies', r.status + ' ' + r.headers.get('location'));
check('Film aus DB gelöscht', (await col.countDocuments()) === 10);

await client.close();
console.log(`\n${pass} bestanden, ${fail} fehlgeschlagen`);
process.exit(fail ? 1 : 0);
