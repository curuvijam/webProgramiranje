package resource;

import java.io.IOException;
import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import model.Vozilo;
import services.VoziloService;

@Path("/vozilo")
public class VoziloResource {
	
	VoziloService voziloService = new VoziloService();
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addVozilo(Vozilo vozilo) throws JsonGenerationException, JsonMappingException, IOException {
		System.out.println("usao u endpoint");
		Vozilo v = voziloService.addVozilo(vozilo);
		if(v != null) {
			return Response.ok(v).build();
		} else {
			return Response.serverError().build();
		}
	}
	
	@Path("/vozila")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getVozila() throws JsonParseException, JsonMappingException, IOException {
		ArrayList<Vozilo> vozila = voziloService.getAll();
		for(Vozilo v: vozila) {
			System.out.println(v.getMarka());
		}
		return Response.ok(vozila).build();
	}
	
	@Path("/{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getVoziloById(@PathParam("id") Long id) throws JsonParseException, JsonMappingException, IOException {
		Vozilo v = voziloService.getVoziloById(id);
		if(v!=null) {
			return Response.ok(v).build();
		} else {
			return Response.serverError().build();
		}
	}
	
	@Path("/{id}")
	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public Response deleteVozilo(@PathParam("id") Long id) throws JsonGenerationException, JsonMappingException, IOException {
		if(voziloService.deleteVozilo(id)) {
			return Response.ok(true).build();
		} else {
			return Response.serverError().build();
		}
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateVozilo(Vozilo vozilo) throws JsonGenerationException, JsonMappingException, IOException {
		if(voziloService.updateVozilo(vozilo)) {
			return Response.ok(vozilo).build();
		} else {
			return Response.serverError().build();
		}
	}
	
	

}
