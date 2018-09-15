package model;

public class Artikal {
	
	public static enum Tip { JELO, PICE }
	
	private Long id;
	private String naziv;
	private double cena;
	private String opis;
	private Tip tip;
	private double kolicina;
	private Long restoranId;
	private boolean obrisan;
	
	public Artikal() {}

	public Artikal(Long id, String naziv, Long cena, String opis, Tip tip, double kolicina, Long restoranId) {
		super();
		this.id = id;
		this.naziv = naziv;
		this.cena = cena;
		this.opis = opis;
		this.tip = tip;
		this.kolicina = kolicina;
		this.restoranId = restoranId;
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

	public double getCena() {
		return cena;
	}

	public void setCena(double cena) {
		this.cena = cena;
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public double getKolicina() {
		return kolicina;
	}

	public void setKolicina(double kolicina) {
		this.kolicina = kolicina;
	}

	public Long getRestoranId() {
		return restoranId;
	}

	public void setRestoranId(Long restoranId) {
		this.restoranId = restoranId;
	}

	public Tip getTip() {
		return tip;
	}

	public void setTip(Tip tip) {
		this.tip = tip;
	}

	public boolean isObrisan() {
		return obrisan;
	}

	public void setObrisan(boolean obrisan) {
		this.obrisan = obrisan;
	}
	
	

}
