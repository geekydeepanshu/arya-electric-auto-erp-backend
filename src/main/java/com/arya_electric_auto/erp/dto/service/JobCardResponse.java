package com.arya_electric_auto.erp.dto.service;

public class JobCardResponse {

    private Long id;
    private String personName;
    private String vehicleName;
    private String complaint;
    private String assignedToName;
    private String status;
    private String createdAt;

    public JobCardResponse() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getPersonName() { return personName; }
    public void setPersonName(String personName) { this.personName = personName; }

    public String getVehicleName() { return vehicleName; }
    public void setVehicleName(String vehicleName) { this.vehicleName = vehicleName; }

    public String getComplaint() { return complaint; }
    public void setComplaint(String complaint) { this.complaint = complaint; }

    public String getAssignedToName() { return assignedToName; }
    public void setAssignedToName(String assignedToName) { this.assignedToName = assignedToName; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getCreatedAt() { return createdAt; }
    public void setCreatedAt(String createdAt) { this.createdAt = createdAt; }
}