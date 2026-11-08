// Umgebungsvariablen definieren (Aufgabe c, Folie 10).
// Der Wert von DB_URI kommt aus der Datei .env im Projektordner.
import { defineEnvVars } from '@sveltejs/kit/env';

export const variables = defineEnvVars({
	DB_URI: {}
});
