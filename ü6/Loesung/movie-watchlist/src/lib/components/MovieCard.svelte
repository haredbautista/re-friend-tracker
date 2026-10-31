<script>
	// Prop: der darzustellende Film (Objekt aus der Datenbank)
	let { movie } = $props();
</script>

<div class="card h-100">
	<a href={'/movies/' + movie._id}>
		<img src={movie.poster} class="card-img-top poster" alt="Poster von {movie.title}" />
	</a>
	<div class="card-body">
		<!-- f) Klick auf den Titel führt zur Detailseite, die ID ist Pfadparameter -->
		<h5 class="card-title">
			<a class="link-light" href={'/movies/' + movie._id}>{movie.title}</a>
		</h5>
		<p class="card-text mb-1">Jahr: {movie.year}</p>
		<p class="card-text mb-1">Dauer: {movie.length}</p>
	</div>
	<div class="card-footer">
		<!-- Watchlist-Button als Formular mit versteckter ID (Folie 17) -->
		<form method="POST" action="?/toggleWatchlist">
			<input type="hidden" name="id" value={movie._id} />
			<input type="hidden" name="watchlist" value={movie.watchlist ? 'false' : 'true'} />
			<button class="btn btn-sm w-100 {movie.watchlist ? 'btn-success' : 'btn-outline-light'}">
				{movie.watchlist ? 'Auf der Watchlist' : 'Zur Watchlist hinzufügen'}
			</button>
		</form>
	</div>
</div>
