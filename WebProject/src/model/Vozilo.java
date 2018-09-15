package model;

public class Vozilo {
	
	public static enum Tip {BICIKL, SKUTER, AUTOMOBIL}
	
	private Long id;
	private String marka;
	private String model;
	private Tip tip;
	private String regOznaka;
	private String godinaProizvodnje;
	private boolean uUpotrebi;
	private String napomena;
	private boolean obrisan;
	
	public Vozilo() {}

	public Vozilo(Long id, String marka, String model, Tip tip, String regOznaka, String godinaProizvodnje, boolean uUpotrebi,
			String napomena) {
		super();
		this.id = id;
		this.marka = marka;
		this.model = model;
		this.tip = tip;
		this.regOznaka = regOznaka;
		this.godinaProizvodnje = godinaProizvodnje;
		this.uUpotrebi = uUpotrebi;
		this.napomena = napomena;
		this.setObrisan(false);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMarka() {
		return marka;
	}

	public void setMarka(String marka) {
		this.marka = marka;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public Tip getTip() {
		return tip;
	}

	public void setTip(Tip tip) {
		this.tip = tip;
	}

	public String getRegOznaka() {
		return regOznaka;
	}

	public void setRegOznaka(String regOznaka) {
		this.regOznaka = regOznaka;
	}

	public String getGodinaProizvodnje() {
		return godinaProizvodnje;
	}

	public void setGodinaProizvodnje(String godinaProizvodnje) {
		this.godinaProizvodnje = godinaProizvodnje;
	}

	public boolean isuUpotrebi() {
		return uUpotrebi;
	}

	public void setuUpotrebi(boolean uUpotrebi) {
		this.uUpotrebi = uUpotrebi;
	}

	public String getNapomena() {
		return napomena;
	}

	public void setNapomena(String napomena) {
		this.napomena = napomena;
	}

	public boolean isObrisan() {
		return obrisan;
	}

	public void setObrisan(boolean obrisan) {
		this.obrisan = obrisan;
	}
	
}
