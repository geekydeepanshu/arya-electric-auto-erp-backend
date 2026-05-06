package com.arya_electric_auto.erp.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "job_card_complaints")
public class JobCardComplaint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "job_card_id", nullable = false)
    private JobCard jobCard;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String status = "PENDING";

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    public JobCardComplaint() {}

    @PrePersist
    public void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    
    
    // getters setters

    public Long getId() { return id; }

    public JobCard getJobCard() { return jobCard; }
    public void setJobCard(JobCard jobCard) { this.jobCard = jobCard; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public LocalDateTime getCreatedAt() { return createdAt; }

	public void setId(Long id) {
		this.id = id;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
}