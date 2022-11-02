console.log("dashboard js connected");

//STEP 1: grab html elements
const logoutBtn = document.querySelector("#logout-btn");
const username = localStorage.getItem("username");
const showUsenameArea = document.querySelector("#showUsername");

//STEP 2: write callback functions
function handleLogout( name, path, domain ) {
    if( get_cookie( name ) ) {
      document.cookie = name + "=" +
        ((path) ? ";path="+path:"")+
        ((domain)?";domain="+domain:"") +
        ";expires=Thu, 01 Jan 1970 00:00:01 GMT";
    }
    
    location.assign("home.html")
    alert("user logged out successfully")
}

function get_cookie(name){
    return document.cookie.split(';').some(c => {
        return c.trim().startsWith(name + '=');
    });
}

function getLocalStorageUsername(){
        showUsenameArea.innerHTML = null;
        showUsenameArea.innerHTML = `
            <p>Hello, ${username}</p>
        `

}
getLocalStorageUsername();

//STEP 3: combine html elements and functions using event listeners.
logoutBtn.addEventListener("click", handleLogout);