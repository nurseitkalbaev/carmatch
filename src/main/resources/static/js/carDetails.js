document.addEventListener("DOMContentLoaded", function () {
// button color is changed, form is displayed
  const rentButtonAuth = document.getElementById("rentAuth");
  const rentButtonNotAuth = document.getElementById("rentNotAuth");
  const exchangeButtonAuth = document.getElementById("exchangeButtonAuth");
  const exchangeButtonNotAuth = document.getElementById("exchangeButtonNotAuth");
  const rentFormContainer = document.getElementById("rentFormContainer");
  const exchangeFormContainer = document.getElementById("exchangeFormContainer");
  const rentForm = document.getElementById("rentForm");
  const exchangeForm = document.getElementById("exchangeForm");


  rentForm.addEventListener("submit", function (event) {
    // Prevent default form submission
    event.preventDefault();
    console.log("Booking a car...", event);
    alert("car is Booked!");
    window.location.href = "/vehicles/carRental";
  });
  exchangeForm.addEventListener("submit", function (event) {
    event.preventDefault();
    console.log("Booking a car...", event);
    alert("exchange is Requested!");
    window.location.href = "/vehicles/carRental";
  });

if(rentButtonAuth != null){
  rentButtonAuth.addEventListener("click", () => {
    rentButtonAuth.classList.add("active");
    exchangeButtonAuth.classList.remove("active");
    rentFormContainer.style.display = "block";
    exchangeFormContainer.style.display = "none";
  });
}

if(exchangeButtonAuth != null){
  exchangeButtonAuth.addEventListener("click", () => {
    rentButtonAuth.classList.remove("active");
    exchangeButtonAuth.classList.add("active");
    rentFormContainer.style.display = "none";
    exchangeFormContainer.style.display = "block";
  });
}

if(rentButtonNotAuth != null){
  rentButtonNotAuth.addEventListener("click", () => {
    window.location.href = "/login";
  });
}
if(exchangeButtonNotAuth != null){
  exchangeButtonNotAuth.addEventListener("click", () => {
    window.location.href = "/login";
  });
}
});