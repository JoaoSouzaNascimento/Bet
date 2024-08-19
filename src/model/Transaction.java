package model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.UUID;

public class Transaction {
    private int id;
    private UUID userId;
    private String type;
    private int status;
    private BigDecimal amount;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    public Transaction(int id, UUID userId, String type, int status, BigDecimal amount, Timestamp createdAt, Timestamp updatedAt) {
        this.id = id;
        this.userId = userId;
        this.type = type;
        this.status = status;
        this.amount = amount;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }
}
