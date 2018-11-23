function validateCustomer(){
	console.log("validateCustomer");
	
	var fnErrMsg = document.getElementById("fnErrMsg");
	var lnErrMsg = document.getElementById("lnErrMsg");
	var dobErrMsg = document.getElementById("dobErrMsg");
	var emailErrMsg = document.getElementById("emailErrMsg");
	var mobileErrMsg = document.getElementById("mobileErrMsg");
	var addressline1ErrMsg = document.getElementById("addressline1ErrMsg");
	var addressline2ErrMsg = document.getElementById("addressline2ErrMsg");
	var cityErrMsg = document.getElementById("cityErrMsg");
	var stateErrMsg = document.getElementById("stateErrMsg");
	var pincodeErrMsg = document.getElementById("pincodeErrMsg");
	var pwdErrMsg = document.getElementById("pwdErrMsg");
	var confirmPwdErrMsg = document.getElementById("confirmPwdErrMsg");
	var errMsgFields = [fnErrMsg,lnErrMsg,dobErrMsg,emailErrMsg,mobileErrMsg,addressline1ErrMsg,
		addressline2ErrMsg,cityErrMsg,stateErrMsg,pincodeErrMsg,pwdErrMsg,confirmPwdErrMsg];
	
	var firstname = registerform.firstname.value;
	var lastname = registerform.lastname.value;
	var dateOfBirth = registerform.dateOfBirth.value;
	var email = registerform.email.value;
	var mobile = registerform.mobile.value;
	var addressline1 = registerform.addressline1.value;
	var addressline2 = registerform.addressline2.value;
	var city = registerform.city.value;
	var state = registerform.state.value;
	var pincode = registerform.pincode.value;
	var password = registerform.password.value;
	var confirmPassword = registerform.confirmPassword.value;
	
	var success = false;
	
	clearAllErrMsg(errMsgFields);
	if(firstname=="" || firstname==null){
		fnErrMsg.innerHTML = "Please enter firstname";
		return false;
	}
	if(!firstname.match(/^[a-zA-Z ]+$/)){
		fnErrMsg.innerHTML = "Please enter valid firstname";
		return false;
	}
	
	if(lastname=="" || lastname==null){
		lnErrMsg.innerHTML = "Please enter lastname";
		return false;
	}
	if(!lastname.match(/^[a-zA-Z ]+$/)){
		lnErrMsg.innerHTML = "Please enter valid lastname";
		return false;
	}
	
	if(dateOfBirth=="" || dateOfBirth==null){
		dobErrMsg.innerHTML = "Please enter dateOfBirth";
		return false;
	}
	var today = new Date();
	var dob = new Date(dateOfBirth);
	if(dob>today){
		dobErrMsg.innerHTML = "Please enter valid date of birth";
		return false;
	}
	
	if(email=="" || email==null){
		emailErrMsg.innerHTML = "Please enter email";
		return false;
	}
	
	if(mobile=="" || mobile==null){
		mobileErrMsg.innerHTML = "Please enter mobile";
		return false;
	}
	if(!mobile.match(/^[6-9]{1}\d{9}$/)){
		mobileErrMsg.innerHTML = "mobile number should be of 10 digit and start with 6,7,8 or 9.";
		return false;
	}
	
	if(addressline1=="" || addressline1==null){
		addressline1ErrMsg.innerHTML = "Please enter addressline1";
		return false;
	}
	
	if(city=="" || city==null){
		cityErrMsg.innerHTML = "Please enter city";
		return false;
	}
	
	if(state=="" || state==null){
		stateErrMsg.innerHTML = "Please enter state";
		return false;
	}
	
	if(pincode=="" || pincode==null){
		pincodeErrMsg.innerHTML = "Please enter pincode";
		return false;
	}
	if(!pincode.match(/^\d{6}$/)){
		pincodeErrMsg.innerHTML = "pincode should be of 6 digit.";
		return false;
	}
	
	if(password=="" || password==null){
		pwdErrMsg.innerHTML = "Please enter password";
		return false;
	}
	if(confirmPassword=="" || confirmPassword==null){
		confirmPwdErrMsg.innerHTML = "Please enter confirm password";
		return false;
	}
	if(password!=confirmPassword){
		confirmPwdErrMsg.innerHTML = "Password didnot match.";
		return false;
	}
	
	return true;
}

function clearAllErrMsg(fields) {
	for(var i=0;i<fields.length;i++){
		fields[i].innerHTML = "";
	}
}