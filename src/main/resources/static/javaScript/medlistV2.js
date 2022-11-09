console.log("medlist js connected");

//step 1, grab html elements
const responseArea = document.querySelector("#responseArea");
const memberId = localStorage.getItem("memberId");
const addBtn = document.querySelector("#addBtn");
const headers = {
    'Content-Type': 'application/json'
}

// step 2, write callback functions
const getAllMedlistByMemberId = async() => {
    await fetch(`http://localhost:8080/api/v1/medlist/member/${memberId}`, {
        method: "GET",
        headers:headers
    })
        .then(res => res.json())
        .then(data => {
            createCards(data)
        })
        .catch(err => console.error(err))
}
getAllMedlistByMemberId();

async function deleteMedlistByMedlistId(medlistId){
    await fetch(`http://localhost:8080/api/v1/medlist/${medlistId}`, {
        method: "DELETE",
        headers: headers
    })
        .catch(err => console.error(err))
    return getAllMedlistByMemberId(memberId);
}

async function addMedlist(obj){
    const body = {
        "med_name": document.querySelector("#input-med-name").value,
        "instruction": document.querySelector("#input-instruction").value
    }
    await fetch(`http://localhost:8080/api/v1/medlist/member/${memberId}`, {
        method: "POST",
        body: JSON.stringify(body),
        headers: headers
    }).then(res => res.json())
        .then(data => {
            // console.log(data)
            location.assign("medlist.html")
        })
        .catch(err => console.error(err.message))

}

const createCards = (array) => {
    responseArea.innerHTML = null;

    array.forEach(obj => {
        const div = document.createElement('div');
        div.classList = "card";

        div.innerHTML = `
            <div id="${obj.id}-med-name"><p>${obj.med_name}</p></div>
            <div id="${obj.id}-instruction" style="justify-content: center; display: flex; align-items: center; flex-direction: column"><p>${obj.instruction}</p></div>
            <br>
            <button id="${obj.id}-btn" type="button" class="btn btn-success">Edit</button>
            <button id="delete-btn" onclick="deleteMedlistByMedlistId(${obj.id})"> X </button>
        `
        responseArea.appendChild(div)

        //below: user clicks Edit button to trigger put method - updating existing info and save it to database.
        const buttonToEdit = document.getElementById(`${obj.id}-btn`);
        const medNameToEdit = document.getElementById(`${obj.id}-med-name`);
        const instructionToEdit = document.getElementById(`${obj.id}-instruction`);
        buttonToEdit.addEventListener('click', () => {
            medNameToEdit.innerHTML = null;
            medNameToEdit.innerHTML = `
                <input type="text" id="${obj.id}-update-name" placeholder="medication name" value="${obj.med_name}"/><br><br>
            `
            instructionToEdit.innerHTML = null;
            instructionToEdit.innerHTML = `
                <input type="text" id="${obj.id}-update-instruction" placeholder="medication name" value="${obj.instruction}"/><br><br>
                <button id="${obj.id}-save" type="button" class="btn btn-success">Save</button>
            `
            buttonToEdit.style.display = 'none';

            const updateSaveBtn = document.getElementById(`${obj.id}-save`);

            function handleMedlistEdit(id){
                let bodyObj = {
                    "id": obj.id,
                    "med_name": document.getElementById(`${obj.id}-update-name`).value,
                    "instruction": document.getElementById(`${obj.id}-update-instruction`).value
                }
                fetch(`http://localhost:8080/api/v1/medlist`, {
                    method: 'PUT',
                    body: JSON.stringify(bodyObj),
                    headers: headers
                }).then(() => {
                    location.assign("medlist.html");
                })
                    .catch(err => console.error(err))
                return getAllMedlistByMemberId(memberId);
            }

            updateSaveBtn.addEventListener('click', handleMedlistEdit);
        })

    })
}



//step 3, combine html elements and functions using event listeners
addBtn.addEventListener('click', addMedlist);
