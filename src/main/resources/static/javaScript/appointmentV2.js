console.log("appointment js connected");

//step 1, grab html elements
const responseArea = document.querySelector("#responseArea");
const memberId = localStorage.getItem("memberId");
const addBtn = document.querySelector("#addBtn");

const headers = {
    'Content-Type': 'application/json'
}

//step 2, write callback functions
const getAllApptByMemberId = async() =>{
    await fetch(`http://localhost:8080/api/v1/appointment/member/${memberId}`, {
        method: "GET",
        headers:headers
    })
        .then(res => res.json())
        .then(data => {
            createCards(data)
        })
        .catch(err => console.error(err))
}
getAllApptByMemberId();

async function deleteApptByApptId(apptId){
    await fetch(`http://localhost:8080/api/v1/appointment/${apptId}`, {
        method: "DELETE",
        headers: headers
    })
        .catch(err => console.error(err))
    return getAllApptByMemberId(memberId);
}

async function addAppt(obj){
    const body = {
        "appt_date_time": document.querySelector("#input-appt-date-time").value,
        "provider_address": document.querySelector("#input-provider-address").value,
        "provider_name":document.querySelector("#input-provider-name").value,
    }
    await fetch(`http://localhost:8080/api/v1/appointment/member/${memberId}`, {
        method: "POST",
        body: JSON.stringify(body),
        headers: headers
    }).then(res => res.json())
        .then(data => {
            // console.log(data)
            location.assign("appointment.html")
        })
        .catch(err => console.error(err.message))

}



const createCards = (array) => {
    responseArea.innerHTML = null;

    array.forEach(obj => {
        const div = document.createElement('div');
        div.classList = "card";

        div.innerHTML = `
            <p>${obj.appt_date_time}</p>
            <p>${obj.provider_name}</p>
            <p>${obj.provider_address}</p>
            
            <button id="delete-btn" onclick="deleteApptByApptId(${obj.appt_id})">X</button>
            <button id="${obj.appt_id}-button" type="button" class="btn btn-dark">transportation</button>

        `
        responseArea.appendChild(div)

        const buttonToTrans = document.getElementById(`${obj.appt_id}-button`);

        buttonToTrans.addEventListener('click', () => {
            // location.assign(`api/v1/transportation/${obj.appt_id}`)
            window.localStorage.setItem('apptId', `${obj.appt_id}`);
            window.open('./transportation.html', '_blank');
        })

    })
}



//step 3, combine html elements and functions by event listeners
addBtn.addEventListener('click', addAppt);