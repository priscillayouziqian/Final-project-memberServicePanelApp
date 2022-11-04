console.log("appointment js connected");

//step 1, grab html elements
const responseArea = document.querySelector("#responseArea");
const memberId = localStorage.getItem("memberId");
const addBtn = document.querySelector("#addBtn");
const modalSaveBtn = document.querySelector("#modal-save-btn");
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

 // async function handleApptEdit(apptId){
 //    apptId.preventDefault()
 //    let bodyObj = {
 //        id: apptId,
 //        "appt_date_time": document.querySelector("#modal-appt-date-time").value,
 //        "provider_name": document.querySelector("#modal-provider-name").value,
 //        "provider_address": document.querySelector("#modal-provider-address").value
 //    }
 //    await fetch(`http://localhost:8080/api/v1/appointment`, {
 //        method: 'PUT',
 //        body: JSON.stringify(bodyObj),
 //        headers: headers
 //    })
 //        .catch(err => console.error(err))
 //     return getAllApptByMemberId(memberId);
 // }
//Modal js from Boostrap
// const myModal = document.getElementById('myModal')
// const myInput = document.getElementById('myInput')
//
// myModal.addEventListener('shown.bs.modal', () => {
//     myInput.focus()
// })
//end of boostrap

const createCards = (array) => {
    responseArea.innerHTML = null;

    array.forEach(obj => {
        const div = document.createElement('div');
        div.classList = "w3-half";

        div.innerHTML = `
            <p>${obj.appt_date_time}</p>
            <p>${obj.provider_name}</p>
            <p>${obj.provider_address}</p>
            <button id="${obj.appt_id}" type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModal" data-bs-whatever="@mdo">Edit</button>
            <button id="delete-btn" onclick="deleteApptByApptId(${obj.appt_id})">X</button>
            <button>transportation</button>

        `
        responseArea.appendChild(div)

       //  const section = document.createElement('section');
       //  section.classList = "modal fade";
       //  section.setAttribute('id','modal fade');
       //  section.setAttribute('tabindex', '-1');
       //  section.setAttribute('aria-labelledby', 'exampleModalLabel');
       //  section.setAttribute('aria-hidden', 'true');
       //
       // div.innerHTML = `
       //
       //        <div class="modal-dialog">
       //          <div class="modal-content">
       //            <div class="modal-header">
       //              <h1 class="modal-title fs-5" id="exampleModalLabel">Edit Appointment Details</h1>
       //              <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
       //            </div>
       //            <div class="modal-body">
       //              <form>
       //                <div class="mb-3">
       //                  <label for="modal-appt-date-time" class="col-form-label">Appointment Date & Time:</label>
       //                  <input type="datetime-local" class="form-control" id="modal-appt-date-time">
       //                </div>
       //                <div class="mb-3">
       //                  <label for="modal-provider-name" class="col-form-label">Provider name:</label>
       //                  <input type="text" class="form-control" id="modal-provider-name">
       //                </div>
       //                <div class="mb-3">
       //                  <label for="modal-provider-address" class="col-form-label">Provider address:</label>
       //                  <input type="text" class="form-control" id="modal-provider-address">
       //                </div>
       //              </form>
       //            </div>
       //            <div class="modal-footer">
       //              <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
       //              <button type="button" class="btn btn-primary" onclick="handleApptEdit(${obj.appt_id})>Save</button>
       //            </div>
       //          </div>
       //        </div>
       //
       // `
       //  responseArea.appendChild(section);
    })
}

//step 3, combine html elements and functions by event listeners
 addBtn.addEventListener('click', addAppt);
