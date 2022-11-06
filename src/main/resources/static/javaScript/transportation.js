console.log("transportation js connected");

//step 1: grab html elements
const responseArea = document.querySelector("#responseArea");
const addTransArea = document.querySelector("#addTransportationArea");
const inputPickupTime = document.querySelector("#input-transportation-provider");
const inputReturnTime = document.querySelector("#input-return-time");
const inputTransProvider = document.querySelector("#input-transportation-provider");

const addTripBtn = document.querySelector("#addTripBtn");
const apptId = localStorage.getItem("apptId");
const headers = {
    'Content-Type': 'application/json'
}

//step 2: write callback functions


const getTransportationByApptId = async() => {
    await fetch(`http://localhost:8080/api/v1/transportation/${apptId}`, {
        method: "GET",
        headers:headers
    })
        .then(res => res.json())
        .then(data => {
            createCards(data);
            if(responseArea !== null){
                addTransArea.style.display = 'none';
            }else{
                responseArea.innerHTML = `
                    <h2>There is no scheduled transportation for the appointment yet</h2>
                `
            }
        })
        .catch(err => console.error(err))
}
getTransportationByApptId();
//
//
//
const createCards = (array) => {

    responseArea.innerHTML = null;

    array.forEach(obj => {
        const div = document.createElement('div');
        div.classList = "w3-third";

        div.innerHTML = `
            <p>${obj.transportation_provider}</p>
            <p>${obj.pick_up_time}</p>
            <p>${obj.return_pick_up_time}</p>
          
            
            <button id="delete-btn" onclick="deleteMedlistByMedlistId(${obj.id})">X</button>

        `
        responseArea.appendChild(div)

    })
}

async function addTrans(obj){
    const body = {
        "pick_up_time": inputPickupTime.value,
        "return_pick_up_time": inputReturnTime.value,
        "transportation_provider": inputTransProvider.value
    }
    await fetch(`http://localhost:8080/api/v1/transportation/appointment/${apptId}`, {
        method: "POST",
        body: JSON.stringify(body),
        headers: headers
    }).then(res => res.json())
        .then(data => {
            // console.log(data)
            location.assign("transportation.html")
        })
        .catch(err => console.error(err.message))

}



//step 3: combine html elements and functions by event listeners
addTripBtn.addEventListener('click', addTrans);