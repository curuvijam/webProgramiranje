package services;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import model.Vozilo;

public class VoziloService {

	private ObjectMapper mapper;
	private File file;
	
	public VoziloService() {
		this.mapper = new ObjectMapper();
		this.file = new File(this.getClass().getClassLoader().getResource("/data/vozila.json").getPath());
	}
	
	public Vozilo addVozilo(Vozilo vozilo) throws JsonGenerationException, JsonMappingException, IOException {
		ArrayList<Vozilo> vozila = getAll();
		System.out.println("prosao get, prihvatio " + vozila.size());
		vozilo.setId((long)vozila.size() + 1);
		vozila.add(vozilo);
		mapper.writeValue(file, vozila);
		return vozilo;
	}
	
	public ArrayList<Vozilo> getAll() throws JsonParseException, JsonMappingException, IOException {
		return mapper.readValue(file, mapper.getTypeFactory().constructCollectionType(ArrayList.class, Vozilo.class));
	}
	
	public Vozilo getVoziloById(Long id) throws JsonParseException, JsonMappingException, IOException {
		ArrayList<Vozilo> vozila = getAll();
		for(Vozilo v: vozila) {
			if(v.getId() == id) {
				return v;
			}	
		}
		return null;
	}
	
	public boolean deleteVozilo(Long id) throws JsonGenerationException, JsonMappingException, IOException {
		ArrayList<Vozilo> vozila = getAll();
		Vozilo vozilo = null;
		int idx = -1;
		
		for(Vozilo v: vozila) {
			if(v.getId() == id) {
				idx = vozila.indexOf(v);
			}
		}
		
		if(idx == -1) {
			return false;
		}
		
		vozilo = vozila.get(idx);
		vozilo.setObrisan(true);
		vozila.set(idx, vozilo);
		mapper.writeValue(file, vozila);
		
		return true;
	}
	
	public boolean updateVozilo(Vozilo vozilo) throws JsonGenerationException, JsonMappingException, IOException {
		ArrayList<Vozilo> vozila = getAll();
		Vozilo voziloUpd = null;
		int idx = -1;
		
		for(Vozilo v: vozila) {
			if(v.getId() == vozilo.getId()) {
				idx = vozila.indexOf(v);
			}
		}
		
		if(idx == -1) {
			return false;
		}
		
		voziloUpd = vozila.get(idx);
		voziloUpd.setGodinaProizvodnje(vozilo.getGodinaProizvodnje());
		voziloUpd.setMarka(vozilo.getMarka());
		voziloUpd.setModel(vozilo.getModel());
		voziloUpd.setNapomena(vozilo.getNapomena());
		voziloUpd.setObrisan(vozilo.isObrisan());
		voziloUpd.setRegOznaka(vozilo.getRegOznaka());
		voziloUpd.setTip(vozilo.getTip());
		voziloUpd.setuUpotrebi(vozilo.isuUpotrebi());
		
		vozila.set(idx, voziloUpd);
		mapper.writeValue(file, vozila);
		
		return true;	
	}
}
