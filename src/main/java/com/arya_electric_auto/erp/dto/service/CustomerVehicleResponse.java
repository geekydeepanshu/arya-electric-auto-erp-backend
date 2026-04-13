package com.arya_electric_auto.erp.dto.service;

public class CustomerVehicleResponse {

    private Long id;
    private String personName;
    private String vehicleName;
    private String vehicleType;
    private String serialNumber;
    private Boolean isFromSale;
    private String purchaseDate;

    public CustomerVehicleResponse() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getPersonName() { return personName; }
    public void setPersonName(String personName) { this.personName = personName; }

    public String getVehicleName() { return vehicleName; }
    public void setVehicleName(String vehicleName) { this.vehicleName = vehicleName; }

    public String getVehicleType() { return vehicleType; }
    public void setVehicleType(String vehicleType) { this.vehicleType = vehicleType; }

    public String getSerialNumber() { return serialNumber; }
    public void setSerialNumber(String serialNumber) { this.serialNumber = serialNumber; }

    public Boolean getIsFromSale() { return isFromSale; }
    public void setIsFromSale(Boolean isFromSale) { this.isFromSale = isFromSale; }

    public String getPurchaseDate() { return purchaseDate; }
    public void setPurchaseDate(String purchaseDate) { this.purchaseDate = purchaseDate; }
}