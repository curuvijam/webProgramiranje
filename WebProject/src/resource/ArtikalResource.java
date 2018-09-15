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

import model.Artikal;
import model.Restoran;
import services.ArtikalService;

@Path("/artikal")
public class ArtikalResource {
	
	ArtikalService artikalService = new ArtikalService();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getArtikli() throws JsonParseException, JsonMappingException, IOException {
		ArrayList<Artikal> artikli = artikalService.getAll();
		return Response.ok(artikli).build();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addArtikal(Artikal artikal) throws JsonGenerationException, JsonMappingException, IOException {
		Artikal a = artikalService.addArtikal(artikal);
		if(a != null) {
			return Response.ok(a).build();
		} else {
			return Response.serverError().build();
		}
	}
	
	@Path("/restoran/{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getArtikliForRestoran(@PathParam("id") Long id) throws JsonParseException, JsonMappingException, IOException {
		ArrayList<Artikal> artikli = artikalService.getArtkliForRestoran(id);
		return Response.ok(artikli).build();
	}
	
	@Path("/{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getArtikalById(@PathParam("id") Long id) throws JsonParseException, JsonMappingException, IOException {
		Artikal artikal = artikalService.getArtikalById(id);
		if(artikal != null) {
			return Response.ok(artikal).build();
		} else {
			return Response.serverError().build();
		}
	}
	
	@Path("/{id}")
	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public Response deleteArtikal(@PathParam("id") Long id) throws JsonParseException, JsonMappingException, IOException {
		if(artikalService.deleteArtikal(id)) {
			return Response.ok(true).build();
		} else {
			return Response.serverError().build();
		}
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateArtikal(Artikal artikal) throws JsonParseException, JsonMappingException, IOException {
		if(artikalService.updateArtikal(artikal)) {
			return Response.ok(artikal).build();
		} else {
			return Response.serverError().build();
		}
	}
	
	@Path("/pretraga")
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	public Response searchArtikal(@QueryParam("naziv") String naziv, @QueryParam("cena") String cena, @QueryParam("tip") String tip, @QueryParam("restoran") String restoran) throws JsonParseException, JsonMappingException, IOException {
		ArrayList<Artikal> artikli = artikalService.searchArtikal(naziv, cena, tip, restoran);
		return Response.ok(artikli).build();
	}

}
