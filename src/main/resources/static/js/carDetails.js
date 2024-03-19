// Geting carId from query parameter
const urlParams = new URLSearchParams(window.location.search);
const carId = urlParams.get("id");

// Fetching data from data.json based on carId
fetch("/resorces/data.json")
  .then((response) => response.json())
  .then((data) => {
    const car = data.hosts.find((host) => host.id === carId);
    const carDetailsContainer = document.getElementById("carDetailsContainer");
    carDetailsContainer.innerHTML = `
                  <img src="${car.image}" alt="${car.make} ${car.model}" class="fullWidth">
                  <h2>${car.make} ${car.model}</h2>
                  <h3>Year: ${car.year}</h3>
                  <h3>Price: $${car.price}</h3>
              `;
  })
  .catch((error) => console.error("Error fetching data:", error));

document.addEventListener("DOMContentLoaded", function () {
  // referencing to the buttons
  const rentButton = document.getElementById("rentButton");
  const exchangeButton = document.getElementById("exchangeButton");

  const rentFormContainer = document.getElementById("rentFormContainer");
  const rentForm = document.getElementById("rentForm");
  const exchangeForm = document.getElementById("exchangeForm");

  rentForm.addEventListener("submit", function (event) {
    // Prevent default form submission
    event.preventDefault();
    // booking functionality implemets here
    console.log("Booking a car...", event);
    alert("car is Booked!");
  });
  exchangeForm.addEventListener("submit", function (event) {
    event.preventDefault();
    console.log("Booking a car...", event);
  });
  exchangeButton.addEventListener("click", function () {
    rentForm.style.display = "none";
  });

  rentButton.addEventListener("click", function () {
    // Display rent form container when rentButton is clicked (toggle effect)
    rentFormContainer.style.display = "block";
    rentForm.style.display = "block";
  });
});

// button color is changed, form is displayed
const rentButton = document.getElementById("rentButton");
const exchangeButton = document.getElementById("exchangeButton");
const rentFormContainer = document.getElementById("rentFormContainer");
const exchangeFormContainer = document.getElementById("exchangeFormContainer");

rentButton.addEventListener("click", () => {
  rentButton.classList.add("active");
  exchangeButton.classList.remove("active");
  rentFormContainer.style.display = "block";
  exchangeFormContainer.style.display = "none";
});

exchangeButton.addEventListener("click", () => {
  rentButton.classList.remove("active");
  exchangeButton.classList.add("active");
  rentFormContainer.style.display = "block";
  exchangeFormContainer.style.display = "block";
});
