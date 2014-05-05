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
import com.supinfo.supcardealer.entities.Category;

@Path("/category")
public class CategoryResource {

	public CategoryResource() {
	}
	
	@POST
	@Consumes({MediaType.APPLICATION_JSON})
	public Response addCategory(Category category) {
		try {
			DaoFactory.getJpaCategoryDao().add(category);
		} catch (Exception e) {
			return Response.status(Response.Status.BAD_REQUEST.getStatusCode()).build();
		}
		return Response.ok().build();
	}

//	@GET
//	@Path("/{id}")
//	@Produces(MediaType.APPLICATION_JSON)
//	public Category getCar(@PathParam("id") Long categoryId) {
//		return DaoFactory.getJpaCategoryDao().find(categoryId);
//	}

	@GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Category> getAllCategory() {
		return DaoFactory.getJpaCategoryDao().findAll();
	}

//	@PUT
//	public Response updateCategory(Category category) {
//		try {
//			DaoFactory.getJpaCategoryDao().update(category);
//		} catch (Exception e) {
//			return Response.status(Response.Status.BAD_REQUEST.getStatusCode()).build();
//		}
//		return Response.ok().build();
//	}
}
