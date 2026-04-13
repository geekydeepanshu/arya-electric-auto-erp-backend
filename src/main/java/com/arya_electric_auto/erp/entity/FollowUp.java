package com.arya_electric_auto.erp.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "follow_ups")
public class FollowUp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 🔗 inquiry_id
    @ManyToOne
    @JoinColumn(name = "inquiry_id", nullable = false)
    private Inquiry inquiry;

    // 🔗 employee_id
    @Column(name = "employee_id")
    private Long employeeId;

    // 📝 notes
    @Column(columnDefinition = "TEXT")
    private String notes;

    // 📅 next_follow_up_date
    @Column(name = "next_follow_up_date")
    private LocalDateTime nextFollowUpDate;

    // 📊 status ENUM
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private FollowUpStatus status;

    // ⏱ created_at (auto)
    @Column(name = "created_at", insertable = false, updatable = false)
    private LocalDateTime createdAt;

    // ⏱ updated_at (auto update)
    @Column(name = "updated_at", insertable = false, updatable = false)
    private LocalDateTime updatedAt;

    // 👤 updated_by
    @Column(name = "updated_by")
    private Long updatedBy;

    // ❌ soft delete
    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Inquiry getInquiry() {
		return inquiry;
	}

	public void setInquiry(Inquiry inquiry) {
		this.inquiry = inquiry;
	}

	public Long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public LocalDateTime getNextFollowUpDate() {
		return nextFollowUpDate;
	}

	public void setNextFollowUpDate(LocalDateTime nextFollowUpDate) {
		this.nextFollowUpDate = nextFollowUpDate;
	}

	public FollowUpStatus getStatus() {
		return status;
	}

	public void setStatus(FollowUpStatus status) {
		this.status = status;
	}

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

    // getters/setters
    
    
}