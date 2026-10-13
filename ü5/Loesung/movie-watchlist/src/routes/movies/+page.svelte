<script>
	import MovieCard from '#lib/components/MovieCard.svelte';
	import { movies } from '#lib/data/movies.js';

	// Suchbegriff, verknüpft mit dem Eingabefeld über bind:value
	let search = $state('');

	// Titel der Filme, die auf der Watchlist stehen
	let watchlist = $state([]);

	// Nur Filme der Watchlist anzeigen? (Checkbox, bind:checked)
	let onlyWatchlist = $state(false);

	// Film zur Watchlist hinzufügen bzw. wieder entfernen.
	// Wird als Prop an die Komponente MovieCard übergeben.
	function toggleWatchlist(movie) {
		if (watchlist.includes(movie.title)) {
			watchlist = watchlist.filter((title) => title !== movie.title);
		} else {
			watchlist.push(movie.title);
		}
	}

	// Liefert die Filme, die zum Suchbegriff und zum Watchlist-Filter passen.
	function filteredMovies() {
		let result = [];
		for (let movie of movies) {
			let matchesSearch = movie.title.toLowerCase().includes(search.toLowerCase());
			let matchesWatchlist = !onlyWatchlist || watchlist.includes(movie.title);
			if (matchesSearch && matchesWatchlist) {
				result.push(movie);
			}
		}
		return result;
	}
</script>

<!-- Page: Route "/movies" -->
<div class="container mt-4">
	<p class="fst-italic text-secondary">Daten und Poster sind fiktiv und wurden für diese Übung generiert.</p>

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
					Nur Watchlist anzeigen ({watchlist.length})
				</label>
			</div>
		</div>
	</div>

	<!-- Layout der Movies mit dem Bootstrap-Gridsystem -->
	<div class="row g-3">
		{#each filteredMovies() as movie}
			<div class="col-sm-6 col-md-4 col-lg-3">
				<MovieCard
					{movie}
					inWatchlist={watchlist.includes(movie.title)}
					onToggle={toggleWatchlist}
				/>
			</div>
		{:else}
			<div class="col-12">
				<p class="text-secondary">Keine Filme gefunden.</p>
			</div>
		{/each}
	</div>
</div>
