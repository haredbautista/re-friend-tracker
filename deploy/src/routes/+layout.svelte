<script>
	// Das Layout gilt für alle Pages: Die Navigation muss so nur einmal definiert werden.
	import favicon from '#lib/assets/favicon.svg';
	import './styles.css';

	let { children } = $props();
</script>

<svelte:head>
	<link rel="icon" href={favicon} />
	<title>ReelStack</title>
</svelte:head>

<!-- Bootstrap Navigation Bar: https://getbootstrap.com/docs/5.3/components/navbar/ -->
<nav class="navbar navbar-expand-md bg-black border-bottom border-secondary">
	<div class="container">
		<a class="navbar-brand fw-bold" href="/">ReelStack</a>
		<button
			class="navbar-toggler"
			type="button"
			data-bs-toggle="collapse"
			data-bs-target="#mainNav"
			aria-controls="mainNav"
			aria-expanded="false"
			aria-label="Navigation umschalten"
		>
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="mainNav">
			<ul class="navbar-nav">
				<li class="nav-item"><a class="nav-link" href="/movies">Movies</a></li>
				<li class="nav-item"><a class="nav-link" href="/about">About</a></li>
			</ul>
		</div>
	</div>
</nav>

<!-- Platzhalter: Hier wird die jeweils aktive Page eingefügt -->
<main>
	{@render children()}
</main>
