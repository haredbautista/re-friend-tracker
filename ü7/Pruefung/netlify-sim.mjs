// Lokale Nachbildung von Netlify: zuerst statische Dateien aus build/ (preferStatic),
// sonst Aufruf der Funktion .netlify/v1/functions/sveltekit-render.mjs.
import http from 'node:http';
import fs from 'node:fs';
import path from 'node:path';
import { pathToFileURL } from 'node:url';
const proj = process.argv[2]; const port = Number(process.argv[3] || 8888);
const fn = (await import(pathToFileURL(proj + '/.netlify/v1/functions/sveltekit-render.mjs'))).default;
const types = { '.js': 'text/javascript', '.css': 'text/css', '.svg': 'image/svg+xml', '.jpg': 'image/jpeg', '.txt': 'text/plain', '.json': 'application/json' };
http.createServer(async (req, res) => {
  const url = new URL(req.url, `http://localhost:${port}`);
  const file = path.join(proj, 'build', decodeURIComponent(url.pathname));
  if (req.method === 'GET' && file.startsWith(path.join(proj, 'build')) && fs.existsSync(file) && fs.statSync(file).isFile()) {
    res.writeHead(200, { 'content-type': types[path.extname(file)] || 'application/octet-stream' });
    return fs.createReadStream(file).pipe(res);
  }
  const chunks = []; for await (const c of req) chunks.push(c);
  const request = new Request(url, { method: req.method, headers: req.headers, body: ['GET', 'HEAD'].includes(req.method) ? undefined : Buffer.concat(chunks), redirect: 'manual' });
  const response = await fn(request, { ip: '127.0.0.1' });
  res.writeHead(response.status, Object.fromEntries(response.headers));
  res.end(Buffer.from(await response.arrayBuffer()));
}).listen(port, () => console.log('netlify-sim on', port));
