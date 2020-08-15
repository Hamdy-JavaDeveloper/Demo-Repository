package com.demo.bean;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.EqualsAndHashCode;
@Component
@Data
@EqualsAndHashCode
@Entity
@Table(name="stores_items")
public class StoreItem {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	@ManyToOne
	@JoinColumn(name="item_id_FK",nullable=false)
	private Item item;
	

	@ManyToOne
	@JoinColumn(name="store_id_FK",nullable=false)
	private Store store;
	
	@Column(columnDefinition="Decimal(16,6) default '0.000000' ",nullable=false)
	private double qty;
	
	@Transient
	private double avgCost;
}
