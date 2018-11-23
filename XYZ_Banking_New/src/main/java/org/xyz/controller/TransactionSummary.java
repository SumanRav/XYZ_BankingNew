package org.xyz.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.xyz.model.Transaction;
import org.xyz.service.ITransactionService;
import org.xyz.service.TransactionService;

/**
 * Servlet implementation class TransactionSummaryPage
 */
public class TransactionSummary extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TransactionSummary() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		long customerId = Long.parseLong(session.getAttribute("customerId").toString());
		
		ITransactionService transactionService = new TransactionService();
		
		String fromDateStr=request.getParameter("fromDate");
		String toDateStr=request.getParameter("toDate");
		
		String fromDateParts[] = fromDateStr.split("-");
		LocalDate fromDate = LocalDate.of(Integer.parseInt(fromDateParts[0]), Integer.parseInt(fromDateParts[1]), Integer.parseInt(fromDateParts[2]));
		String toDateParts[] = toDateStr.split("-");
		LocalDate toDate = LocalDate.of(Integer.parseInt(toDateParts[0]), Integer.parseInt(toDateParts[1]), Integer.parseInt(toDateParts[2]));
		
		Set<Transaction> transactions = transactionService.getAllTransactionsOfCustomerInDateRange(customerId, fromDate, toDate);
		
		PrintWriter out = response.getWriter();
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("<html>\r\n" + 
				"<head>\r\n" + 
				"<meta charset=\"ISO-8859-1\">\r\n" + 
				"<title>Capg Banking</title>\r\n" + 
				"<link type=\"text/css\" rel=\"stylesheet\" href=\"style/style.css\"></link>\r\n" + 
				"</head>\r\n" + 
				"<body>\r\n" + 
				"\r\n" + 
				"<div>\r\n" + 
				"	<h2>Transaction Summary</h2>\n" + 
				"	<table>\r\n" +  
				"		<tr>\r\n" + 
				"			<th>Transaction Id</th>\r\n" + 
				"			<th>Transaction Date</th>\r\n" + 
				"			<th>From</th>\r\n" + 
				"			<th>To</th>\r\n" + 
				"			<th>Type</th>\r\n" + 
				"			<th>Amount</th>\r\n" + 
				"			<th>Description</th>\r\n" + 
				"		</tr>\r\n");
		
		for(Transaction transaction:transactions) {
			sBuilder.append("<tr>\n"
					+ "<td class=\"numeric\">"+ transaction.getTransactionId() +"</td>\n"
					+ "<td class=\"textual\">"+ transaction.getTransactionDate().toString() +"</td>\n"
					+ "<td class=\"numeric\">"+ transaction.getFromAccount().getAccountNumber() +"</td>\n"
					+ "<td class=\"numeric\">"+ transaction.getToAccount().getAccountNumber() +"</td>\n"
					+ "<td class=\"textual\">"+ transaction.getTransactionType().toString().toLowerCase() +"</td>\n"
					+ "<td class=\"numeric\">"+ transaction.getAmount() +"</td>\n"
					+ "<td class=\"textual\">"+ transaction.getDescription() +"</td>\n"
					+ "</tr>\n\n");
		}
			
		sBuilder.append("	</table>\r\n" + 
				"</div>\r\n" + 
				"\r\n" + 
				"<form action=\"view/transactionSummary.html\">\r\n" + 
				"	<input type=\"submit\" value=\"Back\" class=\"button\"> \r\n" + 
				"</form>\r\n" + 
				"	\r\n" + 
				"</body>\r\n" + 
				"</html>");
		
		out.println(sBuilder.toString());
	}

}
