let index = 0;

function showSlide(n) {
    const slides = document.querySelectorAll(".carousel-item");
    if (n >= slides.length) {
        index = 0;
    } else if (n < 0) {
        index = slides.length - 1;
    } else {
        index = n;
    }

    let offset = -index * 100;
    document.querySelector(".carousel-slides").style.transform = `translateX(${offset}%)`;
}

function nextSlide() {
    showSlide(index + 1);
}

function prevSlide() {
    showSlide(index - 1);
}


