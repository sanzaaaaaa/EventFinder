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
