package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLIntegrityConstraintViolationException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import logic.SQLiteOperations;
import logic.formValidation;
import resources.SQLiteOperationOutcomes;
import resources.emailValidationOutcomes;
import resources.passwordValidationOutcomes;

/**
 * Servlet implementation class Controller
 */
@WebServlet("/Controller")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Controller() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("Inside Do Get");
		String actionParam = request.getParameter("action");
		System.out.println("Action Param : "+ actionParam);
		if(actionParam.equals("signup")) {
			request.getRequestDispatcher("signupform.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String actionQueryParam = request.getParameter("action");
		SQLiteOperationOutcomes sqlliteoperationoutcome;
		emailValidationOutcomes emailvalidationoutcome;
		passwordValidationOutcomes passvalidationoutcome;
		
		switch(actionQueryParam) {
			case "": {
				
			}
			case "dosignup": {
				// Get form parameters. 
				String email = request.getParameter("email");
				String password = request.getParameter("password");
				String repassword = request.getParameter("repassword");
				String fname = request.getParameter("fname");
				String lname = request.getParameter("lname");
				
				// ToDo: Validate Email and Password.
				// check email format, password specifications, availability of user email (email already exists). 
				emailvalidationoutcome = formValidation.emailValidation(email);
				passvalidationoutcome = formValidation.passwordValidation(password);
				if(emailvalidationoutcome == emailValidationOutcomes.NULL) {
					request.setAttribute("message","Blank email, seriously?");
					request.getRequestDispatcher("signupform.jsp").forward(request, response);
				
				}
				if(emailvalidationoutcome == emailValidationOutcomes.FORMAT_ERROR) {
					request.setAttribute("message","That doesn't seem to be a valid email!");
					request.getRequestDispatcher("signupform.jsp").forward(request, response);
				}
				if(passvalidationoutcome == passwordValidationOutcomes.NULL) {
					request.setAttribute("message","A blank password is worse than a weak password!");
					request.getRequestDispatcher("signupform.jsp").forward(request, response);
				}
				if(passvalidationoutcome == passwordValidationOutcomes.LENGTH_ERROR) {
					request.setAttribute("message","Password length should be between 8 and 32 characters");
					request.getRequestDispatcher("signupform.jsp").forward(request, response);
				}
				if(passvalidationoutcome == passwordValidationOutcomes.SPACE_ERROR) {
					request.setAttribute("message","Password shouldn't contain spaces");
					request.getRequestDispatcher("signupform.jsp").forward(request, response);
				}
				if(passvalidationoutcome == passwordValidationOutcomes.FORMAT_ERROR) {
					request.setAttribute("message","Password should contains alphabets, digits and special characters");
					request.getRequestDispatcher("signupform.jsp").forward(request, response);
				}
				
				if(!password.equals(repassword)) {
					request.setAttribute("message","Passwords don't match. Please check!");
					request.getRequestDispatcher("signupform.jsp").forward(request, response);
				}
					
				// Insert into SQLite DB.
				sqlliteoperationoutcome = SQLiteOperations.InsertSignUpUserSQLite(email, repassword, fname, lname);
				if(sqlliteoperationoutcome == SQLiteOperationOutcomes.INSERT_SUCCESS) {
					request.getRequestDispatcher("homepage.jsp").forward(request, response);
				}
				else {
					request.getRequestDispatcher("signuperror.jsp").forward(request, response);
				}	
			}
			case "dosignin": {
				String email = request.getParameter("email");
				String password = request.getParameter("password");
				if(formValidation.emailValidation(email).equals(emailValidationOutcomes.PASS)) {
					System.out.println("PASS");
					request.getRequestDispatcher("home.jsp").forward(request, response);
				}
				else if(formValidation.emailValidation(email).equals(emailValidationOutcomes.NULL)) {
					System.out.println("NULL");
					request.getRequestDispatcher("homepage.jsp").forward(request, response);
				}
				else {
					System.out.println("FORMAT ERR");
					request.getRequestDispatcher("homepage.jsp").forward(request, response);
				}
			}
		}
		
		
	}
}
