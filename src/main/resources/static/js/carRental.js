function viewCarDetails(carId) {
    console.log("clicked", carId);
    let id = parseInt(carId);
    window.open("/vehicles/carDetails/" + id, "_blank");
}