package services;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import model.Dostavljac;
import model.Korisnik;
import model.Kupac;
import model.Porudzbina;
import model.Restoran;

public class KorisnikService {
	
	private ObjectMapper mapper;
	private File korisniciFile;
	private File kupciFile;
	private File dostavljaciFile;
	
	public KorisnikService() {
		this.mapper = new ObjectMapper();
		this.korisniciFile = new File("C:\\Users\\Marko\\workspace\\WebProject\\src\\data\\korisnici.json");
		this.kupciFile = new File("C:\\Users\\Marko\\workspace\\WebProject\\src\\data\\kupci.json");
		this.dostavljaciFile = new File("C:\\Users\\Marko\\workspace\\WebProject\\src\\data\\dostavljaci.json");
	}
	
	public boolean insertKorisnik(Korisnik korisnik) throws JsonGenerationException, JsonMappingException, IOException {
		ArrayList<Korisnik> korisnici = getKorisnici();
		ArrayList<Kupac> kupci = getKupci();
		korisnik.setId((long)korisnici.size() + 1);
		korisnik.setUloga(Korisnik.Grupa.KUPAC);
		Kupac kupac = new Kupac(korisnik);
		korisnici.add(korisnik);
		kupci.add(kupac);
		mapper.writeValue(korisniciFile, korisnici);
		mapper.writeValue(kupciFile, kupci);
		return true;
	}
	
	public ArrayList<Korisnik> getKorisnici() throws JsonParseException, JsonMappingException, IOException {
		return mapper.readValue(korisniciFile, mapper.getTypeFactory().constructCollectionType(ArrayList.class, Korisnik.class));
	}
	
	public ArrayList<Kupac> getKupci() throws JsonParseException, JsonMappingException, IOException {
		return mapper.readValue(korisniciFile, mapper.getTypeFactory().constructCollectionType(ArrayList.class, Kupac.class));
	}
	
	public ArrayList<Dostavljac> getDostavljaci() throws JsonParseException, JsonMappingException, IOException {
		return mapper.readValue(korisniciFile, mapper.getTypeFactory().constructCollectionType(ArrayList.class, Dostavljac.class));
	}
	
	public Korisnik getKorisnik(String korisnickoIme) throws JsonParseException, JsonMappingException, IOException {
		ArrayList<Korisnik> korisnici = getKorisnici();
		for(Korisnik k : korisnici) {
			if(k.getKorisnickoIme().equals(korisnickoIme)) {
				return k;
			}	
		}
		return null;
	}
	
	public Korisnik getKorisnikById(Long id) throws JsonParseException, JsonMappingException, IOException {
		ArrayList<Korisnik> korisnici = getKorisnici();
		for(Korisnik k: korisnici) {
			if(k.getId() == id) {
				return k;
			}
		}
		return null;
	}
	
	public Korisnik getKupac(String korisnickoIme) throws JsonParseException, JsonMappingException, IOException {
		ArrayList<Kupac> kupci = getKupci();
		for(Kupac k : kupci) {
			if(k.getKorisnickoIme().equals(korisnickoIme)) {
				return k;
			}	
		}
		return null;
	}
	
	public Korisnik getKupacById(Long id) throws JsonParseException, JsonMappingException, IOException {
		ArrayList<Kupac> kupci = getKupci();
		for(Kupac k: kupci) {
			if(k.getId() == id) {
				return k;
			}
		}
		return null;
	}
	
	public Korisnik getDostavljac(String korisnickoIme) throws JsonParseException, JsonMappingException, IOException {
		ArrayList<Dostavljac> dostavljaci = getDostavljaci();
		for(Dostavljac d : dostavljaci) {
			if(d.getKorisnickoIme().equals(korisnickoIme)) {
				return d;
			}	
		}
		return null;
	}
	
	public Korisnik getDostavljacId(Long id) throws JsonParseException, JsonMappingException, IOException {
		ArrayList<Dostavljac> dostavljaci = getDostavljaci();
		for(Dostavljac d: dostavljaci) {
			if(d.getId() == id) {
				return d;
			}
		}
		return null;
	}
	
	public Korisnik changeRole(Korisnik korisnik) throws JsonParseException, JsonMappingException, IOException {
		ArrayList<Korisnik> korisnici = getKorisnici();
		Korisnik korisnikUpd = null;
		int idx = -1;
		for(Korisnik k: korisnici) {
			if(k.getId() == korisnik.getId()) {
				idx = korisnici.indexOf(k);
			}
		}
		if(idx == -1) {
			return null;
		}
		korisnikUpd = korisnici.get(idx);
		if(korisnikUpd.getUloga() == Korisnik.Grupa.KUPAC) {
			ArrayList<Kupac> kupci = getKupci();
			int idxKupac = -1;
			
			for(Kupac kup : kupci) {
				if(kup.getId() == korisnikUpd.getId()) {
					idxKupac = kupci.indexOf(kup);
				}
			}
			kupci.remove(idxKupac);
			if(korisnik.getUloga() == Korisnik.Grupa.DOSTAVLJAC) {
				ArrayList<Dostavljac> dostavljaci = getDostavljaci();
				Dostavljac dostavljac = new Dostavljac(korisnik);
				dostavljaci.add(dostavljac);
				mapper.writeValue(dostavljaciFile, dostavljaci);
			}
			mapper.writeValue(kupciFile, kupci);
		} else if(korisnikUpd.getUloga() == Korisnik.Grupa.DOSTAVLJAC) {
			ArrayList<Dostavljac> dostavljaci = getDostavljaci();
			int idxDost = -1;
			for(Dostavljac d: dostavljaci) {
				if(d.getId() == korisnikUpd.getId()) {
					idxDost = dostavljaci.indexOf(d);
				}
			}
			dostavljaci.remove(idxDost);
			if(korisnik.getUloga() == Korisnik.Grupa.KUPAC) {
				ArrayList<Kupac> kupci = getKupci();
				Kupac kupac = new Kupac(korisnik);
				kupci.add(kupac);
				mapper.writeValue(kupciFile, kupci);
			}
			mapper.writeValue(dostavljaciFile, dostavljaci);
		} else {
			
			if(korisnik.getUloga() == Korisnik.Grupa.KUPAC) {
				ArrayList<Kupac> kupci = getKupci();
				Kupac kupac = new Kupac(korisnik);
				kupci.add(kupac);
				mapper.writeValue(kupciFile, kupci);
			}
			
			if(korisnik.getUloga() == Korisnik.Grupa.DOSTAVLJAC) {
				ArrayList<Dostavljac> dostavljaci = getDostavljaci();
				Dostavljac dostavljac = new Dostavljac(korisnik);
				dostavljaci.add(dostavljac);
				mapper.writeValue(dostavljaciFile, dostavljaci);
			}
		}
		
		korisnikUpd.setUloga(korisnik.getUloga());
		korisnici.set(idx, korisnikUpd);
		mapper.writeValue(korisniciFile, korisnici);
		
		return korisnikUpd;
		
	}
	
}
