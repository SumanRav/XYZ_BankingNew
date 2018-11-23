package org.xyz.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.xyz.model.CustomerBean;
import org.xyz.model.LoginBean;
import org.xyz.service.ILoginService;
import org.xyz.service.LoginServiceImpl;

/**
 * Servlet implementation class Login
 */
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ILoginService loginService=new LoginServiceImpl();
		
		//Capture the data from View
		String userName=request.getParameter("username");
		String userPwd=request.getParameter("password");
		
		
		//Convert the input into Model
		LoginBean login=new LoginBean();
		login.setUsername(userName);
		login.setPassword(userPwd);
		
		
		//Call Your business Logic
		//Navigate to next page
		CustomerBean customer = loginService.isValidLogin(login);
		if(customer!=null) {
			HttpSession session = request.getSession();
			session.setAttribute("username",customer.getEmailId());
			session.setAttribute("customerId",customer.getCustomerId());
			session.setAttribute("customerName",customer.getFirstName()+" "+customer.getLastName());
			
			
			//response.sendRedirect("MainPage");
			request.getRequestDispatcher("MainPage").forward(request, response);
		}
		else {
			response.sendRedirect("index.html?success=false");
			//request.getRequestDispatcher("index.html?success=false").include(request, response);
		}
		
	}

}
