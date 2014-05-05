package com.supinfo.supcardealer.servlets;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.supinfo.supcardealer.dao.DaoFactory;
import com.supinfo.supcardealer.entities.Car;
import com.supinfo.supcardealer.entities.Rental;
import com.supinfo.supcardealer.entities.User;
import com.supinfo.supcardealer.globals.Globals;

/**
 * Servlet implementation class Rent
 */
@WebServlet("/logged/rent")
public class RentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Rental newRental;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }    
    
	/**
	 * Réserve une voiture en location
	 * @param 	request
	 * @param 	response
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Getting the attributes needed to create a Rental
		HttpSession session = request.getSession();
		String userEmail = (String) session.getAttribute(Globals.SESSION_USERNAME);
		long carId = Long.parseLong( request.getParameter(Globals.PRMTR_ID) );
		String rentalStartDate = request.getParameter(Globals.PRMTR_STARTDATE);
		String rentalEndDate = request.getParameter(Globals.PRMTR_ENDDATE);
		
		//finding the car and the user
		newRental  = new Rental();
		Car c = DaoFactory.getJpaCarDao().find(carId);
		User u = DaoFactory.getJpaUserDao().findUserByEmail(userEmail);
		
		if(u != null) {
			if(c != null) {

				//Checking entered dates
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				Date today = new Date(); 
				Date startDate = new Date();
				Date endDate = new Date();
				try{
					startDate = sdf.parse(rentalStartDate);
					endDate = sdf.parse(rentalEndDate);
				} catch(Exception e) { //wrong Date parsing
					request.getSession().setAttribute(Globals.PRMTR_ERROR, "Les dates entrées n'ont pas le bon format.");
					response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/car?id=" + carId));
					return;
				}
				
				//If the startDate is at least today's date and is earlier than the endDate...
				int days = (int)( (endDate.getTime() - startDate.getTime())/86400000 );
				if( startDate.getTime() >= today.getTime() && days > 0 ) {

					//Car's availability checked
					Boolean carIsAvailable = true;
					ArrayList<Rental> carRentals = DaoFactory.getJpaRentalDao().findRentalsByCarId(c.getId());
					if(carRentals != null) {
						for(int i=0; i<carRentals.size(); i++) {
							try{ 
								if( !(startDate.getTime() > sdf.parse( carRentals.get(i).getEndDate() ).getTime()
									|| endDate.getTime() < sdf.parse( carRentals.get(i).getStartDate() ).getTime()) ) 
								{
									carIsAvailable = false;
								}
							} catch(Exception e) {
								e.printStackTrace();
							}
						}
					}
					if(carIsAvailable) {
						//Billing (Price calculation)
						float rentalPrice = c.getPricePerDay() * days;
						
						//Adding attributes to the new Rental object
						newRental.setRentedCarId(c.getId());
						newRental.setRenterId(u.getId());
						newRental.setStartDate(rentalStartDate);
						newRental.setEndDate(rentalEndDate);
						newRental.setPrice(rentalPrice);
						
						//Adding the new Rental in the Database
		    			DaoFactory.getJpaRentalDao().add(newRental);
		    			
		    			//All is fine, we can display a success message and redirect
		    			request.getSession().setAttribute(Globals.PRMTR_INFO, "Vous avez bien loué la voiture.");		
		    			response.sendRedirect(response.encodeRedirectURL(request.getContextPath() +  "/car?id=" + carId));
	    			
					} else {
						request.getSession().setAttribute(Globals.PRMTR_ERROR, "Cette voiture est indisponible pour le moment car déjà utilisée par une autre personne.");		
						response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/car?id=" + carId));
					}
				} else {
					request.getSession().setAttribute(Globals.PRMTR_ERROR, "Les dates choisies sont invalides.");
					response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/car?id=" + carId));
				}
			} else {
				request.getSession().setAttribute(Globals.PRMTR_ERROR, "Cette voiture est introuvable.");	
				response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/car?id=" + carId));
			}
		} else {
			request.getSession().setAttribute(Globals.PRMTR_ERROR, "Vous devez être connecté pour louer une voiture.");		
			response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/car?id=" + carId));
		}
	}

}
