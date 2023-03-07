//Xử lý kịch bản cho user.html

function generatePermis(){
	//khai báo quyền thực thi trong hệ thống
	var permis = new Array();
	
	permis[0] = "---";
	permis[1] = "Members";
	permis[2] = "Authors";
	permis[3] = "Managers";
	permis[4] = "Admintrator";
	permis[5] = "Super Admintrator";
	
	
	var opt = '<select class="form-control" id="permis" name="slcUserPermis">';
	
	for (var i = 0; i < permis.length; i ++){
		opt += '<option value="'+ i +'">';
		opt += permis[i];
		opt += '</option>';
	}
	opt += '</select>';
	
	
	//in ra màn hình
	document.write(opt);
}

function generateRole(){
	//
	var roles = new Array();
	
	roles[0] = "User";
	roles[1] = "Section";
	roles[2] = "Category";
	roles[3] = "Articles";
	roles[4] = "Product System";
	roles[5] = "Product Group";
	roles[6] = "Product Category";
	roles[7] = "Product";
	roles[8] = "Order";
	roles[9] = "Customer";
	
	var role = '';
	for(var i = 0; i < roles.length; i ++){
		//Mở dòng
		if(i % 3 == 0){
			role += '<div class="row align-items-center">';
		}
		
		role += '<div class="col-lg-4">';
		role += '<input class="form-check-input" disabled name="chks" onclick="checkPermis()" type="checkbox" value="" id = "role'+i+'"> &nbsp;';
		role += '<label class="text-start" for="role'+i+'">';
		role += '<i class="fa-solid fa-user"></i>';
		role += roles[i] + 'management';
		role += '</label>';
		role += '</div>';
		
		//Đóng dòng
		if((i % 3 == 2) ||( i == roles.length-1)){
			role += '</div>'
		}
	}
	
	document.write(role);
}

function setCheckBox(fn, check, dis){
	//Duyệt các phần tử của form
	for(let i = 0; i < fn.elements.length; i ++){
		if((fn.elements[i].type == "checkbox") && (fn.elements[i].name == "chks")){
			fn.elements[i].checked = check;
			fn.elements[i].disabled = dis;
		}
	}
	
}

function refreshPermis(fn){
	//lấy permisson
	var permis = parseInt(document.getElementById("permis").value);
	
	if((permis == 4) || (permis ==5)){
		this.setCheckBox(fn,true,true);
	}else if(permis == 3){
		this.setCheckBox(fn,true,false);
	}else if (permis == 2){
		this.setCheckBox(fn,false,false);
	} else{
		this.setCheckBox(fn,false,true);
	}
	
	this.checkPermis();
}


function checkUsername(){
	//Lấy thông tin
	let username = document.getElementById("username").value;
	
	var validUsername = true;
	
	var message = "";
	
	//Tham chiếu đối tượng hiển thị lỗi
	let errUsername = document.getElementById("errUsername");
	
	//Lấy thông tin email
	let email = document.getElementById("email");
	
	if (username.trim() == ""){
		validUsername = false;
		message = "Username không được để trống.";
	} else{
		if((username.length < 5) || (username.length > 50)){
			validUsername = false;
			message = "Tên đăng nhập trong khoảng 5 đến 50 kí tự.";
		} else{
			if(username.indexOf(" ") != -1){
				validUsername = false;
				message = "Tên đăng nhập không sử dụng \" \".";
			}else if(username.indexOf("@") != -1){
				var parttern = /\w+@\w+[.]\w/;
				if(!username.match(parttern)){
					validUsername = false;
					message = "Sai cấu trúc hộp thư";
					email.disabled = false;
				}else{
					email.disabled = true;
					document.getElementById("errEmail").innerHTML ="";
					document.getElementById("email").value = username;
					document.getElementById("iconEmail").innerHTML = '<i class="fa-solid fa-check"></i>';
					document.getElementById("iconEmail").style.color = "green";
				}
			} else{
				email.disabled = false;
			}
		}
	}
	
	if(!validUsername){
		errUsername.innerHTML = message;
		errUsername.style.color = "red";
		errUsername.style.fontSize = "14px";
		errUsername.style.padding = "2px 0";
	} else{
		errUsername.innerHTML = '<i class="fa-solid fa-check"></i>';
		errUsername.style.color = "green";
		errUsername.style.fontSize = "14px";
	}
	
	return validUsername;
}


