console.log("medlist js connected");

//step 1, grab html elements
const responseArea = document.querySelector("#responseArea");
const cookieArr = document.cookie.split("=")
const memberId = localStorage.getItem("memberId");
const addBtn = document.querySelector("#addBtn");
const headers = {
    'Content-Type': 'application/json'
}

// let medlistIdArr = [];

// step 2, write callback functions
const getAllMedlistByMemberId = async() => {
    await fetch(`http://localhost:8080/api/v1/medlist/member/${memberId}`, {
        method: "GET",
        headers:headers
    })
        .then(res => res.json())
        .then(data => {
            createCards(data)

            // data.forEach(item => {
            //     console.log(item.id);
            //     let medlistId = item.id;
            //     medlistIdArr.push(medlistId);
            //     console.log(medlistIdArr);
            //
            // })
            // console.log(data[1].med_name);
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
        div.classList = "w3-third";

        div.innerHTML = `
            <p>${obj.med_name}</p>
            <p>${obj.instruction}</p>
            <button id="${obj.id}">Edit</button>
            <button>Save</button>
            <button id="delete-btn" onclick="deleteMedlistByMedlistId(${obj.id})">X</button>
            
        `
        responseArea.appendChild(div)
    })
}

//step 3, combine html elements and functions using event listeners
addBtn.addEventListener('click', addMedlist);
