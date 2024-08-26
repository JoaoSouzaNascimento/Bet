package model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

public class Aposta {

	private int id;
	private float amount;
	private List<Palpite> palpites;
	private Boolean status;
	private LocalDate date; 
	
	public Aposta(int id, float amount, List<Palpite> palpites, boolean status, LocalDate date) {
		super();
		this.id = id;
		this.amount = amount;
		this.palpites = palpites;
		this.status = status;
		this.date = date;
	}
	
	public Aposta(float amount, List<Palpite> palpites, String timeZone) {
		super();
		this.amount = amount;
		this.palpites = palpites;
		this.status = null;
		LocalDate.now(ZoneId.of(timeZone));
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public float getAmount() {
		return amount;
	}
	public void setAmount(float amount) {
		this.amount = amount;
	}
	public List<Palpite> getPalpites() {
		return palpites;
	}
	public void setPalpites(List<Palpite> palpites) {
		this.palpites = palpites;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}

	public LocalDate getDate() {
		return date;
	}
	
	public boolean addPalpite(Palpite palpite) {
		return this.palpites.add(palpite);
	}

	public boolean removePalpiteById(int palpiteId) {
		return this.palpites.removeIf(palpite -> palpite.getPartidaId() == palpiteId);		
	}
	
	
}