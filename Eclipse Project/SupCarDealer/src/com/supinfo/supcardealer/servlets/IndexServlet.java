package com.supinfo.supcardealer.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.supinfo.supcardealer.dao.DaoFactory;
import com.supinfo.supcardealer.utils.JSONGenerator;

/**
 * Servlet implementation class IndexServlet
 */
@WebServlet("/index")
public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IndexServlet() {
        super();
    }

	/**
	 * R�cup�re les derni�res voitures ajout�es en base de donn�es et les affiche dans un slider
	 * @param	request
	 * @param	response
	 * @see 	HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// R�cup�ration des 10 derni�res voitures ajout�es en BDD
		request.setAttribute("carsdata", JSONGenerator.getCarsInfo(
												DaoFactory.getJpaCarDao().findNewestCars(10), request.getContextPath()));
		// Envoi � la vue
		request.getRequestDispatcher("/index.jsp").forward(request,response);
	}

}
