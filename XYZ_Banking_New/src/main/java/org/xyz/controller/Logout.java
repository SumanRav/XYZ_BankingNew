package org.xyz.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Logout
 */
public class Logout extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Logout() {
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
		session.invalidate();
		
		PrintWriter out = response.getWriter();
		out.println("<html>\r\n" + 
				"<head>\r\n" + 
				"<meta charset=\"ISO-8859-1\">\r\n" + 
				"<title>Capg Banking</title>\r\n" + 
				"<link type=\"text/css\" rel=\"stylesheet\" href=\"style/style.css\"></link>\r\n" + 
				"</head>\r\n" + 
				"<body>\r\n" + 
				"<form method=\"post\" action=\"index.html\" name=\"accountform\" onsubmit=\"return validateAccount()\">\r\n" + 
				"	<div class=\"success\">You have logged out successfully.</div>\r\n" + 
				"	<input type=\"submit\" value=\"Login\" name=\"back\" class=\"button\">\r\n" + 
				"\r\n" + 
				"</form>\r\n" + 
				"</body>\r\n" + 
				"</html>");
		
	}

	
}
