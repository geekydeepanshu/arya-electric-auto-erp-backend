package com.arya_electric_auto.erp.dto.service;

import java.util.List;

public class JobCardRequest {

    private Long personId;
    private Long vehicleId;
    private Long serviceRequestId;
    private List<String> complaints;
    private Long assetId;
    private Long assignedTo;

    public JobCardRequest() {}

    public Long getPersonId() { return personId; }
    public void setPersonId(Long personId) { this.personId = personId; }

    public Long getVehicleId() { return vehicleId; }
    public void setVehicleId(Long vehicleId) { this.vehicleId = vehicleId; }

    public Long getServiceRequestId() { return serviceRequestId; }
    public void setServiceRequestId(Long serviceRequestId) { this.serviceRequestId = serviceRequestId; }

    
    public Long getAssignedTo() { return assignedTo; }
    public void setAssignedTo(Long assignedTo) { this.assignedTo = assignedTo; }

	public Long getAssetId() {
		return assetId;
	}

	public void setAssetId(Long assetId) {
		this.assetId = assetId;
	}

	public List<String> getComplaints() {
		return complaints;
	}

	public void setComplaints(List<String> complaints) {
		this.complaints = complaints;
	}
    
    
}