//Kiểm tra password

function checkPassword(){
	//lấy pass
	let pass = document.getElementById("pass").value;
	//Lấy confirm pass
	let cfpass = document.getElementById("cfpass").value;
	//lấy username
	let username = document.getElementById("username").value;
	//Biến thông báo lỗi
	var message = "";
	var message1 = "";
	
	//
	let validPassword = true;
	
	//Tham chiếu đối tượng hiển thị lỗi
	
	let errPassword = document.getElementById("errPassword");
	let iconCfPassword = document.getElementById("iconCfPassword");
	let iconPassword = document.getElementById("iconPassword");
	//so sánh pass & cfpass
	if(pass == ""){
		validPassword = false;
		message = "Mật khẩu không được để trống";
	}else{
		if((pass.length < 6) ||(pass.length > 50)){
			validPassword = false;
			message = "Mật khẩu quá ngắn hoặc quá dài";
		}else{
			
			
			
			if((username != "") && (pass.indexOf(username) != -1)){
					validPassword = false;
					message = "Mật khẩu không được chứa tên đăng nhập";
					document.getElementById("cfpass").disabled = true;
			}else{
				document.getElementById("cfpass").disabled = false;
				if(cfpass == ""){
					validPassword = false;
					message1 = "Vui lòng nhập mật khẩu xác nhận.";
				}else{
					if(pass != cfpass){
					validPassword = false;
					message1 = "Mật khẩu xác nhận sai.";
					}
				}
			}
		}
	}
	
	
	if(!validPassword){
		iconPassword.innerHTML = '';
		iconCfPassword.innerHTML = '';
		
		errPassword.innerHTML = message;
		errPassword.style.color = "red";
		errPassword.style.fontSize = "14px";
		errPassword.style.padding = "2px 0";
		
		errCfPassword.innerHTML = message1;
		errCfPassword.style.color = "red";
		errCfPassword.style.fontSize = "14px";
		errCfPassword.style.padding = "2px 0";
		
	}else{
		errCfPassword.innerHTML = "";
		errPassword.innerHTML = "";
		
		iconPassword.innerHTML = '<i class="fa-solid fa-check"></i>';
		iconPassword.style.color = "green";
		
		iconCfPassword.innerHTML = '<i class="fa-solid fa-check"></i>';
		iconCfPassword.style.color = "green";
	}
	
	return validPassword;
}
//Kiểm tra email

function checkEmail(){
	
	let email = document.getElementById("email").value;
	
	var message = "";
	
	let validEmail = true;
	
	let errEmail = document.getElementById("errEmail");
	
	if(email.trim() == ""){
		validEmail = false;
		message = "Vui lòng nhập hộp thư"
	} else if(email.indexOf("@") != -1){
			var parttern = /\w+@\w+[.]\w/;
			if(!email.match(parttern)){
				validEmail = false;
				message = "Sai cấu trúc hộp thư";
			}
	} else{
		validEmail = false;
		message = "Nhập đúng cấu trúc email";
	}
	
	
	if(!validEmail){
		errEmail.innerHTML = message;
		errEmail.style.color = "red";
		errEmail.style.fontSize = "14px";
		errEmail.style.padding = "2px 0";
	}else{
		errEmail.innerHTML = "";
		iconEmail.innerHTML = '<i class="fa-solid fa-check"></i>';
		iconEmail.style.color = "green";
	}
	
	return validEmail;
}

