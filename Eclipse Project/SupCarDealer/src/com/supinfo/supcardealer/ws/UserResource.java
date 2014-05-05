package com.supinfo.supcardealer.ws;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.supinfo.supcardealer.dao.DaoFactory;
import com.supinfo.supcardealer.entities.User;

@Path("/user")
public class UserResource {

	public UserResource() {
	}
	
	@POST
	@Consumes({MediaType.APPLICATION_JSON})
	//@Produces(MediaType.APPLICATION_JSON)
	public Response addUser(User user) {
		try {
			DaoFactory.getJpaUserDao().add(user);
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
	@Path("/{email}")
	@Produces(MediaType.APPLICATION_JSON)
	public User getuserByEmail(@PathParam("email") String email) {
		return DaoFactory.getJpaUserDao().findUserByEmail(email);
	}

	@PUT
	@Consumes({MediaType.APPLICATION_JSON})
	public Response updateUser(User user) {
		try {
			DaoFactory.getJpaUserDao().update(user);
		} catch (Exception e) {
			return Response.status(Response.Status.BAD_REQUEST.getStatusCode()).build();
		}
		return Response.ok().build();
	}
}
