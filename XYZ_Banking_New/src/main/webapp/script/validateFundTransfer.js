function validateFundTransfer(){
	console.log("validateFundTransfer");
	
	var amtErrMsg = document.getElementById("amtErrMsg");
	
	var amount = fundtransferform.amount.value;
	
	var success = false;
	
	if(amount==null || amount<=0){
		amtErrMsg.innerHTML = "Please enter valid amount";
		success = false;
	}else {
		success = true;
	}
	
	return success;
}
