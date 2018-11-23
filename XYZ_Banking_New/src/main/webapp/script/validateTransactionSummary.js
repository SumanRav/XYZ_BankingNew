function validateTransactionSummary(){
	console.log("validateTransactionSummary");
	
	var dateErrMsg = document.getElementById("dateErrMsg");
	
	var fromDate = transactionsummaryform.fromDate.value;
	var toDate = transactionsummaryform.toDate.value;
	
	var success = false;
	
	if(fromDate=="" || fromDate==null || toDate=="" || toDate==null){
		dateErrMsg.innerHTML = "Please enter both date";
		success = false;
	}else if (fromDate>toDate) {
		dateErrMsg.innerHTML = "\'from date\' should be before \'to date\'";
		success = false;
	}else {
		success = true;
	}
	
	return success;
}
