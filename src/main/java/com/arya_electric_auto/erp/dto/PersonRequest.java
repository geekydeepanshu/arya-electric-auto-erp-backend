package com.arya_electric_auto.erp.dto;

import jakarta.validation.constraints.Pattern;

public class PersonRequest {

    private String name;
    
    @Pattern(
    	    regexp = "^[6-9]\\d{9}$",
    	    message = "Phone number must be valid 10 digit Indian mobile number"
    	)
    private String phone;
    
    
    private String city;
    private String address;

    public PersonRequest() {}

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
}
