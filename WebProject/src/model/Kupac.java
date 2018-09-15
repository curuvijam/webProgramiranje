package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Kupac extends Korisnik {
	
	private List<Long> porudzbine;
	private List<Long> omiljeniRestorani;
	
	public Kupac() {}

	public Kupac(List<Long> porudzbine, List<Long> omiljeniRestorani) {
		super();
		this.porudzbine = porudzbine;
		this.omiljeniRestorani = omiljeniRestorani;
	}
	
	public Kupac(Korisnik korisnik) {
		super(korisnik.getId(), korisnik.getKorisnickoIme(), korisnik.getSifra(), korisnik.getIme(), korisnik.getPrezime(), korisnik.getUloga(), korisnik.getKontaktTelefon(), korisnik.getEmail(), korisnik.getDatumRegistracije());;
		// TODO Auto-generated constructor stub
		this.porudzbine = new ArrayList<Long>();
		this.omiljeniRestorani = new ArrayList<Long>();
	}

	public List<Long> getPorudzbine() {
		return porudzbine;
	}

	public void setPorudzbine(List<Long> porudzbine) {
		this.porudzbine = porudzbine;
	}

	public List<Long> getOmiljeniRestorani() {
		return omiljeniRestorani;
	}

	public void setOmiljeniRestorani(List<Long> omiljeniRestorani) {
		this.omiljeniRestorani = omiljeniRestorani;
	}

	

}
