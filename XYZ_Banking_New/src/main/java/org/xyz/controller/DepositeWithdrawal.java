package org.xyz.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.xyz.model.Account;
import org.xyz.model.Transaction;
import org.xyz.model.TransactionType;
import org.xyz.service.AccountService;
import org.xyz.service.IAccountService;
import org.xyz.service.ITransactionService;
import org.xyz.service.TransactionService;

/**
 * Servlet implementation class DepositeWithdrawal
 */
public class DepositeWithdrawal extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DepositeWithdrawal() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		IAccountService accountService = new AccountService();
		ITransactionService transactionService = new TransactionService();
		
		String accountNumber=request.getParameter("account");
		String transactionType=request.getParameter("transactionType");
		String amount=request.getParameter("amount");
		
		Transaction transaction = new Transaction();
		transaction.setTransactionDate(LocalDate.now());
		Account fromAccount = accountService.getAccountFromAccountNumber(Long.parseLong(accountNumber));
		transaction.setFromAccount(fromAccount);
		transaction.setToAccount(fromAccount);
		transaction.setAmount(Double.parseDouble(amount));
		TransactionType transactionType2 = transactionType.equals("deposite") ? TransactionType.DEPOSITE : TransactionType.WITHDRAWAL;
		transaction.setTransactionType(transactionType2);
		transaction.setDescription(transactionType);
		
		PrintWriter out= response.getWriter();
		if(transactionService.createTransaction(transaction)) {
			out.println("<html>\r\n" + 
					"<head>\r\n" + 
					"<meta charset=\"ISO-8859-1\">\r\n" + 
					"<title>Capg Banking</title>\r\n" + 
					"<link type=\"text/css\" rel=\"stylesheet\" href=\"style/style.css\"></link>\r\n" + 
					"</head>\r\n" + 
					"<body>\r\n" + 
					"<form method=\"post\" action=\"DepositeWithdrawalPage\" name=\"accountform\" onsubmit=\"return validateAccount()\">\r\n" + 
					"	<div class=\"success\">Transaction successful.</div>\r\n" + 
					"	<input type=\"submit\" value=\"Back\" name=\"back\" class=\"button\">\r\n" + 
					"\r\n" + 
					"</form>\r\n" + 
					"</body>\r\n" + 
					"</html>");
		}else {
			out.println("<html>\r\n" + 
					"<head>\r\n" + 
					"<meta charset=\"ISO-8859-1\">\r\n" + 
					"<title>Capg Banking</title>\r\n" + 
					"<link type=\"text/css\" rel=\"stylesheet\" href=\"style/style.css\"></link>\r\n" + 
					"</head>\r\n" + 
					"<body>\r\n" + 
					"<form method=\"post\" action=\"DepositeWithdrawalPage\" name=\"accountform\" onsubmit=\"return validateAccount()\">\r\n" + 
					"	<div class=\"failure\">Insufficent balance.</div>\r\n" + 
					"	<input type=\"submit\" value=\"Back\" name=\"back\" class=\"button\">\r\n" + 
					"\r\n" + 
					"</form>\r\n" + 
					"</body>\r\n" + 
					"</html>");
		}
	}

}
