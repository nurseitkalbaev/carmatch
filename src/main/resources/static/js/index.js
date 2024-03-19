// drop down effect is implemented here
const burgerButton = document.querySelector(".hamburgerButton");

burgerButton.addEventListener("click", function () {
  document.querySelector(".navLinks").style.display =
    document.querySelector(".navLinks").style.display == "block"
      ? "none"
      : "block";
});
