import db from '#lib/db.js';
import { error, redirect } from '@sveltejs/kit';

// f) Detailseite: Die ID des Films ist der Pfadparameter movie_id (Folie 6)
export async function load({ params }) {
	const movie = await db.getMovie(params.movie_id);

	if (!movie) {
		error(404, 'Film nicht gefunden');
	}

	return {
		movie
	};
}

// h) Optional: Film löschen und danach zur Übersicht weiterleiten
export const actions = {
	delete: async ({ request }) => {
		const data = await request.formData();
		await db.deleteMovie(data.get('id'));
		redirect(303, '/movies');
	}
};
