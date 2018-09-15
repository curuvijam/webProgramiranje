package services;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import model.Artikal;
import model.Korisnik;
import model.Restoran;

public class RestoranService {

	private ObjectMapper mapper;
	private File file;

	public RestoranService() {
		this.mapper = new ObjectMapper();
		this.file = new File("C:\\Users\\Marko\\workspace\\WebProject\\src\\data\\restorani.json");
	}

	public Restoran addRestoran(Restoran restoran) throws JsonGenerationException, JsonMappingException, IOException {
		ArrayList<Restoran> restorani = getAll();
		restoran.setId((long) restorani.size() + 1);
		restorani.add(restoran);
		mapper.writeValue(file, restorani);
		return restoran;
	}

	public ArrayList<Restoran> getAll() throws JsonParseException, JsonMappingException, IOException {
		return mapper.readValue(file, mapper.getTypeFactory().constructCollectionType(ArrayList.class, Restoran.class));
	}

	public Restoran getRestoranById(Long id) throws JsonParseException, JsonMappingException, IOException {
		ArrayList<Restoran> restorani = mapper.readValue(file,
				mapper.getTypeFactory().constructCollectionType(ArrayList.class, Restoran.class));
		for (Restoran r : restorani) {
			if (r.getId() == id) {
				return r;
			}
		}
		return null;
	}

	public ArrayList<Restoran> getByKat(String kategorija)
			throws JsonParseException, JsonMappingException, IOException {
		ArrayList<Restoran> restorani = getAll();
		ArrayList<Restoran> ret = new ArrayList<Restoran>();

		Restoran.Kategorija kat;
		if (kategorija.toLowerCase().equals("domaca")) {
			kat = Restoran.Kategorija.DOMACA;
		} else if (kategorija.toLowerCase().equals("rostilj")) {
			kat = Restoran.Kategorija.ROSTILJ;
		} else if (kategorija.toLowerCase().equals("kineski")) {
			kat = Restoran.Kategorija.KINESKI;
		} else if (kategorija.toLowerCase().equals("indijski")) {
			kat = Restoran.Kategorija.INDIJSKI;
		} else if (kategorija.toLowerCase().equals("poslasticarnica")) {
			kat = Restoran.Kategorija.POSLASTICARNICA;
		} else {
			kat = Restoran.Kategorija.PICERIJA;
		}
		for (Restoran r : restorani) {
			if (r.getKategorija() == kat) {
				ret.add(r);
			}
		}
		
		return ret;

	}

	public boolean deleteRestoran(Long id) throws JsonParseException, JsonMappingException, IOException {
		ArrayList<Restoran> restorani = getAll();
		Restoran restoran = null;
		int idx = -1;
		for (Restoran r : restorani) {
			if (r.getId() == id) {
				idx = restorani.indexOf(r);
				break;
			}
		}
		if (idx == -1) {
			return false;
		}
		restoran = restorani.get(idx);
		restoran.setObrisan(true);
		restorani.set(idx, restoran);
		mapper.writeValue(file, restorani);
		return true;
	}

	public boolean updateRestoran(Restoran restoran) throws JsonParseException, JsonMappingException, IOException {
		ArrayList<Restoran> restorani = getAll();
		Restoran restoranUpd = null;
		int idx = -1;
		for (Restoran r : restorani) {
			if (r.getId() == restoran.getId()) {
				idx = restorani.indexOf(r);
			}
		}
		if (idx == -1) {
			return false;
		}
		restoranUpd = restorani.get(idx);
		restoranUpd.setAdresa(restoran.getAdresa());
		restoranUpd.setJela(restoran.getJela());
		restoranUpd.setKategorija(restoran.getKategorija());
		restoranUpd.setNaziv(restoran.getNaziv());
		restoranUpd.setPica(restoran.getPica());
		restoranUpd.setObrisan(restoran.isObrisan());

		restorani.set(idx, restoranUpd);

		mapper.writeValue(file, restorani);

		return true;
	}

	public ArrayList<Restoran> searchRestoran(String naziv, String adresa, String kategorija)
			throws JsonParseException, JsonMappingException, IOException {
		ArrayList<Restoran> restorani = getAll();
		ArrayList<Restoran> ret = new ArrayList<Restoran>();

		if (!naziv.equals("undefined")) {
			for (Restoran r : restorani) {
				if (r.getNaziv().toLowerCase().contains(naziv.toLowerCase())) {
					ret.add(r);
				}
			}
		}

		if (!adresa.equals("undefined")) {
			for (Restoran r : restorani) {
				if (r.getAdresa().toLowerCase().contains(adresa.toLowerCase())) {
					ret.add(r);
				}
			}
		}

		if (!kategorija.equals("undefined")) {
			Restoran.Kategorija kat;
			if (kategorija.toLowerCase().equals("domaca")) {
				kat = Restoran.Kategorija.DOMACA;
			} else if (kategorija.toLowerCase().equals("rostilj")) {
				kat = Restoran.Kategorija.ROSTILJ;
			} else if (kategorija.toLowerCase().equals("kineski")) {
				kat = Restoran.Kategorija.KINESKI;
			} else if (kategorija.toLowerCase().equals("indijski")) {
				kat = Restoran.Kategorija.INDIJSKI;
			} else if (kategorija.toLowerCase().equals("poslasticarnica")) {
				kat = Restoran.Kategorija.POSLASTICARNICA;
			} else {
				kat = Restoran.Kategorija.PICERIJA;
			}
			for (Restoran r : restorani) {
				if (r.getKategorija() == kat) {
					ret.add(r);
				}
			}
		}

		return ret;
	}
}
