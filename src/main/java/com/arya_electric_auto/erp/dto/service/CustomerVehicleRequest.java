package com.arya_electric_auto.erp.dto.service;

import java.time.LocalDate;

public class CustomerVehicleRequest {

    private Long personId;
    private String vehicleName;
    private String vehicleType;
    private String serialNumber;
    private Boolean isFromSale;
    private Long saleId;
    private LocalDate purchaseDate;
    private String notes;

    public CustomerVehicleRequest() {}

    public Long getPersonId() { return personId; }
    public void setPersonId(Long personId) { this.personId = personId; }

    public String getVehicleName() { return vehicleName; }
    public void setVehicleName(String vehicleName) { this.vehicleName = vehicleName; }

    public String getVehicleType() { return vehicleType; }
    public void setVehicleType(String vehicleType) { this.vehicleType = vehicleType; }

    public String getSerialNumber() { return serialNumber; }
    public void setSerialNumber(String serialNumber) { this.serialNumber = serialNumber; }

    public Boolean getIsFromSale() { return isFromSale; }
    public void setIsFromSale(Boolean isFromSale) { this.isFromSale = isFromSale; }

    public Long getSaleId() { return saleId; }
    public void setSaleId(Long saleId) { this.saleId = saleId; }

    public LocalDate getPurchaseDate() { return purchaseDate; }
    public void setPurchaseDate(LocalDate purchaseDate) { this.purchaseDate = purchaseDate; }

    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }
}