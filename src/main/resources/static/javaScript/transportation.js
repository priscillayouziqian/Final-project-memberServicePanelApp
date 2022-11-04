console.log("transportation js connected");

//step 1: grab html elements
const inputPickupTime = document.querySelector("#input-transportation-provider");
const inputReturnTime = document.querySelector("#input-return-time");
const inputPickupTime = document.querySelector("#input-pickup-time");
const addTripBtn = document.querySelector("#addTripBtn");
const headers = {
    'Content-Type': 'application/json'
}

//step 2: write callback functions
// const getTransportationByTransId = async() => {
//     await fetch(`http://localhost:8080/api/v1/transportation/${}`, {
//         method: "GET",
//         headers:headers
//     })
//         .then(res => res.json())
//         .then(data => {
//             createCards(data)
//         })
//         .catch(err => console.error(err))
// }
// getTransportationByTransId();
//
//
//
// const createCards = (array) => {
//     responseArea.innerHTML = null;
//
//     array.forEach(obj => {
//         const div = document.createElement('div');
//         div.classList = "w3-third";
//
//         div.innerHTML = `
//             <p>${obj.med_name}</p>
//             <p>${obj.instruction}</p>
//             <button id="${obj.id}">Edit</button>
//             <button>Save</button>
//             <button id="delete-btn" onclick="deleteMedlistByMedlistId(${obj.id})">X</button>
//
//         `
//         responseArea.appendChild(div)
//     })
// }

//step 3: combine html elements and functions by event listeners