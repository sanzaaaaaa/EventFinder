// funzione per il filtraggio in tempo reale della barra di ricerca 
function searchEvents() {
    let input = document.getElementById("searchInput").value.toLowerCase();
    let events = document.querySelectorAll(".grid-item");

    events.forEach(event => {
        let eventName = event.querySelector("p").textContent.toLowerCase();
        if (eventName.includes(input)) {
            event.style.display = "block";
        } else {
            event.style.display = "none";
        }
    });
}

// Funzione per filtrare la lista amici
function searchAmici() {
    let input = document.getElementById("searchAmici").value.toLowerCase();
    let amici = document.querySelectorAll(".amici-list li");

    amici.forEach(amico => {
        let nome = amico.textContent.toLowerCase();
        if (nome.includes(input)) {
            amico.style.display = "flex";
        } else {
            amico.style.display = "none";
        }
    });
}

// Funzione per aggiungere un nuovo amico
function aggiungiAmico() {
    let nome = document.getElementById("nomeAmico").value.trim();
    if (nome === "") return;

    let lista = document.getElementById("listaAmici");
    let nuovoAmico = document.createElement("li");
    nuovoAmico.innerHTML = `${nome} <button onclick="rimuoviAmico(this)">❌</button>`;

    lista.appendChild(nuovoAmico);
    document.getElementById("nomeAmico").value = "";
}

// Funzione per rimuovere un amico
function rimuoviAmico(button) {
    button.parentElement.remove();
}


