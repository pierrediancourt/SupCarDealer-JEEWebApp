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
 * Servlet implementation class EditProfileServlet
 */
@WebServlet("/logged/profile/edit")
public class EditProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private User actualUser; //l'user actuel
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditProfileServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * Affiche un formulaire permettant de modifier les informations relatives à son profil utilisateur
	 * et de changer de mot de passe
	 * @param 	request
	 * @param 	response
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String email = (String) request.getSession().getAttribute(Globals.SESSION_USERNAME);

		actualUser = new User();
		actualUser = DaoFactory.getJpaUserDao().findUserByEmail(email);
		request.setAttribute("user", actualUser);
				
		request.getRequestDispatcher("/logged/editProfile.jsp").forward(request, response);
	}

	/**
	 * Enregistre les informations modifiées du profil envoyées depuis le formulaire du doGet()
	 * @param 	request
	 * @param 	response
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Getting the form's field's values
		String email = request.getParameter(Globals.PRMTR_EMAIL);
    	String oldPassword = request.getParameter(Globals.PRMTR_OLDPASSWORD);
    	String newPassword1 = request.getParameter(Globals.PRMTR_NEWPASSWORD1);
    	String newPassword2 = request.getParameter(Globals.PRMTR_NEWPASSWORD2);
    	String firstname = request.getParameter(Globals.PRMTR_FIRSTNAME);
    	String lastname = request.getParameter(Globals.PRMTR_LASTNAME);
    	
    	//if the new email is valid...
    	if(isEmailValid(email)) {
	    	//if the user didn't change his email, or if the new email doesn't already exist in the BDD...
    		if(email.equals(actualUser.getEmail()) || DaoFactory.getJpaUserDao().findUserByEmail(email)==null) {
	    		//if the user's old password is correct, or if all the passwords' fields are empty...
    			if(EncryptionService.encrypt(oldPassword).equals(actualUser.getPassword())
    				|| (oldPassword.equals("") && newPassword1.equals("") && newPassword2.equals(""))) {
		    		//if the new passwords are correct, or if all the passwords' fields are empty...
    				if((isPasswordValid(newPassword1) && newPassword1.equals(newPassword2))
		    			|| (oldPassword.equals("") && newPassword1.equals("") && newPassword2.equals(""))) {
		            	
    					//Updating the actual User object with the new informations
		    			actualUser.setEmail(email);
		    			//actualUser.setPassword(newPassword1);
		    			actualUser.setFirstname(firstname);
		    			actualUser.setLastname(lastname);
		    			if(!(oldPassword.equals("") && newPassword1.equals("") && newPassword2.equals("")))
		    				actualUser.setPassword(EncryptionService.encrypt(newPassword1));
		    			
		    			//Adding User to the database
		    			DaoFactory.getJpaUserDao().update(actualUser);
		    			
		    			//Connecting the new User and redirect him
		    			HttpSession session = request.getSession();
		    			session.setAttribute(Globals.SESSION_USERNAME, email);
		    			response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/logged/profile"));
		        	} else {
		        		request.setAttribute(Globals.PRMTR_ERROR, "Vos identifiants sont incorrects, veuillez vérifier que les deux mots de passe correspondent et font au moins "+ Globals.PASSWORD_LENGTH  +" caractères.");
		        		request.setAttribute(Globals.PRMTR_EMAIL, email);
		        		doGet(request, response);;
		        	}
	    		} else {
	    			request.setAttribute(Globals.PRMTR_ERROR, "Vos identifiants sont incorrects, l'ancien mot de passe ne correspond pas.");
	    			doGet(request, response);
	    		}
	    	} else {
				request.setAttribute(Globals.PRMTR_ERROR, "Vos identifiants sont incorrects, cette adresse email a déjà été utilisée.");
				doGet(request, response);
	    	}
    	} else {
    		request.setAttribute(Globals.PRMTR_ERROR, "Vos identifiants sont incorrects, cette adresse email n'est pas valide.");
    		doGet(request, response);
    	}
	}
	
	private boolean isPasswordValid(String password){
		if(password.length() >= Globals.PASSWORD_LENGTH){
			return true;
		}
		return false;
	}
	
	/**
	 * Teste si une adresse email a un format valide
	 * @param email
	 * @return	True si l'email est valide, false sinon
	 */
	private static boolean isEmailValid(String email){
		return EmailValidator.getInstance().isValid(email);
	}

}
