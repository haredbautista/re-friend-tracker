// Hilfsskript (nicht Teil der App): erzeugt einfache SVG-Poster für die Demodaten aus Moodle.
// Aufruf: node generate-posters.mjs <zielordner>
import { writeFileSync, mkdirSync } from 'node:fs';
import { join } from 'node:path';

const out = process.argv[2];
mkdirSync(out, { recursive: true });

const posters = [
	['Altura', 'Das Geheimnis von Altura', '#1b2a49', '#c9a227'],
	['Sternenwanderer', 'Sternenwanderer', '#0b1d3a', '#3fa7d6'],
	['Schatten', 'Flüsternde Schatten', '#111111', '#6c2bd9'],
	['Ebene', 'Die letzte Ebene', '#0d1b2a', '#e0e1dd'],
	['Chroniken', 'Chroniken von Morgen', '#3a2e2a', '#e07a5f'],
	['Waechter', 'Die Wächter des Verborgenen', '#7a4f12', '#f2cc8f'],
	['Nebel', 'Nebel über Neustadt', '#2f3e46', '#84a98c'],
	['Medusa', 'Das Lächeln der Medusa', '#1a1a1a', '#e63946'],
	['Echo', 'Echo des Vergessens', '#1d3557', '#a8dadc'],
	['Traum', 'Der Traum von Unendlichkeit', '#f4a261', '#2a9d8f']
];

function escape(text) {
	return text.replaceAll('&', '&amp;').replaceAll('<', '&lt;').replaceAll('>', '&gt;');
}

for (const [slug, title, dark, accent] of posters) {
	const words = title.split(' ');
	const lines = [];
	let line = '';
	for (const w of words) {
		if ((line + ' ' + w).trim().length > 14) {
			lines.push(line.trim());
			line = w;
		} else {
			line = (line + ' ' + w).trim();
		}
	}
	if (line) lines.push(line);
	const text = lines
		.map((l, i) => `<text x="200" y="${400 + i * 46}" text-anchor="middle" font-family="Georgia, serif" font-size="36" font-weight="bold" fill="#ffffff">${escape(l)}</text>`)
		.join('\n  ');
	const svg = `<svg xmlns="http://www.w3.org/2000/svg" width="400" height="600" viewBox="0 0 400 600">
  <defs>
    <linearGradient id="bg" x1="0" y1="0" x2="0" y2="1">
      <stop offset="0" stop-color="${accent}"/>
      <stop offset="1" stop-color="${dark}"/>
    </linearGradient>
  </defs>
  <rect width="400" height="600" fill="url(#bg)"/>
  <circle cx="200" cy="210" r="110" fill="${dark}" opacity="0.85"/>
  <circle cx="200" cy="210" r="70" fill="${accent}" opacity="0.6"/>
  <rect x="24" y="24" width="352" height="552" fill="none" stroke="#ffffff" stroke-opacity="0.5" stroke-width="3"/>
  ${text}
  <text x="200" y="560" text-anchor="middle" font-family="Arial, sans-serif" font-size="16" fill="#ffffff" opacity="0.8">REELSTACK ORIGINAL</text>
</svg>
`;
	writeFileSync(join(out, `${slug}.svg`), svg);
	console.log('written', slug + '.svg');
}
