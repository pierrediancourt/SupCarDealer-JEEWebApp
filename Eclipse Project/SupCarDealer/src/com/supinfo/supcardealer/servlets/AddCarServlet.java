package com.supinfo.supcardealer.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.supinfo.supcardealer.dao.DaoFactory;
import com.supinfo.supcardealer.entities.Car;
import com.supinfo.supcardealer.entities.Category;
import com.supinfo.supcardealer.entities.User;
import com.supinfo.supcardealer.globals.Globals;

/**
 * Servlet implementation class AddCar
 */
@WebServlet("/logged/addCar")
@MultipartConfig
public class AddCarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddCarServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * Affiche un formulaire permettant de mettre une voiture en location
	 * @param	request
	 * @param	response
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Récupération des catégories de voitures
		ArrayList<Category> categories = DaoFactory.getJpaCategoryDao().findAll();
		request.setAttribute("categories", categories);
		// Envoi à la vue
		request.getRequestDispatcher("/logged/addCar.jsp").forward(request, response);
	}

	/**
	 * Ajoute une voiture en BDD grâce aux informations envoyées via le formulaire affiché 
	 * depuis le doGet après vérification. Si les champs ne sont pas remplis correcptement, 
	 * le formulaire est réaffiché avec un message d'erreur.
	 * @param	request
	 * @param	response
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
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
		
		// Récupère les données envoyées depuis le formulaire
		String baggage = request.getParameter(Globals.PRMTR_BAGGAGE);
		String name = request.getParameter(Globals.PRMTR_NAME);
		String gearbox = request.getParameter(Globals.PRMTR_GEARBOX);
		boolean conditionnalAir = Boolean.parseBoolean(request.getParameter(Globals.PRMTR_CONDITIONNAL_AIR));
		try {
			int year = Integer.parseInt(request.getParameter(Globals.PRMTR_YEAR));
			int seats = Integer.parseInt(request.getParameter(Globals.PRMTR_SEATS));
			long categoryId= Long.parseLong(request.getParameter(Globals.PRMTR_CATEGORY));
			int doors = Integer.parseInt(request.getParameter(Globals.PRMTR_SEATS));
			float kilometers = Float.parseFloat(request.getParameter(Globals.PRMTR_KILOMETERS));
			float pricePerDay = Float.parseFloat(request.getParameter(Globals.PRMTR_PRICEPERDAY));
			Category category = DaoFactory.getJpaCategoryDao().find(categoryId);
			User owner = DaoFactory.getJpaUserDao().findUserByEmail((String) request.getSession().getAttribute(Globals.SESSION_USERNAME));
			String imageUrl = null;
			//String imageUrl = this.saveImage(request.getPart(Globals.PRMTR_IMAGEURL));
			
			// Ajout de la nouvelle voiture en BDD
			Car newCar = new Car(	name,
									year,
									category,
									owner,
									seats,
									baggage,
									doors,
									gearbox,
									conditionnalAir,
									kilometers,
									pricePerDay,
									imageUrl);
			DaoFactory.getJpaCarDao().add(newCar);
			
			// Redirection vers la page de la nouvelle voiture
			request.getSession().setAttribute(Globals.PRMTR_INFO, "La voiture a bien été ajoutée.");
			response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/car?id=" + newCar.getId()));
			return;
			
		} catch (NumberFormatException nfe) {
			// Renvoi vers le formulaire
			request.setAttribute(Globals.PRMTR_ERROR, "Certains champs sont incorrects.");
			doGet(request, response);
			return;
		}
	}
	
	/**
	 * Récupère l'image envoyée via le formulaire d'ajout d'une voiture et la sauvegarde.
	 * @param 	(Part) imagepart	Données de l'image envoyée
	 * @return 	(String) 			Lien vers l'image
	 * @see		saveImage
	 */
	public String saveImage(Part imagepart) {
//		 
//		InputStream inputStream = null;
//		OutputStream outputStream = null;
//		
//		try {
//			inputStream = imagepart.getInputStream();
//			outputStream = new FileOutputStream(new File("/WebContent/img/cars/superbird.jpg"));
//	 
//			int read = 0;
//			byte[] bytes = new byte[1024];
//	 
//			while ((read = inputStream.read(bytes)) != -1) {
//				outputStream.write(bytes, 0, read);
//			}
//		} catch (IOException e) {
//			e.printStackTrace();
//		} finally {
//			if (inputStream != null) {
//				try {
//					inputStream.close();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			}
//			if (outputStream != null) {
//				try {
//					outputStream.close();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//	 
//			}
//		}
		return "";
	}

}
