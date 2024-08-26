package model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Aposta {

	private Integer id;
	private UUID userId;
	private BigDecimal amount;
	private Boolean status;
	private LocalDate date;

	private Aposta(UUID userID, BigDecimal amount, LocalDate date) {
		this.userId = userID;
		this.amount = amount;
		this.status = null;
		this.date = date;
	}

	public Aposta(Integer id, UUID userID, BigDecimal amount, List<Palpite> palpites, Boolean status, LocalDate date) {
		this(userID, amount, date);
		this.id = id;
		this.status = status;
	}

	public Aposta(UUID userID, BigDecimal amount, List<Palpite> palpites, String timeZone) {
		this(userID, amount, LocalDate.now(ZoneId.of(timeZone)));
		this.id = null;
		this.status = null;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public UUID getUserId() {
		return userId;
	}

	public void setUserId(UUID userId) {
		this.userId = userId;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

}