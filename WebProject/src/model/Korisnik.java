package model;

import java.util.Date;

public class Korisnik {
	
	public static enum Grupa {KUPAC, ADMINISTRATOR, DOSTAVLJAC}
	private Long id;
	private String korisnickoIme;
	private String sifra;
	private String ime;
	private String prezime;
	private Grupa uloga;
	private String kontaktTelefon;
	private String email;
	private Date datumRegistracije;
	
	public Korisnik() {}

	public Korisnik(Long id, String korisnickoIme, String sifra, String ime, String prezime, Grupa uloga, String kontaktTelefon,
			String email, Date datumRegistracije) {
		super();
		this.id = id;
		this.korisnickoIme = korisnickoIme;
		this.sifra = sifra;
		this.ime = ime;
		this.prezime = prezime;
		this.uloga = uloga;
		this.kontaktTelefon = kontaktTelefon;
		this.email = email;
		this.datumRegistracije = datumRegistracije;
	}

	
	public Long getId() {
		return id;
	}
	

	public void setId(Long id) {
		this.id = id;
	}

	public String getKorisnickoIme() {
		return korisnickoIme;
	}

	public void setKorisnickoIme(String korisnickoIme) {
		this.korisnickoIme = korisnickoIme;
	}

	public String getSifra() {
		return sifra;
	}

	public void setSifra(String sifra) {
		this.sifra = sifra;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public Grupa getUloga() {
		return uloga;
	}

	public void setUloga(Grupa uloga) {
		this.uloga = uloga;
	}

	public String getKontaktTelefon() {
		return kontaktTelefon;
	}

	public void setKontaktTelefon(String kontaktTelefon) {
		this.kontaktTelefon = kontaktTelefon;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getDatumRegistracije() {
		return datumRegistracije;
	}

	public void setDatumRegistracije(Date datumRegistracije) {
		this.datumRegistracije = datumRegistracije;
	}
	
	
	
	
}
