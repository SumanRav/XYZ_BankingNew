function checkStatus() {
	var s = location.search.split('success=')[1];
	if(s){
		document.getElementById("loginFailMsg").innerHTML = "Invalid username or password"
	}
}

function getParams(url) {
	console.log(url);
	var params = {};
	var parser = document.createElement('a');
	parser.href = url;
	var query = parser.search.substring(1);
	console.log(query);
	var vars = query.split('&');
	for (var i = 0; i < vars.length; i++) {
		var pair = vars[i].split('=');
		params[pair[0]] = decodeURIComponent(pair[1]);
	}
	return params;
};

function validate(){
	var userErrMsg = document.getElementById("userErrMsg");
	var passErrMsg = document.getElementById("passErrMsg");
	var username = loginform.username.value;
	var password = loginform.password.value;
	
	var success = false;
	
	if(username=="" || username==null){
		userErrMsg.innerHTML = "Please enter username";
		passErrMsg.innerHTML = "";
		success = false;
	}else if(password=="" || password==null){
		userErrMsg.innerHTML = "";
		passErrMsg.innerHTML = "Please enter password";
		success = false;
	}else{
		userErrMsg.innerHTML = "";
		passErrMsg.innerHTML = "";
		success = true;
	}
	
	return success;
}
