import db from '#lib/db.js';

// e) Daten aus der MongoDB laden (load-Funktion, Folie 4 und 10)
export async function load() {
	return {
		movies: await db.getMovies()
	};
}

// Optionale Erweiterung: Film auf die Watchlist setzen bzw. wieder entfernen.
// Jeder Button auf einer MovieCard ist ein kleines Formular mit versteckter ID (Folie 17).
export const actions = {
	toggleWatchlist: async ({ request }) => {
		const data = await request.formData();
		let movie = {
			_id: data.get('id'),
			watchlist: data.get('watchlist') === 'true'
		};
		await db.updateMovie(movie);
	}
};
