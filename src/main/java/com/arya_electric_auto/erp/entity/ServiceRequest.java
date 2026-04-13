package com.arya_electric_auto.erp.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "service_requests")
public class ServiceRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "person_id", nullable = false)
    private Person person;

    @ManyToOne
    @JoinColumn(name = "vehicle_id")
    private CustomerVehicle vehicle;

    @Column(name = "vehicle_details", columnDefinition = "TEXT")
    private String vehicleDetails;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String complaint;

    @Column(name = "preferred_date")
    private LocalDate preferredDate;

    @Column
    private String status;

    @Column(columnDefinition = "TEXT")
    private String notes;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    public ServiceRequest() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Person getPerson() { return person; }
    public void setPerson(Person person) { this.person = person; }

    public CustomerVehicle getVehicle() { return vehicle; }
    public void setVehicle(CustomerVehicle vehicle) { this.vehicle = vehicle; }

    public String getVehicleDetails() { return vehicleDetails; }
    public void setVehicleDetails(String vehicleDetails) { this.vehicleDetails = vehicleDetails; }

    public String getComplaint() { return complaint; }
    public void setComplaint(String complaint) { this.complaint = complaint; }

    public LocalDate getPreferredDate() { return preferredDate; }
    public void setPreferredDate(LocalDate preferredDate) { this.preferredDate = preferredDate; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}