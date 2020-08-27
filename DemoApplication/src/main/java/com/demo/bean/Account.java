package com.demo.bean;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import lombok.Data;
/*
id	int(11)	NO	PRI	NULL	auto_increment
title	varchar(200)	YES	MUL		
code	varchar(50)	YES	MUL		
balance_in	decimal(13,3)	YES		0.000	
balance_out	decimal(13,3)	YES		0.000	
instal_receipts	decimal(13,3)	YES		0.000	
instal_payments	decimal(13,3)	YES		0.000	
kind	varchar(10)	YES		NULL	
category	varchar(100)	YES	MUL		
phone	varchar(200)	YES	MUL		
email	varchar(100)	YES	MUL		
address	varchar(500)	YES			
address2	varchar(500)	YES			
tax_id	varchar(200)	YES			
more	varchar(500)	YES			
reminder_date	date	YES	MUL	NULL	
sales_price_list	tinyint(4)	YES		0	
sales_discount_per	tinyint(4)	YES		0	
*/
@Component
@Data
@Entity
@Table(name="accounts")

public class Account {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long accId;
	private String title;
	private String code;
	private double balance_in;
	private double  balance_out;
	private double instal_receipts;
	private double instal_payments;
	
	private String kind;
	private String category;
	private String phone;
	private String email;
	private String address;
	private String address2;
	private String tax_id;
	private String more;
	private Date reminder_date;
	private boolean sales_price_list=false;
	private boolean sales_discount_per=false;
	
}
