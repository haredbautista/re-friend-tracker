<script>
	import { movies } from '#lib/data/movies.js';
</script>

<!-- Hauptseite: Route "/" -->
<div class="container hero">
	<div class="row">
		<div class="col-lg-8">
			<h1 class="display-4 fw-semibold">Welcome to ReelStack</h1>
			<p class="lead">Your tracker and search engine for movies and tv shows</p>
			<p class="text-secondary">
				Aktuell sind {movies.length} Filme in der Datenbank.
			</p>
			<a class="btn btn-primary btn-lg mt-2" href="/movies">Movies entdecken</a>
		</div>
	</div>
</div>
