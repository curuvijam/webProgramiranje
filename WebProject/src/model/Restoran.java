package model;

import java.util.ArrayList;
import java.util.List;

public class Restoran {
	
	public static enum Kategorija {DOMACA, ROSTILJ, KINESKI, INDIJSKI, POSLASTICARNICA, PICERIJA}
	
	private Long id;
	private String naziv;
	private String adresa;
	private Kategorija kategorija;
	private List<Long> jela;
	private List<Long> pica;
	private boolean obrisan;
	
	public Restoran() {}

	public Restoran(Long id, String naziv, String adresa, Kategorija kategorija) {
		super();
		this.id = id;
		this.naziv = naziv;
		this.adresa = adresa;
		this.kategorija = kategorija;
		this.jela = new ArrayList<Long>();
		this.pica = new ArrayList<Long>();
		this.setObrisan(false);
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public String getAdresa() {
		return adresa;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}

	public Kategorija getKategorija() {
		return kategorija;
	}

	public void setKategorija(Kategorija kategorija) {
		this.kategorija = kategorija;
	}

	public List<Long> getJela() {
		return jela;
	}

	public void setJela(List<Long> jela) {
		this.jela = jela;
	}

	public List<Long> getPica() {
		return pica;
	}

	public void setPica(List<Long> pica) {
		this.pica = pica;
	}

	public boolean isObrisan() {
		return obrisan;
	}

	public void setObrisan(boolean obrisan) {
		this.obrisan = obrisan;
	}

}
