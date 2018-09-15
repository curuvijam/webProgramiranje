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
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import model.Restoran;
import services.RestoranService;

@Path("/restoran")
public class RestoranResource {
	
	RestoranService restoranService = new RestoranService();
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addRestoran(Restoran restoran) throws JsonGenerationException, JsonMappingException, IOException {
		restoran.setId((long)1);
		Restoran res = restoranService.addRestoran(restoran);
		if(res != null) {
			return Response.ok(res).build();
		} else {
			return Response.serverError().build();
		}
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getRestorani() throws JsonParseException, JsonMappingException, IOException {
		ArrayList<Restoran> restorani = restoranService.getAll();
		return Response.ok(restorani).build();
	}
	
	@Path("/kategorija")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getRestoranByKat(@QueryParam("kategorija") String kategorija) throws JsonParseException, JsonMappingException, IOException {
		ArrayList<Restoran> restorani = restoranService.getByKat(kategorija);
		return Response.ok(restorani).build();
	}
	
	@Path("/{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getRestoranById(@PathParam("id")Long id) throws JsonParseException, JsonMappingException, IOException {
		Restoran restoran = restoranService.getRestoranById(id);
		if(restoran!=null) {
			return Response.ok(restoran).build();
		} else {
			return Response.serverError().build();
		}
	}
	
	@Path("/{id}")
	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public Response deleteRestoran(@PathParam("id") Long id) throws JsonParseException, JsonMappingException, IOException {
		if(restoranService.deleteRestoran(id)) {
			return Response.ok(true).build();
		} else {
			return Response.serverError().build();
		}
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateRestoran(Restoran restoran) throws JsonParseException, JsonMappingException, IOException {
		if(restoranService.updateRestoran(restoran)) {
			return Response.ok(restoran).build();
		} else {
			return Response.serverError().build();
		}
	}
	
	@Path("/pretraga")
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	public Response searchRestoran(@QueryParam("naziv") String naziv, @QueryParam("adresa") String adresa, @QueryParam("kategorija") String kategorija) throws JsonParseException, JsonMappingException, IOException {
		ArrayList<Restoran> restorani = restoranService.searchRestoran(naziv, adresa, kategorija);
		
		return Response.ok(restorani).build();
	}
	

}
