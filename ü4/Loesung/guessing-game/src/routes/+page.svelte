<script>
  // Prototyping – Übung 4: Guessing Game (Zahlenraten)

  // Bereich der Geheimzahl
  const min = 1;
  const max = 10;

  // Die vier Variablen aus der Aufgabenstellung
  let userInput = $state(null); // vom Benutzer eingegebene Zahl (null = leeres Eingabefeld)
  let secretNumber = $state(0); // die zu erratende Geheimzahl
  let output = $state(""); // Text unterhalb des Eingabefelds
  let gameFinished = $state(false); // true, sobald die Zahl erraten wurde

  // Zusatzaufgabe: Anzahl Versuche
  let attempts = $state(0);

  // Spiel initialisieren: beim Laden der Page und bei "Spiel neu starten"
  function initGame() {
    // Zufallszahl zwischen min und max (inklusive), siehe
    // https://www.w3schools.com/jsref/jsref_random.asp
    secretNumber = Math.floor(Math.random() * (max - min + 1)) + min;
    userInput = null;
    output = "";
    gameFinished = false;
    attempts = 0;
  }

  // Spiellogik
  function guess() {
    // Ein leeres Zahlenfeld liefert über bind:value den Wert null.
    if (userInput === null || userInput === undefined || userInput === "") {
      output = "Bitte gib eine Zahl ein.";
      return;
    }

    attempts += 1;

    if (secretNumber > userInput) {
      output = "Die gesuchte Zahl ist grösser";
    } else if (secretNumber < userInput) {
      output = "Die gesuchte Zahl ist kleiner";
    } else {
      output = "Getroffen!";
      gameFinished = true;
    }
  }

  // Zusatzaufgabe: Raten auch mit der Enter-Taste auslösen
  function handleKeydown(event) {
    if (event.key === "Enter" && !gameFinished) {
      guess();
    }
  }

  // i. Spiel beim Laden initialisieren
  initGame();
</script>

<h1>Guessing Game</h1>

<p>Errate die Geheimzahl zwischen {min} und {max}:</p>

<input type="number" min={min} max={max} bind:value={userInput} onkeydown={handleKeydown} />
<button onclick={guess} disabled={gameFinished}>Raten</button>
<button onclick={initGame} disabled={!gameFinished}>Spiel neu starten</button>

<p>{output}</p>

<!-- Zusatzaufgabe: Versuche anzeigen -->
{#if attempts > 0}
  <p class="attempts">
    {#if gameFinished}
      Du hast {attempts} {attempts === 1 ? "Versuch" : "Versuche"} benötigt.
    {:else}
      Versuche bisher: {attempts}
    {/if}
  </p>
{/if}

<style>
  .attempts {
    color: gray;
    font-size: 0.9em;
  }
</style>
