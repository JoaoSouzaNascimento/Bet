package model;

import java.util.UUID;

public class Usuario {

	private UUID id;
	private String username;
	private String nickname;
	private String password;
	private String email;
	private double balance;
	private boolean deleted;
	private CargoUsuario role;
	
	
	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getNickname() {
		return nickname;
	}


	public void setNickname(String nickname) {
		this.nickname = nickname;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public double getBalance() {
		return balance;
	}


	public void setBalance(double balance) {
		this.balance = balance;
	}


	public boolean isDeleted() {
		return deleted;
	}


	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}


	public CargoUsuario getRole() {
		return role;
	}


	public void setRole(CargoUsuario role) {
		this.role = role;
	}


	public UUID getId() {
		return id;
	}


	public Usuario(UUID id, String username, String nickname, String password, String email, double balance,
			boolean deleted, CargoUsuario role) {
		super();
		this.id = id;
		this.username = username;
		this.nickname = nickname;
		this.password = password;
		this.email = email;
		this.balance = balance;
		this.deleted = deleted;
		this.role = role;
	}

	
	
}