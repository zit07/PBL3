// Link Click To Show
const Signuplink = document.getElementById("signup-link");
const Signuplink2 = document.getElementById("signup-link2");
const Loginlink = document.getElementById("login-link");
const Loginlink2 = document.getElementById("login-link2");
const ChangePasslink = document.getElementById("changepass-link");
const ChangePasslink2 = document.getElementById("changepass-link2");
const ChangePINlink = document.getElementById("changepin-link");
const ChangePINlink2 = document.getElementById("changepin-link2");
const Infolink = document.getElementById("info-link"); 

// Form to show
const Signupform = document.getElementById("form-signup");
const Loginform = document.getElementById("form-login");
const ChangePassform = document.getElementById("form-changepassword");
const ChangePINform = document.getElementById("form-changepin");
const Infoform = document.getElementById("form-info");

// Function to show form
const showForm = (form, event) => {
    event.preventDefault(); 
    Signupform.style.display = "none";
    Loginform.style.display = "none"; 
    ChangePassform.style.display = "none";
    ChangePINform.style.display = "none";
    Infoform.style.display = "none";
    form.style.display = "flex";
}

Signuplink.addEventListener("click", (event) => showForm(Signupform, event));
Loginlink.addEventListener("click", (event) => showForm(Loginform, event));
Signuplink2.addEventListener("click", (event) => {
    showForm(Signupform, event);
    Loginform.style.display = "none";
});
Loginlink2.addEventListener("click", (event) => {
    Signupform.style.display = "none";
    showForm(Loginform, event);
});
ChangePasslink.addEventListener("click", (event) => showForm(ChangePassform, event));
ChangePINlink.addEventListener("click", (event) => showForm(ChangePINform, event));
ChangePasslink2.addEventListener("click", (event) => {
    showForm(ChangePassform, event);
    ChangePINform.style.display = "none";
});
ChangePINlink2.addEventListener("click", (event) => {
    ChangePassform.style.display = "none";
    showForm(ChangePINform, event);
});
Infolink.addEventListener("click", (event) => showForm(Infoform, event));

// Show noti
