package com.arya_electric_auto.erp.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "inquiries")

public class Inquiry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 🔗 person (customer/lead)
    @ManyToOne
    @JoinColumn(name = "person_id", nullable = false)
    private Person person;

    // VISIT / CALL / ONLINE
    @Enumerated(EnumType.STRING)
    private InquirySource source;

    private LocalDateTime inquiryDate;

    // NEW / FOLLOW_UP / CONVERTED etc.
    @Enumerated(EnumType.STRING)
    private InquiryStatus status;

    // 🔗 employee handling
    @ManyToOne
    @JoinColumn(name = "handled_by")
    private Employee handledBy;

    private String notes;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Person getPerson() {
		return person;
	}
	public void setPerson(Person person) {
		this.person = person;
	}
	public InquirySource getSource() {
		return source;
	}
	public void setSource(InquirySource source) {
		this.source = source;
	}
	public LocalDateTime getInquiryDate() {
		return inquiryDate;
	}
	public void setInquiryDate(LocalDateTime inquiryDate) {
		this.inquiryDate = inquiryDate;
	}
	public InquiryStatus getStatus() {
		return status;
	}
	public void setStatus(InquiryStatus status) {
		this.status = status;
	}
	public Employee getHandledBy() {
		return handledBy;
	}
	public void setHandledBy(Employee handledBy) {
		this.handledBy = handledBy;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
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
	public LocalDateTime getDeletedAt() {
		return deletedAt;
	}
	public void setDeletedAt(LocalDateTime deletedAt) {
		this.deletedAt = deletedAt;
	}
    
    
    
    
}