package com.supinfo.supcardealer.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.validator.routines.EmailValidator;

import com.supinfo.supcardealer.dao.DaoFactory;
import com.supinfo.supcardealer.entities.User;
import com.supinfo.supcardealer.globals.Globals;
import com.supinfo.supcardealer.utils.EncryptionService;

/**
 * Servlet implementation class Register
 */
@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * Affiche un formulaire permettant de s'inscrire sur le site
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
		request.getRequestDispatcher("/register.jsp").forward(request, response);
	}

	/**
	 * Enregistre le nouvel utilisateur en BDD et le connecte directement à son nouveau compte
	 * @param 	request
	 * @param 	response
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Getting the form's field's values
		String email = request.getParameter(Globals.PRMTR_EMAIL);
    	String password1 = request.getParameter(Globals.PRMTR_PASSWORD1);
    	String password2 = request.getParameter(Globals.PRMTR_PASSWORD2);
    	String firstname = request.getParameter(Globals.PRMTR_FIRSTNAME);
    	String lastname = request.getParameter(Globals.PRMTR_LASTNAME);
    	
    	if(isEmailValid(email)) {
	    	if(DaoFactory.getJpaUserDao().findUserByEmail(email) == null) {
	    		if(isPasswordValid(password1) && password1.equals(password2)) {
	            	//Creation of a new User
	    			User newUser = new User();
	    			newUser.setEmail(email);
	    			newUser.setPassword(EncryptionService.encrypt(password1));
	    			newUser.setFirstname(firstname);
	    			newUser.setLastname(lastname);
	    			
	    			//Adding User to the database
	    			DaoFactory.getJpaUserDao().add(newUser);
	    			
	    			//Connecting the new User and redirect him
	    			HttpSession session = request.getSession();
	    			session.setAttribute(Globals.SESSION_USERNAME, email);
	    			response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/logged/profile"));
	        	} else {
	        		request.setAttribute(Globals.PRMTR_ERROR, "Vos identifiants sont incorrects, veuillez vérifier que les deux mots de passe correspondent et font au moins "+ Globals.PASSWORD_LENGTH  +" caractères.");
	        		request.setAttribute(Globals.PRMTR_EMAIL, email);
	        		request.getRequestDispatcher("/register.jsp").forward(request, response);
	        	}
	    	} else {
				request.setAttribute(Globals.PRMTR_ERROR, "Vos identifiants sont incorrects, cette adresse email a déjà été utilisée.");		
				request.getRequestDispatcher("/register.jsp").forward(request, response);
	    	}
    	} else {
    		request.setAttribute(Globals.PRMTR_ERROR, "Vos identifiants sont incorrects, cette adresse email n'est pas valide.");		
			request.getRequestDispatcher("/register.jsp").forward(request, response);
    	}
	}
	
	/**
	 * Vérifie la complexité du mot de passe
	 * @param password
	 * @return True si le mot de passe est suffisamment complexe, false sinon
	 */
	private boolean isPasswordValid(String password){
		if(password.length() >= Globals.PASSWORD_LENGTH){
			return true;
		}
		return false;
	}
	
	/**
	 * Vérifie si l'adresse email est au bon format
	 * @param email
	 * @return True si l'adresse email est au bon format, false sinon
	 */
	private static boolean isEmailValid(String email){
		return EmailValidator.getInstance().isValid(email);
	}
}


