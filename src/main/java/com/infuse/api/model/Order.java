package com.infuse.api.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "orders")
public class Order {

	// Fields
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "control_number", unique = true, nullable = false)
	private String controlNumber;

	@Column(name = "registration_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date registrationDate;

	@Column(name = "product_name", nullable = false)
	private String productName;

	@Column(name = "unit_price", nullable = false)
	private BigDecimal unitPrice;

	@Column(name = "quantity")
	private Integer quantity;

	@Column(name = "customer_id", nullable = false)
	private Long customerId;

	@Column(name = "discount")
	private BigDecimal discount;

	@Column(name = "total")
	private BigDecimal total;

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

	public Long getCustomerId() {
		return customerId;
	}

	public BigDecimal getDiscount() {
		return discount;
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

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}

	public void setTotalValue(BigDecimal total) {
		this.total = total;
	}

}
