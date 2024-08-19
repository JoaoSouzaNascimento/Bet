package model;

import java.math.BigDecimal;
import java.util.List;

public class Aposta {

	private int id;
	private BigDecimal amount;
	private List<Palpite> palpites;
	private boolean status;
	
	public Aposta(int id, BigDecimal amount, List<Palpite> palpites, boolean status) {
		super();
		this.id = id;
		this.amount = amount;
		this.palpites = palpites;
		this.status = status;
	}
	
	public Aposta(BigDecimal amount, List<Palpite> palpites, boolean status) {
		super();
		this.amount = amount;
		this.palpites = palpites;
		this.status = status;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
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

	public boolean addPalpites(Palpite palpite) {
		return this.palpites.add(palpite);
	}
	
}