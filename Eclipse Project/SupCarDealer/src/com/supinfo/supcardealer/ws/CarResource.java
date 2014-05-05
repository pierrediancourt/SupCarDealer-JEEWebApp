package com.supinfo.supcardealer.ws;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.supinfo.supcardealer.dao.DaoFactory;
import com.supinfo.supcardealer.entities.Car;

@Path("/car")
public class CarResource {

	@POST
	@Consumes({MediaType.APPLICATION_JSON})
//	@Produces(MediaType.APPLICATION_JSON)
	public Response add(Car car) {
		try {
			DaoFactory.getJpaCarDao().add(car);
		} catch (Exception e) {
			return Response.status(Response.Status.BAD_REQUEST.getStatusCode()).build();
		}
		return Response.ok().build();
	}
	
	@GET
	@Path("/allByCategory/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Car> getCarsById(int id) {
		return DaoFactory.getJpaCarDao().findCarsByCategory(id);
	}
	
	@GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Car> getAllCars() {
		return DaoFactory.getJpaCarDao().findAll();
	}

//	@GET
//	@Path("/{id}")
//	@Produces(MediaType.APPLICATION_JSON)
//	public Car getCarInJson(@PathParam("id") Long carId) {
//		return DaoFactory.getJpaCarDao().find(carId);
//	}

	@PUT
	@Consumes({MediaType.APPLICATION_JSON})
	public Response updateCar(Car car) {
		try {
			DaoFactory.getJpaCarDao().update(car);
		} catch (Exception e) {
			return Response.status(Response.Status.BAD_REQUEST.getStatusCode()).build();
		}
		return Response.ok().build();
	}
	
//	@DELETE
//	@Path("/{id}")
//  //	@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
//	public Response removeCar(@PathParam("id") Long carId) {
//		try {
//			DaoFactory.getJpaCarDao().remove(carId);
//		} catch (Exception e) {
//			return Response.status(Response.Status.BAD_REQUEST.getStatusCode()).build();
//		}
//		return Response.ok().build();
//	}
}