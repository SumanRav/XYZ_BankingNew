package org.xyz.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.xyz.model.Account;
import org.xyz.service.AccountService;
import org.xyz.service.IAccountService;

/**
 * Servlet implementation class DepositeWithdrawalPage
 */
public class DepositeWithdrawalPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DepositeWithdrawalPage() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		HttpSession session = request.getSession();
		long customerId = Long.parseLong(session.getAttribute("customerId").toString());
		
		IAccountService accountService = new AccountService();
		Set<Account> accounts = accountService.getAccountsOfCustomer(customerId);
		
		PrintWriter out = response.getWriter();
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("<html>\r\n" + 
				"<head>\r\n" + 
				"<meta charset=\"ISO-8859-1\">\r\n" + 
				"<title>Capg Banking</title>\r\n" + 
				"<link type=\"text/css\" rel=\"stylesheet\" href=\"style/style.css\"></link>\r\n" + 
				"<script src=\"script/validateDepositeWithdrawal.js\"></script>\r\n" + 
				"</head>\r\n" + 
				"<body>\r\n" + 
				"<form action=\"DepositeWithdrawal\" method=\"post\" name=\"depositewithdrawalform\" onsubmit=\"return validateDepositeWithdrawal()\">\r\n" + 
				"	<div>\r\n" + 
				"	<h2>Deposite/Withdrawal</h2>\n" + 
				"		<table>\r\n" +  
				"			<tr>\r\n" + 
				"				<td>Select Account:</td>\r\n" + 
				"				<td>\r\n" + 
				"					<select name=\"account\">\r\n");
		
		for(Account account:accounts) {
			sBuilder.append("<option value=\""+account.getAccountNumber()+"\">"
					+account.getAccountNumber()+" "
					+account.getAccountType().toString()+"</option>\n");
		}
//				"						<option value=\"100\">101</option>\r\n" + 
//				"						<option value=\"101\">102</option>\r\n" + 
//				"						<option value=\"102\">103</option>\r\n" + 
//				"						<option value=\"103\">104</option>\r\n" + 
//				"						\r\n" + 
		sBuilder.append("					</select>\r\n" + 
				"				</td>\r\n" + 
				"			</tr>\r\n" + 
				"			<tr>\r\n" + 
				"				<td>Transaction:</td>\r\n" + 
				"				<td>\r\n" + 
				"					<input type=\"radio\" name=\"transactionType\" value=\"deposite\" checked> Deposite\r\n" + 
				"					<input type=\"radio\" name=\"transactionType\" value=\"withdrawal\"> Withdrawal\r\n" + 
				"				</td>\r\n" + 
				"			</tr>\r\n" + 
				"			<tr>\r\n" + 
				"				<td>Amount:</td>\r\n" + 
				"				<td>\r\n" + 
				"					<input type=\"text\" name=\"amount\" size=\"20\">  \r\n" + 
				"				</td>\r\n" + 
				"				<td><div id=\"amtErrMsg\" class=\"error\"></div></td>\r\n" + 
				"			</tr>\r\n" + 
				"			<tr>\r\n" + 
				"				<td>\r\n" + 
				"					<input type=\"submit\" value=\"Do Transaction\" name=\"doTransaction\" class=\"button\">\r\n" + 
				"				</td>\r\n" + 
				"			</tr>\r\n" + 
				"		\r\n" + 
				"		</table>\r\n" + 
				"	\r\n" + 
				"	</div>\r\n" + 
				"\r\n" + 
				"</form>\r\n" + 
				"</body>\r\n" + 
				"</html>");
		
		out.println(sBuilder.toString());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
