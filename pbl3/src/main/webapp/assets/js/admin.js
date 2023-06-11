// Link Click To Show

const ChangePasslink = document.getElementById("changepass-link");
      
// Form to show
const ChangePassform = document.getElementById("form-changepassword");
      
// Function to show form
	const showForm = (form, event) => {
	    event.preventDefault();
	    ChangePassform.style.display = "none"; 
	    form.style.display = "flex";
	}
	ChangePasslink.addEventListener("click", (event) => showForm(ChangePassform, event));

// Show noti
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

    
$(function () {
  var district;
  var province = JSON.parse(address);
  var tinhValue = $('#tinh').text();
  var huyenValue = $('#huyen').text();
  var xaValue = $('#xa').text();

  const listAddress = document.querySelectorAll('div[name="address_input"]'); 
  for(var i = 0; i < listAddress.length; i++) {
    // Lấy giá trị từ các thẻ span
    var tinhValuei = document.getElementById("tinh" + i).innerHTML;
    var huyenValuei = document.getElementById("huyen" + i).innerHTML;
    var xaValuei = document.getElementById("xa" + i).innerHTML;

    var tinhName = "";
    var huyenName = "";
    var xaName = "";
    $.each(province, function (index, element) {
      if (element.code == tinhValuei) {
        tinhName = element.name
        district = element.districts;
        $.each(district, function (index1, element1) {
          if (element1.code == huyenValuei) {
            huyenName = element1.name
            $.each(element1.wards, function (index2, element2) {
              if (element2.code == xaValuei) {
                xaName = element2.name
              }
              z++;
            });
          }
          y++;
        });
      }
      x++;
    });

    var addressCanteen = document.getElementById("address_canteen" + i);
    if (tinhValuei !== "-1" && huyenValuei !== "-1" && xaValuei !== "-1") {
      addressCanteen.innerHTML = tinhName + ", " + huyenName + ", " + xaName;
    } else if (tinhValuei !== "-1" && huyenValuei !== "-1") {
      addressCanteen.innerHTML = tinhName + ", " + huyenName;
    } else if (tinhValuei !== "-1") {
      addressCanteen.innerHTML = tinhName;
    } else {
      addressCanteen.innerHTML = "Canteen chưa chọn địa chỉ";
    }
  }
  
  
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

const FormListUser = document.querySelectorAll('div[name="form-list-user"]'); 
const LinkListUser = document.querySelectorAll('a[name="link-list-user"]'); 
LinkListUser.forEach((link) => {
  link.addEventListener("click", (event) => {
    event.preventDefault();
    const idlink = link.getAttribute('id')
    FormListUser.forEach((form) => {
      const idform = form.getAttribute('id');
      form.style.display = "none";
      if (idform === idlink) {
        form.style.display = 'flex';
      }
    });
  });
});


const HideFormCart = (event) => {
	event.preventDefault();
  ChangePassform.style.display = "none";
	FormListUser.forEach((form) => {
      form.style.display = "none";
  });
}

goback = document.querySelectorAll('.auth-form__back');
for(var i = 0; i < goback.length; i++) {
    goback[i].addEventListener("click", (event) => HideFormCart(event));
}
