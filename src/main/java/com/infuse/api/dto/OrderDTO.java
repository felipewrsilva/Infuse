package com.infuse.api.dto;

import java.math.BigDecimal;
import java.util.Date;

public class OrderDTO {

    private Long id;
    private String controlNumber;
    private Date registrationDate;
    private String productName;
    private BigDecimal unitPrice;
    private Integer quantity;

    // Constructors
    public OrderDTO() {
    }

    public OrderDTO(Long id, String controlNumber, Date registrationDate, String productName, BigDecimal unitPrice,
            Integer quantity) {
        this.id = id;
        this.controlNumber = controlNumber;
        this.registrationDate = registrationDate;
        this.productName = productName;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
    }

    // Getters
    public Long getId() {
        return id;
    }

    public String getControlNumber() {
        return controlNumber;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public String getProductName() {
        return productName;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public Integer getQuantity() {
        return quantity;
    }

    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setControlNumber(String controlNumber) {
        this.controlNumber = controlNumber;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

}