//check permis
function checkPermis(){
	let permis = parseInt(document.getElementById("permis").value);
	
	var validPermis = true;
	var errRolesHidden = false;
	var message = "Cần chọn tối thiểu 1 role";
	//Tham chiếu vị trí báo lỗi
	let iconPermis = document.getElementById("iconPermis");
	let errRoles = document.getElementById("errRoles");
	
	
	
	if((permis == 2) || (permis == 3)){
		//Tham chiếu form
		let fn = document.getElementById("frmUser");
		
		for(var i = 0; i < fn.elements.length; i ++){
			if((fn.elements[i].type == "checkbox") && (fn.elements[i].name == "chks")){
				if(fn.elements[i].checked){
					validPermis = true;
					break;
				}else{
					validPermis = false;
				}
			}
		}
	} else{
		errRolesHidden = true;
	}
	
	//Xuất thông báo
	if(!validPermis){
		errRoles.innerHTML = message;
		errRoles.style.color = "red";
		errRoles.style.fontSize = "14px";
		errRoles.style.padding = "2px 0";
		
		iconPermis.innerHTML = '<i class="fa-solid fa-xmark"></i>';
		iconPermis.style.color = "red";
	}else{
		if(errRolesHidden){
			iconPermis.style.display = "none";
		}else{
			errRoles.innerHTML = "";
			iconPermis.innerHTML = '<i class="fa-solid fa-check"></i>';
			iconPermis.style.color = "green";
			iconPermis.style.display = "inline-block";
		}
	}
	
	return validPermis;
}


function checkValidUser(){
	//Kiểm tra tên
	let checkName = this.checkUsername();
	//Kiểm tra pass
	let checkPass = this.checkPassword();
	//Kiểm tra email
	let checkEmail = this.checkEmail();
	let email = document.getElementById("email");
	if(email.disabled){
		checkEmail = true;
	}
	
	if(!checkName){
		document.getElementById("username").focus();
		document.getElementById("username").select;
	}else if(!checkPass){
		document.getElementById("pass").focus();
		document.getElementById("pass").select;
	}else if(!checkEmail){
		document.getElementById("email").focus();
		document.getElementById("email").select;
	}
	
	return checkName && checkPass && checkEmail;
}

function createAccount(fn){
	if(this.checkValidUser()){
		fn.method ="Post";
		fn.action ="/adv/user/add";
		fn.submit();
	}
}

function editProfile(fn){
	fn.method ="Post";
	fn.action ="/adv/user/profiles/edit";
	fn.submit();
}
function checkValidChangePass(fn){
	//Lấy giá trị newpass và renewpass
	let newpass = document.getElementById("newPassword").value;
	let renewpass = document.getElementById("renewPassword").value;
	
	var validChangePass = true;
	
	//Đường dẫn thông báo lỗi
	let err = document.getElementById("iconNewPass");
	let err1 = document.getElementById("iconReNewPass");
	newpass = newpass.trim();
	renewpass = renewpass.trim();
	
	if((newpass != "") && (renewpass != "")){
		if(newpass.length < 6){
			validChangePass = false;
		}else{
			if(newpass == renewpass){
				validChangePass = true;
			}else{
				validChangePass = false;
			}
		}
		
	}else{
		validChangePass = false;
	}
	
	
	if(!validChangePass){
		err.innerHTML = "<i class=\"fa-solid fa-xmark\"></i>";
		err.style.color = "#dc3545";
		err.style.fontSize = "16px";
		err1.innerHTML = "<i class=\"fa-solid fa-xmark\"></i>";
		err1.style.color = "#dc3545";
		err1.style.fontSize = "16px";
	}else{
		err.innerHTML = "<i class=\"fa-solid fa-check\"></i>";
		err.style.color = "#28a745";
		err.style.fontSize = "16px";
		err1.innerHTML = "<i class=\"fa-solid fa-check\"></i>";
		err1.style.color = "#28a745";
		err1.style.fontSize = "16px";
	}
	return validChangePass;
	
}
function changePass(fn){
	if(this.checkValidChangePass()){
		fn.method = "POST";
		fn.action = "/adv/user/profiles/changepass";
		fn.submit();
	}
	
}