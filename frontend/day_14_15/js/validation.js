function login() {
   
    // You can perform login validation and authentication here
    // For simplicity, let's just display an alert
    let username = document.getElementById("loginUsername").value.trim();
    let password = document.getElementById("loginPassword").value.trim();

    console.log(`Login clicked. Username: ${username}, Password: ${password}`);
    
}

function register() {
   

    // Frontend validation for registration form
    

    // Validate email format
    
    // Validate username (no special characters)
    

    // Validate password (at least 8 characters, one capital letter, and one numeric)

    let name = document.getElementById("registerName").value.trim();
    let namepattern = /^[A-Za-z0-9]+$/;
    if(!namepattern.test(name))
    {
        alert("Inavlid UserName: Username Cannot Contain Special Characters");
        return;
    }

    let password = document.getElementById("registerPassword").value;
    let passwordpattern = /^(?=.*[A-Z])(?=.*\d).+$/;
    if(!passwordpattern.test(password) || password.length < 8)
    {
        alert("Invalid Password");
        return;
    }

    let email = document.getElementById("registerEmail").value.trim();
    let emailpattern = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
    if(!emailpattern.test(email))
    {
        alert("Invalid Email");
        return;
    }
    let username = document.getElementById("registerUsername").value.trim();

    console.log(`Register clicked. Name: ${name}, Email: ${email}, Username: ${username}, Password: ${password}`);
    
}
module.exports = { login, register };
