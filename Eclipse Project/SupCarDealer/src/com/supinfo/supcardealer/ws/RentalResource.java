package com.supinfo.supcardealer.ws;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.supinfo.supcardealer.dao.DaoFactory;
import com.supinfo.supcardealer.entities.Rental;

@Path("/rental")
public class RentalResource {

	public RentalResource() {
	}
	
	@POST
	@Consumes({MediaType.APPLICATION_JSON})
	//@Produces(MediaType.APPLICATION_JSON)
	public Response addRental(Rental rental) {
		try {
			DaoFactory.getJpaRentalDao().add(rental);
		} catch (Exception e) {
			return Response.status(Response.Status.BAD_REQUEST.getStatusCode()).build();
		}
		return Response.ok().build();
	}

	@GET
	@Path("/allByCar/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Rental> getRentalByCarId(int id) {
		return DaoFactory.getJpaRentalDao().findRentalsByCarId(id);
	}
	
	
	@GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Rental> getAllRental() {
		return DaoFactory.getJpaRentalDao().findAll();
	}
}
