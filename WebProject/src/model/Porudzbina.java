package model;

import java.util.Date;
import java.util.List;

public class Porudzbina {
	
	public static enum Status {PORUCENO, TRAJE, OTKAZANO, DOSTAVLJENO}
	
	private Long id;
	private List<Artikal> stavkePorudzbine;
	private Date datumPorudzbine;
	private Kupac kupac;
	private Dostavljac dostavljac;
	private Status status;
	private String napomena;
	
	public Porudzbina() {}

	public Porudzbina(Long id, List<Artikal> stavkePorudzbine, Date datumPorudzbine, Kupac kupac, Dostavljac dostavljac,
			Status status, String napomena) {
		super();
		this.id = id;
		this.stavkePorudzbine = stavkePorudzbine;
		this.datumPorudzbine = datumPorudzbine;
		this.kupac = kupac;
		this.dostavljac = dostavljac;
		this.status = status;
		this.napomena = napomena;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Artikal> getStavkePorudzbine() {
		return stavkePorudzbine;
	}

	public void setStavkePorudzbine(List<Artikal> stavkePorudzbine) {
		this.stavkePorudzbine = stavkePorudzbine;
	}

	public Date getDatumPorudzbine() {
		return datumPorudzbine;
	}

	public void setDatumPorudzbine(Date datumPorudzbine) {
		this.datumPorudzbine = datumPorudzbine;
	}

	public Kupac getKupac() {
		return kupac;
	}

	public void setKupac(Kupac kupac) {
		this.kupac = kupac;
	}

	public Dostavljac getDostavljac() {
		return dostavljac;
	}

	public void setDostavljac(Dostavljac dostavljac) {
		this.dostavljac = dostavljac;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getNapomena() {
		return napomena;
	}

	public void setNapomena(String napomena) {
		this.napomena = napomena;
	}

}
