<script>
	// form enthält den Rückgabewert der Action nach dem Submit (Folie 16)
	let { form } = $props();
</script>

<!-- Page: Route "/movies/create" -->
<div class="container mt-4">
	<a href="/movies">Back</a>
	<h1 class="mt-2">Add a Movie</h1>

	<form method="POST" action="?/create">
		<div class="mb-3">
			<label for="title" class="form-label">Name</label>
			<input id="title" name="title" class="form-control" type="text" required />
		</div>
		<div class="mb-3">
			<label for="year" class="form-label">Year</label>
			<input id="year" name="year" class="form-control" type="number" min="1888" max="2100" required />
		</div>
		<div class="mb-3">
			<label for="length" class="form-label">Length</label>
			<input id="length" name="length" class="form-control" type="text" placeholder="z.B. 114 Minuten" />
		</div>
		<button type="submit" class="btn btn-primary">Add Movie</button>
	</form>

	{#if form?.success}
		<div class="alert alert-success mt-3">
			«{form.title}» wurde erstellt.
			<a href={'/movies/' + form.id}>Zum Film</a> oder <a href="/movies">zur Übersicht</a>.
		</div>
	{:else if form?.message}
		<div class="alert alert-warning mt-3">{form.message}</div>
	{/if}
</div>
