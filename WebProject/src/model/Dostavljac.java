package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Dostavljac extends Korisnik {
	
	private Long vozilo;
	private List<Long> porudzbine;
	
	public Dostavljac() {}
	
	public Dostavljac(Korisnik korisnik) {
		super(korisnik.getId(), korisnik.getKorisnickoIme(), korisnik.getSifra(), korisnik.getIme(), korisnik.getPrezime(), korisnik.getUloga(), korisnik.getKontaktTelefon(), korisnik.getEmail(), korisnik.getDatumRegistracije());;
		this.vozilo = null;
		this.porudzbine = new ArrayList<Long>();
	}

	public Long getVozilo() {
		return vozilo;
	}

	public void setVozilo(Long vozilo) {
		this.vozilo = vozilo;
	}

	public List<Long> getPorudzbine() {
		return porudzbine;
	}

	public void setPorudzbine(List<Long> porudzbine) {
		this.porudzbine = porudzbine;
	}

	
	
	

}
