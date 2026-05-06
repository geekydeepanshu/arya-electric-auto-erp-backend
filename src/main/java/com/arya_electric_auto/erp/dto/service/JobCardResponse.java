package com.arya_electric_auto.erp.dto.service;

import java.util.List;

public class JobCardResponse {

    private Long id;
    private String personName;
    private String vehicleName;
    private List<String> complaints;
    private String assignedToName;
    private Long assetId;
    private String assetName;
    private String status;
    private String createdAt;

    public JobCardResponse() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getPersonName() { return personName; }
    public void setPersonName(String personName) { this.personName = personName; }

    public String getVehicleName() { return vehicleName; }
    public void setVehicleName(String vehicleName) { this.vehicleName = vehicleName; }

    

    public String getAssignedToName() { return assignedToName; }
    public void setAssignedToName(String assignedToName) { this.assignedToName = assignedToName; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getCreatedAt() { return createdAt; }
    public void setCreatedAt(String createdAt) { this.createdAt = createdAt; }

	public Long getAssetId() {
		return assetId;
	}

	public void setAssetId(Long assetId) {
		this.assetId = assetId;
	}

	public String getAssetName() {
		return assetName;
	}

	public void setAssetName(String assetName) {
		this.assetName = assetName;
	}

	public List<String> getComplaints() {
		return complaints;
	}

	public void setComplaints(List<String> complaints) {
		this.complaints = complaints;
	}
    
    
    
}