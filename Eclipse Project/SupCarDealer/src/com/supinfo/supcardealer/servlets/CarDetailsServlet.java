package com.supinfo.supcardealer.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.supinfo.supcardealer.dao.DaoFactory;
import com.supinfo.supcardealer.entities.Car;
import com.supinfo.supcardealer.globals.Globals;

/**
 * Servlet implementation class CarDetailServlet
 */
@WebServlet("/car")
public class CarDetailsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CarDetailsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * Affiche les informations détaillées sur une voiture dont l'ID a été passé en paramètre
	 * @param 	request
	 * @param 	response
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Si le paramètre ID existe, on affiche la page
		if(request.getParameter(Globals.PRMTR_ID) != null) {
			Long id = Long.parseLong(request.getParameter(Globals.PRMTR_ID));
			Car c = DaoFactory.getJpaCarDao().find(id);
			request.setAttribute(Globals.PRMTR_CAR, c);
			
			String infoMsg = (String) request.getSession().getAttribute(Globals.PRMTR_INFO);
			request.getSession().removeAttribute(Globals.PRMTR_INFO);
			request.setAttribute(Globals.PRMTR_INFO, infoMsg);
			
			String errorMsg = (String) request.getSession().getAttribute(Globals.PRMTR_ERROR);
			request.getSession().removeAttribute(Globals.PRMTR_ERROR);
			request.setAttribute(Globals.PRMTR_ERROR, errorMsg);
			
			request.getRequestDispatcher("/car.jsp").forward(request, response);
			return;
		}
		// Sinon, redirection vers la page affichant toutes les voitures
		response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/cars"));
	}
}
