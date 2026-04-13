package com.arya_electric_auto.erp.dto;

import java.math.BigDecimal;

public class InvoiceItemResponse {

    private String itemName;
    private Integer quantity;
    private BigDecimal price;
    private BigDecimal total;

    // optional (for serialized)
    private String serialNumber;

    public InvoiceItemResponse() {}

    public InvoiceItemResponse(String itemName, Integer quantity,
                               BigDecimal price, BigDecimal total,
                               String serialNumber) {
        this.itemName = itemName;
        this.quantity = quantity;
        this.price = price;
        this.total = total;
        this.serialNumber = serialNumber;
    }

    // getters
    public String getItemName() { return itemName; }
    public Integer getQuantity() { return quantity; }
    public BigDecimal getPrice() { return price; }
    public BigDecimal getTotal() { return total; }
    public String getSerialNumber() { return serialNumber; }
}