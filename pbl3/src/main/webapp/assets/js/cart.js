// Link Click To Show

const ChangePasslink = document.getElementById("changepass-link"),
	  ChangePasslink2 = document.getElementById("changepass-link2"),
	  ChangePINlink = document.getElementById("changepin-link"),
	  ChangePINlink2 = document.getElementById("changepin-link2"),
	  Infolink = document.getElementById("info-link");

// Form to show
const ChangePassform = document.getElementById("form-changepassword"),
	  ChangePINform = document.getElementById("form-changepin"),
	  Infoform = document.getElementById("form-info");

// Function to show form
	const showForm = (form, event) => {
	    event.preventDefault();
	    ChangePassform.style.display = "none";
	    ChangePINform.style.display = "none";
	    Infoform.style.display = "none";
	    form.style.display = "flex";
	}
	ChangePasslink.addEventListener("click", (event) => showForm(ChangePassform, event));
	ChangePINlink.addEventListener("click", (event) => showForm(ChangePINform, event));
	ChangePasslink2.addEventListener("click", (event) => showForm(ChangePassform, event));
	ChangePINlink2.addEventListener("click", (event) => showForm(ChangePINform, event));
	Infolink.addEventListener("click", (event) => showForm(Infoform, event));


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


const HideForm = (event) => {
	event.preventDefault();
	ChangePassform.style.display = "none";
	ChangePINform.style.display = "none";
	Infoform.style.display = "none";
	formscartdetails.forEach((form) => {
      event.preventDefault(); 
      form.style.display = "none";
    });
}

var goback = document.querySelectorAll('.auth-form__back');
for(var i = 0; i < goback.length; i++) {
    console.log(goback[i].className);
    goback[i].addEventListener("click", (event) => HideForm(event));
}
// link to show content
const TatcamonanLink = document.getElementById("link-product_tatca"),
      ListDangbanLink = document.getElementById("link-product_dangban"),
      ListNgungbanLink = document.getElementById("link-product_ngungban"),
      ListDaxoaLink = document.getElementById("link-product_daxoa");

// form to show content
const Tatcamonan = document.getElementById("list-product_tatca"),
      MonanDangban = document.getElementById("list-product_dangban"),
      MonanNgungban = document.getElementById("list-product_ngungban"),
      MonanDaxoa = document.getElementById("list-product_daxoa"),
      ListMonanTitle = document.getElementById("list-product__title");

// Function to show content
const showContent = (content, event) => {
  event.preventDefault(); 
  Tatcamonan.style.display = "none";
  MonanDangban.style.display = "none";
  MonanNgungban.style.display = "none";
  MonanDaxoa.style.display = "none";
  ListMonanTitle.style.display = "block";
  content.style.display = "block";
}



// Show noti
 shownotiChangePassConfirm = document.getElementById('notiChangePassConfirm');
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

const avatar = document.getElementById("img-form"),
	  inputIMG = document.getElementById("input-img-form");
	  
	inputIMG.addEventListener("change", ()=>{
		avatar.src = URL.createObjectURL(inputIMG.files[0]);
	});
	  
	  
const historyLinks = document.querySelectorAll('.header__search-history-item-link');
	historyLinks.forEach(link => {
	  link.addEventListener('mousedown', (event) => {
	    event.preventDefault();
	  });
	});
const btnDelhistoryLinks = document.querySelectorAll('.btn-del-history');
	btnDelhistoryLinks.forEach(link => {
	  link.addEventListener('mousedown', (event) => {
	    event.preventDefault(); 
	  });
	});
	


window.onload = function(){
    var quantityCells = document.querySelectorAll('td.quantity');
    quantityCells.forEach(function(cell) {
      var quantity = cell.textContent.trim();
      if (quantity === '0') {
        var checkbox = cell.parentElement.querySelector('input[type=checkbox]');
        checkbox.disabled = true;
      }
    });
  };
