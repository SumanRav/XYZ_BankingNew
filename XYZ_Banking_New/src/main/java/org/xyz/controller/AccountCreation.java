package org.xyz.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.xyz.dao.AccountDaoImpl;
import org.xyz.dao.IAccountDao;
import org.xyz.model.Account;
import org.xyz.model.AccountType;
import org.xyz.model.CustomerBean;

/**
 * Servlet implementation class AccountCreation
 */
public class AccountCreation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AccountCreation() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		long customerId = Long.parseLong(session.getAttribute("customerId").toString());
		
		String accounttype=request.getParameter("accountType");
		String balance=request.getParameter("balance");
		String description=request.getParameter("description");
		
		Account account = new Account();
		account.setAccountType(AccountType.valueOf(accounttype.toUpperCase()));
		account.setOpeningBalance(Double.parseDouble(balance));
		account.setOpeningDate(LocalDate.now());
		account.setDescription(description);
		
		CustomerBean customerBean = new CustomerBean();
		customerBean.setCustomerId(customerId);
		
		account.setCustomer(customerBean);
		
		IAccountDao accountDao = new AccountDaoImpl();
		boolean success = accountDao.addAccount(account);

		PrintWriter out= response.getWriter();
		if(success) {
			out.println("<html>\r\n" + 
					"<head>\r\n" + 
					"<meta charset=\"ISO-8859-1\">\r\n" + 
					"<title>Capg Banking</title>\r\n" + 
					"<link type=\"text/css\" rel=\"stylesheet\" href=\"style/style.css\"></link>\r\n" + 
					"</head>\r\n" + 
					"<body>\r\n" + 
					"<form method=\"post\" action=\"view/createAccount.html\" name=\"accountform\" onsubmit=\"return validateAccount()\">\r\n" + 
					"	<div class=\"success\">Account created successfully.</div>\r\n" + 
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
					"<form method=\"post\" action=\"view/createAccount.html\" name=\"accountform\" onsubmit=\"return validateAccount()\">\r\n" + 
					"	<div class=\"failure\">Error occured.</div>\r\n" + 
					"	<input type=\"submit\" value=\"Back\" name=\"back\" class=\"button\">\r\n" + 
					"\r\n" + 
					"</form>\r\n" + 
					"</body>\r\n" + 
					"</html>");
		}
	}

}
