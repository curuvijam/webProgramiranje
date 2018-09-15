package resource;


import java.io.IOException;
import java.util.ArrayList;

import javax.ws.rs.Consumes;
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

import model.Korisnik;
import services.KorisnikService;

@Path("/korisnik")
public class KorisnikResource {
	
	KorisnikService korisnikService = new KorisnikService();
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response registerKorisnik(Korisnik korisnik) throws JsonGenerationException, JsonMappingException, IOException {
		if(korisnikService.getKorisnik(korisnik.getKorisnickoIme()) != null) {
			return Response.serverError().build();
		}
		
		if(korisnikService.insertKorisnik(korisnik)) {
			return Response.ok(korisnik).build();	
		} else {
			return Response.serverError().build();
		}
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getKorisnici() throws JsonParseException, JsonMappingException, IOException {
		ArrayList<Korisnik> korisnici = korisnikService.getKorisnici();
		return Response.ok(korisnici).build();
	}
	
	@Path("/{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getKorisnik(@PathParam("id") Long id) throws JsonParseException, JsonMappingException, IOException {
		Korisnik korisnik = korisnikService.getKorisnikById(id);
		if(korisnik != null) {
			return Response.ok(korisnik).build();
		} else {
			return Response.serverError().build();
		}
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response changeRole(Korisnik korisnik) throws JsonParseException, JsonMappingException, IOException {
		Korisnik k = korisnikService.changeRole(korisnik);
		if(k!=null) {
			return Response.ok(k).build();
		} else {
			return Response.serverError().build();
		}
	}
}
