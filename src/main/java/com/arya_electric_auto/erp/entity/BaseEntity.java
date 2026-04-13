package com.arya_electric_auto.erp.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@MappedSuperclass


public abstract class BaseEntity {

    @Column(name = "created_at", insertable = false, updatable = false)
    protected LocalDateTime createdAt;

    @Column(name = "updated_at", insertable = false, updatable = false)
    protected LocalDateTime updatedAt;

    @Column(name = "created_by")
    protected Long createdBy;

    @Column(name = "updated_by")
    protected Long updatedBy;

    @Column(name = "deleted_at")
    protected LocalDateTime deletedAt;

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	public Long getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Long updatedBy) {
		this.updatedBy = updatedBy;
	}

	public LocalDateTime getDeletedAt() {
		return deletedAt;
	}

	public void setDeletedAt(LocalDateTime deletedAt) {
		this.deletedAt = deletedAt;
	}
    
    
    
    
}