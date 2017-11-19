package models;

public class User {
	private Long id;
	private String name;
	private String passwort;
	
	public User() { }
	
	public User(String name, String password) {
		setName(name);
		setPasswort(password);
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPasswort() {
		return passwort;
	}
	public void setPasswort(String passwort) {
		this.passwort = passwort;
	}
}
