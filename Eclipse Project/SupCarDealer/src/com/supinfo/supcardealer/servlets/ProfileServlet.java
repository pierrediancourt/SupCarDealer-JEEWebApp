package com.supinfo.supcardealer.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.supinfo.supcardealer.dao.DaoFactory;
import com.supinfo.supcardealer.entities.User;
import com.supinfo.supcardealer.globals.Globals;

/**
 * Servlet implementation class Profile
 */
@WebServlet("/logged/profile")
public class ProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProfileServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * Affiche les informations du profil de l'utilisateur courant
	 * @param 	request
	 * @param 	response
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String email = (String) request.getSession().getAttribute(Globals.SESSION_USERNAME);
		User u = DaoFactory.getJpaUserDao().findUserByEmail(email);

		request.setAttribute("user", u);
				
		request.getRequestDispatcher("/logged/profile.jsp").forward(request, response);
	}

}
