package com.arya_electric_auto.erp.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "job_cards")
public class JobCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "person_id", nullable = false)
    private Person person;

    @ManyToOne
    @JoinColumn(name = "vehicle_id", nullable = false)
    private CustomerVehicle vehicle;

    @ManyToOne
    @JoinColumn(name = "service_request_id")
    private ServiceRequest serviceRequest;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String complaint;

    @ManyToOne
    @JoinColumn(name = "assigned_to")
    private Employee assignedTo;

    @Column
    private String status;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public JobCard() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Person getPerson() { return person; }
    public void setPerson(Person person) { this.person = person; }

    public CustomerVehicle getVehicle() { return vehicle; }
    public void setVehicle(CustomerVehicle vehicle) { this.vehicle = vehicle; }

    public ServiceRequest getServiceRequest() { return serviceRequest; }
    public void setServiceRequest(ServiceRequest serviceRequest) { this.serviceRequest = serviceRequest; }

    public String getComplaint() { return complaint; }
    public void setComplaint(String complaint) { this.complaint = complaint; }

    public Employee getAssignedTo() { return assignedTo; }
    public void setAssignedTo(Employee assignedTo) { this.assignedTo = assignedTo; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}