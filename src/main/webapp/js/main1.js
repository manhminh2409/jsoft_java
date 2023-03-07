//Các xử lý kịch bản cho Login_v3.html

function checkValidUser(fn){
	//Lấy giá trị thông tin user trên giao diện
	let username = document.getElementById("user").value;
	let userpass = document.getElementById("pass").value;
	
	//Biến ghi nhận thông báo
	var message = "";
	
	//Biến kiểm tra từng thành phần thông tin
	var validusername = true;
	var validuserpass = true;
	
	//Biến hiển thị thông báo lỗi
	let viewErrUser =document.getElementById("errUser");
	let viewErrPass =document.getElementById("errPass");
	
	//Kiểm tra username
	username = username.trim();
	if(username == ""){
		validusername = false;
		message = "Không được bỏ trống tên đăng nhập";
	} else {
		if((username.length < 5) || (username.length > 50)){
			validusername = false;
			message = "Sai định dạng tên đăng nhập: tên đăng nhập quá ngắn hoặc quá dài";
		} else{
			if(username.indexOf(" ") != -1){
				validusername = false;
				message = "Sai định dạng tên đăng nhập: tên đăng nhập chứa dấu cách";
			} else{
				if(username.indexOf("@") != -1){
					let parttern = /\w+@\w+[.]\w/;
					if(!username.match(parttern)){
						validusername = false;
						message = "Sai cấu trúc hộp thư";
					}
				}
			}
		}

	}
	
	//Xuất thông báo của username
	
	if(!validusername){
		document.getElementById("userHelp").innerHTML = "";
		document.getElementById("iconCheckUser").innerHTML = "";
		viewErrUser.innerHTML = message;
		viewErrUser.style.color = "#dc3545";
	} else{
		document.getElementById("iconCheckUser").innerHTML = '<i class="fa-solid fa-check"></i>';
		document.getElementById("iconCheckUser").style.color = "green";
		document.getElementById("userHelp").innerHTML = "";
		viewErrUser.innerHTML ="";
	}
	
	
	//Kiểm tra userPass
	userpass = userpass.trim();
	
	if(userpass == ""){
		validuserpass = false;
		message = "Thiếu mật khẩu";
	}else{
		if(userpass.length < 6){
			validuserpass = false;
			message = "Mật khẩu quá ngắn cần lớn hơn 6 kí tự";
		}
	}
	
	//Xuất thông báo của userpass 
	if(!validuserpass){
		viewErrPass.innerHTML = message;
		viewErrPass.style.color = "#dc3545";
		document.getElementById("iconCheckPass").innerHTML ="";
	}else{
		document.getElementById("iconCheckPass").innerHTML = '<i class="fa-solid fa-check"></i>';
		document.getElementById("iconCheckPass").style.color = "green";
		document.getElementById("userHelp").innerHTML = "";
		viewErrPass.innerHTML ="";	
	}
	
	
	//Trả về trạng thái kiểm tra tổng quan
	return validusername && validuserpass;
}


function login(fn){
	
	if(this.checkValidUser){
		
		fn.method = "post";//Đường dẫn gào phương thức doPOST
		fn.action = "/adv/user/login";//Đường dẫn thực thi của Servlet
		fn.submit();
		
	}
}


