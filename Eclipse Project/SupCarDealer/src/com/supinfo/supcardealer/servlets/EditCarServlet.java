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

/**
 * Servlet implementation class EditCarServlet
 */
@WebServlet("/logged/car/edit")
public class EditCarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditCarServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * Affiche un formulaire permettant de modifier les informations sur une voiture dont
	 * l'utilisateur est le propri�taire
	 * @param 	request
	 * @param 	response
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// L'ID de la voiture � modifier n'a pas �t� renseign� en param�tre,
		// on redirige vers la page affichant toutes les voitures
		if(request.getParameter(Globals.PRMTR_ID) == null) {
			response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/cars"));
			return;
		}
		
		// R�cup�ration des informations sur la voiture
		long carId = Long.parseLong(request.getParameter(Globals.PRMTR_ID));
		Car c = DaoFactory.getJpaCarDao().find(carId);
		request.setAttribute(Globals.PRMTR_CAR, c);
		
		// V�rification de l'appartenance de la voiture � l'utilisateur actuel
		if(DaoFactory.getJpaUserDao().findUserByEmail((String) request.getSession().getAttribute(Globals.SESSION_USERNAME)).getId()
				!= c.getOwner().getId()){
			request.getSession().setAttribute(Globals.PRMTR_INFO, "Vous n'�tes pas le propri�taire de la voiture que vous voulez �diter.");
			response.sendRedirect(request.getContextPath() + "/car?id=" + carId);
			return;
		}
		
		// R�cup�ration des cat�gories de voitures
		ArrayList<Category> categories = (ArrayList<Category>) DaoFactory.getJpaCategoryDao().findAll();
		request.setAttribute("categories", categories);
		
		// Envoi � la vue
		request.getRequestDispatcher("/logged/editCar.jsp").forward(request, response);
		return;
	}

	/**
	 * Met � jour les informations de la voiture apr�s avoir v�rifi� la validit� de ces informations 
	 * et l'appartenance � l'utilisateur souhaitant faire ces modifications
	 * @param 	request
	 * @param 	response
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// R�cup�ration des informations sur la voiture
		long carId = Long.parseLong(request.getParameter("carId"));
		Car c = DaoFactory.getJpaCarDao().find(carId);
		
		// V�rification de l'appartenance de la voiture � l'utilisateur actuel
		if(DaoFactory.getJpaUserDao().findUserByEmail((String) request.getSession().getAttribute(Globals.SESSION_USERNAME)).getId()
				!= c.getOwner().getId()){
			request.getSession().setAttribute(Globals.PRMTR_ERROR, "Vous n'�tes pas le propri�taire de la voiture que vous voulez �diter.");
			response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "car?id=" + carId));
			return;
		}
		
		// Teste si aucun champ n'est vide
		if(request.getParameter(Globals.PRMTR_NAME).equals("") 
			|| request.getParameter(Globals.PRMTR_YEAR).equals("") 
			|| request.getParameter(Globals.PRMTR_SEATS).equals("") 
			|| request.getParameter(Globals.PRMTR_BAGGAGE).equals("") 
			|| request.getParameter(Globals.PRMTR_SEATS).equals("") 
			|| request.getParameter(Globals.PRMTR_GEARBOX).equals("") 
			|| request.getParameter(Globals.PRMTR_CONDITIONNAL_AIR).equals("") 
			|| request.getParameter(Globals.PRMTR_KILOMETERS).equals("") 
			|| request.getParameter(Globals.PRMTR_PRICEPERDAY).equals("") )	{
			// Renvoi vers le formulaire avec un message d'erreur
			request.setAttribute(Globals.PRMTR_ERROR, "Veuillez remplir tous les champs.");
			doGet(request, response);
			return;
		}
		
		// R�cup�re les donn�es envoy�es depuis le formulaire
		String name = request.getParameter(Globals.PRMTR_NAME);
		boolean conditionnalAir = Boolean.parseBoolean(request.getParameter(Globals.PRMTR_CONDITIONNAL_AIR));
		String baggage = request.getParameter(Globals.PRMTR_BAGGAGE);
		String gearbox = request.getParameter(Globals.PRMTR_GEARBOX);
		try {
			long categoryId= Long.parseLong(request.getParameter(Globals.PRMTR_CATEGORY));
			int year = Integer.parseInt(request.getParameter(Globals.PRMTR_YEAR));
			int seats = Integer.parseInt(request.getParameter(Globals.PRMTR_SEATS));
			int doors = Integer.parseInt(request.getParameter(Globals.PRMTR_SEATS));
			float kilometers = Float.parseFloat(request.getParameter(Globals.PRMTR_KILOMETERS));
			float pricePerDay = Float.parseFloat(request.getParameter(Globals.PRMTR_PRICEPERDAY));
			Category category = DaoFactory.getJpaCategoryDao().find(categoryId);
			//String imageUrl = request.getParameter(Globals.PRMTR_IMAGEURL);
			String imageUrl = null;
			
			// Mise � jour de la voiture en BDD
			c.setBaggage(baggage);
			c.setCategory(category);
			c.setConditionalAir(conditionnalAir);
			c.setDoors(doors);
			c.setGearbox(gearbox);
			c.setImageUrl(imageUrl);
			c.setKilometers(kilometers);
			c.setName(name);
			c.setPricePerDay(pricePerDay);
			c.setSeats(seats);
			c.setYear(year);
			DaoFactory.getJpaCarDao().update(c);
			
			// Redirection vers la page de la voiture modifi�e
			request.getSession().setAttribute(Globals.PRMTR_INFO, "La voiture a bien �t� �dit�e.");
			response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/car?id=" + carId));
			return;
			
		} catch (NumberFormatException nfe) {
			// Renvoi vers le formulaire
			request.setAttribute(Globals.PRMTR_ERROR, "Certains champs sont incorrects.");
			doGet(request, response);
			return;
		}
	}
}
