package com.arya_electric_auto.erp.dto.service;

import java.time.LocalDate;

public class ServiceRequestDto {

    private Long id;
    private Long personId;
    private Long vehicleId;
    private String vehicleDetails;
    private String complaint;
    private LocalDate preferredDate;
    private String status;
    private String notes;

    public ServiceRequestDto() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getPersonId() { return personId; }
    public void setPersonId(Long personId) { this.personId = personId; }

    public Long getVehicleId() { return vehicleId; }
    public void setVehicleId(Long vehicleId) { this.vehicleId = vehicleId; }

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
}