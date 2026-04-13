package com.arya_electric_auto.erp.dto;

import java.time.LocalDateTime;
import java.util.List;

public class InquiryResponse {

    private Long id;
    private String name;
    private String phone;
    private String address;
    private String city;
    private String source;
    private String status;
    private LocalDateTime inquiryDate;

    // ✅ important
    private List<String> models;

    public InquiryResponse(Long id, String name, String phone,String address, String city,
                           String source, String status,
                           LocalDateTime inquiryDate,
                           List<String> models) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.city = city;
        this.source = source;
        this.status = status;
        this.inquiryDate = inquiryDate;
        this.models = models;
    }

    // getters
    public Long getId() { return id; }
    public List<String> getModels() { return models; }

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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public LocalDateTime getInquiryDate() {
		return inquiryDate;
	}

	public void setInquiryDate(LocalDateTime inquiryDate) {
		this.inquiryDate = inquiryDate;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setModels(List<String> models) {
		this.models = models;
	}
    
    
}