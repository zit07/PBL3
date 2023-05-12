// Link Click To Show

const Signuplink = document.getElementById("signup-link"),
	  Signuplink2 = document.getElementById("signup-link2"),
	  Loginlink = document.getElementById("login-link"),
	  Loginlink2 = document.getElementById("login-link2"); 

// Form to show
const Signupform = document.getElementById("form-signup"),
	  Loginform = document.getElementById("form-login");

// Function to show form
	const showForm = (form, event) => {
	    event.preventDefault(); 
	    Signupform.style.display = "none";
	    Loginform.style.display = "none"; 
	    form.style.display = "flex";
	}
	Signuplink.addEventListener("click", (event) => showForm(Signupform, event));
	Loginlink.addEventListener("click", (event) => showForm(Loginform, event));
	Signuplink2.addEventListener("click", (event) => showForm(Signupform, event));
	Loginlink2.addEventListener("click", (event) => showForm(Loginform, event));

// Show noti
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



$(function () {
  var district;
  var province = JSON.parse(address);
  var tinhValue = $('#tinh').text();
  var huyenValue = $('#huyen').text();
  var xaValue = $('#xa').text();
	

  $('#province').html('<option value="-1">Chọn tỉnh/thành phố</option>');
  $('#district').html('<option value="-1">Chọn quận/huyện</option>');
  $('#town').html('<option value="-1">Chọn phường/xã</option>');

  var option = [], option1 = [], option2 = [], x = 0, y = 0, z = 0;
  $.each(province, function (index, element) {
    option.push($('<option>').attr('value', element.code).text(element.name));
    if (element.code == tinhValue) {
      option[x].attr('selected', true);
      district = element.districts;
      $.each(district, function (index1, element1) {
        option1.push($('<option>').attr('value', element1.code).text(element1.name));
        if (element1.code == huyenValue) {
          option1[y].attr('selected', true);
          $.each(element1.wards, function (index2, element2) {
            option2.push($('<option>').attr('value', element2.code).text(element2.name));
            if (element2.code == xaValue) {
              option2[z].attr('selected', true);
            }
            z++;
          });
        }
        y++;
      });
    }
    x++;
  });
  $('#province').append(option);
  $('#district').append(option1);
  $('#town').append(option2);

  $('#province').change(function () {
    $('#district').html('<option value="-1">Chọn quận/huyện</option>');
    $('#town').html('<option value="-1">Chọn phường/xã</option>');
    var option1 = [], option2 = [], y = 0, z = 0;
    var value = $(this).val();
    $.each(province, function (index, element) {
      if (element.code == value) {
        district = element.districts;
        $.each(district, function (index1, element1) {
          option1.push($('<option>').attr('value', element1.code).text(element1.name));
        });
      }
    });
    $('#district').append(option1);
    $('#town').append(option2);
  });

  $('#district').change(function () {
    $('#town').html('<option value="-1">Chọn phường/xã</option>');
    var option2 = [], z = 0;
    var value = $(this).val();
    $.each(district, function (index, element) {
      if (element.code == value) {
        $.each(element.wards, function (index1, element1) {
          option2.push($('<option>').attr('value', element1.code).text(element1.name));
        });
      }
    });
    $('#town').append(option2);
  });
});

	  
	  