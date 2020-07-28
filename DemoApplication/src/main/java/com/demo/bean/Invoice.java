package com.demo.bean;

import java.io.Serializable;
import java.security.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.NaturalId;
import org.hibernate.annotations.NaturalIdCache;
import org.springframework.stereotype.Component;

import lombok.Data;



@Component
@Data
@Entity
@Table(name="invoices")

public class Invoice implements Serializable  {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long pk;
	
	@Column(columnDefinition=" int(11) DEFAULT '0'")
	@NaturalId
	private long invoiceId;
	
	@Column(columnDefinition="Varchar(10) COMMENT 'OPEN, SALE, RETURNSALE, SALEQUOTE, PURCHASE, RETURNPUR, INVENT, TRANSFER, ADJUST'" ,nullable = true)
	private String kind;

	
	private long storeId;
	@Column(nullable = true)
	private Date date1;
	@Column(nullable = true)
	private Timestamp time1;
	
	//private Account account;;
	@Column(columnDefinition="Decimal(16,6) default '0.000000' ",nullable =true)
	private double qty;
	@Column(columnDefinition="Decimal(16,6) default '0.000000' ",nullable =true)
	private double total;
	@Column(columnDefinition="Decimal(16,6) default '0.000000' ",nullable =true)
	private double totalIncTax;
	@Column(columnDefinition="Decimal(16,6) default '0.000000' ",nullable =true)
	private double totalCost;
	@Column(columnDefinition="Decimal(16,6) default '0.000000' ",nullable =true)
	private double totalPrice;
	@Column(columnDefinition="Decimal(16,6) default '0.000000' ",nullable =true)
	private double totalIncDiscount1;
	
	private String additionType1;
	@Column(columnDefinition="Decimal(6,3) default '0.000000' ",nullable =true)
	private double additionPer1;
	@Column(columnDefinition="Decimal(16,6) default '0.000000' ",nullable =true)
	private double addition1;
	@Column(columnDefinition="Decimal(6,3) default '0.000000' ",nullable =true)
	private double additionPer2;
	@Column(columnDefinition="Decimal(16,6) default '0.000000' ",nullable =true)
	private double addition2;
	@Column(columnDefinition="Decimal(6,3) default '0.000000' ",nullable =true)
	private double additionPer3;
	@Column(columnDefinition="Decimal(16,6) default '0.000000' ",nullable =true)
	private double addition3;
	@Column(columnDefinition="Decimal(16,6) default '0.000000' ",nullable =true)
	private double additions;
	@Column(columnDefinition="Decimal(16,6) default '0.000000' ",nullable =true)
	private double discount1;
	@Column(columnDefinition="Decimal(16,6) default '0.000000' ",nullable =true)
	private double discount2;
	@Column(columnDefinition="Decimal(6,3) default '0.000000' ",nullable =true)
	private double discountPer2;
	@Column(columnDefinition="Decimal(16,6) default '0.000000' ",nullable =true)
	private double discounts;
	@Column(columnDefinition="Decimal(16,6) default '0.000000' ",nullable =true)
	private double netCost;
	@Column(columnDefinition="Decimal(16,6) default '0.000000' ",nullable =true)
	private double netPrice;
	@Column(columnDefinition="Decimal(16,6) default '0.000000' ",nullable =true)
	private double netTotal;
	@Column(columnDefinition="Decimal(6,3) default '0.000' ",nullable =true)
	private double taxPer1;
	@Column(columnDefinition="Decimal(16,6) default '0.000000' ",nullable =true)
	private double tax1;
	@Column(columnDefinition="Decimal(6,3) default '0.000' ",nullable =true)
	private double taxPer2;
	@Column(columnDefinition="Decimal(16,6) default '0.000000' ",nullable =true)
	private double tax2;
	@Column(columnDefinition="Decimal(16,6) default '0.000000' ",nullable =true)
	private double grandTotal;
	@Column(columnDefinition="Decimal(13,3) default '0.000' ",nullable =true)
	private double customerPay;
	@Column(columnDefinition="Decimal(13,3) default '0.000' ",nullable =true)
	private double customerChange;
	@Column(columnDefinition="Decimal(16,6) default '0.000000' ",nullable =true)
	private double cashBox1; // List<CashBox> cashBoxs;
	
	private long cashBoxId1;
	@Column(columnDefinition="Decimal(16,6) default '0.000000' ",nullable =true)
	private double cashBox2;
	private long cashBoxId2;
	@Column(columnDefinition="Decimal(16,6) default '0.000000' ",nullable =true)
	private double cash;
	@Column(columnDefinition="Decimal(16,6) default '0.000000' ",nullable =true)
	private double cheques;
	@Column(columnDefinition="Decimal(16,6) default '0.000000' ",nullable =true)
	private double credit;
	
	@Column(columnDefinition="Decimal(16,6) default '0.000000' ",nullable =true)
	private double creditPad;
	@Column(columnDefinition="Decimal(16,6) default '0.000000' ",nullable =true)
	private double credit_due;
	private String paymentType;
	private String paymentStatus;
	
	private String expenseType1;
	@Column(columnDefinition="Decimal(16,6) default '0.000000' ",nullable =true)
	private double expense1;
	@Column(columnDefinition="Decimal(16,6) default '0.000000' ",nullable =true)
	private double expenses;
	@Column(columnDefinition="Decimal(16,6) default '0.000000' ",nullable =true)
	private double cashBoxFees;
	@Column(columnDefinition="Decimal(16,6) default '0.000000' ",nullable =true)
	private double realNetCost;
	@Column(columnDefinition="Decimal(16,6) default '0.000000' ",nullable =true)
	private double profit;
	@Column(columnDefinition="Decimal(16,6) default '0.000000' ",nullable =true)
	private double costErrors;
	
	private String reference;
	
	private long store_to_id;//private Store store_to_id;
	private long salesman_id; //private Account account;
	@Column(columnDefinition="tinyint(1) default '0' ")
	private boolean reserved;
	private String status;
	private String chippedBy;
	
	private String custom1;
	private String custom2;
	private String custom3;
	private String custom4;
	private String custom5;
	private String more;
	
	private Date duaDate;
	private Date createdOn;
	private long createdByID;
	private long editedByID;
	private Date editedOn;
	
	
	
	
	
	
	
	
	
	
	
	
}
