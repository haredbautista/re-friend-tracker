import db from '#lib/db.js';

// Anzahl Filme für die Startseite aus der Datenbank laden
export async function load() {
	const movies = await db.getMovies();
	return {
		movieCount: movies.length
	};
}
