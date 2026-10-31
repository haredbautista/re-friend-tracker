<script>
	import MovieCard from '#lib/components/MovieCard.svelte';

	// Die Daten kommen aus der load-Funktion in +page.server.js (Folie 4)
	let { data } = $props();

	// Suchbegriff, verknüpft mit dem Eingabefeld über bind:value
	let search = $state('');

	// Nur Filme der Watchlist anzeigen? (Checkbox, bind:checked)
	let onlyWatchlist = $state(false);

	// Liefert die Filme, die zum Suchbegriff und zum Watchlist-Filter passen.
	function filteredMovies() {
		let result = [];
		for (let movie of data.movies) {
			let matchesSearch = movie.title.toLowerCase().includes(search.toLowerCase());
			let matchesWatchlist = !onlyWatchlist || movie.watchlist;
			if (matchesSearch && matchesWatchlist) {
				result.push(movie);
			}
		}
		return result;
	}
</script>

<!-- Page: Route "/movies" -->
<div class="container mt-4">
	<p class="fst-italic text-secondary">Daten aus der MongoDB «ScreenStackDB», Collection «movies».</p>

	<!-- g) Link zur Seite für das Erfassen neuer Filme -->
	<a class="btn btn-primary mb-3" href="/movies/create">Add New Movie</a>

	<!-- Filter -->
	<div class="row g-2 align-items-center mb-4">
		<div class="col-md-6">
			<input
				type="search"
				class="form-control"
				placeholder="Film suchen …"
				aria-label="Film suchen"
				bind:value={search}
			/>
		</div>
		<div class="col-md-6">
			<div class="form-check">
				<input class="form-check-input" type="checkbox" id="onlyWatchlist" bind:checked={onlyWatchlist} />
				<label class="form-check-label" for="onlyWatchlist">
					Nur Watchlist anzeigen ({data.movies.filter((m) => m.watchlist).length})
				</label>
			</div>
		</div>
	</div>

	<!-- Layout der Movies mit dem Bootstrap-Gridsystem -->
	<div class="row g-3">
		{#each filteredMovies() as movie (movie._id)}
			<div class="col-sm-6 col-md-4 col-lg-3">
				<MovieCard {movie} />
			</div>
		{:else}
			<div class="col-12">
				<p class="text-secondary">Keine Filme gefunden.</p>
			</div>
		{/each}
	</div>
</div>
