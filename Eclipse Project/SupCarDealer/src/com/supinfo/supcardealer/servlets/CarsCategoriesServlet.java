package com.supinfo.supcardealer.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.supinfo.supcardealer.dao.DaoFactory;
import com.supinfo.supcardealer.entities.Car;
import com.supinfo.supcardealer.entities.Category;
import com.supinfo.supcardealer.globals.Globals;
import com.supinfo.supcardealer.utils.JSONGenerator;

/**
 * Servlet implementation class CarsCategoriesServlet
 */
@WebServlet("/cars")
public class CarsCategoriesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CarsCategoriesServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * Affiche les derni�res voitures ajout�es dans un slider et un
	 * formulaire permettant de choisir des cat�gories � afficher
	 * @param 	request
	 * @param 	response
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// R�cup�ration des cat�gories de voitures
		ArrayList<Category> categories = new ArrayList<Category>();
		categories = (ArrayList<Category>) DaoFactory.getJpaCategoryDao().findAll();
		request.setAttribute("categories", categories);
		// R�cup�ration des informations sur les derni�res voitures ajout�es
		// au format JSON pour les afficher dans le slider JS
		request.setAttribute("carsdata", JSONGenerator.getCarsInfo(
											DaoFactory.getJpaCarDao().findNewestCars(10), request.getContextPath()));
		// Envoi � la vue
		request.getRequestDispatcher("/cars.jsp").forward(request, response);
	}

	/**
	 * Affiche les voitures de la ou des cat�gorie(s) s�lectionn�(s) dans un slider et un
	 * formulaire permettant de choisir des cat�gories � afficher
	 * @param 	request
	 * @param 	response
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// R�cup�ration des cat�gories s�lectionn�es via le formulaire
		String[] selectedCategories =  request.getParameterValues(Globals.PRMTR_CATEGORIES);
		request.setAttribute("selectedCategories", selectedCategories);
		
		// R�cup�ration des informations sur les voitures � affichers
		ArrayList<Car> cars = new ArrayList<Car>();
		if(selectedCategories != null) {
			// R�cup�ration des informations sur les voitures appartenant aux cat�gories s�lectionn�es
			for(String sc : selectedCategories) {
				cars.addAll(DaoFactory.getJpaCarDao().findCarsByCategory(Integer.valueOf(sc)));
			}
			
		} else {
			// R�cup�ration des informations sur les derni�res voitures ajout�es
			cars = DaoFactory.getJpaCarDao().findNewestCars(10);
		}
		request.setAttribute("carsdata", JSONGenerator.getCarsInfo(cars, request.getContextPath()));
		
		// R�cup�ration des cat�gories de voitures
		ArrayList<Category> categories = (ArrayList<Category>) DaoFactory.getJpaCategoryDao().findAll();
		request.setAttribute("categories", categories);
		// Envoi � la vue
		request.getRequestDispatcher("/cars.jsp").forward(request, response);
	}

}
