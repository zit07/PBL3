// Link Click To Show

const Signuplink = document.getElementById("signup-link"),
	  Signuplink2 = document.getElementById("signup-link2"),
	  Loginlink = document.getElementById("login-link"),
	  Loginlink2 = document.getElementById("login-link2"),
	  ChangePasslink = document.getElementById("changepass-link"),
	  ChangePasslink2 = document.getElementById("changepass-link2"),
	  ChangePINlink = document.getElementById("changepin-link"),
	  ChangePINlink2 = document.getElementById("changepin-link2"),
	  Infolink = document.getElementById("info-link"); 

// Form to show
const Signupform = document.getElementById("form-signup"),
	  Loginform = document.getElementById("form-login"),
 	  ChangePassform = document.getElementById("form-changepassword"),
	  ChangePINform = document.getElementById("form-changepin"),
	  Infoform = document.getElementById("form-info");

// Function to show form
if  (Signuplink!==null){
	const showForm = (form, event) => {
	    event.preventDefault(); 
	    Signupform.style.display = "none";
	    Loginform.style.display = "none"; 
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
} else {
	const showForm = (form, event) => {
	    event.preventDefault();
	    ChangePassform.style.display = "none";
	    ChangePINform.style.display = "none";
	    Infoform.style.display = "none";
	    form.style.display = "flex";
	}
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
}

// Show noti
if  (Signuplink!==null){
	const shownotisdt = document.getElementById('notiSignupsdt');
	const shownotiSignupConfirm = document.getElementById('notiSignupConfirmPass');
	var formSignup = document.querySelector('.formSignup');
	formSignup.addEventListener('submit', function(event) {
	  var password = formSignup.querySelector('input[name="txtPassword"]');
	  var confirmPassword = formSignup.querySelector('input[name="txtConfirmPassword"]');
	  if (password.value !==  confirmPassword.value) {
	    shownotiSignupConfirm.style.display = "flex";
	    shownotisdt.style.display = "none";
	    event.preventDefault();
	  }
	});
} else {
	const shownotiChangePassConfirm = document.getElementById('notiChangePassConfirm');
	const notiErrorOldPass = document.getElementById('notiErrorOldPass');
	var formChangePass = document.querySelector('.formChangePass');
	formChangePass.addEventListener('submit', function(event) {
	  var newpassword = formChangePass.querySelector('input[name="txtNewPass"]');
	  var newconfirmPassword = formChangePass.querySelector('input[name="txtConfirmNewPass"]');
	  if (newpassword.value !==  newconfirmPassword.value) {
	    shownotiChangePassConfirm.style.display = "flex";
	    notiErrorOldPass.style.display = "none";
	    event.preventDefault(); 
	  }
	});
}

$(function () {
  apiProvince=(prodvince)=>{
      let district;
  
      prodvince.forEach(element => {
          $('#province').append(`<option value="${element.code}">${element.name}</option>`)
      });
      $('#province').change(function () {
          $('#district').html('<option value="-1">Chọn quận/huyện</option>')
          $('#town').html('<option value = "-1"> Chọn phường/xã </option>')
          let value = $(this).val();
          $.each(prodvince,function(index,element){
              if (element.code == value) {
                  district = element.districts;
                  $.each(element.districts,function(index,element1){
                      $('#district').append(`<option value="${element1.code}">${element1.name}</option>`)
                  })
                  
              }
          })         
      });    
      $('#district').change(function () {
          $('#town').html('<option value = "-1"> Chọn phường/xã </option>')
          let value = $(this).val();
          $.each(district,function(index,element){
              if (element.code == value) {
                  element.wards.forEach(element1 => {
                      $('#town').append(`<option value="${element1.code}">${element1.name}</option>`)
                  });
              } 
          })       
      });
  }
  prodvince = JSON.parse(address);
   apiProvince(prodvince);
})

const avatar = document.getElementById("img-form"),
	  inputIMG = document.getElementById("input-img-form");
	  
	inputIMG.addEventListener("change", ()=>{
		avatar.src = URL.createObjectURL(inputIMG.files[0]);
	});
	  

	  
	  