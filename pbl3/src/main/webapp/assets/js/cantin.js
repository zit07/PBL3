// Link Click To Show

const ChangePasslink = document.getElementById("changepass-link"),
      ChangePINlink = document.getElementById("changepin-link"),
	  ChangePasslink2 = document.getElementById("changepass-link2"),
	  ChangePINlink2 = document.getElementById("changepin-link2"),
	  ChangeBanklink = document.getElementById("changeinfoBank-link"),
	  Infolink = document.getElementById("info-link"),
      Infolink2 = document.getElementById("info-link2"),
      ChangeTimeOpenlink = document.getElementById("changeTimeOpen-link"),
      ChangeTimeOpenlink2 = document.getElementById("changeTimeOpen-link2"),
      AddProductlink = document.getElementById("add-product-link");
      
// Form to show
const ChangePassform = document.getElementById("form-changepassword"),
	  ChangePINform = document.getElementById("form-changepin"),
	  Infoform = document.getElementById("form-info"),
	  ChangeBankform = document.getElementById("form-infobank"),
      AddProductform = document.getElementById("form-addProduct"),
      ChangeTimeOpenform = document.getElementById("form-changeTimeOpen");
      
// Function to show form
	const showForm = (form, event) => {
	    event.preventDefault();
	    ChangePassform.style.display = "none";
	    ChangePINform.style.display = "none";
	    AddProductform.style.display = "none";
        Infoform.style.display = "none";
        ChangeTimeOpenform.style.display = "none";
        ChangeBankform.style.display = "none";
	    form.style.display = "flex";
	}
	ChangePasslink.addEventListener("click", (event) => showForm(ChangePassform, event));
	ChangePINlink.addEventListener("click", (event) => showForm(ChangePINform, event));
	ChangePasslink2.addEventListener("click", (event) => showForm(ChangePassform, event));
	ChangePINlink2.addEventListener("click", (event) => showForm(ChangePINform, event));
	Infolink.addEventListener("click", (event) => showForm(Infoform, event));
    ChangeTimeOpenlink.addEventListener("click", (event) => showForm(ChangeTimeOpenform, event));
    Infolink2.addEventListener("click", (event) => showForm(Infoform, event));
    ChangeTimeOpenlink2.addEventListener("click", (event) => showForm(ChangeTimeOpenform, event));
    AddProductlink.addEventListener("click", (event) => showForm(AddProductform, event));
    ChangeBanklink.addEventListener("click", (event) => showForm(ChangeBankform, event));
 	   
    const historyLinks = document.querySelectorAll('.header__search-history-item-link');
	historyLinks.forEach(link => {
	  link.addEventListener('mousedown', (event) => {
	    event.preventDefault();
	  });
	});

const forms = document.querySelectorAll('div[name="form-editProduct"]'); // Lấy tất cả các phần tử có name="form-editProduct"
const links = document.querySelectorAll('a[name="link-editproduct"]'); // Lấy tất cả các thẻ a có name="linkeditproduct"

links.forEach((link) => {
  link.addEventListener("click", (event) => {
	const idlink = link.getAttribute('id')
	forms.forEach((form) => {
      const idform = form.getAttribute('id');
	  event.preventDefault(); 
	  form.style.display = "none";
	  if (idform === idlink) {
		form.style.display = 'flex';
	  }
	});
  });
});


const HideForm = (event) => {
	event.preventDefault();
	ChangePassform.style.display = "none";
	ChangePINform.style.display = "none";
	AddProductform.style.display = "none";
    Infoform.style.display = "none";
    ChangeTimeOpenform.style.display = "none";
    ChangeBankform.style.display = "none";
    forms.forEach((form) => {
      form.style.display = "none";
	});
}

var goback = document.querySelectorAll('.auth-form__back');
for(var i = 0; i < goback.length; i++) {
    goback[i].addEventListener("click", (event) => HideForm(event));
}

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

const img = document.querySelectorAll('.avatar-form__img');
const input_img = document.querySelectorAll('.avatar-form__input');
    for (let i = 0; i < img.length; i++) {
      input_img[i].addEventListener('change', () => {
        img[i].src = URL.createObjectURL(input_img[i].files[0]);
      });
    }
	
const imgElements = document.querySelectorAll('.img-add-product__img');
const imgInputs = document.querySelectorAll('.img-add-product__input');
    for (let i = 0; i < imgElements.length; i++) {
      imgInputs[i].addEventListener('change', () => {
        imgElements[i].src = URL.createObjectURL(imgInputs[i].files[0]);
      });
    }
    
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


const formEditTime = document.getElementById("form-editTime");
formEditTime.addEventListener("submit", (event) => {
	    event.defaultPrevented;
	    for (let i = 2; i <= 8; i++) {
	        const openSelect = document.getElementsByName(`txtTimeOpen${i}`)[0];
	        const closeSelect = document.getElementsByName(`txtTimeClose${i}`)[0];
	        const openValue = parseInt(openSelect.value.replace(":", ""));
	        const closeValue = parseInt(closeSelect.value.replace(":", ""));
	        if (openValue >= closeValue && openValue !== -1 && closeValue !== -1) {
	            alert(`Thời gian đóng cửa phải sau thời gian mở cửa của thứ ${i}`);
	            event.preventDefault();
	            break; // Dừng kiểm tra nếu có ít nhất một ngay làm việc không hợp lệ
	        }
   		}
	});

const btnDelhistoryLinks = document.querySelectorAll('.btn-del-history');
	btnDelhistoryLinks.forEach(link => {
	  link.addEventListener('mousedown', (event) => {
	    event.preventDefault(); 
	  });
	});

const formscartdetails = document.querySelectorAll('div[name="form-cart-detail"]'); 
const linkcartdetails = document.querySelectorAll('a[name="link-cart-detail"]'); 
linkcartdetails.forEach((link) => {
  link.addEventListener("click", (event) => {
    const idlink = link.getAttribute('id')
    formscartdetails.forEach((form) => {
      const idform = form.getAttribute('id');
      event.preventDefault(); 
      form.style.display = "none";
      if (idform === idlink) {
        form.style.display = 'flex';
      }
    });
  });
});

const HideFormCart = (event) => {
	event.preventDefault(); 
	formscartdetails.forEach((form) => {
      form.style.display = "none";
  });
}
goback = document.querySelectorAll('.auth-form__back');
for(var i = 0; i < goback.length; i++) {
    goback[i].addEventListener("click", (event) => HideFormCart(event));
}
