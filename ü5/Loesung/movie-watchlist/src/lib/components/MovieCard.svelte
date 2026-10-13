<script>
	// Props: der darzustellende Film (Objekt), ob er auf der Watchlist ist,
	// und eine Funktion, die beim Klick auf den Button aufgerufen wird.
	let { movie, inWatchlist = false, onToggle } = $props();
</script>

<div class="card h-100">
	<img src={movie.poster} class="card-img-top poster" alt="Poster von {movie.title}" />
	<div class="card-body">
		<h5 class="card-title">{movie.title}</h5>
		<p class="card-text mb-1">Jahr: {movie.year}</p>
		<p class="card-text mb-1">Dauer: {movie.length}</p>
		<p class="card-text mb-2"><span class="badge text-bg-secondary">{movie.genre}</span></p>
		<p class="card-text small text-secondary">Mit {movie.actors.join(', ')}</p>
	</div>
	<div class="card-footer">
		<button
			class="btn btn-sm w-100 {inWatchlist ? 'btn-success' : 'btn-outline-light'}"
			onclick={() => onToggle(movie)}
		>
			{inWatchlist ? 'Auf der Watchlist' : 'Zur Watchlist hinzufügen'}
		</button>
	</div>
</div>
