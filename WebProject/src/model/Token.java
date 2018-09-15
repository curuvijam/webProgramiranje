package model;

public class Token {
	private Korisnik.Grupa role;
	private String username;
	private String token;
	
	
	
	public Token() {
		super();
	}
	public Korisnik.Grupa getRole() {
		return role;
	}
	public void setRole(Korisnik.Grupa role) {
		this.role = role;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
}
