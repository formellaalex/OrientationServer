package com.soft.controllers;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import com.soft.entity.Orientation;
import com.soft.entity.OrientationAvg;
import com.soft.service.DbOperations;


@Path("/orients")
public class MainController {

	private DbOperations dbOperations = new DbOperations();

	@POST
	@Path("add")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response add(Orientation orientation) {
		dbOperations.add(orientation);
		String result = "New row has been added : " + orientation;
		return Response.status(201).entity(result).build();
	}

	@POST
	@Path("addall")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addAll(List<Orientation> orientations) {
		dbOperations.addAll(orientations);
		String result = "All rows has been added";
		return Response.status(201).entity(result).build();
	}

	@GET
	@Path("getorientations")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Orientation> getOrientations() {
		return dbOperations.getOrientations();
	}

	@GET
	@Path("getorientationsavg")
	@Produces(MediaType.APPLICATION_JSON)
	public List<OrientationAvg> getOrientationsAvg() {
		return dbOperations.getAvgs();
	}

}
