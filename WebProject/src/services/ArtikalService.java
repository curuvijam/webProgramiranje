package services;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import model.Artikal;

public class ArtikalService {

	private ObjectMapper mapper;
	private File file;
	
	public ArtikalService() {
		this.mapper = new ObjectMapper();
		this.file = new File("C:\\Users\\Marko\\workspace\\WebProject\\src\\data\\artikli.json");
	}
	
	public Artikal addArtikal(Artikal artikal) throws JsonGenerationException, JsonMappingException, IOException {
		ArrayList<Artikal> artikli = getAll();
		artikal.setId((long)artikli.size() + 1);
		artikli.add(artikal);
		mapper.writeValue(file, artikli);
		return artikal;
	}
	
	public ArrayList<Artikal> getAll() throws JsonParseException, JsonMappingException, IOException {
		return mapper.readValue(file, mapper.getTypeFactory().constructCollectionType(ArrayList.class, Artikal.class));
	}
	
	public ArrayList<Artikal> getArtkliForRestoran(Long id) throws JsonParseException, JsonMappingException, IOException {
		ArrayList<Artikal> artikli = getAll();
		ArrayList<Artikal> ret = new ArrayList<Artikal>();
		
		for(Artikal a : artikli) {
			if(a.getRestoranId() == id) {
				ret.add(a);
			}
		}
		
		return ret;
		
	}
	
	public Artikal getArtikalById(Long id) throws JsonParseException, JsonMappingException, IOException {
		ArrayList<Artikal> artikli = getAll();
		for(Artikal a: artikli) {
			if(a.getId() == id) {
				return a;
			}
		}
		return null;
	}
	
	public boolean deleteArtikal(Long id) throws JsonParseException, JsonMappingException, IOException {
		ArrayList<Artikal> artikli = getAll();
		Artikal artikal = null;
		int idx = -1;
		
		for(Artikal a: artikli) {
			if(a.getId() == id) {
				idx = artikli.indexOf(a);
			}
		}
		
		if(idx == -1) {
			return false;
		}
		
		artikal = artikli.get(idx);
		artikal.setObrisan(true);
		artikli.set(idx, artikal);
		mapper.writeValue(file, artikli);
		
		return true;
	}
	
	public boolean updateArtikal(Artikal artikal) throws JsonParseException, JsonMappingException, IOException {
		ArrayList<Artikal> artikli = getAll();
		Artikal artikalUpd = null;
		int idx = -1;
		
		for(Artikal a: artikli) {
			if(a.getId() == artikal.getId()) {
				idx = artikli.indexOf(a);
			}
		}
		
		if(idx == -1) {
			return false;
		}
		
		artikalUpd = artikli.get(idx);
		artikalUpd.setCena(artikal.getCena());
		artikalUpd.setKolicina(artikal.getKolicina());
		artikalUpd.setNaziv(artikal.getNaziv());
		artikalUpd.setObrisan(artikal.isObrisan());
		artikalUpd.setOpis(artikal.getOpis());
		artikalUpd.setRestoranId(artikal.getRestoranId());
		artikalUpd.setTip(artikal.getTip());
		
		artikli.set(idx, artikalUpd);
		mapper.writeValue(file, artikli);
		return true;
	}
	
	public ArrayList<Artikal> searchArtikal(String naziv, String cena, String tip, String restoran) throws JsonParseException, JsonMappingException, IOException {
		ArrayList<Artikal> artikli = getAll();
		ArrayList<Artikal> ret = new ArrayList<Artikal>();
		System.out.println(naziv);
		System.out.println(cena);
		System.out.println(tip);
		System.out.println(restoran);
		
		if(!naziv.equals("undefined")) {
			for(Artikal a: artikli) {
				if(a.getNaziv().toLowerCase().equals(naziv.toLowerCase())) {
					ret.add(a);
				}
			}
		}
		
		if(!cena.equals("undefined")) {
			double cenaDoub = Integer.parseInt(cena);
			for(Artikal a: artikli) {
				if(a.getCena() == cenaDoub) {
					ret.add(a);
				}
			}
		}
		
		if(!tip.equals("undefined")) {
			Artikal.Tip t;
			if(tip.toLowerCase().equals("pice")) {
				t = Artikal.Tip.PICE;
			} else {
				t = Artikal.Tip.JELO;
			}
			for(Artikal a: artikli) {
				if(a.getTip() == t) {
					ret.add(a);
				}
			}
		}
		
		if(!restoran.equals("undefined")) {
			Long restoranId = (long)Integer.parseInt(restoran);
			for(Artikal a: artikli) {
				if(a.getRestoranId() == restoranId) {
					ret.add(a);
				}
			}
		}
		
		return ret;
	}
}
