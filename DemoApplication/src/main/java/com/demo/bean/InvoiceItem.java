package com.demo.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Component
@Data
@Entity
@Table(name = "invoices_items")
public class InvoiceItem {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long pk;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="invoice_id_FK",referencedColumnName ="invoiceId",nullable=false)
	private Invoice invoice;
	
	@Column(columnDefinition = "Varchar(10) ", nullable = true)
	private String kind;
	private long sn;
	
	
	
	@ManyToOne  //(fetch = FetchType.LAZY)
	@JoinColumn(name="store_id_FK",nullable=false)
	private Store store;
	
	@ManyToOne
	@JoinColumn(name="item_id_FK")
	private Item item;
	
	@Column(columnDefinition = "Decimal(16,6) default '0.000000' ", nullable = true)
	private double qty;
	@Column(columnDefinition = "Decimal(16,6) default '0.000000' ", nullable = true)
	private double amount;
	@Column(columnDefinition = "Decimal(16,6) default '0.000000' ", nullable = true)
	private double amountIncTax;
	@Column(columnDefinition = "Decimal(16,6) default '0.000000' ", nullable = true)
	private double total;
	@Column(columnDefinition = "Decimal(16,6) default '0.000000' ", nullable = true)
	private double totalIncTax;

	private String unit;
	@Column(columnDefinition = "Decimal(11,6) default '1.000000' ", nullable = true)
	private double unitPieces;

	@Column(columnDefinition = "Decimal(16,6) default '0.000000' ", nullable = true)
	private double unitQtyIn;
	@Column(columnDefinition = "Decimal(16,6) default '0.000000' ", nullable = true)
	private double unitQtyOut;
	@Column(columnDefinition = "Decimal(16,6) default '0.000000' ", nullable = true)
	private double unitCost;
	@Column(columnDefinition = "Decimal(16,6) default '0.000000' ", nullable = true)
	private double totalCost;
	@Column(columnDefinition = "Decimal(16,6) default '0.000000' ", nullable = true)
	private double unitPrice;
	@Column(columnDefinition = "Decimal(6,3) default '0.000000' ", nullable = true)
	private double discountPer1;
	@Column(columnDefinition = "Decimal(16,6) default '0.000000' ", nullable = true)
	private double discount1;
	@Column(columnDefinition = "Decimal(16,6) default '0.000000' ", nullable = true)
	private double totalIncDiscount;
	@Column(columnDefinition = "Decimal(16,6) default '0.000000' ", nullable = true)
	private double additions;

	@Column(columnDefinition = "Decimal(16,6) default '0.000000' ", nullable = true)
	private double discount2;
	@Column(columnDefinition = "Decimal(6,3) default '0.000000' ", nullable = true)
	private double discountPer2;
	@Column(columnDefinition = "Decimal(16,6) default '0.000000' ", nullable = true)
	private double discounts;
	@Column(columnDefinition = "Decimal(16,6) default '0.000000' ", nullable = true)
	private double netCost;
	@Column(columnDefinition = "Decimal(16,6) default '0.000000' ", nullable = true)
	private double netPrice;
	@Column(columnDefinition = "Decimal(16,6) default '0.000000' ", nullable = true)
	private double netTotal;

	@Column(columnDefinition = "Decimal(16,6) default '0.000000' ", nullable = true)
	private double qtyIn;
	@Column(columnDefinition = "Decimal(16,6) default '0.000000' ", nullable = true)
	private double qtyOut;
	@Column(columnDefinition = "Decimal(16,6) default '0.000000' ", nullable = true)
	private double cost;
	@Column(columnDefinition = "Decimal(16,6) default '0.000000' ", nullable = true)
	private double pric;
	@Column(columnDefinition = "Decimal(6,3) default '0.000' ", nullable = true)
	private double taxPer1;
	@Column(columnDefinition = "Decimal(16,6) default '0.000000' ", nullable = true)
	private double tax1;
	@Column(columnDefinition = "Decimal(6,3) default '0.000' ", nullable = true)
	private double taxPer2;
	@Column(columnDefinition = "Decimal(16,6) default '0.000000' ", nullable = true)
	private double tax2;
	@Column(columnDefinition = "Decimal(16,6) default '0.000000' ", nullable = true)
	private double grandTotal;
	@Column(columnDefinition = "Decimal(16,6) default '0.000000' ", nullable = true)
	private double expenses;

	private String additionType1;
	@Column(columnDefinition = "Decimal(6,3) default '0.000000' ", nullable = true)
	private double additionPer1;
	@Column(columnDefinition = "Decimal(16,6) default '0.000000' ", nullable = true)
	private double addition1;
	@Column(columnDefinition = "Decimal(6,3) default '0.000000' ", nullable = true)
	private double additionPer2;
	@Column(columnDefinition = "Decimal(16,6) default '0.000000' ", nullable = true)
	private double addition2;
	@Column(columnDefinition = "Decimal(6,3) default '0.000000' ", nullable = true)
	private double additionPer3;
	@Column(columnDefinition = "Decimal(16,6) default '0.000000' ", nullable = true)
	private double addition3;

	@Column(columnDefinition = "Decimal(16,6) default '0.000000' ", nullable = true)
	private double realNetCost;

	@Column(columnDefinition = "Decimal(16,6) default '0.000000' ", nullable = true)
	private double realCost;

	@Column(columnDefinition = "Decimal(16,6) default '0.000000' ", nullable = true)
	private double profit;
	@Column(columnDefinition = "Decimal(16,6) default '0.000000' ", nullable = true)
	private double costErrors;

	private String custom1;
	private String custom2;
	private String custom3;
	private String serials;
	private String cargo;

}
