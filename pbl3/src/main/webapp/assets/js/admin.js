// Link Click To Show

const ChangePasslink = document.getElementById("changepass-link"),
      ListCuslink = document.getElementById("list-cus-link");
      
// Form to show
const ChangePassform = document.getElementById("form-changepassword"),
	  ListCusform = document.getElementById("form-list__custumer");
      
// Function to show form
	const showForm = (form, event) => {
	    event.preventDefault();
	    ChangePassform.style.display = "none";
	    ListCusform.style.display = "none";
	    form.style.display = "flex";
	}
	ChangePasslink.addEventListener("click", (event) => showForm(ChangePassform, event));
	ListCuslink.addEventListener("click", (event) => showForm(ListCusform, event));




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

