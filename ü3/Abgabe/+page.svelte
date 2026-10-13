<script>
  // Prototyping – Übung 3: Taschenrechner
  // Gemäss Aufgabenstellung wurde nur der <script>-Teil angepasst.
  // HTML und <style> sind unverändert aus dem Starter von Moodle.

  let output = $state("0"); // Variable für die Anzeige der Berechnung bzw. des Resultats der Berechnung

  // a) Drei zusätzliche Variablen
  let a = 0; // erste Zahl
  let b = 0; // zweite Zahl
  let operation = null; // gewählte Operation ("+", "-", "*", "/") als String

  // f) Hilfsvariable für den Zustand des Rechners:
  //    true, sobald mit "=" ein Ergebnis berechnet wurde und noch keine neue Eingabe erfolgt ist.
  let resultDisplayed = false;

  // a) Die vier Rechenfunktionen
  function add(x, y) {
    return x + y;
  }

  function subtract(x, y) {
    return x - y;
  }

  function multiply(x, y) {
    return x * y;
  }

  function divide(x, y) {
    return x / y;
  }

  function setDigit(digit) {
    console.log(digit);

    // f) Fall 2: Nach einem Ergebnis wird direkt eine Ziffer eingegeben
    //    -> das Ergebnis wird nicht weiterverwendet, eine neue Rechnung beginnt.
    if (resultDisplayed) {
      allClear();
    }

    // b) Spezialfall: Die 0 am Anfang der Anzeige wird durch die erste Ziffer ersetzt.
    if (output === "0") {
      output = "";
    }

    // b) Rechnung in der Anzeige aufbauen
    output += digit;

    // a) + c) Ziffer der richtigen Zahl zuordnen und mehrstellige Zahlen aufbauen:
    //    bisheriger Wert * 10 + neue Ziffer (z.B. 1 -> 12 -> 123)
    if (operation === null) {
      a = a * 10 + digit;
    } else {
      b = b * 10 + digit;
    }
  }

  function setOperation(op) {
    console.log(op);

    // e) Wird bereits eine zweite Operation eingegeben, wird das Zwischenergebnis
    //    automatisch berechnet (z.B. 12+34 und dann "*" -> 46*).
    if (operation !== null) {
      calculate();
    }

    // f) Fall 1: Nach einem Ergebnis folgt eine Operation
    //    -> das Ergebnis (steht in a) wird weiterverwendet.
    resultDisplayed = false;

    // a) Operation merken und b) in der Anzeige anhängen
    operation = op;
    output += op;
  }

  function calculate() {
    console.log("calculate");

    // Ohne Operation gibt es nichts zu berechnen (z.B. "=" direkt nach dem Start).
    if (operation === null) {
      return;
    }

    // a) Je nach Operation eine der vier Rechenfunktionen verwenden
    let result;
    if (operation === "+") {
      result = add(a, b);
    } else if (operation === "-") {
      result = subtract(a, b);
    } else if (operation === "*") {
      result = multiply(a, b);
    } else if (operation === "/") {
      result = divide(a, b);
    }

    // Ergebnis anzeigen (Databinding auf output)
    output = String(result);

    // d) Mit dem Ergebnis weiterrechnen: Das Ergebnis wird zur ersten Zahl,
    //    die zweite Zahl und die Operation werden zurückgesetzt.
    a = result;
    b = 0;
    operation = null;

    // f) Zustand merken: Es wurde soeben ein Ergebnis berechnet.
    resultDisplayed = true;
  }

  function allClear() {
    console.log("allClear");

    // a) Alle Werte auf die Anfangswerte zurücksetzen
    a = 0;
    b = 0;
    operation = null;
    output = "0";
    resultDisplayed = false;
  }
</script>

<div class="calculator">
  <div id="display">{output}</div>

  <div class="keypad">
    <button class="key" onclick={() => { setDigit(7); }}>7</button>
    <button class="key" onclick={() => { setDigit(8); }}>8</button>
    <button class="key" onclick={() => { setDigit(9); }}>9</button>
    <button class="key operation" onclick={() => { setOperation("/"); }}>/</button>

    <button class="key" onclick={() => { setDigit(4); }}>4</button>
    <button class="key" onclick={() => { setDigit(5); }}>5</button>
    <button class="key" onclick={() => { setDigit(6); }}>6</button>
    <button class="key operation" onclick={() => { setOperation("*"); }}>*</button>

    <button class="key" onclick={() => { setDigit(1); }}>1</button>
    <button class="key" onclick={() => { setDigit(2); }}>2</button>
    <button class="key" onclick={() => { setDigit(3); }}>3</button>
    <button class="key operation" onclick={() => { setOperation("-"); }}>-</button>

    <button class="key" onclick={() => { setDigit(0); }}>0</button>
    <button class="key operation" onclick={allClear}>AC</button>
    <button class="key operation" onclick={calculate}>=</button>
    <button class="key operation" onclick={() => { setOperation("+"); }}>+</button>
  </div>
</div>

<style>
  /* Container-Element für den gesamten Rechner */
  .calculator {
    background-color: black;
    padding: 1em;
    /* Breite ohne padding: 4xTastenbreite + 3xGap */
    width: 13.5em;
  }

  /* Zahlenanzeige */
  #display {
    color: white;
    font-weight: bold;
    background-color: black;

    border: 1px solid white;
    padding: 0.5em;
    text-align: end;
    margin-bottom: 0.5em;
    font-family: "Courier New", Courier, monospace;
    font-size: 1.3em;
  }

  /* Tasten als Grid. Dokumentation zu Grid: https://www.w3schools.com/CSSref/pr_grid.asp */
  .keypad {
    display: grid;
    grid-template-columns: repeat(4, 3em);
    grid-template-rows: repeat(4, 3em);
    row-gap: 0.5em;
    column-gap: 0.5em;
  }

  /* Einzelne Tasten */
  .key {
    background-color: gray;
    color: white;
    font-family: "Courier New", Courier, monospace;
    font-size: 1.2em;
    font-weight: bold;

    justify-content: center;
    align-items: center;
    border-radius: 100%;
    border: none;
  }

  /* Mit der :active Pseudoklasse kann das Aussehen des Buttons verändert werden, solange er gedrückt wird.
   Siehe https://www.w3schools.com/cssref/sel_active.asp */
  .key:active {
    background-color: lightgrey;
  }

  /* Spezialfarbe für Operationstasten */
  .operation {
    background-color: orangered;
  }

  /* Aussehen eines gerade geklickten Operators (+,-,*,/) */
  .operation:active {
    background-color: white;
    color: orangered;
  }
</style>
