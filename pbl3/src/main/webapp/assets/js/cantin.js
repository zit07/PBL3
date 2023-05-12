// Link Click To Show

const ChangePasslink = document.getElementById("changepass-link"),
      ChangePINlink = document.getElementById("changepin-link"),
	  ChangePasslink2 = document.getElementById("changepass-link2"),
	  ChangePINlink2 = document.getElementById("changepin-link2"),
	  Infolink = document.getElementById("info-link"),
      Infolink2 = document.getElementById("info-link2"),
      ChangeTimeOpenlink = document.getElementById("changeTimeOpen-link"),
      ChangeTimeOpenlink2 = document.getElementById("changeTimeOpen-link2"),
      AddProductlink = document.getElementById("add-product-link");
      
// Form to show
const ChangePassform = document.getElementById("form-changepassword"),
	  ChangePINform = document.getElementById("form-changepin"),
	  Infoform = document.getElementById("form-info"),
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
    
    const historyLinks = document.querySelectorAll('.header__search-history-item-link');
	historyLinks.forEach(link => {
	  link.addEventListener('mousedown', (event) => {
	    event.preventDefault();
	  });
	});




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

const NotshowTitleMonan = (title) => {
  title.style.display = "none";
}
const changeBG = (link) => {
	TatcamonanLink.classList.remove("choose");
	ListDangbanLink.classList.remove("choose");
	ListNgungbanLink.classList.remove("choose");
	ListDaxoaLink.classList.remove("choose");
	link.classList.add("choose");
}
TatcamonanLink.addEventListener("click", (event) => {
  showContent(Tatcamonan, event);
  NotshowTitleMonan(ListMonanTitle);
  changeBG(TatcamonanLink);
});
ListDangbanLink.addEventListener("click", (event) => {
    showContent(MonanDangban, event);
    changeBG(ListDangbanLink);
});
ListNgungbanLink.addEventListener("click", (event) => {
    showContent(MonanNgungban, event);
    changeBG(ListNgungbanLink);
});
ListDaxoaLink.addEventListener("click", (event) => {
    showContent(MonanDaxoa, event);
    changeBG(ListDaxoaLink);
});


const avatar = document.getElementById("img-form"),
	  inputIMG = document.getElementById("input-img-form");
	inputIMG.addEventListener("change", ()=>{
		avatar.src = URL.createObjectURL(inputIMG.files[0]);
	});
	
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

const formEditTime = document.getElementById("form-editTime");
formEditTime.addEventListener("submit", (event) => {
	    event.defaultPrevented;
	    for (let i = 2; i <= 8; i++) {
	        const openSelect = document.getElementsByName(`txtTimeOpen${i}`)[0];
	        const closeSelect = document.getElementsByName(`txtTimeClose${i}`)[0];
	        const openValue = parseInt(openSelect.value.replace(":", ""));
	        const closeValue = parseInt(closeSelect.value.replace(":", ""));
	        if (openValue >= closeValue && openValue !== -1 && closeValue !== -1) {
				console.log(closeValue+openValue);
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

