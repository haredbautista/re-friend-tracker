<script>
	let { data } = $props();
</script>

<!-- Page: Route "/movies/[movie_id]" -->
<div class="container mt-4">
	<a href="/movies">Back</a>
	<h1 class="mt-2">{data.movie.title}</h1>

	<div class="row g-4 mt-1">
		<div class="col-md-4">
			<img src={data.movie.poster} class="img-fluid poster border" alt="Poster von {data.movie.title}" />
		</div>
		<div class="col-md-8">
			<p>Jahr: {data.movie.year}</p>
			<p>Dauer: {data.movie.length}</p>
			<p class="mb-1">Cast:</p>
			{#if data.movie.actors && data.movie.actors.length > 0}
				<ul>
					{#each data.movie.actors as actor}
						<li>{actor}</li>
					{/each}
				</ul>
			{:else}
				<p class="text-secondary">Keine Angaben</p>
			{/if}

			<p>
				Watchlist:
				{#if data.movie.watchlist}
					<span class="badge text-bg-success">Ja</span>
				{:else}
					<span class="badge text-bg-secondary">Nein</span>
				{/if}
			</p>

			<!-- h) Optional: Löschen über eine Form Action mit versteckter ID -->
			<form method="POST" action="?/delete" class="mt-3">
				<input type="hidden" name="id" value={data.movie._id} />
				<button type="submit" class="btn btn-danger">Delete Movie</button>
			</form>
		</div>
	</div>
</div>
