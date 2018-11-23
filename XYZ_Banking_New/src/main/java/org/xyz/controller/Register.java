package org.xyz.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.xyz.model.AddressBean;
import org.xyz.model.CustomerBean;
import org.xyz.service.IRegisterService;
import org.xyz.service.RegisterServiceImpl;

/**
 * Servlet implementation class Register
 */
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Register() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		IRegisterService registerService = new RegisterServiceImpl();
		
		//Capture the data from View
		String firstname=request.getParameter("firstname");
		String lastname=request.getParameter("lastname");
		String dateOfBirth=request.getParameter("dateOfBirth");
		String email=request.getParameter("email");
		String mobile=request.getParameter("mobile");
		
		String addressline1=request.getParameter("addressline1");
		String addressline2=request.getParameter("addressline2");
		String city=request.getParameter("city");
		String state=request.getParameter("state");
		String pincode=request.getParameter("pincode");
		
		String password=request.getParameter("password");
		
		
		//Convert the input into Model
		CustomerBean customerBean = new CustomerBean();
		customerBean.setFirstName(firstname);
		customerBean.setLastName(lastname);
		
		String dateParts[] = dateOfBirth.split("-");
		LocalDate date = LocalDate.of(Integer.parseInt(dateParts[0]), Integer.parseInt(dateParts[1]), Integer.parseInt(dateParts[2]));
		customerBean.setDateOfBirth(date);
		
		customerBean.setEmailId(email);
		customerBean.setMobileNo(mobile);
		customerBean.setPassword(password);
		
		AddressBean addressBean = new AddressBean();
		addressBean.setAddressLine1(addressline1);
		addressBean.setAddressLine2(addressline2);
		addressBean.setCity(city);
		addressBean.setState(state);
		addressBean.setPincode(pincode);
		
		customerBean.setAddress(addressBean);
		
		PrintWriter out= response.getWriter();
		if(registerService.registerCustomer(customerBean)) {
			out.println("<html>\r\n" + 
					"<head>\r\n" + 
					"<meta charset=\"ISO-8859-1\">\r\n" + 
					"<title>Capg Banking</title>\r\n" + 
					"<link type=\"text/css\" rel=\"stylesheet\" href=\"style/style.css\"></link>\r\n" + 
					"</head>\r\n" + 
					"<body>\r\n" + 
					"<form method=\"post\" action=\"index.html\" name=\"accountform\" onsubmit=\"return validateAccount()\">\r\n" + 
					"	<div class=\"success\">Registration successful.</div>\r\n" + 
					"	<input type=\"submit\" value=\"Login\" name=\"back\" class=\"button\">\r\n" + 
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
					"<form method=\"post\" action=\"view/register.html\" name=\"accountform\" onsubmit=\"return validateAccount()\">\r\n" + 
					"	<div class=\"failure\">Error occured.</div>\r\n" + 
					"	<input type=\"submit\" value=\"Back\" name=\"back\" class=\"button\">\r\n" + 
					"\r\n" + 
					"</form>\r\n" + 
					"</body>\r\n" + 
					"</html>");
		}
	}

}
