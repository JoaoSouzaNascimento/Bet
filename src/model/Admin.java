package model;

import java.sql.Timestamp;
import java.util.UUID;

public class Admin extends Usuario {

	private UUID adminId;
	private String action;
	private Timestamp logDate;

	public Admin(UUID id, String username, String nickname, String password, String email, double balance,
			boolean deleted, String role, UUID adminId, String action, Timestamp logDate) {
		super(id, username, nickname, password, email, balance, deleted, role);
		this.adminId = adminId;
		this.action = action;
		this.logDate = logDate;
	}

	public Admin(UUID id, String username, String nickname, String password, String email, UUID adminId, String action, Timestamp logDate) {
		super(id, username, nickname, password, email);
		this.adminId = adminId;
		this.action = action;
		this.logDate = logDate;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public Timestamp getLogDate() {
		return logDate;
	}

	public void setLogDate(Timestamp logDate) {
		this.logDate = logDate;
	}

	public UUID getAdminId() {
		return adminId;
	}

	@Override
	public String toString() {
		return "Adm [adminId=" + adminId + ", action=" + action + ", logDate=" + logDate + "]";
	}
	
	
	
}
