package org.xyz.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.xyz.service.IRegisterService;
import org.xyz.service.RegisterServiceImpl;

/**
 * Servlet implementation class MainPage
 */
public class MainPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MainPage() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		/*File file = new File("XYZ_Banking_New/src/main/webapp/view/mainpage.html");
		if(!file.exists()) {
			file.createNewFile();
			System.out.println(file.getAbsolutePath());
		}
		FileReader reader = new FileReader(file);
		BufferedReader bufferedReader = new BufferedReader(reader);
		StringBuilder sBuilder = new StringBuilder();
		String line = "";
		while(line!=null) {
			line = bufferedReader.readLine();
			System.out.println(line);
			sBuilder.append(line);
		}
		bufferedReader.close();*/
		HttpSession session = request.getSession();
		String customerId = session.getAttribute("customerId").toString();
		String customerName = session.getAttribute("customerName").toString();
		
		
		//long customerId = Long.parseLong(session.getAttribute("customerId").toString());
		
		PrintWriter out = response.getWriter();
		String page="<html>\r\n" + 
				"<head>\r\n" + 
				"<meta charset=\"ISO-8859-1\">\r\n" + 
				"<title>Capg Banking</title>\r\n" + 
				"<link type=\"text/css\" rel=\"stylesheet\" href=\"style/mainpage.css\"></link>\r\n" + 
				"<link type=\"text/css\" rel=\"stylesheet\" href=\"style/style.css\"></link>\r\n" + 
				"</head>\r\n" + 
				"<body>\r\n" + 
				"<h1 class=\"heading\" align=\"center\">Capg Banking</h1>\r\n" + 
				"<div id=\"username\" align=\"right\">"+customerName+"\r\n" + 
				"</div>\r\n" + 
				"<hr>\n" +
				"<div>\r\n" + 
				"	<ul>\r\n" + 
				"		<li><a href=\"view/createAccount.html\" target=\"mainframe\">Create account</a></li>\r\n" + 
				"		<li><a href=\"DepositeWithdrawalPage\" target=\"mainframe\">Deposite/Withdrawal</a></li>\r\n" + 
				"		<li><a href=\"FundTransferPage\" target=\"mainframe\">Fund transfer</a></li>\r\n" + 
				"		<li><a href=\"view/transactionSummary.html\" target=\"mainframe\">Transaction summary</a></li>\r\n" + 
				"		<li><a href=\"Logout\">Logout</a></li>\r\n" + 
				"	</ul>"+
				"	\r\n" + 
				"	<div id=\"mainframe\" align=\"center\">\r\n" + 
				"		<iframe name=\"mainframe\" width=\"80%\" height=\"60%\" src=\"view/titlePage.html\" align=\"center\"></iframe>\r\n" + 
				"	</div>\r\n" + 
				"</div>\r\n" + 
				"\r\n" + 
				"</body>\r\n" + 
				"</html>";
		
		out.println(page);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
