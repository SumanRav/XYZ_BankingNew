function validateDepositeWithdrawal(){
	console.log("validateDepositeWithdrawal");
	
	var amtErrMsg = document.getElementById("amtErrMsg");
	
	var amount = depositewithdrawalform.amount.value;
	
	var success = false;
	
	if(amount==null || amount<=0){
		amtErrMsg.innerHTML = "Please enter valid amount";
		success = false;
	}else {
		success = true;
	}
	
	return success;
}
