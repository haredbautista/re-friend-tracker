<!-- Page: Route "/about" -->
<div class="container mt-4">
	<h1>About ReelStack</h1>
	<p class="lead">Prototyp einer Movie Watchlist, erstellt mit SvelteKit, Bootstrap und MongoDB.</p>
	<ul>
		<li>Navigation und Layout: <code>src/routes/+layout.svelte</code></li>
		<li>Datenbankzugriff: <code>src/lib/db.js</code> (Verbindung über <code>DB_URI</code> aus <code>.env</code>)</li>
		<li>Filmliste mit load-Funktion: <code>src/routes/movies/+page.server.js</code></li>
		<li>Detailseite mit Pfadparameter: <code>src/routes/movies/[movie_id]/</code></li>
		<li>Film erfassen mit Form Action: <code>src/routes/movies/create/</code></li>
		<li>Komponente für einen Film: <code>src/lib/components/MovieCard.svelte</code></li>
	</ul>
</div>
