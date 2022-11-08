console.log("transportation js connected");

//step 1: grab html elements
const responseArea = document.querySelector("#responseArea");
const addTransArea = document.querySelector("#addTransportationArea");
const inputPickupTime = document.querySelector("#input-pickup-time");
const inputReturnTime = document.querySelector("#input-return-time");
const inputTransProvider = document.querySelector("#input-transportation-provider");

const addTripBtn = document.querySelector("#addTripBtn");
const apptId = localStorage.getItem("apptId");
const headers = {
    'Content-Type': 'application/json'
}

//step 2: write callback functions


const getTransportationByApptId = async() => {
    await fetch(`http://localhost:8080/api/v1/transportation/appointment/${apptId}`, {
        method: "GET",
        headers:headers
    })
        .then(res => res.json())
        .then(data => {
            createCards(data)
        })
        .catch(err => console.error(err))
}
getTransportationByApptId();

async function addTrans(obj){
    const body = {
        "transportation_provider": inputTransProvider.value,
        "pick_up_time": inputPickupTime.value,
        "return_pick_up_time": inputReturnTime.value

    }
    const response = await fetch(`http://localhost:8080/api/v1/transportation/appointment/${apptId}`, {
        method: "POST",
        body: JSON.stringify(body),
        headers: headers
    })
        // .then(res => res.json())
        // .then(data => {
        //     // console.log(data)
        //     // location.assign("transportation.html")
        // })
        .catch(err => console.error(err.message))

    if(response.status == 200){
        return getTransportationByApptId(apptId);
    }
}

async function deleteTransByTransId(transportationId){
    await fetch(`http://localhost:8080/api/v1/transportation/${transportationId}`, {
        method: "DELETE",
        headers: headers
    })
        .catch(err => console.error(err))
    return getTransportationByApptId(apptId);
}

const createCards = (array) => {

    responseArea.innerHTML = null;

    array.forEach(obj => {

        const div = document.createElement('div');
        div.classList = "card";

        div.innerHTML = `
            <p>${obj.transportation_provider}</p>
            <p>${obj.pick_up_time}</p>
            <p>${obj.return_pick_up_time}</p>
          
            
            <button id="delete-btn" onclick="deleteTransByTransId(${obj.id})">X</button>

        `
        responseArea.appendChild(div)

    })
}





//step 3: combine html elements and functions by event listeners
addTripBtn.addEventListener('click', addTrans);