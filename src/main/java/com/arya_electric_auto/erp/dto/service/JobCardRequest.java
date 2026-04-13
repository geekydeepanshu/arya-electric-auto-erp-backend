package com.arya_electric_auto.erp.dto.service;

public class JobCardRequest {

    private Long personId;
    private Long vehicleId;
    private Long serviceRequestId;
    private String complaint;
    private Long assignedTo;

    public JobCardRequest() {}

    public Long getPersonId() { return personId; }
    public void setPersonId(Long personId) { this.personId = personId; }

    public Long getVehicleId() { return vehicleId; }
    public void setVehicleId(Long vehicleId) { this.vehicleId = vehicleId; }

    public Long getServiceRequestId() { return serviceRequestId; }
    public void setServiceRequestId(Long serviceRequestId) { this.serviceRequestId = serviceRequestId; }

    public String getComplaint() { return complaint; }
    public void setComplaint(String complaint) { this.complaint = complaint; }

    public Long getAssignedTo() { return assignedTo; }
    public void setAssignedTo(Long assignedTo) { this.assignedTo = assignedTo; }
}