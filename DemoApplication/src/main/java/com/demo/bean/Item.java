package com.demo.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
@Entity
@Table(name="items")
public class Item {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	
private long itemId;
private String itemName;
@Column(columnDefinition="Decimal(13,3) default '0.000' ")
private double price1;
@Column(columnDefinition="Decimal(13,3) default '0.000' ")
private double price2;
@Column(columnDefinition="Decimal(13,3) default '0.000' ")
private double price3;
@Column(columnDefinition="Decimal(13,3) default '0.000' ")
private double price4;
@Column(columnDefinition="Decimal(13,3) default '0.000' ")
private double priceMin;
@Column(columnDefinition="Decimal(18,3) default '0.000' ")
private double discountPer;
@Column(columnDefinition="char(1) default 'Y' ")
private char taxType='Y';
@Column(columnDefinition="tinyint(1) default '0' ")
private boolean PriceIncludeTax;
private String barcode;
private String code1;
private String code2;
private String categroy1;
private String categroy2;
private String categroy3;
private String categroy4;
private String categroy5;
private String categroy6;
private String unit;
@Column(columnDefinition="Decimal(16,6) default '0.000000' ")
private double qty;
@Column(columnDefinition="Decimal(16,6) default '0.000000' ")
private double reorderQty;
@Column(columnDefinition="tinyint(1) default '0' ")
private boolean service=false;
@Column(columnDefinition="tinyint(1) default '0' ")
private boolean started=false;
@Column(columnDefinition="tinyint(1) default '0' ")
private boolean dead=false;
@Column(columnDefinition="Decimal(16,6) default '0.000000' ")
private double avgCost;
@Column(columnDefinition="Decimal(16,6) default '0.000000' ")
private double lastCost=0;

private Date lastPurchased;
private String photo;
private String more;

}
