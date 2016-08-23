package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import logic.formValidation;
import resources.emailValidationOutcomes;

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
