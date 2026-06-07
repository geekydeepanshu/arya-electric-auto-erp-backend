package com.arya_electric_auto.erp.entity;

import jakarta.persistence.*;

import java.time.Instant;


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

    private Instant inquiryDate;

    // NEW / FOLLOW_UP / CONVERTED etc.
    @Enumerated(EnumType.STRING)
    private InquiryStatus status;

    // 🔗 employee handling
    @ManyToOne
    @JoinColumn(name = "handled_by")
    private Employee handledBy;

    private String notes;

    private  Instant createdAt;
    private Instant updatedAt;
    private Instant deletedAt;
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
	public Instant getInquiryDate() {
		return inquiryDate;
	}
	public void setInquiryDate(Instant inquiryDate) {
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
	public Instant getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Instant createdAt) {
		this.createdAt = createdAt;
	}
	public Instant getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Instant updatedAt) {
		this.updatedAt = updatedAt;
	}
	public Instant getDeletedAt() {
		return deletedAt;
	}
	public void setDeletedAt(Instant deletedAt) {
		this.deletedAt = deletedAt;
	}
    
    
    
    
}