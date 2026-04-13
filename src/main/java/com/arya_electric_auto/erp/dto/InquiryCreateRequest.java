package com.arya_electric_auto.erp.dto;

import com.arya_electric_auto.erp.entity.InquirySource;

import java.util.List;

public class InquiryCreateRequest {

    private String name;
    private String phone;
    private String city;
    private String address;
    private InquirySource source;
    private List<Long> modelIds;
    private String notes;

    public InquiryCreateRequest() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public InquirySource getSource() {
        return source;
    }

    public void setSource(InquirySource source) {
        this.source = source;
    }

    public List<Long> getModelIds() {
        return modelIds;
    }

    public void setModelIds(List<Long> modelIds) {
        this.modelIds = modelIds;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
