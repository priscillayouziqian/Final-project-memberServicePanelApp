console.clear();
console.log("hello");

// STEP 1: grab the html elements
const regsiterBtn = document.querySelector("#register-btn");
const loginBtn = document.querySelector("#login-btn");
const username = document.querySelector(".username");
const password = document.querySelector(".password");
const baseUrl = 'http://localhost:8080/api/v1/members'

const headers = {
    'Content-Type':'application/json'
}

// STEP 2: write callback functions
const registerMember = async() =>{
    const body = {
        "username": username.value,
        "password": password.value
    }

    const response = await fetch(`${baseUrl}/register`, {
        method: "POST",
        body: JSON.stringify(body),
        headers: headers
    })
        .catch(err => console.error(err.message))
    
    const responseArr = await response.json()

    if(response.status === 200){
        document.cookie = `memberId=${responseArr[1]}`
        alert("member registered successfully")
        window.location.replace(responseArr[0])
    }
};

const loginMember = async() =>{
    const body = {
        "username": username.value,
        "password": password.value
    }

    const response = await fetch(`${baseUrl}/login`, {
        method: "POST",
        body: JSON.stringify(body),
        headers: headers
    })
        .catch(err => console.error(err.message))
    
    const responseArr = await response.json()

    if(response.status === 200){
        document.cookie = `memberId=${responseArr[1]}`
        location.assign("dashboard.html")
        // window.location.replace(responseArr[0])
    }
};

// STEP 3: combine elements and functions by using event listensers
regsiterBtn.addEventListener('click', registerMember);
loginBtn.addEventListener('click', loginMember);