import db from '#lib/db.js';

// g) Form Action zum Erfassen eines neuen Films (Folie 15 und 16)
export const actions = {
	create: async ({ request }) => {
		const data = await request.formData();

		let movie = {
			title: data.get('title'),
			year: Number(data.get('year')),
			length: data.get('length')
		};

		// Einfache Prüfung: Ohne Titel wird nichts gespeichert.
		if (!movie.title || movie.title.trim() === '') {
			return { success: false, message: 'Bitte gib einen Namen ein.' };
		}

		// createMovie ergänzt Poster (Platzhalter), actors und watchlist
		const id = await db.createMovie(movie);

		return { success: true, id, title: movie.title };
	}
};
