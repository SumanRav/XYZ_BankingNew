function validateAccount(){
	console.log("validateAccount");
	
	var balErrMsg = document.getElementById("balErrMsg");
	
	var balance = accountform.balance.value;
	
	var success = false;
	
	if(balance==null || balance<=500){
		balErrMsg.innerHTML = "Please enter balance grater than 500";
		success = false;
	}else {
		success = true;
	}
	
	return success;
}