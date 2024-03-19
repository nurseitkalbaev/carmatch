// Fetch data from data.json
fetch("/data/data.json")
  .then((response) => response.json())
  .then((data) => {
    // Get reference to the table body
    const tableBody = document.querySelector("#carTable tbody");

    // Loop through the data and create table rows
    data.hosts.forEach((host) => {
      const row = ` 
                    <tr onclick="viewCarDetails(${host.id})">
                        <td>${host.year}</td>
                        <td>${host.make}</td>
                        <td>${host.model}</td>
                        <td>${host.location}</td>
                        <td>$${host.price}</td>
                        <td><img src="${host.image}" alt="${host.make} ${host.model}" style="width: 400px"></td>
                     </tr> 
            `;
      // Append row to the table body
      tableBody.innerHTML += row;
    });
  })
  .catch((error) => console.error("Error fetching data:", error));

function viewCarDetails(carId) {
  console.log("clicked", carId);
  // Redirect to a new page with car details
  window.open(`carDetails.html?id=${carId}`, "_blank");
}
