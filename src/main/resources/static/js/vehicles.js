function toggleForm() {
    document.getElementById("addCarBtn").style.display = "none";
    document.getElementById("addCarForm").style.display = "block";
}

function cancelAdd() {
    document.getElementById("addCarBtn").style.display = "block";
    document.getElementById("addCarForm").style.display = "none";
}

function toggleEditForm() {
    let editForm = document.getElementById("editForm");
    let listingsTable = document.getElementById("listingsTable");
    let addButton = document.getElementById("addCarBtn");

    document.getElementById("editYear").value = car.year;
    document.getElementById("editMake").value = car.make;
    document.getElementById("editModel").value = car.model;
    document.getElementById("editLocation").value = car.location;
    document.getElementById("editPrice").value = car.price;
    document.getElementById("editImageUrl").value = car.imageUrl;

    addButton.style.display = "none";
    editForm.style.display = "block";
    listingsTable.style.display = "none";
}
function cancelEdit() {
    let editForm = document.getElementById("editForm");
    let listingsTable = document.getElementById("listingsTable");
    let addButton = document.getElementById("addCarBtn");

    addButton.style.display = "block";
    editForm.style.display = "none";
    listingsTable.style.display = "block";
}