<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Transactions</title>
<link type="text/css" rel="stylesheet" href="../style/transactionSummary.css"></link>
<link type="text/css" rel="stylesheet" href="../style/style.css"></link>
</head>
<body>

<div>
	<table>
		<caption>Transaction Summary</caption>
		<tr>
			<th>Transaction Id</th>
			<th>Transaction Date</th>
			<th>From</th>
			<th>To</th>
			<th>Type</th>
			<th>Amount</th>
			<th>Description</th>
		</tr>

<%@ page import="org.xyz.service.TransactionService,
org.xyz.service.ITransactionService,
java.time.LocalDate,
java.util.Set,
org.xyz.model.Transaction
"
 %>
	
<%
	long customerId = Long.parseLong(session.getAttribute("customerId").toString());
	
	ITransactionService transactionService = new TransactionService();
	
	String fromDateStr=request.getParameter("fromDate");
	String toDateStr=request.getParameter("toDate");
	
	String fromDateParts[] = fromDateStr.split("-");
	LocalDate fromDate = LocalDate.of(Integer.parseInt(fromDateParts[0]), Integer.parseInt(fromDateParts[1]), Integer.parseInt(fromDateParts[2]));
	String toDateParts[] = toDateStr.split("-");
	LocalDate toDate = LocalDate.of(Integer.parseInt(toDateParts[0]), Integer.parseInt(toDateParts[1]), Integer.parseInt(toDateParts[2]));
	
	Set<Transaction> transactions = transactionService.getAllTransactionsOfCustomerInDateRange(customerId, fromDate, toDate);
	
	for(Transaction transaction:transactions){
%>

		<tr>
			<td class="numeric"><%=transaction.getTransactionId() %></td>
			<td class="textual"><%=transaction.getTransactionDate().toString() %></td>
			<td class="numeric"><%=transaction.getFromAccount().getAccountNumber() %></td>
			<td class="numeric"><%=transaction.getToAccount().getAccountNumber() %></td>
			<td class="textual"><%=transaction.getTransactionType().toString().toLowerCase() %></td>
			<td class="numeric"><%=transaction.getAmount() %></td>
			<td class="textual"><%=transaction.getDescription() %></td>
		</tr>
	
<%
	}
%>

	</table>
</div>

<form action="../view/transactionSummary.html">
	<input type="submit" value="Back" class="button"> 
</form>
	
</body>
</html>