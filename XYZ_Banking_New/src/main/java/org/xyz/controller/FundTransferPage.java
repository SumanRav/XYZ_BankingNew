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
 * Servlet implementation class FundTransferPage
 */
public class FundTransferPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FundTransferPage() {
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
		Set<Account> fromAccounts = accountService.getAccountsOfCustomer(customerId);
		Set<Account> toAccounts = accountService.getAccountsOfOtherCustomers(customerId);
		
		PrintWriter out = response.getWriter();
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("<html>\r\n" + 
				"<head>\r\n" + 
				"<meta charset=\"ISO-8859-1\">\r\n" + 
				"<title>capgBanking</title>\r\n" + 
				"<link type=\"text/css\" rel=\"stylesheet\" href=\"style/style.css\"></link>\r\n" + 
				"<script src=\"script/validateFundTransfer.js\"></script>\r\n" + 
				"</head>\r\n" + 
				"<body>\r\n" + 
				"<form method=\"post\" action=\"FundTransfer\" name=\"fundtransferform\" onsubmit=\"return validateFundTransfer()\">\r\n" + 
				"	<div>\r\n"+ 
				"	<h2>Fund Transfer</h2>\n" + 
				"		<table>\r\n" + 
				"			<tr>\r\n" + 
				"				<td>Select your Account:</td>\r\n" + 
				"				<td>\r\n" + 
				"					<select name=\"fromAccount\">\r\n");
		for(Account account:fromAccounts) {
			sBuilder.append("<option value=\""+account.getAccountNumber()+"\">"
					+account.getAccountNumber()+" "
					+account.getAccountType().toString()+"</option>\n");
		}
				 
		sBuilder.append("					</select>\r\n" + 
				"				</td>\r\n" + 
				"			</tr>\r\n" + 
				"			<tr>\r\n" + 
				"				<td>Select target Account:</td>\r\n" + 
				"				<td>\r\n" + 
				"					<select name=\"toAccount\">\r\n");
		
		for(Account account:toAccounts) {
			sBuilder.append("<option value=\""+account.getAccountNumber()+"\">"
					+account.getAccountNumber()+" "
					+account.getAccountType().toString()+"</option>\n");
		}
		
		sBuilder.append("					</select>\r\n" + 
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
				"					<input type=\"submit\" value=\"Transfer\" name=\"doTransfer\" class=\"button\">\r\n" + 
				"				</td>\r\n" + 
				"			</tr>\r\n" + 
				"		\r\n" + 
				"		</table>\r\n" + 
				"	\r\n" + 
				"	</div>\r\n" + 
				"\r\n" + 
				"</form>\r\n" + 
				"</body>\r\n" + 
				"</html>" );
		
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
