package com.supinfo.supcardealer.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.supinfo.supcardealer.dao.DaoFactory;
import com.supinfo.supcardealer.entities.User;
import com.supinfo.supcardealer.globals.Globals;
import com.supinfo.supcardealer.utils.EncryptionService;

/**
 * Servlet implementation class Login
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * Affiche un formulaire permettant à un utilisateur de se connecter
	 * @param 	request
	 * @param 	response
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// User is already logged in, redirect to home page
		if(request.getSession().getAttribute(Globals.SESSION_USERNAME) != null) {
			response.sendRedirect(request.getContextPath());
			return;
		}
		request.getRequestDispatcher("/login.jsp").forward(request, response);
	}

	/**
	 * Connecte un utilisateur
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// User is already logged in, redirect to home page
		if(request.getSession().getAttribute(Globals.SESSION_USERNAME) != null) {
			response.sendRedirect(request.getContextPath());
			return;
		}
		
		 String email = request.getParameter("email");
	     String password = request.getParameter("password");
	  
	     HttpSession session = request.getSession();
	     User u = DaoFactory.getJpaUserDao().findUserByEmail(email);
	     
	     if(u == null || !u.getPassword().equals(EncryptionService.encrypt(password))){
	    	 request.setAttribute(Globals.PRMTR_ERROR, "L'authentification a échoué. Mauvais mot de passe ou compte inexistant.");
		     request.getRequestDispatcher("/login.jsp").forward(request, response);
	     } else {
	    	 session.setAttribute(Globals.SESSION_USERNAME, email);
	    	 response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/logged/profile"));
	     }
	}
}
