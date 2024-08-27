package model;

import java.util.UUID;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class AdminLog {
    private Integer id;
    private UUID adminId;
    private String action;
    private Timestamp createdAt;
    
	public AdminLog(Integer id, UUID adminId, String action, Timestamp createdAt) {
		this.id = id;
		this.adminId = adminId;
		this.action = action;
		this.createdAt = createdAt;
	}
	
	public AdminLog(UUID adminId, String action) {
		this.id = null;
		this.adminId = adminId;
		this.action = action;
		this.createdAt = null;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public UUID getAdminId() {
		return adminId;
	}

	public void setAdminId(UUID adminId) {
		this.adminId = adminId;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}
	
	
}
