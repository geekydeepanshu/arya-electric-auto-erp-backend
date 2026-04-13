package com.arya_electric_auto.erp.dto.service;

public class ServiceHistoryItem {

    private Long jobCardId;
    private String vehicleName;
    private String complaint;
    private String date;
    private String status;
    private Double finalAmount;

    public ServiceHistoryItem() {}

    public Long getJobCardId() {
        return jobCardId;
    }

    public void setJobCardId(Long jobCardId) {
        this.jobCardId = jobCardId;
    }

    public String getVehicleName() {
        return vehicleName;
    }

    public void setVehicleName(String vehicleName) {
        this.vehicleName = vehicleName;
    }

    public String getComplaint() {
        return complaint;
    }

    public void setComplaint(String complaint) {
        this.complaint = complaint;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Double getFinalAmount() {
        return finalAmount;
    }

    public void setFinalAmount(Double finalAmount) {
        this.finalAmount = finalAmount;
    }
}
