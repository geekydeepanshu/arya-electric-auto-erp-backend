package com.arya_electric_auto.erp.dto.service;

import java.util.List;

public class ServiceHistoryResponse {

    private List<ServiceHistoryItem> items;

    public ServiceHistoryResponse() {}

    public List<ServiceHistoryItem> getItems() {
        return items;
    }

    public void setItems(List<ServiceHistoryItem> items) {
        this.items = items;
    }
}
