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
	 * Affiche les dernières voitures ajoutées dans un slider et un
	 * formulaire permettant de choisir des catégories à afficher
	 * @param 	request
	 * @param 	response
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Récupération des catégories de voitures
		ArrayList<Category> categories = new ArrayList<Category>();
		categories = (ArrayList<Category>) DaoFactory.getJpaCategoryDao().findAll();
		request.setAttribute("categories", categories);
		// Récupération des informations sur les dernières voitures ajoutées
		// au format JSON pour les afficher dans le slider JS
		request.setAttribute("carsdata", JSONGenerator.getCarsInfo(
											DaoFactory.getJpaCarDao().findNewestCars(10), request.getContextPath()));
		// Envoi à la vue
		request.getRequestDispatcher("/cars.jsp").forward(request, response);
	}

	/**
	 * Affiche les voitures de la ou des catégorie(s) sélectionné(s) dans un slider et un
	 * formulaire permettant de choisir des catégories à afficher
	 * @param 	request
	 * @param 	response
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Récupération des catégories sélectionnées via le formulaire
		String[] selectedCategories =  request.getParameterValues(Globals.PRMTR_CATEGORIES);
		request.setAttribute("selectedCategories", selectedCategories);
		
		// Récupération des informations sur les voitures à affichers
		ArrayList<Car> cars = new ArrayList<Car>();
		if(selectedCategories != null) {
			// Récupération des informations sur les voitures appartenant aux catégories sélectionnées
			for(String sc : selectedCategories) {
				cars.addAll(DaoFactory.getJpaCarDao().findCarsByCategory(Integer.valueOf(sc)));
			}
			
		} else {
			// Récupération des informations sur les dernières voitures ajoutées
			cars = DaoFactory.getJpaCarDao().findNewestCars(10);
		}
		request.setAttribute("carsdata", JSONGenerator.getCarsInfo(cars, request.getContextPath()));
		
		// Récupération des catégories de voitures
		ArrayList<Category> categories = (ArrayList<Category>) DaoFactory.getJpaCategoryDao().findAll();
		request.setAttribute("categories", categories);
		// Envoi à la vue
		request.getRequestDispatcher("/cars.jsp").forward(request, response);
	}

}
